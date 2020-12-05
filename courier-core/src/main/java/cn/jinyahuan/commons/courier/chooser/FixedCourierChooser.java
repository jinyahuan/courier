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

/**
 * 固定的信使选择器，固定返回构造对象时指定的信使。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class FixedCourierChooser
        extends AbstractStaticCourierChooser
        implements StaticCourierChooser, Serializable
{
    private static final long serialVersionUID = 1L;

    protected final Courier courier;

    public FixedCourierChooser(Courier courier) {
        this.courier = courier;
    }

    @Override
    public Courier choose(Object key) {
        return courier;
    }
}
