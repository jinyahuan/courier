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

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 信使服务商{@link CourierSupplierPriority 优先级}选择器，优先选择已配置的优先级高
 * （{@link CourierSupplierPriority#value() 值}越小优先级越高）的服务商，若优先级相同，则按类名排序。
 *
 * @author Yahuan Jin
 * @see CourierSupplierPriority
 * @see CourierSupplierFixedChooser
 * @see CourierSupplierManualChooser
 * @see CourierSupplierRandomChooser
 * @see CourierSupplierRoundChooser
 * @since 0.1
 */
public class CourierSupplierPriorityChooser extends AbstractCourierSupplierChooser {
    public CourierSupplierPriorityChooser() {
        super();
    }

    public CourierSupplierPriorityChooser(List<CourierSupplier> enableCourierHosts) {
        super(enableCourierHosts);
    }

    /**
     * 获取排好序的所有信使服务商的第一个，具体排序规则查看{@link #setEnableCourierSuppliers(List)}。
     *
     * @param key 当前实现类中，此参数无效，所以可以传任何值，包括{@code null}
     * @return maybe null
     */
    @Override
    public CourierSupplier choose(Object key) {
        CourierSupplier courierHost = null;

        List<CourierSupplier> enableCourierHosts = getEnableCourierSuppliers();
        int count = enableCourierHosts.size();
        if (count > 0) {
            courierHost = enableCourierHosts.get(0);
        }

        return courierHost;
    }

    /**
     * 设置所有启用的信使服务商。并且在设置时排好序。
     * <pre>
     *     第一优先级是{@link CourierSupplierPriority#value()}的值，越小优先级越高
     *     如果同级，则第二优先级是{@link CourierSupplier}实现类的类名，根据{@link String#compareTo(String) compareTo}的值
     * </pre>
     *
     * @param suppliers
     */
    @Override
    public void setEnableCourierSuppliers(List<CourierSupplier> suppliers) {
        int size = CollectionUtils.size(suppliers);
        if (size == 1) {
            super.enableCourierSuppliers = suppliers;
        }
        else if (size > 1) {
            List<CourierSupplier> list = suppliers.stream()
                    .sorted(Comparator
                            .comparing(h -> {
                                CourierSupplierPriority annotation = h.getClass().getAnnotation(CourierSupplierPriority.class);
                                return Objects.isNull(annotation) ? Integer.MAX_VALUE : annotation.value();
                            })
                            .thenComparing(h -> h.getClass().getName())
                    )
                    .collect(Collectors.toList());
            super.enableCourierSuppliers = list;
        }
    }
}
