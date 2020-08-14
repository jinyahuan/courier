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
import java.util.Objects;

/**
 * 信使的处理器上下文。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierProcessorContext extends CourierContext {
    /**
     * 设置指定类型的处理器。
     *
     * @param type
     * @param processors
     * @return 之前设置的处理器 (may be null)
     */
    List<? extends CourierProcessor> setProcessorsOfType(Class<? extends CourierProcessor> type,
                                                         List<? extends CourierProcessor> processors);

    /**
     * 设置请求处理器。
     *
     * @param processors
     * @return 之前设置的请求处理器 (may be null)
     */
    default List<CourierRequestProcessor> setRequestProcessors(List<CourierRequestProcessor> processors) {
        return (List<CourierRequestProcessor>) setProcessorsOfType(CourierRequestProcessor.class, processors);
    }

    /**
     * 设置重试处理器。
     *
     * @param processors
     * @return 之前设置的重试处理器 (may be null)
     */
    default List<CourierRetryProcessor> setRetryProcessors(List<CourierRetryProcessor> processors) {
        return (List<CourierRetryProcessor>) setProcessorsOfType(CourierRetryProcessor.class, processors);
    }

    /**
     * 设置响应处理器。
     *
     * @param processors
     * @return 之前设置的响应处理器 (may be null)
     */
    default List<CourierResponseProcessor> setResponseProcessors(List<CourierResponseProcessor> processors) {
        return (List<CourierResponseProcessor>) setProcessorsOfType(CourierResponseProcessor.class, processors);
    }

    // - - -

    /**
     * 获取所有的处理器。
     *
     * @return 所有可用的处理器
     */
    Map<Class<? extends CourierProcessor>, List<? extends CourierProcessor>> getProcessorContext();

    /**
     * 获取指定类型的所有处理器。
     *
     * @param type 需要匹配的类型
     * @return not null, may be empty
     */
    default List<? extends CourierProcessor> getProcessorsOfType(Class<? extends CourierProcessor> type) {
        Objects.requireNonNull(type);

        Map<Class<? extends CourierProcessor>, List<? extends CourierProcessor>> processorMap = getProcessorContext();
        if (MapUtils.isNotEmpty(processorMap)) {
            List<? extends CourierProcessor> processors = processorMap.get(type);
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
    default List<CourierRequestProcessor> getRequestProcessors() {
        return (List<CourierRequestProcessor>) getProcessorsOfType(CourierRequestProcessor.class);
    }

    /**
     * 获取所有的重试处理器。
     *
     * @return not null, may be empty
     */
    default List<CourierRetryProcessor> getRetryProcessors() {
        return (List<CourierRetryProcessor>) getProcessorsOfType(CourierRetryProcessor.class);
    }

    /**
     * 获取所有的响应处理器。
     *
     * @return not null, may be empty
     */
    default List<CourierResponseProcessor> getResponseProcessors() {
        return (List<CourierResponseProcessor>) getProcessorsOfType(CourierResponseProcessor.class);
    }

    // - - -

    /**
     * 处理器上下文中是否有处理器（包括所有处理器）。
     *
     * @return {@code true}, 有至少一个或更多的处理器；否则没有处理器
     */
    default boolean hasProcessors() {
        return MapUtils.isNotEmpty(getProcessorContext());
    }

    /**
     * 处理器上下文是否有请求处理器。
     *
     * @return {@code true}, 有至少一个或更多的请求处理器；否则没有请求处理器
     */
    default boolean hasRequestProcessors() {
        return CollectionUtils.isNotEmpty(getRequestProcessors());
    }

    /**
     * 处理器上下文是否有重试处理器。
     *
     * @return {@code true}, 有至少一个或更多的重试处理器；否则没有重试处理器
     */
    default boolean hasRetryProcessors() {
        return CollectionUtils.isNotEmpty(getRetryProcessors());
    }

    /**
     * 处理器上下文是否有响应处理器。
     *
     * @return {@code true}, 有至少一个或更多的响应处理器；否则没有响应处理器
     */
    default boolean hasResponseProcessors() {
        return CollectionUtils.isNotEmpty(getResponseProcessors());
    }
}
