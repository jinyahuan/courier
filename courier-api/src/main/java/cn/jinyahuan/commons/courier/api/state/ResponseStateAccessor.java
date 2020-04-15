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

package cn.jinyahuan.commons.courier.api.state;

/**
 * 响应状态的属性访问器。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public interface ResponseStateAccessor {
    /**
     * 获取响应的状态码。
     *
     * @return
     */
    int getState();

    /**
     * 获取响应的错误编码。
     *
     * @return
     */
    String getCode();

    /**
     * 获取响应的错误信息。
     *
     * @return
     */
    String getMsg();
}
