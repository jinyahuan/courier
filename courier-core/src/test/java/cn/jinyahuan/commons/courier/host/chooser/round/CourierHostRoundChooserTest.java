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

package cn.jinyahuan.commons.courier.host.chooser.round;

import cn.jinyahuan.commons.courier.host.CourierHost;
import cn.jinyahuan.commons.courier.host.chooser.CourierHostChoosable;
import cn.jinyahuan.commons.courier.host.chooser.CourierHostRoundChooser;
import cn.jinyahuan.commons.courier.host.chooser.priority.host.OtherCourierHost;
import cn.jinyahuan.commons.courier.host.chooser.priority.host.SecondCourierHost;
import cn.jinyahuan.commons.courier.host.chooser.priority.host.SupremeCourierHost;
import cn.jinyahuan.commons.courier.host.chooser.priority.host.SupremeSecondCourierHost;
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
    private List<CourierHost> enableCourierHosts;
    private CourierHostChoosable chooser;

    @Before
    public void before() {
        Set<CourierHost> set = new LinkedHashSet<>();
        set.add(new SecondCourierHost(1, "SecondCourierHost"));
        set.add(new OtherCourierHost(11, "OtherCourierHost"));
        set.add(new SupremeCourierHost(21, "SupremeCourierHost"));
        set.add(new SupremeSecondCourierHost(31, "SupremeSecondCourierHost"));

        enableCourierHosts = new ArrayList<>(set);
        chooser = new CourierHostRoundChooser(enableCourierHosts);
    }

    @Test
    public void testChoose() {
        final int size = chooser.getEnableCourierHosts().size();

        final int times = 666;
        for (int j = 0; j < times; j++) {
            CourierHost courierHost = chooser.choose(null);

            int cycleIndex = j % size;

            assertEquals(enableCourierHosts.get(cycleIndex), courierHost);
        }
    }

    @Test
    public void testGetEnableCourierHosts() {
        assertEquals(enableCourierHosts, chooser.getEnableCourierHosts());
    }

    @Test
    public void testGetNextIndex()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Class<CourierHostRoundChooser> clazz = CourierHostRoundChooser.class;
        Method method = clazz.getDeclaredMethod("getNextIndex", int.class);
        method.setAccessible(true);

        final int times = 666;
        int size = chooser.getEnableCourierHosts().size();
        for (int j = 0; j < times; j++) {
            for (int i = 0; i < size; i++) {
                int nextIndex = (int) method.invoke(chooser, size);
                assertEquals(i, nextIndex);
            }
        }
    }
}