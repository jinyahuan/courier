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

package cn.jinyahuan.commons.courier.supplier.vacancy;

import cn.jinyahuan.commons.courier.Courier;
import cn.jinyahuan.commons.courier.supplier.AbstractCourierSupplier;
import cn.jinyahuan.commons.courier.supplier.CourierSupplier;

/**
 * 空缺的信使服务商，也就是默认的信使服务商。
 *
 * <p>
 * {@link VacancyCourierFactory}提供了几种静态的不可变的{@link VacancyCourier 空缺信使}实例。
 *
 * <p>
 * 如果上面的要求还不满足你的要求，那么还可以通过继承{@link VacancyCourierFactory.VacancyCourierAdapter VacancyCourierAdapter}类，然后根据需要重写逻辑
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class VacancyCourierSupplier extends AbstractCourierSupplier
        implements CourierSupplier
{
    private Courier courier;

    /**
     * 无参构造器。
     * <p>内部构造一个服务不可用的信使，该信使所有的方法都会返回
     * {@link VacancyCourierFactory#IMMUTABLE_SERVICE_UNAVAILABLE_STATE_COURIER 服务不可用状态的响应对象}。
     */
    public VacancyCourierSupplier() {
        this(VacancyCourierFactory.IMMUTABLE_SERVICE_UNAVAILABLE_STATE_COURIER);
    }

    public VacancyCourierSupplier(VacancyCourier courier) {
        this.courier = courier;
    }

    @Override
    public Courier getCourier() {
        return courier;
    }
}
