package com.alioth4j.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Registry that stores <code>ObserverAction</code> and manages the mapping relationship between event type and corresponding observer actions.
 */
public class ObserverRegistry {

    /**
     * The core data structure to store <code>ObserverAction<code/> and manage the mapping relationship between event type and a set of observer actions.
     */
    private Map<Class<?> /* Event type */, CopyOnWriteArraySet<ObserverAction> /* Corresponding methods to be invoked */> observerActions
            = new ConcurrentHashMap<>();

    /**
     * Register an observer.
     * @param observer the observer to be registered
     */
    public void register(Object observer) {
        for (Method method : observer.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?> eventType = method.getParameterTypes()[0];
                ObserverAction observerAction = new ObserverAction(observer, method);
                observerActions.putIfAbsent(eventType, new CopyOnWriteArraySet<>());
                observerActions.get(eventType).add(observerAction);
            }
        }
    }

    /**
     * Get all observer actions that match the event type.
     * @param event the event that occurs on the subject
     * @return all observer actions that match the event type
     */
    public List<ObserverAction> getMatchedObserverActions(Object event) {
        Class<?> postedEventType = event.getClass();
        List<ObserverAction> allMatchedObserverActions = new ArrayList<>();
        for (Map.Entry<Class<?>, CopyOnWriteArraySet<ObserverAction>> entry : this.observerActions.entrySet()) {
            Class<?> eventType = entry.getKey();
            CopyOnWriteArraySet<ObserverAction> matchedObserverActions = entry.getValue();
            if (eventType.isAssignableFrom(postedEventType)) {
                allMatchedObserverActions.addAll(matchedObserverActions);
            }
        }
        return allMatchedObserverActions;
    }

}
