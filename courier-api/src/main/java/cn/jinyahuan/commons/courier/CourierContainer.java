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

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 信使容器。
 * <p>
 * 用于池化信使，不需要每次都实例化。
 *
 * @author Yahuan Jin
 * @see cn.jinyahuan.commons.courier.chooser.CourierChooser CourierChooser
 * @since 0.1
 */
public interface CourierContainer {
    /**
     * 将一个指定的信使注册到信使容器（如果已经注册过，则会覆盖已注册的信使）。
     *
     * @param key     (not null)信使的 key
     * @param courier (not null)待注册的信使对象
     * @return 返回原先与 key 关联的信使；或者返回{@code null}，当前未注册过该信使
     */
    Courier register(String key, Courier courier);

    /**
     * 将一个指定的信使注册到信使容器（如果已经注册过，则不会注册，即不会覆盖已注册的信使）。
     *
     * @param key     (not null)信使的 key
     * @param courier (not null)待注册的信使对象
     * @return 返回原先与 key 关联的信使；或者返回{@code null}，当前未注册过该信使
     */
    Courier registerIfAbsent(String key, Courier courier);

    /**
     * 移除与{@code key}关联的信使。
     *
     * @param key 信使的 key
     * @return 返回被移除的信使；或者返回{@code null}，如果信使不存在
     */
    Courier unregister(String key);

    /**
     * 移除指定的信使。
     *
     * @param courier 需要移除的信使
     * @return 返回被移除的信使；或者返回{@code null}，如果信使不存在
     */
    Courier unregister(Courier courier);

    /**
     * 获取指定的信使。
     *
     * @param key 信使的 key
     * @return 返回指定的信使; 或者返回{@code null}，如果信使不存在
     */
    Courier getCourier(String key);

    /**
     * 获取信使缓存池中所有已注册的信使的 key。
     *
     * @return not null, may be empty
     */
    Set<String> getCourierKeys();

    /**
     * 获取信使缓存池中所有已注册的信使。
     *
     * @return not null, may be empty
     */
    Collection<Courier> getCouriers();

    /**
     * 获取信使缓存池中所有已注册的信使及其注册的 key。
     *
     * @return not null, may be empty
     */
    Set<Map.Entry<String, Courier>> getCourierAndKeys();

    /**
     * 检测信使池中是否存在指定的信使。
     *
     * @param key 信使的 key
     * @return {@code true}, 信使池中是否存在指定的信使
     */
    boolean containsCourier(String key);

    /**
     * 检测信使池中是否存在指定的信使。
     *
     * @param courier 信使实例
     * @return {@code true}, 信使池中是否存在指定的信使
     */
    boolean containsCourier(Courier courier);

    /**
     * 信使池中已注册的信使的数量。
     *
     * @return 信使池中已注册的信使的数量。如果池中没有注册过信使，则为{@code 0}
     */
    int size();
}
