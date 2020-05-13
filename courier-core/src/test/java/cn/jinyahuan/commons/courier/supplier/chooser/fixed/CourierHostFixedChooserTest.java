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

package cn.jinyahuan.commons.courier.supplier.chooser.fixed;

import cn.jinyahuan.commons.courier.supplier.CourierSupplier;
import cn.jinyahuan.commons.courier.supplier.chooser.CourierSupplierChooser;
import cn.jinyahuan.commons.courier.supplier.chooser.CourierSupplierFixedChooser;
import cn.jinyahuan.commons.courier.supplier.chooser.fixed.host.OtherCourierSupplier;
import cn.jinyahuan.commons.courier.supplier.chooser.fixed.host.TestCourierSupplier;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class CourierHostFixedChooserTest {
    private static final CourierSupplier courierHost = new TestCourierSupplier(1, "test");

    private CourierSupplierChooser chooser;

    @Before
    public void before() {
        chooser = new CourierSupplierFixedChooser(courierHost);
    }

    @Test
    public void testChoose() {
        final int times = 666;
        for (int i = 0; i < times; i++) {
            assertEquals(courierHost, chooser.choose(null));
        }
    }

    @Test
    public void testGetEnableCourierHosts() {
        assertEquals(Collections.emptyList(), chooser.getEnableCourierSuppliers());
    }

    @Test
    public void testSetEnableCourierHosts() {
        chooser.setEnableCourierSuppliers(null);
        assertEquals(Collections.emptyList(), chooser.getEnableCourierSuppliers());

        chooser.setEnableCourierSuppliers(Arrays.asList(new TestCourierSupplier(), new OtherCourierSupplier()));
        assertEquals(Collections.emptyList(), chooser.getEnableCourierSuppliers());
    }
}