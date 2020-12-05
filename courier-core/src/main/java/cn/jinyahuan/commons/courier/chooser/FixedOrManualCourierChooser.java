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

import java.io.Serializable;
import java.util.Objects;

/**
 * 手动或固定的信使选择器。
 * <p>
 * 实例化对象的时候传入一个固定的信使，当调用{@code choose}方法时，可以手动指定一个信使，
 * 如果选择失败，则返回构造对象时传入的固定的信使。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class FixedOrManualCourierChooser
        extends FixedCourierChooser
        implements StaticCourierChooser, Serializable
{
    private static final long serialVersionUID = 1L;

    public FixedOrManualCourierChooser(Courier courier) {
        super(courier);
    }

    /**
     * 选择一个信使。
     *
     * @param key 需要指定的{@link Courier}对象
     * @return 返回指定的信使；或者返回{@code 构造对象时传入的信使}，如果 key 为{@code null}，或者不是{@link Courier}实例
     */
    @Override
    public Courier choose(Object key) {
        Courier result = this.courier;
        if (Objects.nonNull(key) && key instanceof Courier) {
            result = (Courier) key;
        }
        return result;
    }
}
