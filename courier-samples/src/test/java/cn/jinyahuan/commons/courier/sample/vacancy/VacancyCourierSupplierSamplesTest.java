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

package cn.jinyahuan.commons.courier.sample.vacancy;

//import cn.jinyahuan.commons.courier.Courier;
//import cn.jinyahuan.commons.courier.context.CourierContext;
//import cn.jinyahuan.commons.courier.request.normal.DefaultNormalCourierRequest;
//import cn.jinyahuan.commons.courier.supplier.JdkDynamicProxyCourier;
//import cn.jinyahuan.commons.courier.supplier.vacancy.VacancyCourier;
//import cn.jinyahuan.commons.courier.supplier.vacancy.VacancyCourierFactory;
//import cn.jinyahuan.commons.courier.supplier.vacancy.VacancyCourierSupplier;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class VacancyCourierSupplierSamplesTest {
    @Test
    public void test() {
//        VacancyCourier courier = VacancyCourierFactory.IMMUTABLE_SERVICE_UNAVAILABLE_STATE_COURIER;
//        // todo [urgent] 代理类 由 Courier 变成 CourierSupplier
//        VacancyCourierSupplier supplier = new VacancyCourierSupplier(courier);
//        CourierContext context = new DefaultCourierContext();
//
//        Class clazz = courier.getClass();
//        ClassLoader classLoader = clazz.getClassLoader();
//        Class<?>[] interfaces = clazz.getInterfaces();
//        JdkDynamicProxyCourier courierProxy = new JdkDynamicProxyCourier(courier, context);
//
//        Courier courierEnhancer = (Courier)Proxy.newProxyInstance(classLoader, interfaces, courierProxy);
//        System.out.println(courierEnhancer.query(new DefaultNormalCourierRequest()));
    }
}