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
import cn.jinyahuan.commons.courier.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的信使的服务供应商选择器。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public abstract class AbstractCourierSupplierChooser implements CourierSupplierChooser {
    /**
     * 所有启用的信使服务供应商。
     */
    protected List<CourierSupplier> enableCourierSuppliers;

    public AbstractCourierSupplierChooser() {
        // 不设置成不可变的 List 的原因是：可能有人会通过 getEnableCourierSuppliers()，然后进行元素的增删改操作
        this.enableCourierSuppliers = new ArrayList<>(5);
    }

    public AbstractCourierSupplierChooser(List<CourierSupplier> enableCourierSuppliers) {
        setEnableCourierSuppliers(enableCourierSuppliers);
    }

    @Override
    public List<CourierSupplier> getEnableCourierSuppliers() {
        return enableCourierSuppliers;
    }

    @Override
    public void setEnableCourierSuppliers(List<CourierSupplier> suppliers) {
        int size = CollectionUtils.size(suppliers);
        if (size > 0) {
            this.enableCourierSuppliers = suppliers;
        }
    }
}
