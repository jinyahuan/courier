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

package cn.jinyahuan.commons.courier;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抽象的信使容器。
 * <p>
 * 用于提供一个信使容器模板，简化开发。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public abstract class AbstractCourierContainer implements CourierContainer {
    /**
     * 信使缓存池。
     */
    protected Map<String, Courier> couriers;

    public AbstractCourierContainer() {
        this(new ConcurrentHashMap<>(64));
    }

    public AbstractCourierContainer(Map<String, Courier> couriers) {
        this.couriers = Objects.requireNonNull(couriers, "couriers must not be null");
    }

    @Override
    public Courier register(String key, Courier courier) {
        return couriers.put(
                Objects.requireNonNull(key, "key must not be null"),
                Objects.requireNonNull(courier, "courier must not be null")
        );
    }

    @Override
    public Courier registerIfAbsent(String key, Courier courier) {
        return couriers.putIfAbsent(
                Objects.requireNonNull(key, "key must not be null"),
                Objects.requireNonNull(courier, "courier must not be null")
        );
    }

    @Override
    public Courier unregister(String key) {
        return couriers.remove(key);
    }

    @Override
    public Courier unregister(Courier courier) {
        Set<Map.Entry<String, Courier>> courierAndKeys = getCourierAndKeys();
        if (!courierAndKeys.isEmpty()) {
            Iterator<Map.Entry<String, Courier>> iterator = courierAndKeys.iterator();
            while (iterator.hasNext()) {
                Courier v = iterator.next().getValue();
                if (Objects.equals(courier, v)) {
                    iterator.remove();
                    return v;
                }
            }
        }
        return null;
    }

    @Override
    public Courier getCourier(String key) {
        return couriers.get(key);
    }

    @Override
    public Set<String> getCourierKeys() {
        return couriers.keySet();
    }

    @Override
    public Collection<Courier> getCouriers() {
        return couriers.values();
    }

    @Override
    public Set<Map.Entry<String, Courier>> getCourierAndKeys() {
        return couriers.entrySet();
    }

    @Override
    public boolean containsCourier(String key) {
        return couriers.containsKey(key);
    }

    @Override
    public boolean containsCourier(Courier courier) {
        return couriers.containsValue(courier);
    }

    @Override
    public int size() {
        return couriers.size();
    }
}
