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

package cn.jinyahuan.commons.courier.chooser;

import cn.jinyahuan.commons.courier.Courier;
import cn.jinyahuan.commons.courier.CourierContainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 信使服务商轮询选择器，轮询选择已配置的服务商。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class RoundCourierChooser
        extends AbstractStaticCourierChooser
        implements StaticCourierChooser, PooledCourierChooser, Serializable
{
    private static final long serialVersionUID = 1L;

    protected final AtomicInteger currentIndex = new AtomicInteger(-1);

    protected CourierContainer courierContainer;

    public RoundCourierChooser(CourierContainer courierContainer) {
        this.courierContainer = courierContainer;
    }

    /**
     * 轮询获取一个信使服务商。
     *
     * @param key 当前实现类中，此参数无效，所以可以传任何值，包括{@code null}
     * @return maybe null
     * @throws ChoosingCourierFailException Most likely because courier container execute remove operation
     */
    @Override
    public Courier choose(Object key) throws ChoosingCourierFailException {
        Courier courier = null;

        int size = courierContainer.size();
        if (size > 0) {
            List<Courier> list = new ArrayList<>(courierContainer.getCouriers());
            try {
                courier = size == 1 ? list.get(0)
                        : list.get(getNextIndex(size));
            } catch (Exception e) {
                // may be remove courier
                throw new ChoosingCourierFailException(e);
            }
        }

        return courier;
    }

    /**
     * 获取从 0 开始到指定上限（不包括）内的下一个数，当达到最大值(limit - 1)后进行重新从 0 开始循环获取。
     * <p>例如: limit 为 4，那么取值为: 0, 1, 2, 3, 0, 1, 2, ...</p>
     *
     * @param limit 上限（不包括）
     * @return
     */
    protected int getNextIndex(int limit) {
        for (; ; ) {
            int current = currentIndex.get();
            int next = (current + 1) % limit;
            if (currentIndex.compareAndSet(current, next)) {
                return next;
            }
        }
    }
}
