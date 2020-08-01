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

package cn.jinyahuan.commons.courier.request;

import cn.jinyahuan.commons.courier.util.ArrayUtils;

import java.util.Objects;

/**
 * 信使请求属性的访问器。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierRequestAttributeAccessor {
    /**
     * 获取所有的手机号。
     *
     * @return
     */
    String[] getPhones();

    /**
     * 设置所有的手机号。
     *
     * @param phones
     */
    void setPhones(String[] phones);

    /**
     * 获取模板编号。
     *
     * @return
     */
    String getTemplateCode();

    /**
     * 设置模板编号。
     *
     * @param templateCode
     */
    void setTemplateCode(String templateCode);

    /**
     * 获取所有的参数。
     *
     * @return
     */
    String[] getParams();

    /**
     * 设置所有的参数。
     *
     * @param params
     */
    void setParams(String[] params);

    // - - -

    /**
     * 获取第一个手机号。
     *
     * @return
     */
    default String getPhone() {
        String[] phones = getPhones();
        if (ArrayUtils.isNotEmpty(phones)) {
            return phones[0];
        }
        return null;
    }

    /**
     * 设置一个手机号。
     *
     * @param phone
     */
    default void setPhone(String phone) {
        setPhones(new String[]{phone});
    }
}
