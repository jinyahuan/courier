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

package cn.jinyahuan.commons.courier.supplier.chooser.priority;

import cn.jinyahuan.commons.courier.supplier.CourierSupplier;
import cn.jinyahuan.commons.courier.supplier.chooser.CourierSupplierChooser;
import cn.jinyahuan.commons.courier.supplier.chooser.CourierSupplierPriorityChooser;
import cn.jinyahuan.commons.courier.supplier.chooser.priority.host.OtherCourierSupplier;
import cn.jinyahuan.commons.courier.supplier.chooser.priority.host.SecondCourierSupplier;
import cn.jinyahuan.commons.courier.supplier.chooser.priority.host.SupremeCourierSupplier;
import cn.jinyahuan.commons.courier.supplier.chooser.priority.host.SupremeSecondCourierSupplier;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class CourierSupplierPriorityChooserTest {
    private static final CourierSupplier supremeCourierHost = new SupremeCourierSupplier(21, "21");

    @Test
    public void testChoose() {
        String supremeCourierHostClassName = SupremeCourierSupplier.class.getName();
        String SupremeSecondCourierHostClassName = SupremeSecondCourierSupplier.class.getName();
        assertEquals(true, supremeCourierHostClassName.compareTo(SupremeSecondCourierHostClassName) < 1);

        // - - -

        Set<CourierSupplier> set = new LinkedHashSet<>();
        set.add(new SecondCourierSupplier(1, "SecondCourierHost"));
        set.add(new OtherCourierSupplier(11, "OtherCourierHost"));
        set.add(supremeCourierHost);
        set.add(new SupremeSecondCourierSupplier(31, "SupremeSecondCourierHost"));

        final CourierSupplierChooser chooser = new CourierSupplierPriorityChooser(new ArrayList<>(set));

        final int times = 666;
        for (int i = 0; i < times; i++) {
            assertEquals(supremeCourierHost, chooser.choose(null));
        }
    }

    @Test
    public void testSetEnableCourierHosts() {
        final CourierSupplierChooser chooser = new CourierSupplierPriorityChooser();
        assertEquals(0, chooser.getEnableCourierSuppliers().size());

        chooser.setEnableCourierSuppliers(null);
        assertEquals(0, chooser.getEnableCourierSuppliers().size());

        // - - -

        final List<CourierSupplier> singleCourierHosts = Arrays.asList(
                new OtherCourierSupplier(11, "OtherCourierHost")
        );
        chooser.setEnableCourierSuppliers(singleCourierHosts);
        assertEquals(singleCourierHosts, chooser.getEnableCourierSuppliers());

        // - - -

        final CourierSupplier secondCourierHost = new SecondCourierSupplier(1, "1");
        final CourierSupplier supremeSecondCourierHost = new SupremeSecondCourierSupplier(31, "31");
        final CourierSupplier courierHost = new OtherCourierSupplier(11, "11");
        final List<CourierSupplier> multiCourierHosts = Arrays.asList(
                secondCourierHost,
                supremeSecondCourierHost,
                supremeCourierHost,
                courierHost
        );
        final List<CourierSupplier> expectedCourierHosts = Arrays.asList(
                supremeCourierHost,
                supremeSecondCourierHost,
                secondCourierHost,
                courierHost
        );
        chooser.setEnableCourierSuppliers(multiCourierHosts);
        assertEquals(expectedCourierHosts, chooser.getEnableCourierSuppliers());
    }
}