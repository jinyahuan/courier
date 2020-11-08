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
 * 信使响应信息的属性访问器。
 *
 * @param <T> 响应的数据的类型
 * @author Yahuan Jin
 * @see CourierResponse
 * @since 0.1
 */
public interface CourierResponseAttributeAccessor<T> {
    /**
     * 获取响应的状态码。
     *
     * @return 响应的状态码
     */
    int getState();

    /**
     * 设置响应的状态码。
     *
     * @param state 响应的状态码
     */
    void setState(int state);

    /**
     * 获取响应的错误编码。
     *
     * @return 响应的错误编码
     */
    String getCode();

    /**
     * 设置响应的错误编码。
     *
     * @param code 响应的错误编码
     */
    void setCode(String code);

    /**
     * 获取响应的错误信息。
     *
     * @return 响应的错误信息
     */
    String getMsg();

    /**
     * 设置响应的错误信息。
     *
     * @param msg 响应的错误信息
     */
    void setMsg(String msg);

    /**
     * 获取响应的数据。
     *
     * @return 响应的数据
     */
    T getResponseData();

    /**
     * 设置响应的数据。
     *
     * @param responseData 响应的数据
     */
    void setResponseData(T responseData);

    /**
     * 获取签名。
     *
     * @return 签名
     */
    String getSign();

    /**
     * 设置签名。
     *
     * @param sign 签名
     */
    void setSign(String sign);
}
