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

package cn.jinyahuan.commons.courier.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * JDK 序列化/反序列化相关的工具类。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public final class JdkSerializationUtils {
    private JdkSerializationUtils() {}

    /**
     * 将对象序列化为 ISO_8859_1 字符集编码的字符串。
     *
     * @param obj 待序列化的对象
     * @return 序列化后的字符串；或者为{@code null}，如果 obj 为{@code null}
     * @throws IOException 序列化时抛出的异常
     * @see #serializeToString(Object, String)
     * @see #deserializeByString(String, String)
     * @see #deserializeByString(String)
     */
    public static String serializeToString(Object obj) throws IOException {
        return serializeToString(obj, StandardCharsets.ISO_8859_1.name());
    }

    /**
     * 将 ISO_8859_1 字符集编码的字符串反序列化为对象。
     *
     * @param str 待反序列化的字符串
     * @return 序列化后的对象；或者为{@code null}，如果 str 为{@code null}
     * @throws IOException            反序列化时抛出的异常
     * @throws ClassNotFoundException 反序列化时抛出的异常
     * @see #deserializeByString(String, String)
     * @see #serializeToString(Object, String)
     * @see #serializeToString(Object)
     */
    public static Object deserializeByString(String str) throws IOException, ClassNotFoundException {
        return deserializeByString(str, StandardCharsets.ISO_8859_1.name());
    }

    /**
     * 将对象序列化为指定字符集编码的字符串。
     *
     * @param obj     待序列化的对象
     * @param charset 字符串的编码集
     * @return 序列化后的字符串；或者为{@code null}，如果 obj 为{@code null}
     * @throws IOException 序列化时抛出的异常
     * @see #serializeToString(Object)
     * @see #deserializeByString(String, String)
     * @see #deserializeByString(String)
     */
    private static String serializeToString(Object obj, String charset) throws IOException {
        if (Objects.isNull(obj)) {
            return null;
        }
        String str;
        try (
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os)
        ) {
            oos.writeObject(obj);
            str = os.toString(charset);
        }
        return str;
    }

    /**
     * 将字符串反序列化为对象。
     *
     * @param str     待反序列化的字符串
     * @param charset 字符串的编码集
     * @return 序列化后的对象；或者为{@code null}，如果 str 为{@code null}
     * @throws IOException            反序列化时抛出的异常
     * @throws ClassNotFoundException 反序列化时抛出的异常
     * @see #deserializeByString(String)
     * @see #serializeToString(Object, String)
     * @see #serializeToString(Object)
     */
    private static Object deserializeByString(String str, String charset) throws IOException, ClassNotFoundException {
        if (Objects.isNull(str)) {
            return null;
        }
        Object obj;
        byte[] bytes = str.getBytes(charset);
        try (
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))
        ) {
            obj = ois.readObject();
        }
        return obj;
    }
}
