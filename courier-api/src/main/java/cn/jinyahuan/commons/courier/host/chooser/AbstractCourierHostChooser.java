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
import cn.jinyahuan.commons.courier.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的信使服务商的选择器。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public abstract class AbstractCourierHostChooser implements CourierHostChoosable {
    /**
     * 所有启用的信使服务商。
     */
    protected List<CourierHost> enableCourierHosts;

    public AbstractCourierHostChooser() {
        // 不设置成不可变的 List 的原因是：可能有人会通过 getEnableCourierHosts()，然后进行元素的添加操作
        this.enableCourierHosts = new ArrayList<>(5);
    }

    public AbstractCourierHostChooser(List<CourierHost> enableCourierHosts) {
        setEnableCourierHosts(enableCourierHosts);
    }

    @Override
    public List<CourierHost> getEnableCourierHosts() {
        return enableCourierHosts;
    }

    @Override
    public void setEnableCourierHosts(List<CourierHost> enableCourierHosts) {
        int size = CollectionUtils.size(enableCourierHosts);
        if (size > 0) {
            this.enableCourierHosts = enableCourierHosts;
        }
    }
}
