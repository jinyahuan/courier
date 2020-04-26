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

package cn.jinyahuan.commons.courier.logging.log4j.v1;

import cn.jinyahuan.commons.courier.logging.Loggable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * todo 参考log4j做测试
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class Log4jLoggerTest {
    private static final String CLASS_NAME = Log4jLoggerTest.class.getName();
    private Loggable log;

    @Before
    public void before() {
        log = new Log4jLogger(CLASS_NAME);
    }

    @Test
    public void testDebug() {
        log.debug("debug");
    }

    @Test
    public void testFormatDebug() {
        log.debug("d%sg, %d", "ebu", 123);
    }

    @Test
    public void testInfo() {
        log.info("info");
    }

    @Test
    public void testFormatInfo() {
        log.debug("i%so, %d", "nf", 123);
    }

    @Test
    public void testWarn() {
        log.warn("warn");
    }

    @Test
    public void testFormatWarn() {
        log.debug("w%sn, %d", "ar", 123);
    }

    @Test
    public void testError() {
        log.error("error");
    }

    @Test
    public void testFormatError() {
        log.debug("e%sr, %d", "rro", 123);
    }

    @Test
    public void testIsDebugEnabled() {
        assertEquals(true, log.isDebugEnabled());
    }
}