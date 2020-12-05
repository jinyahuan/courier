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
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * 信使服务商{@link CourierChooser.Priority 优先级}选择器，优先选择已配置的优先级高
 * （{@link CourierChooser.Priority#value() 值}越小优先级越高）的服务商，若优先级相同，则按类名排序。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class PriorityCourierChooser
        extends AbstractStaticCourierChooser
        implements StaticCourierChooser, PooledCourierChooser, Serializable
{
    private static final long serialVersionUID = 1L;

    private static final CopyOnWriteArrayList emptyList = new CopyOnWriteArrayList();

    /**
     * 操作容器时，最好加锁，并且如果是同时有注册、移除注册操作，则每次操作完后都调用 resetCouriers 方法
     */
    protected CourierContainer courierContainer;

    protected transient CopyOnWriteArrayList<Courier> couriers = emptyList;

    public PriorityCourierChooser(CourierContainer courierContainer) {
        this.courierContainer = courierContainer;

        resetCouriers();
    }

    /**
     * 获取排好序的所有信使服务商的第一个。
     *
     * @param key 当前实现类中，此参数无效，所以可以传任何值，包括{@code null}
     * @return maybe null
     */
    @Override
    public Courier choose(Object key) {
        Courier courier = null;

        // 已注册的信使数是看容器，而非当前缓存的信使池
        int size = courierContainer.size();
        if (size > 0) {
            // 如果容器的信使注册数与当前缓存的一致，不一定就是信使也一致（注册、移除注册同时操作了）
            if (size == couriers.size()) {
                courier = couriers.get(0);
            }
            // 如果注册数不一致了，那么需要重置当前缓存的信使池
            else {
                resetCouriers();

                courier = choose(key);
            }
        }

        return courier;
    }

    /**
     * 设置所有启用的信使服务商。并且在设置时排好序。
     * <pre>
     *     第一优先级是{@link CourierChooser.Priority#value()}的值，越小优先级越高
     *     如果同级，则第二优先级是{@link Courier}实现类的类名字典序，根据{@link String#compareTo(String) compareTo}的值
     * </pre>
     */
    private void resetCouriers() {
        int size = courierContainer.size();
        if (couriers.size() != size) {
            synchronized (courierContainer) {
                size = courierContainer.size();
                if (couriers.size() != size) {
                    if (size == 0) {
                        this.couriers = emptyList;
                    }
                    else {
                        List<Courier> list = courierContainer.getCouriers().stream()
                                .sorted(Comparator
                                        .comparing(c -> {
                                            CourierChooser.Priority annotation =
                                                    c.getClass().getAnnotation(CourierChooser.Priority.class);
                                            return Objects.isNull(annotation) ? Integer.MAX_VALUE
                                                    : annotation.value();
                                        })
                                        .thenComparing(c -> c.getClass().getName())
                                )
                                .collect(Collectors.toList());
                        this.couriers = new CopyOnWriteArrayList<>(list);
                    }
                }
            }
        }
    }
}
