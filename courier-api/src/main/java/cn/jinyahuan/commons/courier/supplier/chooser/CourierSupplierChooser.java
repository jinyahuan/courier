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

package cn.jinyahuan.commons.courier.supplier.chooser;

import cn.jinyahuan.commons.courier.supplier.CourierSupplier;

import java.util.List;

/**
 * 信使的服务供应商选择器。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierSupplierChooser {
    /**
     * 选择一个信使服务供应商。
     *
     * @param key
     * @return
     */
    CourierSupplier choose(Object key);

    /**
     * 设置所有启用的信使服务供应商。
     *
     * @param suppliers
     */
    void setEnableCourierSuppliers(List<CourierSupplier> suppliers);

    /**
     * 获取所有启用的信使服务供应商。
     *
     * @return
     */
    List<CourierSupplier> getEnableCourierSuppliers();
}
