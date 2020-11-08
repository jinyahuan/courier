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

import java.util.Objects;

/**
 * 抽象的信使响应信息。
 *
 * @param <T> 响应的数据的类型
 * @author Yahuan Jin
 * @since 0.1
 */
public abstract class AbstractCourierResponse<T> implements CourierResponse<T> {
    /** 状态码 */
    protected int state;
    /** 错误码 */
    protected String code;
    /** 错误信息 */
    protected String msg;
    /** 响应的数据 */
    protected T responseData;
    /** 签名 */
    protected String sign;

    public AbstractCourierResponse(CourierResponseStateAccessor responseState, T responseData, String sign) {
        this.state = responseState.getState();
        this.code = responseState.getCode();
        this.msg = responseState.getMsg();
        this.responseData = responseData;
        this.sign = sign;
    }

    public AbstractCourierResponse(CourierResponseStateAccessor responseState, T responseData) {
        this(responseState, responseData, null);
    }

    public AbstractCourierResponse(CourierResponseStateAccessor responseState) {
        this(responseState, null);
    }

    public AbstractCourierResponse() {}

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public T getResponseData() {
        return responseData;
    }

    @Override
    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    @Override
    public String getSign() {
        return sign;
    }

    @Override
    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourierResponse<?> that = (CourierResponse<?>) o;
        return getState() == that.getState() &&
                Objects.equals(getCode(), that.getCode()) &&
                Objects.equals(getMsg(), that.getMsg()) &&
                Objects.equals(getResponseData(), that.getResponseData()) &&
                Objects.equals(getSign(), that.getSign());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), getCode(), getMsg(), getResponseData(), getSign());
    }
}
