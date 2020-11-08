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

package cn.jinyahuan.commons.courier.response;

/**
 * 信使响应信息类。
 * <p>
 * 判断响应信息的状态可以使用{@link cn.jinyahuan.commons.courier.response.state.CourierResponseState}类的{@code isSuccess}方法。
 *
 * @param <T> 响应的数据的类型
 * @author Yahuan Jin
 * @see DefaultCourierResponse
 * @see ImmutableCourierResponse
 * @see AbstractCourierResponse
 * @see CourierResponseAttributeAccessor
 * @since 0.1
 */
public interface CourierResponse<T> extends CourierResponseAttributeAccessor<T> {

}
