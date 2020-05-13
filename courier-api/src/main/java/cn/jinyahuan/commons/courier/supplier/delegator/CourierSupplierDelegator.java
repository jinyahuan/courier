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

package cn.jinyahuan.commons.courier.supplier.delegator;

import cn.jinyahuan.commons.courier.api.model.CourierRequest;
import cn.jinyahuan.commons.courier.supplier.CourierSupplier;

import java.io.Serializable;

/**
 * 信使的服务供应商委派器。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierSupplierDelegator extends Serializable {
    /**
     * 获取委派的信使的服务供应商。
     *
     * @return
     */
    CourierSupplier assign();

    /**
     * 获取委派的信使的服务供应商。
     *
     * @param key
     * @return
     */
    CourierSupplier assign(String key);

    /**
     * 获取委派的信使的服务供应商。
     *
     * @param request
     * @return
     */
    default CourierSupplier assign(CourierRequest request) {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取委派的信使的服务供应商。
     *
     * @param key
     * @param request
     * @return
     */
    default CourierSupplier assign(String key, CourierRequest request) {
        throw new UnsupportedOperationException();
    }
}
