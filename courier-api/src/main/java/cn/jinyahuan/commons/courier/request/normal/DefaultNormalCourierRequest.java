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

package cn.jinyahuan.commons.courier.request.normal;

import cn.jinyahuan.commons.courier.request.CourierRequestExpandedParamContainerModeEnum;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 默认的常规的信使请求信息类。
 *
 * @author Yahuan Jin
 * @see AbstractNormalCourierRequest
 * @see NormalCourierRequest
 * @since 0.1
 */
public class DefaultNormalCourierRequest
        extends AbstractNormalCourierRequest
        implements NormalCourierRequest, Serializable
{
    private static final long serialVersionUID = 1L;

    public DefaultNormalCourierRequest(CourierRequestExpandedParamContainerModeEnum expandedParamContainerMode) {
        super(expandedParamContainerMode);
    }

    public DefaultNormalCourierRequest() {
        super();
    }

    @Override
    public String toString() {
        return "DefaultNormalCourierRequest{" +
                "phone='" + phone + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", templateFillers=" + Arrays.toString(templateFillers) +
                ", expandedParams=" + expandedParams +
                '}';
    }
}
