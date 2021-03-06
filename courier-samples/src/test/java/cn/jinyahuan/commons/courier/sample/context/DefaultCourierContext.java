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

package cn.jinyahuan.commons.courier.sample.context;

import cn.jinyahuan.commons.courier.context.AbstractCourierContext;
import cn.jinyahuan.commons.courier.context.IllegalCourierContextStateException;

/**
 * @author Yahuan Jin
 * @since 0.1
 */
public class DefaultCourierContext extends AbstractCourierContext {
    private static final Object STATE_LOCK_FLAG = new Object();

    public void setStateToInitializing() {
        State state = getState();
        if (State.isNew(state)) {
            synchronized (STATE_LOCK_FLAG) {
                if (State.isNew(state = getState())) {
                    this.state = State.INITIALIZING;
                    return;
                }
            }
        }
        throw new IllegalCourierContextStateException(
                "Required state must be State#NEW, but found " + state);
    }

    public void setStateToInitialized() {
        State state = getState();
        if (State.isInitializing(state)) {
            synchronized (STATE_LOCK_FLAG) {
                if (State.isInitializing(state = getState())) {
                    this.state = State.INITIALIZED;
                    return;
                }
            }
        }
        throw new IllegalCourierContextStateException(
                "Required state must be State#INITIALIZING, but found " + state);
    }

    public void setStateToRunning() {
        State state = getState();
        if (State.canRunning(state)) {
            synchronized (STATE_LOCK_FLAG) {
                if (State.canRunning(state = getState())) {
                    this.state = State.RUNNING;
                    return;
                }
            }
        }
        throw new IllegalCourierContextStateException(
                "Required state must be State#MAINTAINING or State#INITIALIZED, but found " + state);
    }
}
