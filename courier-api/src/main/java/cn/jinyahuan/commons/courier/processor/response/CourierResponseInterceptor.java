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

package cn.jinyahuan.commons.courier.processor.response;

import cn.jinyahuan.commons.courier.response.CourierResponse;
import cn.jinyahuan.commons.courier.supplier.CourierSupplier;

/**
 * 响应的拦截器。如果需要对响应参数进行调整，则可以对其进行拦截处理。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierResponseInterceptor {
    /**
     * 拦截响应。
     *
     * @param response
     * @param supplier
     */
    void intercept(CourierResponse response, CourierSupplier supplier);
}
