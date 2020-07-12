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

import cn.jinyahuan.commons.courier.processor.callback.CourierExceptionCallbackProcessor;
import cn.jinyahuan.commons.courier.processor.callback.CourierRequestCallbackProcessor;
import cn.jinyahuan.commons.courier.processor.callback.CourierRetryCallbackProcessor;
import cn.jinyahuan.commons.courier.processor.callback.CourierResponseCallbackProcessor;

/**
 * 信使回调处理器的上下文。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierCallbackProcessorContext extends CourierContextAttributeAccessor {
    /**
     * 获取异常回调处理器。
     *
     * @return may be null
     */
    CourierExceptionCallbackProcessor getExceptionCallbackProcessor();

    /**
     * 获取在请求信使服务商之前的回调处理器。
     *
     * @return may be null
     */
    CourierRequestCallbackProcessor getBeforeRequestCallbackProcessor();

    /**
     * 获取重发回调处理器。
     * <p>
     * {@link CourierRetryCallbackProcessor}应当在{@link CourierRequestCallbackProcessor}之后，
     * {@link CourierResponseCallbackProcessor}之前执行。
     * </p>
     *
     * @return may be null
     */
    CourierRetryCallbackProcessor getResendCallbackProcessor();

    /**
     * 获取在信使服务商响应之后的回调处理器。
     * <p></p>
     *
     * @return may be null
     */
    CourierResponseCallbackProcessor getAfterResponseCallbackProcessor();
}
