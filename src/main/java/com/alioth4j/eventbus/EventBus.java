package com.alioth4j.eventbus;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Sync observer design pattern implementation.
 */
public class EventBus {

    /**
     * The thread pool to maintain consistency in form of <code>AsyncEventBus</code>.
     * Use single thread in <code>EventBus</code> as this is the sync mode.
     */
    private Executor executor;

    /**
     * The instance of <code>ObserverRegistry</code>.
     */
    private ObserverRegistry observerRegistry = new ObserverRegistry();


    public EventBus() {
        this(Executors.newSingleThreadExecutor());
    }

    public EventBus(Executor executor) {
        this.executor = executor;
    }


    /**
     * Register an observer.
     * @param observer the observer to be registered
     */
    public void register(Object observer) {
        observerRegistry.register(observer);
    }

    /**
     * Trigger the event.
     * @param event the event to be triggered
     */
    public void post(Object event) {
        List<ObserverAction> matchedObserverActions = observerRegistry.getMatchedObserverActions(event);
        for (ObserverAction matchedObserverAction : matchedObserverActions) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    matchedObserverAction.execute(event);
                }
            });
        }
    }

}
