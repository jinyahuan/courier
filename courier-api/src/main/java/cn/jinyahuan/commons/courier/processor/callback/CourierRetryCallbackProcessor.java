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

package cn.jinyahuan.commons.courier.processor.callback;

import cn.jinyahuan.commons.courier.Courier;
import cn.jinyahuan.commons.courier.processor.retry.CourierRetryProcessor;
import cn.jinyahuan.commons.courier.request.CourierRequest;
import cn.jinyahuan.commons.courier.response.CourierResponse;

/**
 * 信使重发回调处理器。
 * <p>
 * 当执行{@link CourierRetryProcessor}时触发，
 * 执行{@link CourierRetryProcessor}前，执行{@code callBeforeRetryProcess}，
 * 完全执行完{@link CourierRetryProcessor}后，执行{@code callAfterRetryProcess}。
 * </p>
 *
 * @author Yahuan Jin
 * @see CourierRetryProcessor
 * @since 0.1
 */
public interface CourierRetryCallbackProcessor extends CourierCallbackProcessor {
    /**
     * 执行{@link CourierRetryProcessor}前执行。
     *
     * @param courier  信使
     * @param request  信使请求服务商的信息
     * @param response 信使服务商响应的信息（执行{@link CourierRetryProcessor}前最后的
     * @return 预留，暂无用处
     */
    default Object callBeforeRetryProcess(Courier courier, CourierRequest request, CourierResponse<?> response) {
        return null;
    }

    /**
     * 完全执行完{@link CourierRetryProcessor}后执行。
     *
     * @param courier  信使
     * @param request  信使请求服务商的信息
     * @param response 信使服务商响应的信息（执行{@link CourierRetryProcessor}后最新的
     * @return 预留，暂无用处
     */
    default Object callAfterRetryProcess(Courier courier, CourierRequest request, CourierResponse<?> response) {
        return null;
    }
}
