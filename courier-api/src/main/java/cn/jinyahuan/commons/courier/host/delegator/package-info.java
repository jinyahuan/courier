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
 * 信使服务商的委派器。
 * <p>用于委派一个指定的服务商，比如因某种原因（已配置的服务商不稳定、非正式环境模拟短信服务 等等）临时指定一个服务商。</p>
 * <p>注意：委派器会使{@link cn.jinyahuan.commons.courier.host.chooser.CourierHostChoosable 信使服务商的选择器}失效，
 * 也就是说当启用委派器时，如果委派了一个可用的服务商时，就不会再走委派器的逻辑，即最终的服务商是委派了的可用的服务商。</p>
 */
package cn.jinyahuan.commons.courier.host.delegator;