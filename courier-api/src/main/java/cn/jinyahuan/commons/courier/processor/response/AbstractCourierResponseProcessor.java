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

package cn.jinyahuan.commons.courier.processor.response;

import cn.jinyahuan.commons.courier.processor.CourierProcessorPriority;
import cn.jinyahuan.commons.courier.response.CourierResponse;
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
public abstract class AbstractCourierResponseProcessor implements CourierResponseProcessor {
    protected List<CourierResponseInterceptor> interceptors;

    public AbstractCourierResponseProcessor() {
        this(getDefaultInterceptors());
    }

    public AbstractCourierResponseProcessor(List<CourierResponseInterceptor> interceptors) {
        setInterceptors(interceptors);
    }

    @Override
    public List<CourierResponseInterceptor> getInterceptors() {
        return this.interceptors;
    }

    @Override
    public void setInterceptors(List<CourierResponseInterceptor> interceptors) {
        if (CollectionUtils.isNotEmpty(interceptors)) {
            List<CourierResponseInterceptor> list = interceptors.stream()
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
    public void process(CourierResponse response, CourierSupplier supplier) {
        if (CollectionUtils.isNotEmpty(interceptors)) {
            for (final CourierResponseInterceptor interceptor : interceptors) {
                interceptor.intercept(response, supplier);
            }
        }
    }

    protected static List<CourierResponseInterceptor> getDefaultInterceptors() {
        List<CourierResponseInterceptor> list = new CopyOnWriteArrayList<>();
        list.add(new CourierResponseNoOpInterceptor());
        return list;
    }
}
