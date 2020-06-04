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

import cn.jinyahuan.commons.courier.processor.CourierProcessor;
import cn.jinyahuan.commons.courier.response.CourierResponse;
import cn.jinyahuan.commons.courier.supplier.CourierSupplier;

import java.util.List;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierResponseProcessor extends CourierProcessor {
    /**
     * 获取所有的响应的拦截器。
     *
     * @return
     */
    List<CourierResponseInterceptor> getInterceptors();

    /**
     * 替换所有的响应的拦截器。
     * <p>用于动态切换{@link CourierSupplier}时，同时切换对应的{@link CourierResponseInterceptor}。</p>
     *
     * @param interceptors 非{@code null}是才进行替换
     */
    void setInterceptors(List<CourierResponseInterceptor> interceptors);

    /**
     * 处理信使响应。
     *
     * @param response
     * @param supplier
     */
    void process(CourierResponse response, CourierSupplier supplier);
}
