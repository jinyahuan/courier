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
import cn.jinyahuan.commons.courier.host.vacancy.exception.VacancyCourierHostException;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Yahuan Jin
 * @see ThrowableVacancyCourierTest
 * @see VacancyCourier_DefaultStrategy_Test
 * @since 0.1
 */
public class VacancyCourier_ThrowException_Test {
    private static final VacancyHostHandleStrategy STRATEGY = VacancyHostHandleStrategy.THROW_EXCEPTION;

    private Courier courier;

    @Before
    public void before() {
        courier = new VacancyCourier(STRATEGY);
    }

    @Test(expected = VacancyCourierHostException.class)
    public void testSend() {
        courier.send(null);
    }

    @Test(expected = VacancyCourierHostException.class)
    public void testQuery() {
        courier.query(null);
    }

    @Test(expected = VacancyCourierHostException.class)
    public void testSendAsync() {
        courier.sendAsync(null);
    }

    @Test(expected = VacancyCourierHostException.class)
    public void testSendBatch() {
        courier.sendBatch(null);
    }

    @Test(expected = VacancyCourierHostException.class)
    public void testQueryBatch() {
        courier.queryBatch(null);
    }

    @Test(expected = VacancyCourierHostException.class)
    public void testSendScheduled() {
        courier.sendScheduled(null);
    }

    @Test(expected = VacancyCourierHostException.class)
    public void testQueryScheduled() {
        courier.queryScheduled(null);
    }
}