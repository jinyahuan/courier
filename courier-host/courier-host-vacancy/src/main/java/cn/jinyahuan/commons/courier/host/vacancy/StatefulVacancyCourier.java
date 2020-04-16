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
import cn.jinyahuan.commons.courier.api.model.RequestAttribute;
import cn.jinyahuan.commons.courier.api.model.ResponseAttribute;
import cn.jinyahuan.commons.courier.api.state.ServiceUnavailableResponse;

import java.util.Objects;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class StatefulVacancyCourier implements Courier {
    private VacancyHostHandleStrategy handleStrategy;

    public StatefulVacancyCourier(VacancyHostHandleStrategy handleStrategy) {
        this.handleStrategy = Objects.requireNonNull(handleStrategy);
    }

    @Override
    public ResponseAttribute send(RequestAttribute requestAttribute) {
        ResponseAttribute responseAttribute = getResponseInstanceByHandleStrategy(handleStrategy);
        handle(requestAttribute, responseAttribute);
        return responseAttribute;
    }

    @Override
    public ResponseAttribute query(RequestAttribute requestAttribute) {
        ResponseAttribute responseAttribute = getResponseInstanceByHandleStrategy(handleStrategy);
        handle(requestAttribute, responseAttribute);
        return responseAttribute;
    }

    @Override
    public ResponseAttribute sendAsync(RequestAttribute requestAttribute) {
        ResponseAttribute responseAttribute = getResponseInstanceByHandleStrategy(handleStrategy);
        handle(requestAttribute, responseAttribute);
        return responseAttribute;
    }

    @Override
    public ResponseAttribute sendBatch(RequestAttribute requestAttribute) {
        ResponseAttribute responseAttribute = getResponseInstanceByHandleStrategy(handleStrategy);
        handle(requestAttribute, responseAttribute);
        return responseAttribute;
    }

    @Override
    public ResponseAttribute queryBatch(RequestAttribute requestAttribute) {
        ResponseAttribute responseAttribute = getResponseInstanceByHandleStrategy(handleStrategy);
        handle(requestAttribute, responseAttribute);
        return responseAttribute;
    }

    @Override
    public ResponseAttribute sendScheduled(RequestAttribute requestAttribute) {
        ResponseAttribute responseAttribute = getResponseInstanceByHandleStrategy(handleStrategy);
        handle(requestAttribute, responseAttribute);
        return responseAttribute;
    }

    @Override
    public ResponseAttribute queryScheduled(RequestAttribute requestAttribute) {
        ResponseAttribute responseAttribute = getResponseInstanceByHandleStrategy(handleStrategy);
        handle(requestAttribute, responseAttribute);
        return responseAttribute;
    }

    /**
     * 通过处理策略来获取对象的响应标准模板实例。
     *
     * @param handleStrategy
     * @return
     */
    protected ResponseAttribute getResponseInstanceByHandleStrategy(VacancyHostHandleStrategy handleStrategy) {
        ResponseAttribute response;

        switch (handleStrategy) {
            case RESPONSE_SERVICE_UNAVAILABLE_STATE:
                response = new ServiceUnavailableResponse();
                break;
            default:
                throw new UnsupportedOperationException();
        }

        return response;
    }

    /**
     * 默认不进行处理，由子类进行实现。
     *
     * @param requestAttribute
     * @return
     */
    protected void handle(RequestAttribute requestAttribute, ResponseAttribute responseAttribute) {
        // 由子类进行实现
    }
}
