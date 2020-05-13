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

package cn.jinyahuan.commons.courier.supplier.chooser.random;

import cn.jinyahuan.commons.courier.supplier.CourierSupplier;
import cn.jinyahuan.commons.courier.supplier.chooser.CourierSupplierChooser;
import cn.jinyahuan.commons.courier.supplier.chooser.CourierSupplierRandomChooser;
import cn.jinyahuan.commons.courier.supplier.chooser.random.host.OtherCourierSupplier;
import cn.jinyahuan.commons.courier.supplier.chooser.random.host.SecondCourierSupplier;
import cn.jinyahuan.commons.courier.supplier.chooser.random.host.SupremeCourierSupplier;
import cn.jinyahuan.commons.courier.supplier.chooser.random.host.SupremeSecondCourierSupplier;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class CourierHostRandomChooserTest {
    private static final boolean PRINT_TEST_STAT = true;

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
        chooser = new CourierSupplierRandomChooser(enableCourierHosts);
    }

    @Test
    public void testChoose() {
        // todo CourierHostRandomChooserTest
    }

    @Test
    public void testGetEnableCourierHosts() {
        assertEquals(enableCourierHosts, chooser.getEnableCourierSuppliers());
    }

    @Test
    public void testGetNextIndex()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Class<CourierSupplierRandomChooser> clazz = CourierSupplierRandomChooser.class;
        Method method = clazz.getDeclaredMethod("getNextIndex", int.class);
        method.setAccessible(true);

        final int times = 66666;
        final int size = chooser.getEnableCourierSuppliers().size();
        Map<Integer, Integer> totalStatMap = new HashMap<>(size);
        for (int j = 0; j < times; j++) {
            Map<Integer, Integer> eachStatMap = new HashMap<>(size);

            for (int i = 0; i < size; i++) {
                int nextIndex = (int) method.invoke(chooser, size);
                eachStatMap.compute(nextIndex, (k, v) -> Objects.isNull(v) ? 1 : v + 1);
            }

            for (Map.Entry<Integer, Integer> entry : eachStatMap.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                totalStatMap.compute(key, (k, v) -> Objects.isNull(v) ? 1 : v + value);
            }
        }

        assertEquals(size, totalStatMap.size()); // 所有的元素都取到过
        final int delta = (int) Math.round(times * 0.05); // 误差值,（注意测试次数要稍微大点）
        if (PRINT_TEST_STAT) {
            printTestGetNextIndexResult(times, delta, totalStatMap);
        }

        for (Map.Entry<Integer, Integer> entry : totalStatMap.entrySet()) {
            assertEquals(times, entry.getValue(), delta);
        }
    }

    @Test
    public void testGetRandom() {
        // todo 补充测试
    }

    private void printTestGetNextIndexResult(int times, int delta, Map<Integer, Integer> statMap) {
        System.out.println("---------------");
        System.out.println("cn.jinyahuan.commons.courier.host.chooser.random" +
                ".CourierHostRandomChooserTest.testGetNextIndex: " + System.lineSeparator()
                + "total test times: " + times + System.lineSeparator()
                + "delta times: " + delta + System.lineSeparator()
                + "test result: " + statMap);
        System.out.println("---------------");
    }
}