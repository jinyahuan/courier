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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 不可变的信使响应属性类。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@ToString
@EqualsAndHashCode
public final class ImmutableCourierResponse<T> implements CourierResponse<T> {
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    @Getter
    private final int state;
    /** 错误码 */
    @Getter
    private final String code;
    /** 错误信息 */
    @Getter
    private final String msg;
    /** 响应的数据 */
    @Getter
    private final T responseData;
    /** 签名 */
    @Getter
    private final String sign;

    public ImmutableCourierResponse(CourierResponseStateAccessor responseState, T responseData) {
        this.state = responseState.getState();
        this.code = responseState.getCode();
        this.msg = responseState.getMsg();
        this.responseData = responseData;
        this.sign = null;
    }

    @Override
    public void setState(int state) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCode(String code) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMsg(String msg) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setResponseData(T responseData) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setSign(String sign) {
        throw new UnsupportedOperationException();
    }
}
