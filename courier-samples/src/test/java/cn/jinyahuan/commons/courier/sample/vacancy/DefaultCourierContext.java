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

package cn.jinyahuan.commons.courier.sample.vacancy;

import cn.jinyahuan.commons.courier.context.AbstractCourierContext;
import cn.jinyahuan.commons.courier.processor.CourierProcessor;
import cn.jinyahuan.commons.courier.processor.request.CourierRequestProcessor;
import cn.jinyahuan.commons.courier.sample.vacancy.processor.DefaultCourierRequestProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class DefaultCourierContext extends AbstractCourierContext {
    @Override
    public List<? extends CourierProcessor> setProcessorsOfType(Class<? extends CourierProcessor> type, List<? extends CourierProcessor> processors) {
        this.processors = new HashMap<>();

        return this.processors.put(type, processors);
    }

    public static void main(String[] args) {
        List<CourierRequestProcessor> processors = new ArrayList<>();
        processors.add(new DefaultCourierRequestProcessor());

        DefaultCourierContext context = new DefaultCourierContext();

        context.setRequestProcessors(processors);

        List<CourierRequestProcessor> requestProcessors = context.getRequestProcessors();
        System.out.println(requestProcessors);
    }
}
