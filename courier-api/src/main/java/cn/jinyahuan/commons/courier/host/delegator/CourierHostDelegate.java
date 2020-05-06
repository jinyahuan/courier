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

import cn.jinyahuan.commons.courier.api.model.RequestAttribute;
import cn.jinyahuan.commons.courier.host.CourierHost;

/**
 * 服务商委派器。
 * <p>推荐结合动态数据来进行实时的委托，比如：Redis, Zookeeper, Database等。</p>
 * <p>推荐的做法是：将{@link CourierHost}的实现类名全名存储到存储容器中，比如 redis 中，然后通过反射来实例化类。</p>
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierHostDelegate {
    /**
     * 获取委派的服务商。
     *
     * @return
     */
    CourierHost assign();

    /**
     * 获取委派的服务商。
     *
     * @param key
     * @return
     */
    CourierHost assign(String key);

    /**
     * 获取委派的服务商。
     *
     * @param request
     * @return
     */
    default CourierHost assign(RequestAttribute request) {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取委派的服务商。
     *
     * @param key
     * @param request
     * @return
     */
    default CourierHost assign(String key, RequestAttribute request) {
        throw new UnsupportedOperationException();
    }
}
