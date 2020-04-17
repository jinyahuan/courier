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

package cn.jinyahuan.commons.courier.api.model;

import cn.jinyahuan.commons.courier.api.state.ResponseStateAccessor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 抽象的响应信息类。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class AbstractResponse<T> implements ResponseAttribute<T> {
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

    public AbstractResponse() {}

    public AbstractResponse(ResponseStateAccessor responseState) {
        this.state = responseState.getState();
        this.code = responseState.getCode();
        this.msg = responseState.getMsg();
    }

    public AbstractResponse(ResponseStateAccessor responseState, T responseData) {
        this(responseState);
        this.responseData = responseData;
    }
}
