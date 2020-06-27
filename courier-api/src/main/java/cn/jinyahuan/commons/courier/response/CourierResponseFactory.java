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
     * 一个不可变的{@link CourierResponseState#FAILURE 失败状态}的信使响应属性对象常量。
     * <p>此对象的{@link AbstractCourierResponse#responseData 响应内容}为{@code null}。</p>
     *
     * @see #newImmutableFailedResponse(Object) (Object)
     */
    public static final CourierResponse IMMUTABLE_FAILED_RESPONSE = newImmutableFailedResponse(null);
    /**
     * 一个不可变的{@link CourierResponseState#SUCCESS 成功状态}的信使响应属性对象常量。
     * <p>此对象的{@link AbstractCourierResponse#responseData 响应内容}为{@code null}。</p>
     *
     * @see #newImmutableSuccessfulResponse(Object)
     */
    public static final CourierResponse IMMUTABLE_SUCCESSFUL_RESPONSE = newImmutableSuccessfulResponse(null);
    /**
     * 一个不可变的{@link CourierResponseState#SERVICE_UNAVAILABLE 服务不可用状态}的信使响应属性对象常量。
     * <p>此对象的{@link AbstractCourierResponse#responseData 响应内容}为{@code null}。</p>
     *
     * @see #newImmutableServiceUnavailableResponse(Object)
     */
    public static final CourierResponse IMMUTABLE_SERVICE_UNAVAILABLE_RESPONSE = newImmutableServiceUnavailableResponse(null);

    // - - -

    /**
     * 实例化一个不可变的{@link CourierResponseState#SUCCESS 成功状态}的信使响应属性对象。
     *
     * @param data {@link AbstractCourierResponse#responseData 响应内容}
     * @param <T>  {@link AbstractCourierResponse#responseData 响应内容}的类型
     * @return
     * @see #IMMUTABLE_SUCCESSFUL_RESPONSE
     */
    public static <T> CourierResponse<T> newImmutableSuccessfulResponse(T data) {
        return new ImmutableCourierResponse<>(CourierResponseState.SUCCESS, data);
    }

    /**
     * 实例化一个不可变的{@link CourierResponseState#SERVICE_UNAVAILABLE 服务不可用状态}的信使响应属性对象。
     *
     * @param data {@link AbstractCourierResponse#responseData 响应内容}
     * @param <T>  {@link AbstractCourierResponse#responseData 响应内容}的类型
     * @return
     * @see #IMMUTABLE_SERVICE_UNAVAILABLE_RESPONSE
     */
    public static <T> CourierResponse<T> newImmutableServiceUnavailableResponse(T data) {
        return new ImmutableCourierResponse<>(CourierResponseState.SERVICE_UNAVAILABLE, data);
    }

    /**
     * 实例化一个不可变的{@link CourierResponseState#FAILURE 失败状态}的信使响应属性对象。
     *
     * @param data {@link AbstractCourierResponse#responseData 响应内容}
     * @param <T>  {@link AbstractCourierResponse#responseData 响应内容}的类型
     * @return
     * @see #IMMUTABLE_FAILED_RESPONSE
     */
    public static <T> CourierResponse<T> newImmutableFailedResponse(T data) {
        return new ImmutableCourierResponse<>(CourierResponseState.FAILURE, data);
    }
}
