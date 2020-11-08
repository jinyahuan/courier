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

package cn.jinyahuan.commons.courier.response.state;

import cn.jinyahuan.commons.courier.response.CourierResponse;

import java.util.Objects;

/**
 * 信使响应状态码枚举类。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public enum CourierResponseState implements CourierResponseStateAccessor {
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
     * 失败。
     */
    FAILURE(99, "99", "failure"),
    /**
     * 服务不可用。
     */
    SERVICE_UNAVAILABLE(503, "503", "service unavailable"),
    ;

    private static final CourierResponseState[] ENUMS = CourierResponseState.values();

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

    CourierResponseState(int state, String code, String msg) {
        this.state = state;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    /**
     * 根据状态值找到对应的枚举。
     *
     * @param state 状态值
     * @return may be null
     */
    public static CourierResponseState valueOf(Integer state) {
        if (Objects.nonNull(state)) {
            final int value = state;
            for (final CourierResponseState anEnum : ENUMS) {
                if (value == anEnum.getState()) {
                    return anEnum;
                }
            }
        }
        return null;
    }

    /**
     * 是否是成功状态。
     *
     * @param state 状态枚举实例
     * @return (not null) {@code true}，成功状态；{@code false}，其他状态
     * @see #isSuccess(Integer)
     * @see #isSuccess(CourierResponse)
     */
    public static Boolean isSuccess(CourierResponseState state) {
        return state == SUCCESS;
    }

    /**
     * 是否是成功状态。
     *
     * @param state 状态值
     * @return (not null) {@code true}，成功状态；{@code false}，其他状态
     * @see #isSuccess(CourierResponseState)
     * @see #isSuccess(CourierResponse)
     */
    public static Boolean isSuccess(Integer state) {
        return Objects.nonNull(state) && state == SUCCESS.getState();
    }

    /**
     * 是否是成功状态。
     *
     * @param response 响应信息
     * @return (not null) {@code true}，成功状态；{@code false}，其他状态
     * @see #isSuccess(Integer)
     * @see #isSuccess(CourierResponseState)
     */
    public static Boolean isSuccess(CourierResponse<?> response) {
        return Objects.nonNull(response) && isSuccess(response.getState());
    }
}
