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

package cn.jinyahuan.commons.courier.listener;

import java.util.EventListener;

/**
 * 相关事件的监听器。
 * <p>例如：委托信使服务商、信使执行出异常、执行回调等。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierEventListener extends EventListener {

}
