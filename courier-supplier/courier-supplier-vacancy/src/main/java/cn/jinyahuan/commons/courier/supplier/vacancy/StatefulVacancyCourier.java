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

import cn.jinyahuan.commons.courier.Courier;
import cn.jinyahuan.commons.courier.request.CourierRequest;
import cn.jinyahuan.commons.courier.response.CourierResponse;
import cn.jinyahuan.commons.courier.response.CourierResponseFactory;

import java.util.Objects;

/**
 * 状态化的空缺服务商信使。即信使工作时会响应某种状态的信息。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class StatefulVacancyCourier implements Courier {
    private VacancyHostHandleStrategy handleStrategy;

    public StatefulVacancyCourier(VacancyHostHandleStrategy handleStrategy) {
        this.handleStrategy = Objects.requireNonNull(handleStrategy);
    }

    @Override
    public CourierResponse send(CourierRequest request) {
        CourierResponse response = getResponseInstanceByHandleStrategy(handleStrategy);
        handle(request, response);
        return response;
    }

    @Override
    public CourierResponse query(CourierRequest request) {
        CourierResponse response = getResponseInstanceByHandleStrategy(handleStrategy);
        handle(request, response);
        return response;
    }

    @Override
    public CourierResponse sendAsync(CourierRequest request) {
        CourierResponse response = getResponseInstanceByHandleStrategy(handleStrategy);
        handle(request, response);
        return response;
    }

    @Override
    public CourierResponse sendBatch(CourierRequest request) {
        CourierResponse response = getResponseInstanceByHandleStrategy(handleStrategy);
        handle(request, response);
        return response;
    }

    @Override
    public CourierResponse queryBatch(CourierRequest request) {
        CourierResponse response = getResponseInstanceByHandleStrategy(handleStrategy);
        handle(request, response);
        return response;
    }

    @Override
    public CourierResponse sendScheduled(CourierRequest request) {
        CourierResponse response = getResponseInstanceByHandleStrategy(handleStrategy);
        handle(request, response);
        return response;
    }

    @Override
    public CourierResponse queryScheduled(CourierRequest request) {
        CourierResponse response = getResponseInstanceByHandleStrategy(handleStrategy);
        handle(request, response);
        return response;
    }

    /**
     * 通过处理策略来获取对象的响应标准模板实例。
     *
     * @param handleStrategy
     * @return
     */
    protected CourierResponse getResponseInstanceByHandleStrategy(VacancyHostHandleStrategy handleStrategy) {
        CourierResponse response;

        switch (handleStrategy) {
            case RESPONSE_SERVICE_UNAVAILABLE_STATE:
                response = CourierResponseFactory.IMMUTABLE_SERVICE_UNAVAILABLE_RESPONSE;
                break;
            default:
                throw new UnsupportedOperationException();
        }

        return response;
    }

    /**
     * 默认不进行处理，由子类进行实现。
     *
     * @param request
     * @param response
     * @return
     */
    protected void handle(CourierRequest request, CourierResponse response) {
        // 由子类进行实现
    }
}
