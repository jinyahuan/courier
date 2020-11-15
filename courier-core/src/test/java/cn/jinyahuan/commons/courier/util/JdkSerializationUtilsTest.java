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

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class JdkSerializationUtilsTest {

    @Test
    public void testSerializeToString() throws IOException {
        assertNotNull(JdkSerializationUtils.serializeToString(128));
        assertNotNull(JdkSerializationUtils.serializeToString("你龍Ab1()*@"));
        assertNotNull(JdkSerializationUtils.serializeToString(new HashMap<>()));
    }

    @Test
    public void testDeserializeByString() throws IOException, ClassNotFoundException {
        final Integer num = 128;
        String numSerialization = JdkSerializationUtils.serializeToString(num);
        Object numDeserialization = JdkSerializationUtils.deserializeByString(numSerialization);
        assertEquals(num, numDeserialization);

        final String str = "你龍Ab1()*@";
        String strSerialization = JdkSerializationUtils.serializeToString(str);
        Object strDeserialization = JdkSerializationUtils.deserializeByString(strSerialization);
        assertEquals(str, strDeserialization);

        final List<Object> list = new ArrayList<>(6);
        list.add("龍");
        list.add(1);
        list.add(true);
        list.add('\u0000');
        String listSerialization = JdkSerializationUtils.serializeToString(list);
        Object listDeserialization = JdkSerializationUtils.deserializeByString(listSerialization);
        assertEquals(list, listDeserialization);

        final Map<String, Object> map = new HashMap<>(8);
        map.put("你", "你");
        map.put("龍", 1);
        map.put("A", true);
        map.put("b", '\u0000');
        String mapSerialization = JdkSerializationUtils.serializeToString(list);
        Object mapDeserialization = JdkSerializationUtils.deserializeByString(mapSerialization);
        assertEquals(list, mapDeserialization);
    }

}