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

package cn.jinyahuan.commons.courier.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * todo 抽象的请求信息类。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class AbstractRequest implements RequestAttribute {
    /** 手机号 */
    protected String phone;
    /** 模板编码 */
    protected String templateCode;
    /** 参数列表 */
    protected String[] params;
    /** 拓展参数列表（可能会去除的参数） */
    @Deprecated
    private Map<String, String> extendedParams;

    public AbstractRequest() {
        init();
    }

    /**
     * 添加额外的参数。
     *
     * @param name
     * @param value
     */
    protected void addExtendedParam(String name, String value) {
        extendedParams.put(name, value);
    }

    /**
     * 初始化。
     */
    protected void init() {
        extendedParams = new LinkedHashMap<>();
    }
}
