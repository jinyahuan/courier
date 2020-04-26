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
import org.apache.log4j.Logger;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class Log4jLogger implements Loggable {
    private Logger log;

    public Log4jLogger(String className) {
        this.log = Logger.getLogger(className);
    }

    @Override
    public void debug(String s) {
        log.debug(s);
    }

    /**
     * @param format 注意格式，参考{@link String#format(String, Object...)}
     * @param args
     */
    @Override
    public void debug(String format, Object... args) {
        String msg = String.format(format, args);
        log.debug(msg);
    }

    @Override
    public void info(String s) {
        log.info(s);
    }

    /**
     * @param format 注意格式，参考{@link String#format(String, Object...)}
     * @param args
     */
    @Override
    public void info(String format, Object... args) {
        String msg = String.format(format, args);
        log.info(msg);
    }

    @Override
    public void warn(String s) {
        log.warn(s);
    }

    /**
     * @param format 注意格式，参考{@link String#format(String, Object...)}
     * @param args
     */
    @Override
    public void warn(String format, Object... args) {
        String msg = String.format(format, args);
        log.warn(msg);
    }

    @Override
    public void error(String s) {
        log.error(s);
    }

    /**
     * @param format 注意格式，参考{@link String#format(String, Object...)}
     * @param args
     */
    @Override
    public void error(String format, Object... args) {
        String msg = String.format(format, args);
        log.error(msg);
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }
}
