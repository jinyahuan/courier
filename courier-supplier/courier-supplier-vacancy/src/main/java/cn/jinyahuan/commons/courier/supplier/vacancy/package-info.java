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

/**
 * 空缺的信使服务提供商 的相关功能实现。
 *
 * <pre>信使：
 * 1. {@link cn.jinyahuan.commons.courier.supplier.vacancy.VacancyCourierFactory VacancyCourierFactory}提供了几种默认的 空缺信使 实现
 * 2. 通过{@link cn.jinyahuan.commons.courier.supplier.vacancy.VacancyCourierFactory#newUnifiedOperationVacancyCourier(java.util.concurrent.Callable) VacancyCourierFactory.newUnifiedOperationVacancyCourier}方法来实例化一个自定义执行逻辑的 空缺信使
 * 3. 通过继承{@link cn.jinyahuan.commons.courier.supplier.vacancy.VacancyCourierFactory.VacancyCourierAdapter VacancyCourierAdapter}类，然后根据需要重写逻辑
 * 4. 实现{@link cn.jinyahuan.commons.courier.supplier.vacancy.VacancyCourier}接口，并重写全部逻辑
 * </pre>
 *
 * <pre>信使服务提供商：
 * 1. {@link cn.jinyahuan.commons.courier.supplier.vacancy.VacancyCourierSupplier VacancyCourierSupplier}
 * </pre>
 *
 * <pre>请求信息 model：
 * 1. 默认不提供
 * </pre>
 *
 * <pre>响应信息 model：
 * 1. 默认不提供
 * </pre>
 *
 * <pre>其他组件：
 * 1. 默认不提供
 * </pre>
 *
 * @since 0.1
 */
package cn.jinyahuan.commons.courier.supplier.vacancy;