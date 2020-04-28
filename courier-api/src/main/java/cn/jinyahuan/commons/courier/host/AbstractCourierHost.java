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

package cn.jinyahuan.commons.courier.host;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 抽象的信使服务商。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@ToString
@EqualsAndHashCode
public abstract class AbstractCourierHost implements CourierHost {
    /** 信使服务商的 id */
    @Getter
    @Setter
    protected Integer id;

    /** 信使服务商的名称 */
    @Getter
    @Setter
    protected String name;
}
