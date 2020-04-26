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

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public interface Loggable {
    void debug(String s);

    void debug(String format, Object... args);

    void info(String s);

    void info(String format, Object... args);

    void warn(String s);

    void warn(String format, Object... args);

    void error(String s);

    void error(String format, Object... args);

    boolean isDebugEnabled();

    /**
     * 日志界别常量类。
     */
    final class Level {
        public static final int OFF = 0;
        public static final int ERROR = 10;
        public static final int WARN = 20;
        public static final int INFO = 30;
        public static final int DEBUG = 40;
        public static final int ALL = 50;
    }
}
