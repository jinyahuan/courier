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

import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态的信使选择器，用于动态选择一个信使。
 * <p>
 * 可以在程序运行期间从数据中心（比如：数据库、Redis、Zookeeper等）获取一个指定的信使信息（可以是：编号、类名、对象序列化后的数据流等），
 * 然后根据获取的信使信息从对象缓存池中获取，或者进行实例化然后存入对象缓存池。
 * <pre>几种实现方式的简单介绍：
 * 1. 编号。简单高效，但项目不做实现，要对所有信使实现类对象进行编号，项目内实现可配置的实例化对象还是蛮复杂的（后期的版本可能会补上）。
 * 1.1 对所有信使实现类的所有构造器编编号，比如用纯数字1，2，3，...，编完编号后所有可能的信使对象就与编号绑定了，
 * 知道编号就知道用哪个类的哪个构造器实例化对象
 * 1.1.1 建议每个实现类预留一定量的编号，比如A实现类预留100个编号（1-99），B实现类也预留100个编号（100-199）...
 * 1.2 根据获取的信使信息从对象缓存池中获取，或者进行实例化然后存入对象缓存池
 * 1.2.1 对象缓存池的实现：最简单的就是用{@link ConcurrentHashMap}来实现，key=编号，value=信使实现类对象
 *
 * 2. 类名。获取到类名后，然后根据获取的信使信息从对象缓存池中获取，或者进行根据反射机制实例化然后存入对象缓存池。
 * 2.1 实例化对象时有个缺陷，那就是指定的信使实现类必须要有一个无参的构造器，因为要用这个构造器进行实例化。可以使用配置的方式来弥补这个缺陷，
 * 不过实现起来就相对复杂了，吃力不讨好，还不如使用第三种方案。
 * 简单设计的配置(json格式):
 * {
 * "className":"cn.xxx.XxxCourier",
 * "constructor":{
 *      "parameterTypes":["java.lang.String","java.util.Map"]
 *      "data":[
 *              {"contentType":"string","content":"hello"},
 *              {"contentType":"json","content":{"k1":"v1","k2":"v2"}}
 *          ]
 *      }
 * }
 *
 * 3. 对象序列化后的数据流。没啥好说的就是序列化/反序列化机制。
 * 3.1 序列化/反序列化机制可以用java自带的，也可以用各种json实现，还可以用protobuf等第三方方案，甚至可以自定义一套方案。
 * </pre>
 *
 * @author Yahuan Jin
 * @see AbstractDynamicCourierChooser
 * @see StaticCourierChooser
 * @see CourierChooser
 * @since 0.1
 */
public interface DynamicCourierChooser extends CourierChooser {

}
