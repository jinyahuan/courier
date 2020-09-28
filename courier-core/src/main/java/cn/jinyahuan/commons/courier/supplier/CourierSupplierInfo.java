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

import lombok.*;

import java.time.Instant;

/**
 * {@link CourierSupplier}的包装类。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourierSupplierInfo {
    /**
     * 工厂名，该工厂是{@link #courierSupplier}的创建者。
     */
    private String factoryName;

    /**
     * 信使服务商。
     */
    private CourierSupplier courierSupplier;

    /**
     * 当前对象的创建时间。
     */
    private Instant createTime;
}