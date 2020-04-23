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
public class PrintStreamLogger implements Loggable {
    public PrintStreamLogger(String className) {}

    @Override
    public void debug(String s) {
        System.out.println(s);
    }

    @Override
    public void debug(String format, Object... args) {
        System.out.printf(format, args);
    }

    @Override
    public void info(String s) {
        System.out.println(s);
    }

    @Override
    public void info(String format, Object... args) {
        System.out.printf(format, args);
    }

    @Override
    public void warn(String s) {
        System.err.println(s);
    }

    @Override
    public void warn(String format, Object... args) {
        System.err.printf(format, args);
    }

    @Override
    public void error(String s) {
        System.err.println(s);
    }

    @Override
    public void error(String format, Object... args) {
        System.err.printf(format, args);
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }
}
