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
     * 容器的状态枚举类。
     * <pre>状态的流转流程如下：
     *    NEW -> INITIALIZING -> INITIALIZED -----------
     *     |         |               |                 |
     *     |         |               |                \|/
     *     |---->----|------>--------|-------<-------RUNNING
     *                               |                /|\
     *                               |                 |
     *                              \|/               \|/
     *              DESTROYED <- STOPPING <--------MAINTAINING
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

        /**
         * 是否是{@link #NEW}状态。
         *
         * @param state 需要检查的枚举状态实例
         * @return {@code true}，是{@link #NEW}状态；否则不是{@link #NEW}状态
         */
        public static boolean isNew(final State state) {
            return state == NEW;
        }

        /**
         * 是否是{@link #INITIALIZING}状态。
         *
         * @param state 需要检查的枚举状态实例
         * @return {@code true}，是{@link #INITIALIZING}状态；否则不是{@link #INITIALIZING}状态
         */
        public static boolean isInitializing(final State state) {
            return state == INITIALIZING;
        }

        /**
         * 是否是{@link #RUNNING}状态。
         *
         * @param state 需要检查的枚举状态实例
         * @return {@code true}，是{@link #RUNNING}状态；否则不是{@link #RUNNING}状态
         */
        public static boolean isRunning(final State state) {
            return state == RUNNING;
        }

        /**
         * 是否是可以运行的状态。
         *
         * @param state 需要检查的枚举状态实例
         * @return {@code true}，是可以运行的状态；否则不是可运行的状态
         */
        public static boolean canRunning(final State state) {
            return state == MAINTAINING || state == INITIALIZED;
        }

        /**
         * 是否是可以进行维护的状态。
         *
         * @param state 需要检查的枚举状态实例
         * @return {@code true}，是可以进行维护的状态；否则不是可维护的状态
         */
        public static boolean canMaintaining(final State state) {
            return state == RUNNING;
        }
    }
}
