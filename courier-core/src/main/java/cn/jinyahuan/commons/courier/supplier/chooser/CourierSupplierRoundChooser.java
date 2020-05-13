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

package cn.jinyahuan.commons.courier.supplier.chooser;

import cn.jinyahuan.commons.courier.supplier.CourierSupplier;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 信使服务商轮询选择器，轮询选择已配置的服务商。
 *
 * @author Yahuan Jin
 * @see CourierSupplierFixedChooser
 * @see CourierSupplierManualChooser
 * @see CourierSupplierPriorityChooser
 * @see CourierSupplierRandomChooser
 * @since 0.1
 */
public class CourierSupplierRoundChooser extends AbstractCourierSupplierChooser {
    private final AtomicInteger currentIndex = new AtomicInteger(-1);

    public CourierSupplierRoundChooser() {
        super();
    }

    public CourierSupplierRoundChooser(List<CourierSupplier> enableCourierHosts) {
        super(enableCourierHosts);
    }

    /**
     * 轮询获取一个信使服务商。
     *
     * @param key 当前实现类中，此参数无效，所以可以传任何值，包括{@code null}
     * @return maybe null
     */
    @Override
    public CourierSupplier choose(Object key) {
        CourierSupplier courierHost = null;

        List<CourierSupplier> enableCourierHosts = getEnableCourierSuppliers();
        int count = enableCourierHosts.size();
        if (count == 1) {
            courierHost = enableCourierHosts.get(0);
        }
        else if (count > 1) {
            courierHost = enableCourierHosts.get(getNextIndex(count));
        }

        return courierHost;
    }

    /**
     * 获取从 0 开始到指定上限（不包括）内的下一个数，当达到最大值(limit - 1)后进行重新从 0 开始循环获取。
     * <p>例如: limit 为 4，那么取值为: 0, 1, 2, 3, 0, 1, 2, ...</p>
     *
     * @param limit 上限（不包括）
     * @return
     */
    private int getNextIndex(int limit) {
        for (; ; ) {
            int current = currentIndex.get();
            int next = (current + 1) % limit;
            if (currentIndex.compareAndSet(current, next)) {
                return next;
            }
        }
    }
}
