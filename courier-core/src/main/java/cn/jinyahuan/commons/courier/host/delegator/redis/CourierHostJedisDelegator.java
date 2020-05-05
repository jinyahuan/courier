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
import cn.jinyahuan.commons.courier.util.StringUtils;
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
public class CourierHostJedisDelegator implements CourierHostDelegate {
    /**
     * 存放委派器类名的 key 名。
     */
    protected String delegatorKeyName = CourierRedisKeyConstants.DEFAULT_DELEGATOR_KEY_NAME;

    protected Pool<Jedis> jedisPool;

    public CourierHostJedisDelegator(Pool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * @param key
     * @return maybe null
     */
    @Override
    public CourierHost assign(String key) {
        CourierHost delegator = null;

        String delegatorValue = getDelegatorValue();
        if (StringUtils.isNotEmpty(delegatorValue)) {
            delegator = parseDelegator(delegatorValue);
        }

        return delegator;
    }

    /**
     * 获取一个 redis client 实例。
     *
     * @return
     */
    protected Jedis getRedisClientInstance() {
        return jedisPool.getResource();
    }

    /**
     * 获取存放委派器类的值。
     *
     * @return maybe null
     */
    protected String getDelegatorValue() {
        Jedis redisClient = getRedisClientInstance();
        String value = redisClient.get(delegatorKeyName);
        redisClient.close();
        return value;
    }

    /**
     * 初始化委派的信使服务商。
     *
     * @param delegator
     */
    protected void initDelegator(CourierHost delegator) {}

    /**
     * 将信使服务商的类名转换成实例。
     *
     * @param className
     * @return maybe null
     */
    protected CourierHost parseDelegator(String className) {
        CourierHost delegator = null;

        try {
            Class clazz = Class.forName(className);
            // todo 可能有的服务商不提供默认的构造器
            delegator = (CourierHost) clazz.newInstance();

            initDelegator(delegator);

        } catch (Exception e) {
            log.warn("Case Delegator class failure: className={}", className, e);
        }

        return delegator;
    }
}
