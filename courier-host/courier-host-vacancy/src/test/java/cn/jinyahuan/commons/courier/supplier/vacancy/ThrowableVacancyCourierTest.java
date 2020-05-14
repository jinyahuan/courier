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
import org.junit.Before;
import org.junit.Test;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class ThrowableVacancyCourierTest {
    private Courier courier;

    @Before
    public void before() {
        courier = new ThrowableVacancyCourier();
    }

    @Test(expected = VacancyCourierSupplierException.class)
    public void testSend() {
        courier.send(null);
    }

    @Test(expected = VacancyCourierSupplierException.class)
    public void testQuery() {
        courier.query(null);
    }

    @Test(expected = VacancyCourierSupplierException.class)
    public void testSendAsync() {
        courier.sendAsync(null);
    }

    @Test(expected = VacancyCourierSupplierException.class)
    public void testSendBatch() {
        courier.sendBatch(null);
    }

    @Test(expected = VacancyCourierSupplierException.class)
    public void testQueryBatch() {
        courier.queryBatch(null);
    }

    @Test(expected = VacancyCourierSupplierException.class)
    public void testSendScheduled() {
        courier.sendScheduled(null);
    }

    @Test(expected = VacancyCourierSupplierException.class)
    public void testQueryScheduled() {
        courier.queryScheduled(null);
    }
}