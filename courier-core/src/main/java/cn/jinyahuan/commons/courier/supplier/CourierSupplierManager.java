/*
 * Copyright (c) 2020 The Courier Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.jinyahuan.commons.courier.supplier;

import cn.jinyahuan.commons.courier.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.function.Predicate;

/**
 * 信使服务商的管理器。通过 java spi 机制进行导入。
 * todo 管理运行时的信使服务商
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@Slf4j
public class CourierSupplierManager {
    private static final CourierSupplierContainerHolder holder = new CourierSupplierContainerHolder();

    static {
        loadAndRegisterSuppliers();
    }

    public static boolean register(String supplierFactoryClassName) {
        return register(supplierFactoryClassName, true);
    }

    public static boolean register(String supplierFactoryClassName, boolean cover) {
        boolean isRegistered = false;

        if (StringUtils.isNotEmpty(supplierFactoryClassName)) {
            try {
                Class<?> clazz = Class.forName(supplierFactoryClassName);

                if (!CourierSupplierFactory.class.isAssignableFrom(clazz)) {
                    log.warn("{} not CourierSupplierFactory represented representation", supplierFactoryClassName);
                }
                else {
                    // 一定要有一个无参构造器
                    Constructor<CourierSupplierFactory> constructor =
                            (Constructor<CourierSupplierFactory>) clazz.getConstructor();

                    isRegistered = register(constructor.newInstance(), cover);
                }
            } catch (Throwable t) {
                log.error("Supplier Factory register failure", t);
            }
        }

        return isRegistered;
    }

    public static boolean register(CourierSupplierFactory supplierFactory) {
        return register(supplierFactory, true);
    }

    public static synchronized boolean register(CourierSupplierFactory supplierFactory, boolean cover) {
        if (Objects.isNull(supplierFactory)) {
            return false;
        }

        ConcurrentMap<String, CourierSupplierFactory> registeredSupplierFactories = holder.getRegisteredSupplierFactories();

        boolean isRegistered = false;

        final String supplierFactoryClassName = supplierFactory.getClass().getName();
        final CourierSupplier supplier = supplierFactory.getSupplier();
        final boolean hasSupplier = Objects.nonNull(supplier);
        if (hasSupplier) {
            CourierSupplierFactory oldFactory = registeredSupplierFactories.get(supplierFactoryClassName);
            CourierSupplierFactory putReturnFactory;

            if (cover) {
                putReturnFactory = registeredSupplierFactories.put(supplierFactoryClassName, supplierFactory);
            }
            else {
                putReturnFactory = registeredSupplierFactories.putIfAbsent(supplierFactoryClassName, supplierFactory);
            }

            isRegistered = Objects.equals(putReturnFactory, oldFactory);
        }

        final int currentSize = registeredSupplierFactories.size();
        Predicate<CourierSupplierFactory> excludedPredicate = supplierFactory.getExcludedPredicate();
        if (Objects.nonNull(excludedPredicate)) {
            doExcludeSupplierFactories0(excludedPredicate);
        }
        final int finalSize = registeredSupplierFactories.size();
        // 如果清空了，则进行回滚
        if (finalSize == 0) {
            registeredSupplierFactories.remove(supplierFactoryClassName);
            log.warn("{} include danger option[CLEAR ALL FACTORIES], rollback", supplierFactoryClassName);
            return false;
        }
        final boolean isExecuteExclude = (currentSize - finalSize) > 0;

        if (hasSupplier || isExecuteExclude) {
            refresh();
        }

        return isRegistered;
    }

    /**
     * 注销信使服务商。
     *
     * @param supplierFactory
     * @throws RuntimeException 如果注销中断
     */
    public static void deregister(CourierSupplierFactory supplierFactory) {
        Objects.requireNonNull(supplierFactory, "supplierFactory must not be null");

        deregister(supplierFactory.getClass().getName());
    }

    /**
     * 注销信使服务商。
     *
     * @param supplierFactoryClassName
     * @throws RuntimeException 如果注销中断
     */
    public static synchronized void deregister(String supplierFactoryClassName) {
        if (StringUtils.isNotEmpty(supplierFactoryClassName)) {
            ConcurrentMap<String, CourierSupplierFactory> factories = holder.getRegisteredSupplierFactories();

            // 移除工厂
            final CourierSupplierFactory factory = factories.remove(supplierFactoryClassName);

            if (Objects.isNull(factory)) {
                log.warn("Supplier factory deregister failure[not registered in factories] => {}", supplierFactoryClassName);
            }
            else {
                log.info("Supplier factory deregistered => {}", supplierFactoryClassName);

                final String factoryName = factory.getName();
                final List<CourierSupplierInfo> updatedSuppliers = new ArrayList<>();

                List<CourierSupplierInfo> supplierInfos = new ArrayList<>(holder.getRegisteredSuppliers());
                for (CourierSupplierInfo supplierInfo : supplierInfos) {
                    if (!Objects.equals(factoryName, supplierInfo.getFactoryName())) {
                        updatedSuppliers.add(supplierInfo);
                    }
                }

                if (updatedSuppliers.isEmpty()) {
                    log.warn("Suppliers deregister failure[after deregister will none available supplier] <= {}",
                            supplierFactoryClassName);
                }
                else {
                    if (!compareAndSetRegisteredSuppliers(updatedSuppliers)) {
                        // 还原
                        factories.putIfAbsent(supplierFactoryClassName, factory);
                        log.info("Supplier factory registered[rollback] <= {}", supplierFactoryClassName);

                        throw new RuntimeException("Deregister " + supplierFactoryClassName + " failure");
                    }
                    else {
                        for (final CourierSupplierInfo supplierInfo : updatedSuppliers) {
                            final CourierSupplier courierSupplier = supplierInfo.getCourierSupplier();
                            log.info("Supplier deregistered => {}@{}", courierSupplier.getClass().getName(), courierSupplier.hashCode());
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取所有的信使服务商。
     *
     * @return not null, may be EMPTY
     */
    public static List<CourierSupplierInfo> getSuppliers() {
        CopyOnWriteArrayList<CourierSupplierInfo> registeredSuppliers = holder.getRegisteredSuppliers();
        if (!registeredSuppliers.isEmpty()) {
            return new ArrayList<>(registeredSuppliers);
        }
        return Collections.emptyList();
    }

    private static void loadAndRegisterSuppliers() {
        loadSupplierFactories();

        excludeSupplierFactories();

        refresh();
    }

    private static void loadSupplierFactories() {
        ConcurrentMap<String, CourierSupplierFactory> registeredSupplierFactories = holder.getRegisteredSupplierFactories();

        ServiceLoader<CourierSupplierFactory> factories = ServiceLoader.load(CourierSupplierFactory.class);

        Iterator<CourierSupplierFactory> factoryIterator = factories.iterator();
        while (factoryIterator.hasNext()) {
            CourierSupplierFactory factory = factoryIterator.next();
            registeredSupplierFactories.putIfAbsent(factory.getClass().getName(), factory);
        }

        log.info("Courier supplier factory loaded, loading {} instance", registeredSupplierFactories.size());
    }

    private static void excludeSupplierFactories() {
        ConcurrentMap<String, CourierSupplierFactory> registeredSupplierFactories = holder.getRegisteredSupplierFactories();

        if (registeredSupplierFactories.isEmpty()) {
            return;
        }

        Set<Predicate<CourierSupplierFactory>> predicates = getExcludedPredicate();

        log.info("Has {} predicates to exclude courier supplier factory", predicates.size());

        if (!predicates.isEmpty()) {
            doExcludeSupplierFactories(predicates);

            log.info("After exclude courier supplier factory, final has {} instance", registeredSupplierFactories.size());
        }
    }

    private static Set<Predicate<CourierSupplierFactory>> getExcludedPredicate() {
        ConcurrentMap<String, CourierSupplierFactory> registeredSupplierFactories = holder.getRegisteredSupplierFactories();

        Set<Predicate<CourierSupplierFactory>> predicates = new HashSet<>(8);

        Iterator<Map.Entry<String, CourierSupplierFactory>> iterator = registeredSupplierFactories.entrySet().iterator();
        while (iterator.hasNext()) {
            Predicate<CourierSupplierFactory> predicate = iterator.next().getValue().getExcludedPredicate();
            if (Objects.nonNull(predicate)) {
                predicates.add(predicate);
            }
        }

        return predicates;
    }

    private static void doExcludeSupplierFactories(Set<Predicate<CourierSupplierFactory>> excludePredicates) {
        for (Predicate<CourierSupplierFactory> predicate : excludePredicates) {
            doExcludeSupplierFactories0(predicate);
        }
    }

    private static void doExcludeSupplierFactories0(Predicate<CourierSupplierFactory> predicate) {
        ConcurrentMap<String, CourierSupplierFactory> registeredSupplierFactories = holder.getRegisteredSupplierFactories();
        int currentSize = registeredSupplierFactories.size();
        if (currentSize > 0) {
            Iterator<Map.Entry<String, CourierSupplierFactory>> innerIterator = registeredSupplierFactories.entrySet().iterator();
            while (innerIterator.hasNext()) {
                CourierSupplierFactory factory = innerIterator.next().getValue();
                if (predicate.test(factory)) {
                    innerIterator.remove();
                }
            }
        }
        log.info("After {} exclude, instance changed: {} => {}",
                predicate.getClass().getSimpleName(), currentSize, registeredSupplierFactories.size());
    }

    private static synchronized void refresh() {
        ConcurrentMap<String, CourierSupplierFactory> registeredSupplierFactories = holder.getRegisteredSupplierFactories();
        int size = registeredSupplierFactories.size();
        if (size == 0) {
            log.warn("Suppliers registered failure[none available supplier factories]");
            return;
        }

        List<CourierSupplierInfo> suppliers = new ArrayList<>(size);
        Collection<CourierSupplierFactory> factories = registeredSupplierFactories.values();
        for (final CourierSupplierFactory courierSupplierFactory : factories) {
            String factoryName = courierSupplierFactory.getName();
            CourierSupplier supplier = courierSupplierFactory.getSupplier();
            Instant now = Instant.now();
            CourierSupplierInfo supplierInfo = new CourierSupplierInfo(factoryName, supplier, now);

            suppliers.add(supplierInfo);
        }

        boolean registered = compareAndSetRegisteredSuppliers(suppliers);
        if (!registered) {
            log.warn("Suppliers registered failure");
        }
        else {
            for (final CourierSupplierInfo supplierInfo : suppliers) {
                CourierSupplier supplier = supplierInfo.getCourierSupplier();
                log.info("Supplier registered <= {}@{}", supplier.getClass().getName(), supplier.hashCode());
            }
        }
    }

    private static boolean compareAndSetRegisteredSuppliers(List<CourierSupplierInfo> updatedSuppliers) {
        AtomicReferenceFieldUpdater<CourierSupplierContainerHolder, CopyOnWriteArrayList> updater =
                AtomicReferenceFieldUpdater.newUpdater(
                        CourierSupplierContainerHolder.class,
                        CopyOnWriteArrayList.class,
                        "registeredSuppliers"
                );

        return updater.compareAndSet(
                holder,
                holder.getRegisteredSuppliers(),
                new CopyOnWriteArrayList(updatedSuppliers)
        );
    }

    private static class CourierSupplierContainerHolder {
        volatile CopyOnWriteArrayList<CourierSupplierInfo> registeredSuppliers = new CopyOnWriteArrayList<>();

        private static final ConcurrentMap<String, CourierSupplierFactory> registeredSupplierFactories;

        static {
            registeredSupplierFactories = new ConcurrentHashMap<>(16);
        }

        CopyOnWriteArrayList<CourierSupplierInfo> getRegisteredSuppliers() {
            return registeredSuppliers;
        }

        ConcurrentMap<String, CourierSupplierFactory> getRegisteredSupplierFactories() {
            return registeredSupplierFactories;
        }
    }
}
