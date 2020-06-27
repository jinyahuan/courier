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

package cn.jinyahuan.commons.courier.processor;

import cn.jinyahuan.commons.courier.CourierException;

/**
 * 信使处理器进行相关处理时出现的某些异常。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class CourierProcessorException extends CourierException {
    private static final long serialVersionUID = 1L;

    public CourierProcessorException() {
        super();
    }

    public CourierProcessorException(String message) {
        super(message);
    }

    public CourierProcessorException(Throwable cause) {
        super(cause);
    }

    public CourierProcessorException(String message, Throwable cause) {
        super(message, cause);
    }
}
