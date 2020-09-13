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
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * todo 所有信使服务商的管理器。通过 java spi 机制进行导入。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@Slf4j
public class CourierSupplierManager {
    private static final CopyOnWriteArrayList<CourierSupplier> registeredSuppliers = new CopyOnWriteArrayList<>();

    static {
        loadAndRegisterSpiSuppliers();
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

    private static void loadAndRegisterSpiSuppliers() {
        ServiceLoader<CourierSupplier> suppliers = ServiceLoader.load(CourierSupplier.class);
        Iterator<CourierSupplier> iterator = suppliers.iterator();
        try {
            while (iterator.hasNext()) {
                register(iterator.next());
            }
        } catch (Exception ex) {
        }
    }
}
