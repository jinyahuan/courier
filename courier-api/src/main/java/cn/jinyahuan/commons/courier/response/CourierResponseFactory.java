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

package cn.jinyahuan.commons.courier.response;

import cn.jinyahuan.commons.courier.response.state.CourierResponseState;

/**
 * 信使响应属性类的工厂。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class CourierResponseFactory {
    /**
     * 公共的{@link CourierResponseState#FAILURE 失败状态}的信使响应属性类。
     * <p>没有{@link AbstractCourierResponse#responseData 响应内容}。</p>
     *
     * @see #newFailedResponse(Object) (Object)
     */
    public static final CourierResponse COMMON_FAILED_RESPONSE = newFailedResponse(null);
    /**
     * 公共的{@link CourierResponseState#SUCCESS 成功状态}的信使响应属性类。
     * <p>没有{@link AbstractCourierResponse#responseData 响应内容}。</p>
     *
     * @see #newSuccessfulResponse(Object)
     */
    public static final CourierResponse COMMON_SUCCESSFUL_RESPONSE = newSuccessfulResponse(null);
    /**
     * 公共的{@link CourierResponseState#SERVICE_UNAVAILABLE 服务不可用状态}的信使响应属性类。
     * <p>没有{@link AbstractCourierResponse#responseData 响应内容}。</p>
     *
     * @see #newServiceUnavailableResponse(Object)
     */
    public static final CourierResponse COMMON_SERVICE_UNAVAILABLE_RESPONSE = newServiceUnavailableResponse(null);

    // - - -

    /**
     * 创建一个{@link CourierResponseState#SUCCESS 成功状态}的信使响应属性类。
     *
     * @param data {@link AbstractCourierResponse#responseData 响应内容}
     * @param <T>  {@link AbstractCourierResponse#responseData 响应内容}的类型
     * @return
     * @see #COMMON_SUCCESSFUL_RESPONSE
     */
    public static <T> CourierResponse<T> newSuccessfulResponse(T data) {
        return new ImmutableCourierResponse<>(CourierResponseState.SUCCESS, data);
    }

    /**
     * 创建一个{@link CourierResponseState#SERVICE_UNAVAILABLE 服务不可用状态}的信使响应属性类。
     *
     * @param data {@link AbstractCourierResponse#responseData 响应内容}
     * @param <T>  {@link AbstractCourierResponse#responseData 响应内容}的类型
     * @return
     * @see #COMMON_SERVICE_UNAVAILABLE_RESPONSE
     */
    public static <T> CourierResponse<T> newServiceUnavailableResponse(T data) {
        return new ImmutableCourierResponse<>(CourierResponseState.SERVICE_UNAVAILABLE, data);
    }

    /**
     * 创建一个{@link CourierResponseState#FAILURE 失败状态}的信使响应属性类。
     *
     * @param data {@link AbstractCourierResponse#responseData 响应内容}
     * @param <T>  {@link AbstractCourierResponse#responseData 响应内容}的类型
     * @return
     * @see #COMMON_FAILED_RESPONSE
     */
    public static <T> CourierResponse<T> newFailedResponse(T data) {
        return new ImmutableCourierResponse<>(CourierResponseState.FAILURE, data);
    }
}
