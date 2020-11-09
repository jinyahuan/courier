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

package cn.jinyahuan.commons.courier.request.normal;

import cn.jinyahuan.commons.courier.request.CourierRequest;

import java.util.Map;
import java.util.Set;

/**
 * 常规的信使请求信息类。即发送单个短信时的请求信息。
 *
 * @author Yahuan Jin
 * @see DefaultNormalCourierRequest
 * @see AbstractNormalCourierRequest
 * @since 0.1
 */
public interface NormalCourierRequest extends CourierRequest {
    /**
     * 获取手机号。
     *
     * @return 手机号
     */
    String getPhone();

    /**
     * 设置手机号。
     *
     * @param phone 手机号
     */
    void setPhone(String phone);

    /**
     * 获取短信模板编号。
     *
     * @return 短信模板编号
     */
    String getTemplateCode();

    /**
     * 设置短信模板编号。
     *
     * @param templateCode 短信模板编号
     */
    void setTemplateCode(String templateCode);

    /**
     * 获取短信模板内容中的占位符的填充数据。
     *
     * @return 短信模板内容中的占位符的填充数据
     */
    Object[] getTemplateFillers();

    /**
     * 设置短信模板内容中的占位符的填充数据。
     *
     * @param templateFillers 短信模板内容中的占位符的填充数据
     */
    void setTemplateFillers(Object... templateFillers);

    /**
     * 设置一个拓展参数。
     *
     * @param key 拓展参数的名称
     * @param val 拓展参数的值
     * @return {@code null}, 如果没有设置过该拓展参数; 否则为原先的拓展参数的值
     */
    Object setExpandedParam(String key, Object val);

    /**
     * 获取指定拓展参数的值。
     *
     * @param key 拓展参数的名称
     * @return 指定拓展参数的值
     */
    Object getExpandedParam(String key);

    /**
     * 获取所有指定参数的名称和值。
     *
     * @return 所有指定参数的名称和值（如果没有拓展参数，则返回一个empty集合）
     */
    Set<Map.Entry<String, Object>> getAllExpandedParamKeyAndVal();

    /**
     * 清除指定的拓展参数。
     *
     * @param key 拓展参数的名称
     * @return {@code null}, 如果没有设置过该拓展参数; 否则为拓展参数的值
     */
    Object removeExpandedParam(String key);

    /**
     * 清除所有拓展参数。
     */
    void removeAllExpandedParam();
}
