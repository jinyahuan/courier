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

package cn.jinyahuan.commons.courier.api.state;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态码枚举类。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@Getter
@AllArgsConstructor
public enum ResponseState implements ResponseStateAccessor {
    /**
     * 失败。
     */
    FAILURE(0, "0", "failure"),
    /**
     * 成功。
     */
    SUCCESS(1, "1", "ok"),
    /**
     * 异步操作提交成功。
     */
    ASYNC_SUBMIT_SUCCESS(2, "2", "async submit ok"),
    /**
     * 批量操作提交成功。
     */
    BATCH_SUBMIT_SUCCESS(3, "3", "batch submit ok"),
    /**
     * 定时操作提交成功。
     */
    SCHEDULED_SUBMIT_SUCCESS(4, "4", "scheduled submit ok"),
    /**
     * 服务不可用。
     */
    SERVICE_UNAVAILABLE(101, "101", "service unavailable"),
    ;

    /**
     * 状态码。
     */
    private final int state;

    /**
     * 错误编码。
     */
    private final String code;

    /**
     * 错误信息。
     */
    private final String msg;
}
