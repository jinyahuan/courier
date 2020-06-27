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
import cn.jinyahuan.commons.courier.response.CourierResponse;
import cn.jinyahuan.commons.courier.response.CourierResponseFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class StatefulVacancyCourier_ResponseServiceUnavailableState_Test {
    private static final VacancyHostHandleStrategy STRATEGY =
            VacancyHostHandleStrategy.RESPONSE_SERVICE_UNAVAILABLE_STATE;
    private static final CourierResponse EXPECTED_RESPONSE = CourierResponseFactory.IMMUTABLE_SERVICE_UNAVAILABLE_RESPONSE;

    private Courier courier;

    @Before
    public void before() {
        courier = new StatefulVacancyCourier(STRATEGY);
    }

    @Test(expected = NullPointerException.class)
    public void testNoneParamConstruct() {
        new StatefulVacancyCourier(null);
    }

    @Test
    public void testSend() {
        CourierResponse response = courier.send(null);
        assertEquals(EXPECTED_RESPONSE, response);
    }

    @Test
    public void testQuery() {
        CourierResponse response = courier.query(null);
        assertEquals(EXPECTED_RESPONSE, response);
    }

    @Test
    public void testSendAsync() {
        CourierResponse response = courier.sendAsync(null);
        assertEquals(EXPECTED_RESPONSE, response);
    }

    @Test
    public void testSendBatch() {
        CourierResponse response = courier.sendBatch(null);
        assertEquals(EXPECTED_RESPONSE, response);
    }

    @Test
    public void testQueryBatch() {
        CourierResponse response = courier.queryBatch(null);
        assertEquals(EXPECTED_RESPONSE, response);
    }

    @Test
    public void testSendScheduled() {
        CourierResponse response = courier.sendScheduled(null);
        assertEquals(EXPECTED_RESPONSE, response);
    }

    @Test
    public void testQueryScheduled() {
        CourierResponse response = courier.queryScheduled(null);
        assertEquals(EXPECTED_RESPONSE, response);
    }

    @Test
    public void testGetResponseInstanceByHandleStrategy() {
        CourierResponse response =
                ((StatefulVacancyCourier) courier).getResponseInstanceByHandleStrategy(STRATEGY);
        assertEquals(EXPECTED_RESPONSE, response);
    }

    @Test
    public void testHandle() {
    }
}