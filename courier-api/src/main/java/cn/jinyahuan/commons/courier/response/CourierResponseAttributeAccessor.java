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

package cn.jinyahuan.commons.courier.response;

/**
 * 信使响应属性的访问器。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface CourierResponseAttributeAccessor<T> {
    /**
     * 获取响应的状态码。
     *
     * @return
     */
    int getState();

    /**
     * 设置响应的状态码。
     *
     * @param state
     */
    void setState(int state);

    /**
     * 获取响应的错误编码。
     *
     * @return
     */
    String getCode();

    /**
     * 设置响应的错误编码。
     *
     * @param code
     */
    void setCode(String code);

    /**
     * 获取响应的错误信息。
     *
     * @return
     */
    String getMsg();

    /**
     * 设置响应的错误信息。
     *
     * @param msg
     */
    void setMsg(String msg);

    /**
     * 获取响应的数据。
     *
     * @return
     */
    T getResponseData();

    /**
     * 设置响应的数据。
     *
     * @param responseData
     */
    void setResponseData(T responseData);

    /**
     * 获取签名。
     *
     * @return
     */
    String getSign();

    /**
     * 设置签名。
     *
     * @param sign
     */
    void setSign(String sign);
}
