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

/**
 * 信使选择器，用于选择一个信使。
 *
 * @author Yahuan Jin
 * @see StaticCourierChooser
 * @see DynamicCourierChooser
 * @since 0.1
 */
public interface CourierChooser {
    /**
     * 选择一个信使。
     *
     * @param key 用于选择信使的 key
     * @return may be null
     * @throws ChoosingCourierFailException 选择失败时会抛出此异常
     */
    Courier choose(Object key) throws ChoosingCourierFailException;
}
