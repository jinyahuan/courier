///*
// * Copyright (c) 2020 The Courier Authors
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package cn.jinyahuan.commons.courier.supplier;
//
//import cn.jinyahuan.commons.courier.Courier;
//import cn.jinyahuan.commons.courier.CourierException;
//import cn.jinyahuan.commons.courier.context.CourierCallbackProcessorContext;
//import cn.jinyahuan.commons.courier.context.CourierContext;
//import cn.jinyahuan.commons.courier.context.CourierProcessorContext;
//import cn.jinyahuan.commons.courier.processor.CourierProcessorException;
//import cn.jinyahuan.commons.courier.processor.callback.CourierRequestCallbackProcessor;
//import cn.jinyahuan.commons.courier.processor.callback.CourierResponseCallbackProcessor;
//import cn.jinyahuan.commons.courier.processor.request.CourierRequestProcessor;
//import cn.jinyahuan.commons.courier.processor.response.CourierResponseProcessor;
//import cn.jinyahuan.commons.courier.processor.retry.CourierRetryProcessor;
//import cn.jinyahuan.commons.courier.request.CourierRequest;
//import cn.jinyahuan.commons.courier.response.CourierResponse;
//import cn.jinyahuan.commons.courier.util.CollectionUtils;
//import lombok.extern.slf4j.Slf4j;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.List;
//import java.util.Objects;
//
///**
// * todo 完善
// * todo 抽象出执行流程的抽象方法
// * todo 拆分功能，每块功能一层代理
// *
// * @author Yahuan Jin
// * @since 0.1
// */
//@Slf4j
//public class JdkDynamicProxyCourier implements InvocationHandler {
//    protected Courier courier;
//
//    protected CourierContext context;
//
//    /**
//     * 是否需要重试。
//     */
//    protected volatile boolean needRetry;
//    protected volatile boolean needCallbackBeforeRequest;
//    protected volatile boolean needCallbackAfterResponse;
//
//    public JdkDynamicProxyCourier(Courier courier, CourierContext context) {
//        this.courier = courier;
//        this.context = context;
//
//        needRetry = hasRetryProcessor(this.context);
//        needCallbackBeforeRequest = hasRequestCallbackProcessor(this.context);
//        needCallbackAfterResponse = hasResponseCallbackProcessor(this.context);
//    }
//
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        CourierRequest request = obtainCourierRequest(args);
//        CourierSupplier supplier = obtainCourierSupplier(args);
//
//        processSupplier(request, supplier);
//
//        processRequest(request, supplier);
//
//        processBeforeRequestCallback(request, supplier);
//
//        CourierResponse response = null;
//
//        try {
//            response = invokeCourier(method, args);
//        } catch (Throwable throwable) {
//            handleInvokeCourierException(courier, method, args, response, throwable);
//        }
//
//        response = processRetry(response, method, args);
//
//        processResponse(response, supplier);
//
//        processAfterResponseCallback(request, response);
//
//        return response;
//    }
//
//    protected void processSupplier(final CourierRequest request, final CourierSupplier supplier) {
//
//    }
//
//    /**
//     * 处理请求参数。
//     *
//     * @param request
//     * @param supplier
//     */
//    protected void processRequest(final CourierRequest request, final CourierSupplier supplier) {
//        final CourierProcessorContext processorContext = this.context.getProcessorContext();
//        if (Objects.nonNull(processorContext)) {
//            final List<CourierRequestProcessor> requestProcessors = processorContext.getRequestProcessors();
//            if (CollectionUtils.isNotEmpty(requestProcessors)) {
//                for (final CourierRequestProcessor processor : requestProcessors) {
//                    try {
//                        processor.process(request, supplier);
//                    } catch (Exception e) {
//                        throw new CourierProcessorException("Courier (JdkDynamicProxy) process request failure", e);
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 请求信使服务商前的回调。
//     *
//     * @param request
//     * @param supplier
//     */
//    protected void processBeforeRequestCallback(final CourierRequest request, final CourierSupplier supplier) {
//        if (needCallbackBeforeRequest) {
//            final CourierProcessorContext processorContext = this.context.getProcessorContext();
//            if (Objects.nonNull(processorContext)) {
//                CourierCallbackProcessorContext callbackProcessorContext = processorContext.getCallbackProcessorContext();
//                if (Objects.nonNull(callbackProcessorContext)) {
//                    CourierRequestCallbackProcessor processor = callbackProcessorContext.getBeforeRequestCallbackProcessor();
//                    if (Objects.nonNull(processor)) {
//                        processor.call(courier, request);
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 调用信使（实际执行的方法）。
//     *
//     * @param method
//     * @param args
//     * @return
//     */
//    protected CourierResponse invokeCourier(final Method method, final Object[] args) {
//        CourierResponse result = null;
//        try {
//            result = (CourierResponse) method.invoke(this.courier, args);
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            log.error("Courier (JdkDynamicProxy) process failure.", e);
//        }
//        return result;
//    }
//
//    protected void handleInvokeCourierException(Courier courier, Method method, Object[] args, CourierResponse response, Throwable throwable) {
//        log.error("Courier (JdkDynamicProxy) invoke failure.", throwable);
//        if (!getNeedRetry()) {
//            throw new CourierException(throwable);
//        }
//    }
//
//    /**
//     * 处理失败重试。
//     *
//     * @param response
//     * @param method
//     * @param args
//     * @return
//     */
//    protected CourierResponse processRetry(final CourierResponse response, final Method method, final Object[] args) {
//        if (needRetry) {
//            if (Objects.isNull(response) || !response.isSuccess()) {
//                final CourierProcessorContext processorContext = this.context.getProcessorContext();
//                if (Objects.nonNull(processorContext)) {
//                    final List<CourierRetryProcessor> retryProcessors = processorContext.getRetryProcessors();
//                    if (CollectionUtils.isNotEmpty(retryProcessors)) {
//                        CourierResponse tempResponse;
//                        for (final CourierRetryProcessor processor : retryProcessors) {
//                            tempResponse = processor.retry(courier, method, args);
//                            if (Objects.nonNull(tempResponse) && tempResponse.isSuccess()) {
//                                return tempResponse;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return response;
//    }
//
//    protected void processResponse(final CourierResponse response, final CourierSupplier supplier) {
//        final CourierProcessorContext processorContext = this.context.getProcessorContext();
//        if (Objects.nonNull(processorContext)) {
//            final List<CourierResponseProcessor> responseProcessors = processorContext.getResponseProcessors();
//            if (CollectionUtils.isNotEmpty(responseProcessors)) {
//                for (final CourierResponseProcessor processor : responseProcessors) {
//                    try {
//                        processor.process(response, supplier);
//                    } catch (Exception e) {
//                        throw new CourierProcessorException("Courier (JdkDynamicProxy) process response failure", e);
//                    }
//                }
//            }
//        }
//    }
//
//    protected void processAfterResponseCallback(CourierRequest request, CourierResponse response) {
//        if (needCallbackAfterResponse) {
//            final CourierProcessorContext processorContext = this.context.getProcessorContext();
//            if (Objects.nonNull(processorContext)) {
//                CourierCallbackProcessorContext callbackProcessorContext = processorContext.getCallbackProcessorContext();
//                if (Objects.nonNull(callbackProcessorContext)) {
//                    CourierResponseCallbackProcessor processor = callbackProcessorContext.getAfterResponseCallbackProcessor();
//                    if (Objects.nonNull(processor)) {
//                        processor.call(courier, request, response);
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 是否有重试处理器。
//     *
//     * @param context
//     * @return
//     */
//    protected boolean hasRetryProcessor(final CourierContext context) {
//        final CourierProcessorContext processorContext = this.context.getProcessorContext();
//        if (Objects.nonNull(processorContext)) {
//            return CollectionUtils.size(processorContext.getRetryProcessors()) > 0;
//        }
//        return false;
//    }
//
//    protected boolean hasRequestCallbackProcessor(final CourierContext context) {
//        final CourierProcessorContext processorContext = this.context.getProcessorContext();
//        if (Objects.nonNull(processorContext)) {
//            CourierCallbackProcessorContext callbackProcessorContext = processorContext.getCallbackProcessorContext();
//            if (Objects.nonNull(callbackProcessorContext)) {
//                return Objects.nonNull(callbackProcessorContext.getBeforeRequestCallbackProcessor());
//            }
//        }
//        return false;
//    }
//
//    protected boolean hasResponseCallbackProcessor(final CourierContext context) {
//        final CourierProcessorContext processorContext = this.context.getProcessorContext();
//        if (Objects.nonNull(processorContext)) {
//            CourierCallbackProcessorContext callbackProcessorContext = processorContext.getCallbackProcessorContext();
//            if (Objects.nonNull(callbackProcessorContext)) {
//                return Objects.nonNull(callbackProcessorContext.getAfterResponseCallbackProcessor());
//            }
//        }
//        return false;
//    }
//
//    protected boolean getNeedRetry() {
//        return needRetry;
//    }
//
//    protected static CourierRequest obtainCourierRequest(final Object[] args) {
//        if (Objects.nonNull(args)) {
//            for (final Object arg : args) {
//                if (arg instanceof CourierRequest) {
//                    return (CourierRequest) arg;
//                }
//            }
//        }
//        return null;
//    }
//
//    protected static CourierSupplier obtainCourierSupplier(final Object[] args) {
//        if (Objects.nonNull(args)) {
//            for (final Object arg : args) {
//                if (arg instanceof CourierSupplier) {
//                    return (CourierSupplier) arg;
//                }
//            }
//        }
//        return null;
//    }
//}
