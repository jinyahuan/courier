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

package cn.jinyahuan.commons.courier.chooser;

/**
 * 静态的信使选择器，用于选择一个信使。
 * 与{@link DynamicCourierChooser 动态的信使选择器}不同的是，静态的信使选择器的规则在程序运行时就已经固定，不能再进行动态更改了。
 * <pre>几种实现方式的简单介绍：
 * 1. 固定返回某个信使。
 *
 * 2. 轮询返回已注册的信使。
 *
 * 3. 随机返回已注册的信使。
 *
 * 4. 从已注册的信使中获取优先级最高的。
 *
 * 5. 手动指定。这种方式与第1种很像，不同的是这种方式是在选择信使时才动态指定一个信使，而第一种方式是一直是固定的某个信使。
 * </pre>
 *
 * @author Yahuan Jin
 * @see AbstractStaticCourierChooser
 * @see DynamicCourierChooser
 * @see CourierChooser
 * @since 0.1
 */
public interface StaticCourierChooser extends CourierChooser {

}
