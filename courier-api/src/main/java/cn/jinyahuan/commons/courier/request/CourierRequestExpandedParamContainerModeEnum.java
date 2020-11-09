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

package cn.jinyahuan.commons.courier.request;

/**
 * 信使请求信息参数容器模式枚举类。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public enum CourierRequestExpandedParamContainerModeEnum {
    /**
     * 常规的（无序的）。对应的容器为{@link java.util.HashMap HashMap}。
     */
    NORMAL,
    /**
     * 按插入的先后顺序排序。对应的容器为{@link java.util.LinkedHashMap LinkedHashMap}。
     */
    INSERTION_ORDER,
    /**
     * 自然排序。对应的容器为{@link java.util.TreeMap TreeMap}。
     */
    NATURAL_ORDER,
    /**
     * 并发的无序的。对应的容器为{@link java.util.concurrent.ConcurrentHashMap ConcurrentHashMap}。
     * <p>
     * 注意: 仅仅只是容器类为可并发的容器。
     */
    CONCURRENCY_NORMAL
    ;
}
