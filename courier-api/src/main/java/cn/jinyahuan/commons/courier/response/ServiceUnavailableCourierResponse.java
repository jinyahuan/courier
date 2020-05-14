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
import lombok.ToString;

/**
 * 带有服务不可用状态的信使响应属性类。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@ToString(callSuper = true)
public class ServiceUnavailableCourierResponse<T> extends AbstractCourierResponse<T> {
    private static final long serialVersionUID = 1L;

    public ServiceUnavailableCourierResponse() {
        super(CourierResponseState.SERVICE_UNAVAILABLE);
    }

    public ServiceUnavailableCourierResponse(T responseData) {
        super(CourierResponseState.SERVICE_UNAVAILABLE, responseData);
    }
}
