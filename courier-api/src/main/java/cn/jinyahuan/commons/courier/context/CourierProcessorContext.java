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

import cn.jinyahuan.commons.courier.processor.CourierProcessor;
import cn.jinyahuan.commons.courier.processor.request.CourierRequestProcessor;
import cn.jinyahuan.commons.courier.processor.response.CourierResponseProcessor;
import cn.jinyahuan.commons.courier.processor.retry.CourierRetryProcessor;
import cn.jinyahuan.commons.courier.util.CollectionUtils;
import cn.jinyahuan.commons.courier.util.MapUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierProcessorContext extends CourierContext {
    /**
     * 获取所有的处理器。
     *
     * @return 所有可用的处理器
     */
    Map<Class<?>, List<CourierProcessor>> getProcessorContext();

    /**
     * 获取指定类型的所有处理器。
     *
     * @param clazz 需要匹配的类型
     * @return not null, may be empty
     */
    default List<? extends CourierProcessor> getProcessorsOfType(Class<?> clazz) {
        Map<Class<?>, List<CourierProcessor>> processorMap = getProcessorContext();
        if (MapUtils.isNotEmpty(processorMap)) {
            List<CourierProcessor> processors = processorMap.get(clazz);
            if (CollectionUtils.isNotEmpty(processors)) {
                return processors;
            }
        }
        return Collections.emptyList();
    }

    /**
     * 获取所有的请求处理器。
     *
     * @return not null, may be empty
     */
    default List<? extends CourierRequestProcessor> getRequestProcessors() {
        return (List<? extends CourierRequestProcessor>) getProcessorsOfType(CourierRequestProcessor.class);
    }

    /**
     * 获取所有的重试处理器。
     *
     * @return not null, may be empty
     */
    default List<? extends CourierRetryProcessor> getRetryProcessors() {
        return (List<? extends CourierRetryProcessor>) getProcessorsOfType(CourierRetryProcessor.class);
    }

    /**
     * 获取所有的响应处理器。
     *
     * @return not null, may be empty
     */
    default List<? extends CourierResponseProcessor> getResponseProcessors() {
        return (List<? extends CourierResponseProcessor>) getProcessorsOfType(CourierResponseProcessor.class);
    }
}
