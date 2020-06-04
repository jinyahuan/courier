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

import cn.jinyahuan.commons.courier.processor.request.CourierRequestProcessor;
import cn.jinyahuan.commons.courier.processor.response.CourierResponseProcessor;

import java.lang.annotation.*;

/**
 * 处理器的优先级。
 * <p>不同的类型不共享优先级，即优先级只针对统一类型。比如，即使{@link CourierResponseProcessor}的优先级比
 * {@link CourierRequestProcessor}高，还是{@link CourierRequestProcessor}先进行执行。
 * 但是多个{@link CourierRequestProcessor}时会按优先级逐个遍历执行。
 * </p>
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CourierProcessorPriority {
    /**
     * 优先级。
     *
     * @return
     */
    int value() default Integer.MAX_VALUE - 7;
}
