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

package cn.jinyahuan.commons.courier.context;

import cn.jinyahuan.commons.courier.processor.callback.CourierCallbackProcessor;
import cn.jinyahuan.commons.courier.processor.request.CourierRequestProcessor;
import cn.jinyahuan.commons.courier.processor.response.CourierResponseProcessor;
import cn.jinyahuan.commons.courier.processor.retry.CourierRetryProcessor;

import java.util.List;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierProcessorContext {
    /**
     * 获取所有的请求处理器。
     *
     * @return
     */
    List<CourierRequestProcessor> getRequestProcessors();

    /**
     * 获取回调处理器的上下文。
     *
     * @return
     */
    CourierCallbackProcessorContext getCallbackProcessorContext();

    /**
     * 获取所有的重试处理器。
     *
     * @return
     */
    List<CourierRetryProcessor> getRetryProcessors();

    /**
     * 获取所有的响应处理器。
     *
     * @return
     */
    List<CourierResponseProcessor> getResponseProcessors();
}
