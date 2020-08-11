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

import cn.jinyahuan.commons.courier.config.CourierConfigurable;
import cn.jinyahuan.commons.courier.processor.CourierProcessor;

import java.util.List;
import java.util.Map;

/**
 * 抽象的信使上下文。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public abstract class AbstractCourierContext
        implements CourierConfigurableContext, CourierProcessorContext {

    protected String id;
    protected String name;
    protected CourierConfigurable configuration;
    protected Map<Class<?>, List<CourierProcessor>> processors;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CourierConfigurable getConfiguration() {
        return configuration;
    }

    @Override
    public Map<Class<?>, List<CourierProcessor>> getProcessorContext() {
        return processors;
    }
}
