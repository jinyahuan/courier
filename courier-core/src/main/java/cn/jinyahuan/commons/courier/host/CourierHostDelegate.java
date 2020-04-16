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

package cn.jinyahuan.commons.courier.host;

import cn.jinyahuan.commons.courier.api.model.RequestAttribute;

/**
 * 服务商委派器。
 * <p>
 * 注意: 委派服务商是强制性的，会使默认的服务商选择策略失效。
 * </p>
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierHostDelegate {
    /**
     * 获取委派的服务商。
     *
     * @param key
     * @return
     */
    CourierHost assign(String key);

    /**
     * 获取委派的服务商。
     *
     * @param request
     * @return
     */
    default CourierHost assign(RequestAttribute request) {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取委派的服务商。
     *
     * @param key
     * @param request
     * @return
     */
    default CourierHost assign(String key, RequestAttribute request) {
        throw new UnsupportedOperationException();
    }
}
