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
import java.util.Objects;
import java.util.Random;

/**
 * 信使服务商随机选择器，随机选择一个已配置的服务商。
 *
 * @author Yahuan Jin
 * @see CourierSupplierFixedChooser
 * @see CourierSupplierManualChooser
 * @see CourierSupplierPriorityChooser
 * @see CourierSupplierRoundChooser
 * @since 0.1
 */
public class CourierSupplierRandomChooser extends AbstractCourierSupplierChooser {
    protected volatile Random random;

    public CourierSupplierRandomChooser() {
        super();
    }

    public CourierSupplierRandomChooser(List<CourierSupplier> enableCourierHosts) {
        super(enableCourierHosts);
    }

    /**
     * 随机获取一个信使服务商。
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
     * 随机获取从 0 开始到指定上限（不包括）内的一个值。
     * <p>例如: limit 为 4，那么取值可能为 0 到 3 之间(包括 0 和 3)的任何值。</p>
     *
     * @param limit 上限（不包括）
     * @return
     */
    protected int getNextIndex(int limit) {
        return getRandom(true).nextInt(limit);
    }

    /**
     * 获取{@link Random}实例。
     * <p>默认实现是{@link Random}，如有需要可以进行继承并重写实现类，
     * 例如：{@link java.util.concurrent.ThreadLocalRandom},
     * {@link java.security.SecureRandom}等。</p>
     *
     * @param singleton 是否是单例
     * @return
     */
    protected Random getRandom(boolean singleton) {
        if (singleton) {
            if (Objects.isNull(random)) {
                synchronized (this) {
                    if (Objects.isNull(random)) {
                        Random temp = new Random();
                        random = temp;
                    }
                }
            }
            return random;
        }
        else {
            return new Random();
        }
    }
}
