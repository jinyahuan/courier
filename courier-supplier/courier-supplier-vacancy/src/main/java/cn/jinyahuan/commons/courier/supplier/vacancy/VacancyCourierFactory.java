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

package cn.jinyahuan.commons.courier.supplier.vacancy;

import cn.jinyahuan.commons.courier.request.CourierRequest;
import cn.jinyahuan.commons.courier.response.CourierResponse;
import cn.jinyahuan.commons.courier.response.CourierResponseFactory;

/**
 * 空缺服务商的信使工厂。提供用于生产空缺服务商的特定信使。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public final class VacancyCourierFactory {
    /**
     * 一个不可变的异常化的空缺服务商的信使，该信使的所有方法都会抛出{@link VacancyCourierSupplierException}异常。
     */
    public static final VacancyCourier IMMUTABLE_THROWABLE_COURIER =
            new ThrowableVacancyCourier();

    /**
     * 一个不可变的服务不可用化的空缺服务商的信使，该信使的所有方法返回
     * {@link CourierResponseFactory#IMMUTABLE_SERVICE_UNAVAILABLE_RESPONSE 服务不可用状态的响应}。
     */
    public static final VacancyCourier IMMUTABLE_SERVICE_UNAVAILABLE_STATE_COURIER =
            new ServiceUnavailableResponseVacancyCourier();

    /**
     * 一个不可变的失败化的空缺服务商的信使，该信使的所有方法返回
     * {@link CourierResponseFactory#IMMUTABLE_FAILED_RESPONSE 失败状态的响应}。
     */
    public static final VacancyCourier IMMUTABLE_FAILED_STATE_COURIER =
            new FailedResponseVacancyCourier();

    /**
     * 一个不可变的成功化的空缺服务商的信使，该信使的所有方法返回
     * {@link CourierResponseFactory#IMMUTABLE_SUCCESSFUL_RESPONSE 成功状态的响应}。
     */
    public static final VacancyCourier IMMUTABLE_SUCCESSFUL_STATE_COURIER =
            new SuccessfulResponseVacancyCourier();

    // - - -

    /**
     * 空缺服务商的信使适配器。
     *
     * <p>
     * 提供快速创建一个{@link VacancyCourier}实例的基类。可以通过覆盖方法的进行自定义设计。
     *
     * <p>
     * 本实例所有方法的默认操作都是直接返回{@code null}。
     */
    public static class VacancyCourierAdapter implements VacancyCourier {
        @Override
        public CourierResponse send(CourierRequest request) {
            return handle(request);
        }

        @Override
        public CourierResponse query(CourierRequest request) {
            return handle(request);
        }

        @Override
        public CourierResponse sendAsync(CourierRequest request) {
            return handle(request);
        }

        @Override
        public CourierResponse sendBatch(CourierRequest request) {
            return handle(request);
        }

        @Override
        public CourierResponse queryBatch(CourierRequest request) {
            return handle(request);
        }

        @Override
        public CourierResponse sendScheduled(CourierRequest request) {
            return handle(request);
        }

        @Override
        public CourierResponse queryScheduled(CourierRequest request) {
            return handle(request);
        }

        /**
         * 默认的处理方式是直接返回{@code null}。
         *
         * @param request 请求参数
         * @return {@code null}
         */
        protected CourierResponse handle(CourierRequest request) {
            return null;
        }
    }

    /**
     * 异常化的空缺服务商的信使，该信使的所有方法都会抛出{@link VacancyCourierSupplierException}异常。
     */
    static class ThrowableVacancyCourier
            extends VacancyCourierAdapter
            implements VacancyCourier {
        @Override
        protected CourierResponse handle(CourierRequest request) {
            throw new VacancyCourierSupplierException();
        }
    }

    /**
     * 服务不可用化的空缺服务商的信使，该信使的所有方法返回
     * {@link CourierResponseFactory#IMMUTABLE_SERVICE_UNAVAILABLE_RESPONSE 服务不可用状态的响应}。
     */
    static class ServiceUnavailableResponseVacancyCourier
            extends VacancyCourierAdapter
            implements VacancyCourier {
        @Override
        protected CourierResponse handle(CourierRequest request) {
            return CourierResponseFactory.IMMUTABLE_SERVICE_UNAVAILABLE_RESPONSE;
        }
    }

    /**
     * 失败化的空缺服务商的信使，该信使的所有方法返回
     * {@link CourierResponseFactory#IMMUTABLE_FAILED_RESPONSE 失败状态的响应}。
     */
    static class FailedResponseVacancyCourier
            extends VacancyCourierAdapter
            implements VacancyCourier {
        @Override
        protected CourierResponse handle(CourierRequest request) {
            return CourierResponseFactory.IMMUTABLE_FAILED_RESPONSE;
        }
    }

    /**
     * 成功化的空缺服务商的信使，该信使的所有方法返回
     * {@link CourierResponseFactory#IMMUTABLE_SUCCESSFUL_RESPONSE 成功状态的响应}。
     */
    static class SuccessfulResponseVacancyCourier
            extends VacancyCourierAdapter
            implements VacancyCourier {
        @Override
        protected CourierResponse handle(CourierRequest request) {
            return CourierResponseFactory.IMMUTABLE_SUCCESSFUL_RESPONSE;
        }
    }
}
