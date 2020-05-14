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

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 当使用空缺的短信服务商时的处理策略。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@Getter
@AllArgsConstructor
public enum VacancyHostHandleStrategy implements HandleStrategyAttributeAccessor {
    /** 抛出某种异常 */
    THROW_EXCEPTION(1),
    /** 响应为服务不可用状态 */
    RESPONSE_SERVICE_UNAVAILABLE_STATE(2),
    ;

    private final int id;
}
