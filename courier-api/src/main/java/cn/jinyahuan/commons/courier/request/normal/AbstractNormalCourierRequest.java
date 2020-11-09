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

import cn.jinyahuan.commons.courier.request.CourierRequestExpandedParamContainerModeEnum;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抽象的常规的信使请求信息类。
 *
 * @author Yahuan Jin
 * @see DefaultNormalCourierRequest
 * @see NormalCourierRequest
 * @since 0.1
 */
public abstract class AbstractNormalCourierRequest implements NormalCourierRequest {
    /** 手机号。 */
    protected String phone;
    /** 短信模板编号。 */
    protected String templateCode;
    /** 短信模板内容中的占位符的填充数据。 */
    protected Object[] templateFillers;
    /** 拓展参数。 */
    protected Map<String, Object> expandedParams;

    public AbstractNormalCourierRequest(CourierRequestExpandedParamContainerModeEnum expandedParamContainerMode) {
        init(expandedParamContainerMode);
    }

    public AbstractNormalCourierRequest() {
        this(CourierRequestExpandedParamContainerModeEnum.NORMAL);
    }

    // - - -

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getTemplateCode() {
        return templateCode;
    }

    @Override
    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    @Override
    public Object[] getTemplateFillers() {
        return templateFillers;
    }

    @Override
    public void setTemplateFillers(Object... templateFillers) {
        this.templateFillers = templateFillers;
    }

    @Override
    public Object setExpandedParam(String key, Object val) {
        return expandedParams.put(key, val);
    }

    @Override
    public Object getExpandedParam(String key) {
        return expandedParams.get(key);
    }

    @Override
    public Set<Map.Entry<String, Object>> getAllExpandedParamKeyAndVal() {
        return expandedParams.entrySet();
    }

    @Override
    public Object removeExpandedParam(String key) {
        return expandedParams.remove(key);
    }

    @Override
    public void removeAllExpandedParam() {
        expandedParams.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NormalCourierRequest that = (NormalCourierRequest) o;
        return Objects.equals(getPhone(), that.getPhone()) &&
                Objects.equals(getTemplateCode(), that.getTemplateCode()) &&
                Arrays.equals(getTemplateFillers(), that.getTemplateFillers()) &&
                Objects.equals(getAllExpandedParamKeyAndVal(), that.getAllExpandedParamKeyAndVal());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getPhone(), getTemplateCode(), getAllExpandedParamKeyAndVal());
        result = 31 * result + Arrays.hashCode(getTemplateFillers());
        return result;
    }

    /**
     * 初始化。
     *
     * @param expandedParamContainerMode 拓展参数的容器的模式
     */
    protected void init(CourierRequestExpandedParamContainerModeEnum expandedParamContainerMode) {
        // 不指拓展参数容器的定初始化大小，因为不一定会用到
        switch (expandedParamContainerMode) {
            case NORMAL: {
                expandedParams = new HashMap<>();
            }
            break;
            case INSERTION_ORDER: {
                expandedParams = new LinkedHashMap<>();
            }
            break;
            case NATURAL_ORDER: {
                expandedParams = new TreeMap<>();
            }
            break;
            case CONCURRENCY_NORMAL: {
                expandedParams = new ConcurrentHashMap<>();
            }
            break;
            default:
                throw new UnsupportedOperationException("Expanded param container mode not supported -> "
                        + expandedParamContainerMode);
        }
    }
}
