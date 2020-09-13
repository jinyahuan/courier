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

import cn.jinyahuan.commons.courier.Courier;

/**
 * 信使的服务供应商。
 * <pre>
 * 注意：
 * 1. 必须重写{@link Object#hashCode() hashCode}和{@link Object#equals(Object) equals}方法
 *    原因：实例维护器（cn.jinyahuan.commons.courier.supplier.CourierSupplierManager）需要
 * 2. 每个实例的id必须唯一
 * </pre>
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierSupplier {
    /**
     * 获取信使。
     *
     * @return
     */
    Courier getCourier();

    /**
     * 设置唯一标识id。
     *
     * @param id
     * @return
     */
    void setId(int id);

    /**
     * 获取唯一标识id。
     *
     * @return
     */
    int getId();

    // todo 信使服务商的行为
}
