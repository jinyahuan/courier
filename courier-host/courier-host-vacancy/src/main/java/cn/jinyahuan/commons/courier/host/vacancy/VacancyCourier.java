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

package cn.jinyahuan.commons.courier.host.vacancy;

import cn.jinyahuan.commons.courier.api.Courier;
import cn.jinyahuan.commons.courier.api.model.CourierRequest;
import cn.jinyahuan.commons.courier.api.model.CourierResponse;

import java.util.Objects;

/**
 * 空缺服务商的信使。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class VacancyCourier implements Courier {
    /**
     * 默认的处理策略。
     */
    private static final VacancyHostHandleStrategy DEFAULT_HANDLE_STRATEGY =
            VacancyHostHandleStrategy.RESPONSE_SERVICE_UNAVAILABLE_STATE;

    private Courier courier;

    public VacancyCourier() {
        this(DEFAULT_HANDLE_STRATEGY);
    }

    public VacancyCourier(VacancyHostHandleStrategy handleStrategy) {
        init(handleStrategy);
    }

    @Override
    public CourierResponse send(CourierRequest request) {
        return courier.send(request);
    }

    @Override
    public CourierResponse query(CourierRequest request) {
        return courier.query(request);
    }

    @Override
    public CourierResponse sendAsync(CourierRequest request) {
        return courier.sendAsync(request);
    }

    @Override
    public CourierResponse sendBatch(CourierRequest request) {
        return courier.sendBatch(request);
    }

    @Override
    public CourierResponse queryBatch(CourierRequest request) {
        return courier.queryBatch(request);
    }

    @Override
    public CourierResponse sendScheduled(CourierRequest request) {
        return courier.sendScheduled(request);
    }

    @Override
    public CourierResponse queryScheduled(CourierRequest request) {
        return courier.queryScheduled(request);
    }

    /**
     * 初始化。
     *
     * @param handleStrategy
     */
    protected void init(VacancyHostHandleStrategy handleStrategy) {
        Objects.requireNonNull(handleStrategy);

        Courier tempCourier;
        switch (handleStrategy) {
            case THROW_EXCEPTION:
                tempCourier = new ThrowableVacancyCourier();
                break;
            case RESPONSE_SERVICE_UNAVAILABLE_STATE:
                tempCourier = new StatefulVacancyCourier(VacancyHostHandleStrategy.RESPONSE_SERVICE_UNAVAILABLE_STATE);
                break;
            default:
                throw new UnsupportedOperationException();
        }

        this.courier = tempCourier;
    }
}
