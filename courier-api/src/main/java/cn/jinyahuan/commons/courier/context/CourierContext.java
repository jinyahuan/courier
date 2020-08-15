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

package cn.jinyahuan.commons.courier.context;

/**
 * 信使上下文。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierContext {
    /**
     * 获取信使上下文的唯一id。
     *
     * @return
     */
    String getId();

    /**
     * 获取信使上下文的唯一名词。
     *
     * @return
     */
    String getName();

    /**
     * 获取容器的状态。
     *
     * @return
     */
    State getState();

    /**
     * 容器是否处于运行中状态。如果不处于运行中，则信使不应该执行逻辑。
     *
     * @return {@code true}, 容器正在运行中; 否则容器不处于运行中状态
     */
    default boolean isRunning() {
        return getState() == State.RUNNING;
    }

    /**
     * 容器的状态枚举类。
     * <pre>状态的流转流程如下：
     *    NEW -> INITIALIZING -> INITIALIZED
     *                               |
     *                              \|/
     *                           RUNNING  <-> MAINTAINING
     *                               |            |
     *                              \|/           |
     *              DESTROYED <- STOPPING <-------|
     * </pre>
     */
    enum State {
        /**
         * 容器刚创建。
         */
        NEW,
        /**
         * before 初始化。
         */
        INITIALIZING,
        /**
         * after 初始化。
         */
        INITIALIZED,
        /**
         * 运行中。
         */
        RUNNING,
        /**
         * 容器正在进行维护。
         */
        MAINTAINING,
        /**
         * 容器正在关闭。
         */
        STOPPING,
        /**
         * 容器已被摧毁。
         */
        DESTROYED;
    }
}
