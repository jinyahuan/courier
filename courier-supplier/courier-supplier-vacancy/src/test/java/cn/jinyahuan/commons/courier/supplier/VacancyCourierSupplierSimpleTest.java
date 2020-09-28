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

import org.junit.Test;

import java.util.List;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class VacancyCourierSupplierSimpleTest {
    @Test
    public void test() throws ClassNotFoundException {
        Class.forName("cn.jinyahuan.commons.courier.supplier.CourierSupplierManager");

        List<CourierSupplierInfo> suppliers = CourierSupplierManager.getSuppliers();
        suppliers.forEach(System.out::println);
        System.out.println("-----------------------");

        CourierSupplierManager.register("cn.jinyahuan.commons.courier.supplier.ManualSupplierFactory");

        suppliers = CourierSupplierManager.getSuppliers();
        suppliers.forEach(System.out::println);
        System.out.println("-----------------------");

        CourierSupplierManager.deregister("cn.jinyahuan.commons.courier.supplier.ManualSupplierFactory");
        suppliers = CourierSupplierManager.getSuppliers();
        System.out.println("current suppliers size: " + suppliers.size());
        System.out.println("-----------------------");

        CourierSupplierManager.deregister("cn.jinyahuan.commons.courier.supplier.ReplacedSupplierFactory");
        suppliers = CourierSupplierManager.getSuppliers();
        System.out.println("current suppliers size: " + suppliers.size());
        System.out.println("-----------------------");
    }
}
