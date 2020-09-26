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

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
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
    private static final CopyOnWriteArrayList<CourierSupplier> registeredSuppliers = new CopyOnWriteArrayList<>();
    private static final ConcurrentMap<String, CourierSupplierFactory> registeredSupplierFactories = new ConcurrentHashMap<>();

    static {
        loadAndRegisterSuppliers();
    }

    /**
     * 注册信使服务商。
     *
     * @param supplier
     */
    public static synchronized void register(CourierSupplier supplier) {
        Objects.requireNonNull(supplier, "supplier must not be null");
        registeredSuppliers.addIfAbsent(supplier);
        log.info("Courier supplier registered: {}", supplier);
    }

    /**
     * 注销信使服务商。
     *
     * @param supplier
     */
    public static synchronized void deregisterDriver(CourierSupplier supplier) {
        Objects.requireNonNull(supplier, "supplier must not be null");
        if (registeredSuppliers.contains(supplier)) {
            registeredSuppliers.remove(supplier);
            log.info("Courier supplier deregistered: {}", supplier);
        }
        else {
            log.warn("Courier supplier deregistered failure, supplier={} could not find from registered", supplier);
        }
    }

    /**
     * 注销信使服务商。
     *
     * @param supplierId
     */
    public static synchronized void deregisterDriver(int supplierId) {
        for (int i = 0; i < registeredSuppliers.size(); i++) {
            CourierSupplier supplier = registeredSuppliers.get(i);
            if (supplier.getId() == supplierId) {
                registeredSuppliers.remove(i);
                return;
            }
        }
        log.warn("Courier supplier deregistered failure, supplierId={} could not find from registered", supplierId);
    }

    /**
     * 获取所有的信使服务商。
     *
     * @return not null, may be EMPTY
     */
    public static List<CourierSupplier> getSuppliers() {
        if (!registeredSuppliers.isEmpty()) {
            return new ArrayList<>(registeredSuppliers);
        }
        return Collections.emptyList();
    }

    private static void loadAndRegisterSuppliers() {
        loadSupplierFactories();

        excludeSupplierFactories();

        registerSuppliers();
    }

    private static void loadSupplierFactories() {
        ServiceLoader<CourierSupplierFactory> factories = ServiceLoader.load(CourierSupplierFactory.class);

        Iterator<CourierSupplierFactory> factoryIterator = factories.iterator();
        while (factoryIterator.hasNext()) {
            CourierSupplierFactory factory = factoryIterator.next();
            registeredSupplierFactories.putIfAbsent(factory.getClass().getName(), factory);
        }

        log.info("Courier supplier factory loaded, loading {} instance", registeredSupplierFactories.size());
    }

    private static void excludeSupplierFactories() {
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
            if (registeredSupplierFactories.isEmpty()) {
                break;
            }

            Iterator<Map.Entry<String, CourierSupplierFactory>> innerIterator = registeredSupplierFactories.entrySet().iterator();
            while (innerIterator.hasNext()) {
                CourierSupplierFactory factory = innerIterator.next().getValue();
                if (predicate.test(factory)) {
                    innerIterator.remove();
                }
            }

            log.info("After {} exclude, has {} instance",
                    predicate.getClass().getSimpleName(), registeredSupplierFactories.size());
        }
    }

    private static void registerSuppliers() {
        if (registeredSupplierFactories.isEmpty()) {
            throw new RuntimeException("None available CourierSupplier instance");
        }
        else {
            Collection<CourierSupplierFactory> supplierFactories = registeredSupplierFactories.values();
            for (CourierSupplierFactory factory : supplierFactories) {
                CourierSupplier supplier = factory.getSupplier();
                if (Objects.nonNull(supplier)) {
                    register(supplier);
                }
            }
        }
    }
}
