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

package cn.jinyahuan.commons.courier.host.chooser;

import cn.jinyahuan.commons.courier.host.CourierHost;

/**
 * 信使服务商{@link CourierHostPriority 优先级}选择器，优先选择已配置的优先级高
 * （{@link CourierHostPriority#value() 值}越小优先级越高）的服务商。
 *
 * @author Yahuan Jin
 * @see CourierHostPriority
 * @since 0.1
 */
public class CourierHostPriorityChooser extends AbstractCourierHostChooser {
    @Override
    public CourierHost choose(Object key) {
        // todo implement choose method
        return null;
    }
}
