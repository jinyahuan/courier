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

package cn.jinyahuan.commons.courier.supplier;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 抽象的信使的服务供应商。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public abstract class AbstractCourierSupplier implements CourierSupplier {
    private static final int CYCLE_CRITICAL_ID = 0;
    private static final int DEFAULT_ID_GENERATOR_DELTA = 1;
    /**
     * 服务供应商的id生成器。
     */
    protected static AtomicInteger idGenerator = new AtomicInteger(1);

    /**
     * 服务供应商的唯一实例id。
     */
    protected volatile int id;

    public AbstractCourierSupplier(int id) {
        this.id = id;
    }

    public AbstractCourierSupplier() {
        this(generateId());
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractCourierSupplier that = (AbstractCourierSupplier) o;
        return this.id == that.getId() && getCourier() == that.getCourier();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getCourier());
    }

    /**
     * 生成id。当出现0值后，生成器就会抛出异常（因为id值即将轮回了）。
     *
     * @return
     */
    protected static int generateId() {
        if (!isCycleCriticalId(idGenerator.get())) {
            synchronized (idGenerator) {
                if (!isCycleCriticalId(idGenerator.get())) {
                    return idGenerator.addAndGet(DEFAULT_ID_GENERATOR_DELTA);
                }
            }
        }
        throw new RuntimeException("id generate failure, the value will cycle");
    }

    /**
     * id值是否是轮回临界值。
     *
     * @param value
     * @return
     */
    private static boolean isCycleCriticalId(int value) {
        return value == CYCLE_CRITICAL_ID;
    }
}
