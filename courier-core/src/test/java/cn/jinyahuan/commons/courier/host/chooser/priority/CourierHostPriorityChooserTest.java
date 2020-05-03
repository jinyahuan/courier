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

package cn.jinyahuan.commons.courier.host.chooser.priority;

import cn.jinyahuan.commons.courier.host.CourierHost;
import cn.jinyahuan.commons.courier.host.chooser.CourierHostChoosable;
import cn.jinyahuan.commons.courier.host.chooser.CourierHostPriorityChooser;
import cn.jinyahuan.commons.courier.host.chooser.priority.host.OtherCourierHost;
import cn.jinyahuan.commons.courier.host.chooser.priority.host.SecondCourierHost;
import cn.jinyahuan.commons.courier.host.chooser.priority.host.SupremeCourierHost;
import cn.jinyahuan.commons.courier.host.chooser.priority.host.SupremeSecondCourierHost;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class CourierHostPriorityChooserTest {
    private static final CourierHost supremeCourierHost = new SupremeCourierHost(21, "21");

    @Test
    public void testChoose() {
        String supremeCourierHostClassName = SupremeCourierHost.class.getName();
        String SupremeSecondCourierHostClassName = SupremeSecondCourierHost.class.getName();
        assertEquals(true, supremeCourierHostClassName.compareTo(SupremeSecondCourierHostClassName) < 1);

        // - - -

        Set<CourierHost> set = new LinkedHashSet<>();
        set.add(new SecondCourierHost(1, "SecondCourierHost"));
        set.add(new OtherCourierHost(11, "OtherCourierHost"));
        set.add(supremeCourierHost);
        set.add(new SupremeSecondCourierHost(31, "SupremeSecondCourierHost"));

        final CourierHostChoosable chooser = new CourierHostPriorityChooser(new ArrayList<>(set));

        final int times = 666;
        for (int i = 0; i < times; i++) {
            assertEquals(supremeCourierHost, chooser.choose(null));
        }
    }

    @Test
    public void testSetEnableCourierHosts() {
        final CourierHostChoosable chooser = new CourierHostPriorityChooser();
        assertEquals(0, chooser.getEnableCourierHosts().size());

        chooser.setEnableCourierHosts(null);
        assertEquals(0, chooser.getEnableCourierHosts().size());

        // - - -

        final List<CourierHost> singleCourierHosts = Arrays.asList(
                new OtherCourierHost(11, "OtherCourierHost")
        );
        chooser.setEnableCourierHosts(singleCourierHosts);
        assertEquals(singleCourierHosts, chooser.getEnableCourierHosts());

        // - - -

        final CourierHost secondCourierHost = new SecondCourierHost(1, "1");
        final CourierHost supremeSecondCourierHost = new SupremeSecondCourierHost(31, "31");
        final CourierHost courierHost = new OtherCourierHost(11, "11");
        final List<CourierHost> multiCourierHosts = Arrays.asList(
                secondCourierHost,
                supremeSecondCourierHost,
                supremeCourierHost,
                courierHost
        );
        final List<CourierHost> expectedCourierHosts = Arrays.asList(
                supremeCourierHost,
                supremeSecondCourierHost,
                secondCourierHost,
                courierHost
        );
        chooser.setEnableCourierHosts(multiCourierHosts);
        assertEquals(expectedCourierHosts, chooser.getEnableCourierHosts());
    }
}