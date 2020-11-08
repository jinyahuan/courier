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
import java.util.Objects;

/**
 * 不可变的信使响应信息类。
 *
 * @param <T> 响应的数据的类型
 * @author Yahuan Jin
 * @see DefaultCourierResponse
 * @see AbstractCourierResponse
 * @see CourierResponse
 * @since 0.1
 */
public final class ImmutableCourierResponse<T> implements CourierResponse<T>, Serializable {
    private static final long serialVersionUID = 1L;

    /** 状态码。 */
    private final int state;
    /** 错误码。 */
    private final String code;
    /** 错误信息。 */
    private final String msg;
    /** 响应的数据。 */
    private final T responseData;
    /** 签名。 */
    private final String sign;

    public ImmutableCourierResponse(CourierResponseStateAccessor responseState, T responseData, String sign) {
        this.state = responseState.getState();
        this.code = responseState.getCode();
        this.msg = responseState.getMsg();
        this.responseData = responseData;
        this.sign = sign;
    }

    public ImmutableCourierResponse(CourierResponseStateAccessor responseState, T responseData) {
        this(responseState, responseData, null);
    }

    public ImmutableCourierResponse(CourierResponseStateAccessor responseState) {
        this(responseState, null);
    }

    @Override
    public int getState() { return state; }

    @Override
    public void setState(int state) { throw new UnsupportedOperationException(); }

    @Override
    public String getCode() { return code; }

    @Override
    public void setCode(String code) { throw new UnsupportedOperationException(); }

    @Override
    public String getMsg() { return msg; }

    @Override
    public void setMsg(String msg) { throw new UnsupportedOperationException(); }

    @Override
    public T getResponseData() { return responseData; }

    @Override
    public void setResponseData(T responseData) { throw new UnsupportedOperationException(); }

    @Override
    public String getSign() { return sign; }

    @Override
    public void setSign(String sign) { throw new UnsupportedOperationException(); }

    @Override
    public String toString() {
        return "ImmutableCourierResponse{" +
                "state=" + state +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", responseData=" + responseData +
                ", sign='" + sign + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImmutableCourierResponse<?> that = (ImmutableCourierResponse<?>) o;
        return state == that.state &&
                Objects.equals(code, that.code) &&
                Objects.equals(msg, that.msg) &&
                Objects.equals(responseData, that.responseData) &&
                Objects.equals(sign, that.sign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, code, msg, responseData, sign);
    }
}
