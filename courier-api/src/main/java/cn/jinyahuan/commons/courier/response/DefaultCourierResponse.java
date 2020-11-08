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

import cn.jinyahuan.commons.courier.response.state.CourierResponseStateAccessor;

import java.io.Serializable;

/**
 * 默认的信使响应信息实现。
 *
 * @param <T> 响应的数据的类型
 * @author Yahuan Jin
 * @see ImmutableCourierResponse
 * @see AbstractCourierResponse
 * @see CourierResponse
 * @since 0.1
 */
public class DefaultCourierResponse<T>
        extends AbstractCourierResponse<T>
        implements CourierResponse<T>, Serializable
{
    private static final long serialVersionUID = 1L;

    public DefaultCourierResponse(CourierResponseStateAccessor responseState, T responseData, String sign) {
        super(responseState, responseData, sign);
    }

    public DefaultCourierResponse(CourierResponseStateAccessor responseState, T responseData) {
        this(responseState, responseData, null);
    }

    public DefaultCourierResponse(CourierResponseStateAccessor responseState) {
        this(responseState, null);
    }

    public DefaultCourierResponse() {}

    @Override
    public String toString() {
        return "DefaultCourierResponse{" +
                "state=" + state +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", responseData=" + responseData +
                ", sign='" + sign + '\'' +
                '}';
    }
}
