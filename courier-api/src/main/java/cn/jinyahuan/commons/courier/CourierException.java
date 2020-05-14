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

package cn.jinyahuan.commons.courier;

/**
 * 信使中所有异常类的超类。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class CourierException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public CourierException() {
        super();
    }

    public CourierException(String message) {
        super(message);
    }

    public CourierException(Throwable cause) {
        super(cause);
    }
}
