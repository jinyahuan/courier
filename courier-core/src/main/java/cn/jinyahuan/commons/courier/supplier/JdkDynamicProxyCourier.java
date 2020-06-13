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

package cn.jinyahuan.commons.courier.supplier;

import cn.jinyahuan.commons.courier.Courier;
import cn.jinyahuan.commons.courier.context.CourierContext;
import cn.jinyahuan.commons.courier.context.CourierProcessorContext;
import cn.jinyahuan.commons.courier.processor.request.CourierRequestProcessor;
import cn.jinyahuan.commons.courier.processor.response.CourierResponseProcessor;
import cn.jinyahuan.commons.courier.request.CourierRequest;
import cn.jinyahuan.commons.courier.response.CourierResponse;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * todo 完善
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@Slf4j
public class JdkDynamicProxyCourier implements InvocationHandler {
    protected Courier courier;

    protected CourierContext context;

    public JdkDynamicProxyCourier(Courier courier, CourierContext context) {
        this.courier = courier;
        this.context = context;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        CourierRequest request = obtainCourierRequest(args);
        CourierSupplier supplier = obtainCourierSupplier(args);

        processSupplier(request, supplier);

        processRequest(request, supplier);

        processBeforeRequestCallback(request, supplier);

        CourierResponse response = processRetry(method, args);

        processResponse(response, supplier);

        processAfterResponseCallback(response, supplier);

        return response;
    }

    protected void processSupplier(final CourierRequest request, final CourierSupplier supplier) {

    }

    protected void processRequest(final CourierRequest request, final CourierSupplier supplier) {
        final CourierProcessorContext processorContext = this.context.getProcessorContext();
        final List<CourierRequestProcessor> requestProcessors = processorContext.getRequestProcessors();

        for (final CourierRequestProcessor processor : requestProcessors) {
            try {
                processor.process(request, supplier);
            } catch (Exception e) {
            }
        }
    }

    protected void processBeforeRequestCallback(final CourierRequest request, final CourierSupplier supplier) {

    }

    protected CourierResponse processRetry(final Method method, final Object[] args) {
        CourierResponse result = null;
        // todo retry
        try {
            result = (CourierResponse) method.invoke(this.courier, args);
        } catch (IllegalAccessException | InvocationTargetException e) {

        }
        return result;
    }

    protected void processResponse(final CourierResponse response, final CourierSupplier supplier) {
        final CourierProcessorContext processorContext = this.context.getProcessorContext();
        final List<CourierResponseProcessor> responseProcessors = processorContext.getResponseProcessors();

        for (final CourierResponseProcessor processor : responseProcessors) {
            try {
                processor.process(response, supplier);
            } catch (Exception e) {

            }
        }
    }

    protected void processAfterResponseCallback(final CourierResponse response, final CourierSupplier supplier) {

    }

    protected static CourierRequest obtainCourierRequest(final Object[] args) {
        if (Objects.nonNull(args)) {
            for (final Object arg : args) {
                if (arg instanceof CourierRequest) {
                    return (CourierRequest) arg;
                }
            }
        }
        return null;
    }

    protected static CourierSupplier obtainCourierSupplier(final Object[] args) {
        if (Objects.nonNull(args)) {
            for (final Object arg : args) {
                if (arg instanceof CourierSupplier) {
                    return (CourierSupplier) arg;
                }
            }
        }
        return null;
    }
}