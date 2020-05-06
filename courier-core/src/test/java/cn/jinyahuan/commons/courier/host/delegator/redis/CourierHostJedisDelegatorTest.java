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

package cn.jinyahuan.commons.courier.host.delegator.redis;

import cn.jinyahuan.commons.courier.constant.CourierRedisKeyConstants;
import cn.jinyahuan.commons.courier.host.CourierHost;
import cn.jinyahuan.commons.courier.host.delegator.CourierHostDelegate;
import cn.jinyahuan.commons.courier.host.delegator.redis.host.OtherCourierHost;
import cn.jinyahuan.commons.courier.host.delegator.redis.host.TestCourierHost;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.util.Pool;

import static org.junit.Assert.*;

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
    private CourierHostDelegate delegator;

    @Before
    public void before() {
        jedisPool = new JedisPool(new JedisPoolConfig(), IP, PORT, Protocol.DEFAULT_TIMEOUT, AUTH);
        delegator = new CourierHostJedisDelegator(jedisPool);
    }

    @After
    public void after() {
        jedisPool.destroy();
    }

    @Test
    public void testAssign() {
        final String key = CourierRedisKeyConstants.DEFAULT_DELEGATOR_KEY_NAME;
        String className = String.class.getName();

        jedisPool.getResource().del(key);

        CourierHost host = delegator.assign();
        assertNull(host);

        jedisPool.getResource().set(key, "");
        host = delegator.assign();
        assertNull(host);

        jedisPool.getResource().set(key, className);
        host = delegator.assign();
        assertNull(host);

        className = TestCourierHost.class.getName();
        jedisPool.getResource().set(key, className);
        host = delegator.assign();
        assertTrue(host instanceof TestCourierHost);

        className = OtherCourierHost.class.getName();
        jedisPool.getResource().set(key, className);
        host = delegator.assign();
        assertTrue(host instanceof OtherCourierHost);

        jedisPool.getResource().del(key);
    }
}