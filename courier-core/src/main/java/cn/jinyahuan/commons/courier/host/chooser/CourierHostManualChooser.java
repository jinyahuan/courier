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

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 信使服务商手动选择器，每次返回手动指定特定服务商。
 *
 * @author Yahuan Jin
 * @see CourierHostFixedChooser
 * @see CourierHostPriorityChooser
 * @see CourierHostRandomChooser
 * @see CourierHostRoundChooser
 * @since 0.1
 */
public class CourierHostManualChooser extends AbstractCourierHostChooser {
    public CourierHostManualChooser() {
        super();
    }

    /**
     * 指定一个信使服务商。
     *
     * @param key 需要指定的{@link CourierHost}
     * @return maybe null
     */
    @Override
    public CourierHost choose(Object key) {
        CourierHost courierHost = null;
        if (Objects.nonNull(key) && key instanceof CourierHost) {
            courierHost = (CourierHost) key;
        }
        return courierHost;
    }

    /**
     * 获取所有启用的信使服务商。
     * <p>当前实现类中，此操作永远返回{@link Collections#emptyList() 空的的List}。</p>
     *
     * @return
     */
    @Override
    public List<CourierHost> getEnableCourierHosts() {
        return Collections.emptyList();
    }

    /**
     * 设置所有启用的信使服务商。
     * <p>当前实现类中，此操作无效。</p>
     *
     * @param enableCourierHosts
     */
    @Override
    public void setEnableCourierHosts(List<CourierHost> enableCourierHosts) {}
}
