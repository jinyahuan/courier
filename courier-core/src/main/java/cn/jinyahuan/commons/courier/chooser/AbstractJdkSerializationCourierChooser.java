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
import cn.jinyahuan.commons.courier.util.JdkSerializationUtils;

import java.io.IOException;
import java.util.Objects;

/**
 * 以序列化字符串为信使信息的抽象的动态的信使选择器。
 * <p>
 * 实现方式：从数据中心（比如：数据库、Redis、Zookeeper等）获取序列化后的字符串，然后反序列化为信使对象。
 * <p>
 * 这种实现方式要求信使对象必须实现{@link java.io.Serializable}接口。
 *
 * @author Yahuan Jin
 * @see AbstractClassNameCourierChooser
 * @see AbstractDynamicCourierChooser
 * @see DynamicCourierChooser
 * @see StaticCourierChooser
 * @see CourierChooser
 * @since 0.1
 */
public abstract class AbstractJdkSerializationCourierChooser extends AbstractDynamicCourierChooser {
    /**
     * 将待委派的信使的序列化字符串转换成实例。
     *
     * @param courierInfo 信使对象序列化后的字符串
     * @return (may be null) 信使实例
     * @throws ChoosingCourierFailException 转换失败时会抛出此异常
     */
    @Override
    protected Courier parseDelegator(Object courierInfo) throws ChoosingCourierFailException {
        Courier courier = null;
        if (Objects.nonNull(courierInfo) && courierInfo instanceof String) {
            String serializedStr = (String) courierInfo;
            try {
                courier = (Courier) JdkSerializationUtils.deserializeByString(serializedStr);
            } catch (IOException | ClassNotFoundException e) {
                throw new ChoosingCourierFailException(e);
            }
        }
        return courier;
    }
}
