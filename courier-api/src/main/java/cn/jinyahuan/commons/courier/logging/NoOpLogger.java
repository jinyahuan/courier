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
 * 无操作的日志记录器。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class NoOpLogger implements Loggable {
    public NoOpLogger(String className) {}

    @Override
    public void debug(String s) {}

    @Override
    public void debug(String format, Object... args) {}

    @Override
    public void info(String s) {}

    @Override
    public void info(String format, Object... args) {}

    @Override
    public void warn(String s) {}

    @Override
    public void warn(String format, Object... args) {}

    @Override
    public void error(String s) {}

    @Override
    public void error(String format, Object... args) {}

    @Override
    public boolean isDebugEnabled() {
        return false;
    }
}
