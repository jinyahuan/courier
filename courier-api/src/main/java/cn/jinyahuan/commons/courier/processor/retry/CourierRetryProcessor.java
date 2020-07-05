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

package cn.jinyahuan.commons.courier.processor.retry;

import cn.jinyahuan.commons.courier.Courier;
import cn.jinyahuan.commons.courier.processor.CourierProcessor;
import cn.jinyahuan.commons.courier.response.CourierResponse;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 信使重试处理器。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierRetryProcessor extends CourierProcessor {
    /**
     * 获取重试的总次数，即重试的最大次数。每次重试都会记录重试的当前总次数，当达到{@link #getRetryCount() 重试的最大总次数}时，会中断重试。
     *
     * @return
     */
    int getRetryCount();

    /**
     * 重试。
     *
     * @param courier
     * @param method  {@link Courier}执行的目标方法，即发送短信的具体方法
     * @param args    {@link Courier}执行的目标方法的参数，即发送短信的具体方法的参数
     * @return
     */
    default CourierResponse retry(Courier courier, Method method, Object[] args) {
        CourierResponse response = null;

        final int retryCount = getRetryCount();
        if (retryCount > 0) {
            int currentRetryCount = 0;
            while (currentRetryCount++ < retryCount) {
                try {
                    response = (CourierResponse) method.invoke(courier, args);
                } catch (Throwable e) {
                    handleException(courier, method, args, response, e);
                }

                if (Objects.nonNull(response) && response.isSuccess()) {
                    handleRetrySuccess(courier, method, args, currentRetryCount);
                    break;
                }
            }
        }

        return response;
    }

    /**
     * 处理重试期间发生的异常。
     *
     * @param courier
     * @param method    {@link Courier}执行的目标方法，即发送短信的具体方法
     * @param args      {@link Courier}执行的目标方法的参数，即发送短信的具体方法的参数
     * @param response  当前的请求响应对象(may be null)
     * @param throwable
     */
    default void handleException(Courier courier, Method method, Object[] args, CourierResponse response, Throwable throwable) {
    }

    /**
     * 处理重试执行成功。
     *
     * @param courier
     * @param method          {@link Courier}执行的目标方法，即发送短信的具体方法
     * @param args            {@link Courier}执行的目标方法的参数，即发送短信的具体方法的参数
     * @param totalRetryCount 执行成功时，花费的总的重试次数
     */
    default void handleRetrySuccess(Courier courier, Method method, Object[] args, int totalRetryCount) {
    }
}
