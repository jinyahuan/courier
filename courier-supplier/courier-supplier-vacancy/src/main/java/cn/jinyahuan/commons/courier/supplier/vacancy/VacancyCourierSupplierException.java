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

import cn.jinyahuan.commons.courier.CourierException;

/**
 * 信使服务商空缺异常。
 *
 * <p>
 * 通常用于没有指定信使服务商时，调用信使的方法时抛出的异常。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class VacancyCourierSupplierException extends CourierException {
    static final long serialVersionUID = 1L;

    public VacancyCourierSupplierException() {
        super();
    }

    public VacancyCourierSupplierException(String message) {
        super(message);
    }

    public VacancyCourierSupplierException(Throwable cause) {
        super(cause);
    }

    public VacancyCourierSupplierException(String message, Throwable cause) {
        super(message, cause);
    }
}
