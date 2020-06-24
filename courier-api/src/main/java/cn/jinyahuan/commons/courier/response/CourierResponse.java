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

import java.io.Serializable;

/**
 * 信使响应属性类。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierResponse<T> extends CourierResponseAttributeAccessor<T>, Serializable {
    /**
     * 是否是成功（发送成功，而非请求成功）的状态。
     *
     * @return (not null) {@code true}，成功的状态；{@code false}，失败的状态
     * @see CourierResponseState#isSuccess(java.lang.Integer)
     * @see CourierResponseState#isSuccess(cn.jinyahuan.commons.courier.response.state.CourierResponseState)
     */
    default Boolean isSuccess() {
        return CourierResponseState.isSuccess(this.getState());
    }
}
