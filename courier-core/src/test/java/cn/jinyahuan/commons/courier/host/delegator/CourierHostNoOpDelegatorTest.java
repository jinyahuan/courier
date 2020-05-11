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

package cn.jinyahuan.commons.courier.host.delegator;

import cn.jinyahuan.commons.courier.api.model.CourierRequest;
import cn.jinyahuan.commons.courier.constant.CourierRedisKeyConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class CourierHostNoOpDelegatorTest {
    private CourierHostDelegate delegator;

    @Before
    public void before() {
        delegator = new CourierHostNoOpDelegator();
    }

    @Test
    public void testAssign() {
        assertNull(delegator.assign());
    }

    @Test
    public void testAssign_key() {
        assertNull(delegator.assign((String) null));
        assertNull(delegator.assign(CourierRedisKeyConstants.DEFAULT_DELEGATOR_KEY_NAME));
    }

    @Test
    public void testAssign_request() {
        assertNull(delegator.assign((CourierRequest) null));
    }

    @Test
    public void testAssign_key_request() {
        assertNull(delegator.assign(null, null));
        assertNull(delegator.assign(CourierRedisKeyConstants.DEFAULT_DELEGATOR_KEY_NAME, null));
    }
}