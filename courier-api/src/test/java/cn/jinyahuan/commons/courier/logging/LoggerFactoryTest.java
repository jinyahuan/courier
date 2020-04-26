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

package cn.jinyahuan.commons.courier.logging;

import cn.jinyahuan.commons.courier.logging.log4j.v1.Log4jLogger;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

/**
 * todo 补全测试用例
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class LoggerFactoryTest {
    @Before
    public void before() {
    }

    @Test
    public void testConstructor() throws NoSuchFieldException, IllegalAccessException {
        LoggerFactory factory = new LoggerFactory();

        Field fieldDefaultLoggerImpl = LoggerFactory.class.getDeclaredField("DEFAULT_LOGGER_IMPL");
        fieldDefaultLoggerImpl.setAccessible(true);
        Object expected = fieldDefaultLoggerImpl.get(factory);

        assertEquals(expected, factory.getLoggerImpl());
    }

    @Test
    public void testConstructor_loggerImpl() {
        LoggerFactory factory1 = new LoggerFactory(NoOpLogger.class.getName());

        LoggerFactory factory2 = new LoggerFactory(StdOutLogger.class.getName());

        LoggerFactory factory3 = new LoggerFactory(Log4jLogger.class.getName());
    }

    @Test
    public void testGetLogger_class() {

    }

    @Test
    public void testGetLogger_className() {
    }
}