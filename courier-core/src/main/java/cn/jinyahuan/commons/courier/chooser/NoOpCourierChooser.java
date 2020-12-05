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
 * No-Operation dynamic courier chooser. The {@code choose} method always return {@code null}.
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class NoOpCourierChooser implements StaticCourierChooser, DynamicCourierChooser {
    /**
     * Choose a courier by the key.
     * <p>
     * The current implementation always returns {@code null}.
     *
     * @param key the key to choose courier
     * @return always return {@code null}
     */
    @Override
    public Courier choose(Object key) throws ChoosingCourierFailException {
        return null;
    }
}
