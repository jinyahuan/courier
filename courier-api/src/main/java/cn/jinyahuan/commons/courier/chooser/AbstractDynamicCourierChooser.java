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
 * 抽象的动态的信使选择器。
 *
 * @author Yahuan Jin
 * @see AbstractStaticCourierChooser
 * @see DynamicCourierChooser
 * @see StaticCourierChooser
 * @see CourierChooser
 * @since 0.1
 */
public abstract class AbstractDynamicCourierChooser implements DynamicCourierChooser {
    @Override
    public Courier choose(Object key) throws ChoosingCourierFailException {
        Courier delegator = null;

        Object courierInfo = getDelegatingCourierInfo(key);
        if (Objects.nonNull(courierInfo)) {
            delegator = parseDelegator(courierInfo);

            decoratingDelegatedCourier(delegator);
        }

        return delegator;
    }

    /**
     * 获取待选择的信使的信息。
     *
     * @return maybe null
     */
    protected Object getDelegatingCourierInfo(Object key) {
        return null;
    }

    /**
     * 根据待选择的信使的信息，将其转换成实例。
     *
     * @param courierInfo 信使的信息
     * @return (may be null) 信使实例
     * @throws ChoosingCourierFailException 转换失败时会抛出此异常
     */
    protected Courier parseDelegator(Object courierInfo) throws ChoosingCourierFailException {
        return null;
    }

    /**
     * 装饰信使。
     *
     * @param courier 待装饰的信使对象
     */
    protected void decoratingDelegatedCourier(Courier courier) {}
}
