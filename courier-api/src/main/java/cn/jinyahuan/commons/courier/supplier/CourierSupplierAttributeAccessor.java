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

/**
 * 信使的服务供应商的属性访问器
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierSupplierAttributeAccessor {
    /**
     * 获取信使服务商的 id。
     *
     * @return
     */
    Integer getId();

    /**
     * 设置信使服务商的 id。
     *
     * @param id
     */
    void setId(Integer id);

    /**
     * 获取信使服务商的名称。
     *
     * @return
     */
    String getName();

    /**
     * 设置信使服务商的名称。
     *
     * @param name
     */
    void setName(String name);
}
