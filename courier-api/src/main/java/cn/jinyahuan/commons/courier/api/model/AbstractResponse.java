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

import cn.jinyahuan.commons.courier.api.ResponseState;
import cn.jinyahuan.commons.courier.api.ResponseStateAccessor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
@Getter
@Setter
@ToString
public abstract class AbstractResponse<T> implements ResponseAttribute<T> {
    protected int state;

    protected String code;

    protected String msg;

    protected T responseData;

    protected String sign;

    public AbstractResponse() {
        this(ResponseState.FAILURE);
    }

    public AbstractResponse(ResponseStateAccessor responseState) {
        this.state = responseState.getState();
        this.code = responseState.getCode();
        this.msg = responseState.getMsg();
    }
}
