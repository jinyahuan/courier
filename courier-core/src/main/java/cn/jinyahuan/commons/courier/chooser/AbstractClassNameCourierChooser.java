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

import cn.jinyahuan.commons.courier.Courier;

import java.util.Objects;

/**
 * 以类名为信使信息的抽象的动态的信使选择器。
 * <p>
 * 实现方式：从数据中心（比如：数据库、Redis、Zookeeper等）获取一个指定的信使实现类类名，然后通过反射机制实例化。
 * 这种实现方式虽然简单易用，但是有个缺陷，那就是指定的信使实现类必须要有一个无参的构造器，且用这个构造器进行实例化，
 * 为啥这么搞？因为程序不知道该用哪个构造器实例化呀。
 *
 * @author Yahuan Jin
 * @see AbstractJdkSerializationCourierChooser
 * @see AbstractDynamicCourierChooser
 * @see DynamicCourierChooser
 * @see StaticCourierChooser
 * @see CourierChooser
 * @since 0.1
 */
public abstract class AbstractClassNameCourierChooser extends AbstractDynamicCourierChooser {
    /**
     * 将待委派的信使的类名转换成实例。
     *
     * @param courierInfo 信使的类名字符串
     * @return (may be null) 信使实例
     * @throws ChoosingCourierFailException 转换失败时会抛出此异常
     */
    @Override
    protected Courier parseDelegator(Object courierInfo) throws ChoosingCourierFailException {
        Courier courier = null;
        if (Objects.nonNull(courierInfo) && courierInfo instanceof String) {
            String className = (String) courierInfo;
            try {
                Class clazz = Class.forName(className);

                courier = (Courier) clazz.newInstance();
            } catch (Exception e) {
                throw new ChoosingCourierFailException(e);
            }
        }
        return courier;
    }
}
