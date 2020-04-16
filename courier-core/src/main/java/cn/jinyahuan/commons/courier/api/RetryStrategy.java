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

package cn.jinyahuan.commons.courier.api;

/**
 * 重试策略。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public enum RetryStrategy {
    /** 不重试（默认的策略） */
    NEVER,
    /** 如果请求服务商超时，则重试 */
    IF_TIMEOUT,
    /** 如果请求服务商响应为明确的失败结果（就是说你发送失败了），则重试 */
    IF_CLEARLY_FAILURE,
    ;
}
