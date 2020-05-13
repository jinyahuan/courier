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

package cn.jinyahuan.commons.courier.supplier.chooser.round;

import cn.jinyahuan.commons.courier.supplier.CourierSupplier;
import cn.jinyahuan.commons.courier.supplier.chooser.CourierSupplierChooser;
import cn.jinyahuan.commons.courier.supplier.chooser.CourierSupplierRoundChooser;
import cn.jinyahuan.commons.courier.supplier.chooser.priority.host.OtherCourierSupplier;
import cn.jinyahuan.commons.courier.supplier.chooser.priority.host.SecondCourierSupplier;
import cn.jinyahuan.commons.courier.supplier.chooser.priority.host.SupremeCourierSupplier;
import cn.jinyahuan.commons.courier.supplier.chooser.priority.host.SupremeSecondCourierSupplier;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class CourierHostRoundChooserTest {
    private List<CourierSupplier> enableCourierHosts;
    private CourierSupplierChooser chooser;

    @Before
    public void before() {
        Set<CourierSupplier> set = new LinkedHashSet<>();
        set.add(new SecondCourierSupplier(1, "SecondCourierHost"));
        set.add(new OtherCourierSupplier(11, "OtherCourierHost"));
        set.add(new SupremeCourierSupplier(21, "SupremeCourierHost"));
        set.add(new SupremeSecondCourierSupplier(31, "SupremeSecondCourierHost"));

        enableCourierHosts = new ArrayList<>(set);
        chooser = new CourierSupplierRoundChooser(enableCourierHosts);
    }

    @Test
    public void testChoose() {
        final int size = chooser.getEnableCourierSuppliers().size();

        final int times = 666;
        for (int j = 0; j < times; j++) {
            CourierSupplier courierHost = chooser.choose(null);

            int cycleIndex = j % size;

            assertEquals(enableCourierHosts.get(cycleIndex), courierHost);
        }
    }

    @Test
    public void testGetEnableCourierHosts() {
        assertEquals(enableCourierHosts, chooser.getEnableCourierSuppliers());
    }

    @Test
    public void testGetNextIndex()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Class<CourierSupplierRoundChooser> clazz = CourierSupplierRoundChooser.class;
        Method method = clazz.getDeclaredMethod("getNextIndex", int.class);
        method.setAccessible(true);

        final int times = 666;
        int size = chooser.getEnableCourierSuppliers().size();
        for (int j = 0; j < times; j++) {
            for (int i = 0; i < size; i++) {
                int nextIndex = (int) method.invoke(chooser, size);
                assertEquals(i, nextIndex);
            }
        }
    }
}