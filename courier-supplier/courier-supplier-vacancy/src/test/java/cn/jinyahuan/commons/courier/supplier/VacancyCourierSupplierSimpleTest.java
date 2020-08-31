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

package cn.jinyahuan.commons.courier.supplier;

import cn.jinyahuan.commons.courier.Courier;
import cn.jinyahuan.commons.courier.response.CourierResponseFactory;
import org.junit.Test;

import java.util.ServiceLoader;

import static org.junit.Assert.assertEquals;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class VacancyCourierSupplierSimpleTest {
    @Test
    public void test() {
        ServiceLoader<CourierSupplier> suppliers = ServiceLoader.load(CourierSupplier.class);

        for (final CourierSupplier supplier : suppliers) {
            final Courier courier = supplier.getCourier();

            assertEquals(
                    CourierResponseFactory.IMMUTABLE_SERVICE_UNAVAILABLE_RESPONSE,
                    courier.send(null)
            );
        }
    }
}
