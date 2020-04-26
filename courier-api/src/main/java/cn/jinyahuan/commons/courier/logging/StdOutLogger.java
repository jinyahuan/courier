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

import java.io.PrintStream;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class StdOutLogger implements Loggable {
    /**
     * 日志级别。
     *
     * @see Loggable.Level
     */
    protected volatile int level;

    public StdOutLogger(String className) {}

    @Override
    public void debug(String s) {
        if (isDebugEnabled()) {
            System.out.println(s);
        }
    }

    /**
     * @param format 注意格式，参考{@link PrintStream#printf(String, Object...)}
     * @param args
     */
    @Override
    public void debug(String format, Object... args) {
        if (isDebugEnabled()) {
            System.out.printf(format, args);
        }
    }

    @Override
    public void info(String s) {
        if (isInfoEnabled()) {
            System.out.println(s);
        }
    }

    /**
     * @param format 注意格式，参考{@link PrintStream#printf(String, Object...)}
     * @param args
     */
    @Override
    public void info(String format, Object... args) {
        if (isInfoEnabled()) {
            System.out.printf(format, args);
        }
    }

    @Override
    public void warn(String s) {
        if (isWarnEnabled()) {
            System.err.println(s);
        }
    }

    /**
     * @param format 注意格式，参考{@link PrintStream#printf(String, Object...)}
     * @param args
     */
    @Override
    public void warn(String format, Object... args) {
        if (isWarnEnabled()) {
            System.err.printf(format, args);
        }
    }

    @Override
    public void error(String s) {
        if (isErrorEnabled()) {
            System.err.println(s);
        }
    }

    /**
     * @param format 注意格式，参考{@link PrintStream#printf(String, Object...)}
     * @param args
     */
    @Override
    public void error(String format, Object... args) {
        if (isErrorEnabled()) {
            System.err.printf(format, args);
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return level == Level.DEBUG || isAllEnabled();
    }

    protected boolean isAllEnabled() {
        return level == Level.ALL;
    }

    protected boolean isInfoEnabled() {
        return level == Level.INFO || isAllEnabled();
    }

    protected boolean isWarnEnabled() {
        return level == Level.WARN || isAllEnabled();
    }

    protected boolean isErrorEnabled() {
        return level == Level.ERROR || isAllEnabled();
    }
}
