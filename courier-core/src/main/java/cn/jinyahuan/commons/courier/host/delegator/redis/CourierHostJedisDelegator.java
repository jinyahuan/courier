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

import cn.jinyahuan.commons.courier.host.delegator.AbstractCourierHostDelegator;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.util.Pool;

/**
 * 用 jedis 实现的 Redis 版服务商委派器。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@Slf4j
public class CourierHostJedisDelegator extends AbstractCourierHostDelegator {
    protected Pool<Jedis> jedisPool;

    public CourierHostJedisDelegator(Pool jedisPool) {
        super();
        this.jedisPool = jedisPool;
    }

    public CourierHostJedisDelegator(Pool jedisPool, String delegatorKeyName) {
        super(delegatorKeyName);
        this.jedisPool = jedisPool;
    }

    /**
     * 获取存放委派器类的值。
     *
     * @return maybe null
     */
    @Override
    protected String getDelegatorClassName(String key) {
        String className = null;
        try {
            Jedis redisClient = getRedisClientInstance();
            className = redisClient.get(key);
            redisClient.close();
        } catch (Exception e) {
            log.error("Get Delegator class name failure.", e);
        }
        return className;
    }

    /**
     * 获取一个 redis client 实例。
     *
     * @return
     */
    protected Jedis getRedisClientInstance() {
        return jedisPool.getResource();
    }
}
