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
import cn.jinyahuan.commons.courier.host.vacancy.exception.VacancyCourierHostException;

/**
 * 抛异常的空缺服务商信使。即信使工作时就会抛异常。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class ThrowableVacancyCourier implements Courier {
    @Override
    public ResponseAttribute send(RequestAttribute requestAttribute) {
        return handle(requestAttribute);
    }

    @Override
    public ResponseAttribute query(RequestAttribute requestAttribute) {
        return handle(requestAttribute);
    }

    @Override
    public ResponseAttribute sendAsync(RequestAttribute requestAttribute) {
        return handle(requestAttribute);
    }

    @Override
    public ResponseAttribute sendBatch(RequestAttribute requestAttribute) {
        return handle(requestAttribute);
    }

    @Override
    public ResponseAttribute queryBatch(RequestAttribute requestAttribute) {
        return handle(requestAttribute);
    }

    @Override
    public ResponseAttribute sendScheduled(RequestAttribute requestAttribute) {
        return handle(requestAttribute);
    }

    @Override
    public ResponseAttribute queryScheduled(RequestAttribute requestAttribute) {
        return handle(requestAttribute);
    }

    /**
     * 默认的处理逻辑是抛出{@link VacancyCourierHostException}异常。
     *
     * @param requestAttribute
     * @return
     */
    protected ResponseAttribute handle(RequestAttribute requestAttribute) {
        throw new VacancyCourierHostException();
    }
}
