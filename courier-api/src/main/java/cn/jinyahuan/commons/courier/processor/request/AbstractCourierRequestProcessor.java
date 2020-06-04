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

package cn.jinyahuan.commons.courier.processor.request;

import cn.jinyahuan.commons.courier.processor.CourierProcessorPriority;
import cn.jinyahuan.commons.courier.request.CourierRequest;
import cn.jinyahuan.commons.courier.supplier.CourierSupplier;
import cn.jinyahuan.commons.courier.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public abstract class AbstractCourierRequestProcessor implements CourierRequestProcessor {
    protected List<CourierRequestInterceptor> interceptors;

    public AbstractCourierRequestProcessor() {
        this(getDefaultInterceptors());
    }

    public AbstractCourierRequestProcessor(List<CourierRequestInterceptor> interceptors) {
        setInterceptors(interceptors);
    }

    @Override
    public List<CourierRequestInterceptor> getInterceptors() {
        return this.interceptors;
    }

    @Override
    public void setInterceptors(List<CourierRequestInterceptor> interceptors) {
        if (CollectionUtils.isNotEmpty(interceptors)) {
            List<CourierRequestInterceptor> list = interceptors.stream()
                    .sorted(Comparator.comparing(
                            p -> {
                                CourierProcessorPriority annotation =
                                        p.getClass().getAnnotation(CourierProcessorPriority.class);
                                return Objects.nonNull(annotation) ? annotation.value() : Integer.MAX_VALUE;
                            })
                    )
                    .collect(Collectors.toList());

            this.interceptors = new CopyOnWriteArrayList<>(list);
        }
    }

    @Override
    public void process(CourierRequest request, CourierSupplier supplier) {
        if (CollectionUtils.isNotEmpty(interceptors)) {
            for (final CourierRequestInterceptor interceptor : interceptors) {
                interceptor.intercept(request, supplier);
            }
        }
    }

    protected static List<CourierRequestInterceptor> getDefaultInterceptors() {
        List<CourierRequestInterceptor> list = new CopyOnWriteArrayList<>();
        list.add(new CourierRequestNoOpInterceptor());
        return list;
    }
}
