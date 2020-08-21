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
import java.util.concurrent.ConcurrentHashMap;

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
    protected volatile State state;
    protected Map<Class<? extends CourierProcessor>, List<? extends CourierProcessor>> processors;

    public AbstractCourierContext() {
        init();
    }

    // todo 构造器重载

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public CourierConfigurable getConfiguration() {
        return this.configuration;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public List<? extends CourierProcessor> setProcessorsOfType(Class<? extends CourierProcessor> type, List<? extends CourierProcessor> processors) {
        return this.processors.put(type, processors);
    }

    @Override
    public Map<Class<? extends CourierProcessor>, List<? extends CourierProcessor>> getProcessorContext() {
        return this.processors;
    }

    protected void init() {
        this.id = getClass().getName() + "@" + System.identityHashCode(this);
        // 默认情况下 name 与 id 是一样的
        this.name = this.id;
        // todo 设计并实现 CourierConfigurable
        this.configuration = null;
        this.state = State.NEW;
        this.processors = new ConcurrentHashMap<>(64);
    }
}
