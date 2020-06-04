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

import cn.jinyahuan.commons.courier.Courier;
import cn.jinyahuan.commons.courier.request.CourierRequest;
import cn.jinyahuan.commons.courier.supplier.CourierSupplier;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 基于 JDK 动态代理实现的 信使请求参数处理器。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class JdkDynamicProxyCourierRequestProcessor implements InvocationHandler {
    private Courier courier;

    private CourierRequestProcessor processor;

    public JdkDynamicProxyCourierRequestProcessor(Courier courier, CourierRequestProcessor processor) {
        this.courier = courier;
        this.processor = processor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        CourierRequest request = obtainCourierRequest(args);
        CourierSupplier supplier = obtainCourierSupplier(args);

        processRequest(request, supplier);

        return method.invoke(courier, args);
    }

    protected void processRequest(CourierRequest request, CourierSupplier supplier) {
        processor.process(request, supplier);
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
