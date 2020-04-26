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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 日志记录器工厂。
 *
 * @author Yahuan Jin
 * @since 0.1
 */
public class LoggerFactory {
    /**
     * 默认的日志记录器实现类名。
     */
    private static final String DEFAULT_LOGGER_IMPL = "cn.jinyahuan.commons.courier.logging.NoOpLogger";

    private Constructor<? extends Loggable> loggerConstructor;

    private static final Loggable singletonNoOpLoggerImpl = new NoOpLogger(LoggerFactory.class.getName());

    protected String loggerImpl;

    /**
     * 如果实现类是无操作的日志记录器，是否启用单例模式（节约资源）。
     */
    protected boolean singletonNoOpLoggerImplFlag = true;

    /**
     * @throws RuntimeException 如果找不到对应的日志记录器实现类，或者找到不指定的构造器
     */
    public LoggerFactory() {
        this(DEFAULT_LOGGER_IMPL);
    }

    /**
     * @param loggerImpl
     * @throws RuntimeException 如果找不到对应的日志记录器实现类，或者找到不指定的构造器
     */
    public LoggerFactory(String loggerImpl) {
        this.loggerImpl = loggerImpl;

        try {
            Class clazz = Class.forName(this.loggerImpl);
            loggerConstructor = clazz.getDeclaredConstructor(String.class);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param clazz
     * @return
     * @throws RuntimeException
     */
    public Loggable getLogger(Class clazz) {
        return getLogger(clazz.getName());
    }

    /**
     * @param className
     * @return
     * @throws RuntimeException
     */
    public Loggable getLogger(String className) {
        Loggable loggable;

        if (DEFAULT_LOGGER_IMPL.equals(getLoggerImpl()) && singletonNoOpLoggerImplFlag) {
            loggable = singletonNoOpLoggerImpl;
        }
        else {
            try {
                loggable = loggerConstructor.newInstance(className);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        return loggable;
    }

    /**
     * 获取日志记录器的实现类名（全名）。
     *
     * @return
     */
    public String getLoggerImpl() {
        return this.loggerImpl;
    }
}
