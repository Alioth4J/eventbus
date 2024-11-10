package com.alioth4j.eventbus;

import java.util.concurrent.Executor;

/**
 * Async observer design pattern implementation.
 */
public class AsyncEventBus extends EventBus {

    /**
     * The thread pool is provided by the user.
     * @param executor the thread pool to use
     */
    public AsyncEventBus(Executor executor) {
        super(executor);
    }

}
