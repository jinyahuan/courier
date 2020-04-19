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
import java.util.Set;

/**
 * 抽象的请求信息类。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
@ToString
@EqualsAndHashCode
public abstract class AbstractRequest implements RequestAttribute {
    /** 所有的手机号 */
    @Getter
    @Setter
    protected String[] phones;
    /** 模板编号 */
    @Getter
    @Setter
    protected String templateCode;
    /** 参数列表 */
    @Getter
    @Setter
    protected String[] params;
    /** 拓展参数列表 */
    @Deprecated // 可能会去除
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
    @Deprecated // 可能会去除
    protected void setExtendedParam(String name, String value) {
        extendedParams.put(name, value);
    }

    /**
     * 获取指定额外参数的值。
     *
     * @param name
     * @return
     */
    @Deprecated // 可能会去除
    protected String getExtendedParam(String name) {
        return extendedParams.get(name);
    }

    /**
     * 获取所有额外的参数。
     * <p>注意: 返回值只是一个引用，即修改了返回值中的对象的键与值，同时也会作用到此对象。</p>
     *
     * @return
     */
    @Deprecated // 可能会去除
    protected Set<Map.Entry<String, String>> getAllExtendedParam() {
        return extendedParams.entrySet();
    }

    /**
     * 初始化。
     */
    protected void init() {
        extendedParams = new LinkedHashMap<>();
    }
}
