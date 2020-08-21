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

package cn.jinyahuan.commons.courier.sample.context;

import cn.jinyahuan.commons.courier.context.CourierContext;
import cn.jinyahuan.commons.courier.processor.request.CourierRequestProcessor;
import cn.jinyahuan.commons.courier.processor.response.CourierResponseProcessor;
import cn.jinyahuan.commons.courier.processor.retry.CourierRetryProcessor;
import cn.jinyahuan.commons.courier.sample.processor.DefaultCourierRequestProcessor;
import cn.jinyahuan.commons.courier.sample.processor.DefaultCourierResponseProcessor;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class ContextDemo {
    @Test
    public void testContext() {
        final DefaultCourierContext context = new DefaultCourierContext();

        assertEquals(CourierContext.State.NEW, context.getState());
        context.setStateToInitializing();
        assertEquals(CourierContext.State.INITIALIZING, context.getState());

        final List<CourierRequestProcessor> requestProcessors = getRequestProcessors();
        context.setRequestProcessors(requestProcessors);

        final List<CourierResponseProcessor> responseProcessors = getResponseProcessors();
        context.setResponseProcessors(responseProcessors);

        final List<CourierRetryProcessor> retryProcessors = getRetryProcessors();
        context.setRetryProcessors(retryProcessors);

        // todo set other processors

        assertEquals(CourierContext.State.INITIALIZING, context.getState());
        context.setStateToInitialized();
        assertEquals(CourierContext.State.INITIALIZED, context.getState());
        context.setStateToRunning();
        assertEquals(CourierContext.State.RUNNING, context.getState());

        assertEquals(requestProcessors, context.getRequestProcessors());
        assertEquals(retryProcessors, context.getRetryProcessors());
        assertEquals(responseProcessors, context.getResponseProcessors());
    }

    private List<CourierRequestProcessor> getRequestProcessors() {
        List<CourierRequestProcessor> processors = new CopyOnWriteArrayList<>();
        processors.add(new DefaultCourierRequestProcessor());

        return processors;
    }

    private List<CourierRetryProcessor> getRetryProcessors() {
        List<CourierRetryProcessor> processors = new CopyOnWriteArrayList<>();

        return processors;
    }

    private List<CourierResponseProcessor> getResponseProcessors() {
        List<CourierResponseProcessor> processors = new CopyOnWriteArrayList<>();

        processors.add(new DefaultCourierResponseProcessor());

        return processors;
    }
}
