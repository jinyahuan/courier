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

package cn.jinyahuan.commons.courier.supplier.delegator.redis;

import cn.jinyahuan.commons.courier.supplier.CourierSupplier;
import cn.jinyahuan.commons.courier.supplier.delegator.CourierSupplierDelegator;
import cn.jinyahuan.commons.courier.supplier.delegator.redis.host.OtherCourierSupplier;
import cn.jinyahuan.commons.courier.supplier.delegator.redis.host.TestCourierSupplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.util.Pool;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
@Ignore("local redis instance, skip ...")
public class CourierHostJedisDelegatorTest {
    private static final String IP = "192.168.8.223";
    private static final int PORT = 6379;
    private static final String AUTH = "123456";

    private Pool<Jedis> jedisPool;
    private CourierSupplierDelegator delegator;

    @Before
    public void before() {
        jedisPool = new JedisPool(new JedisPoolConfig(), IP, PORT, Protocol.DEFAULT_TIMEOUT, AUTH);
        delegator = new CourierSupplierJedisDelegator(jedisPool);
    }

    @After
    public void after() {
        jedisPool.destroy();
    }

    @Test
    public void testAssign() {
        final String key = CourierSupplierDelegator.DEFAULT_DELEGATOR_KEY_NAME;
        String className = String.class.getName();

        jedisPool.getResource().del(key);

        CourierSupplier host = delegator.assign();
        assertNull(host);

        jedisPool.getResource().set(key, "");
        host = delegator.assign();
        assertNull(host);

        jedisPool.getResource().set(key, className);
        host = delegator.assign();
        assertNull(host);

        className = TestCourierSupplier.class.getName();
        jedisPool.getResource().set(key, className);
        host = delegator.assign();
        assertTrue(host instanceof TestCourierSupplier);

        className = OtherCourierSupplier.class.getName();
        jedisPool.getResource().set(key, className);
        host = delegator.assign();
        assertTrue(host instanceof OtherCourierSupplier);

        jedisPool.getResource().del(key);
    }
}