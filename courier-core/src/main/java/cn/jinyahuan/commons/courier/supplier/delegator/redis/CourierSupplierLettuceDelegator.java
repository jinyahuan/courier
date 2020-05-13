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

import cn.jinyahuan.commons.courier.supplier.delegator.AbstractCourierSupplierDelegator;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.ObjectPool;

/**
 * 用 lettuce 实现的 Redis 版服务商委派器。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@Slf4j
public class CourierSupplierLettuceDelegator extends AbstractCourierSupplierDelegator {
    private static final long serialVersionUID = 1L;

    protected ObjectPool<StatefulRedisConnection<String, String>> objectPool;

    public CourierSupplierLettuceDelegator(ObjectPool<StatefulRedisConnection<String, String>> objectPool) {
        super();
        this.objectPool = objectPool;
    }

    public CourierSupplierLettuceDelegator(String delegatorKeyName, ObjectPool objectPool) {
        super(delegatorKeyName);
        this.objectPool = objectPool;
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
            StatefulRedisConnection<String, String> redisClient = getRedisClientInstance();
            RedisCommands<String, String> commands = redisClient.sync();
            className = commands.get(key);
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
    protected StatefulRedisConnection<String, String> getRedisClientInstance() {
        StatefulRedisConnection<String, String> connection = null;

        try {
            connection = objectPool.borrowObject();
        } catch (Exception e) {
            log.error("Get redis client instance failure.", e);
        }

        return connection;
    }
}
