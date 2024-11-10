package com.alioth4j.eventbus.test.subject;

import com.alioth4j.eventbus.AsyncEventBus;
import com.alioth4j.eventbus.EventBus;

import com.alioth4j.eventbus.test.event.ChildEvent;
import com.alioth4j.eventbus.test.event.ParentEvent;
import com.alioth4j.eventbus.test.observer.Observer1;
import com.alioth4j.eventbus.test.observer.Observer2;

import java.util.concurrent.Executors;

public class Subject {

    public static void main(String[] args) throws InterruptedException {
        // Test EventBus
        EventBus eventBus = new EventBus();
        eventBus.register(new Observer1());
        eventBus.register(new Observer2());

        eventBus.post(new ParentEvent("This is the message from EventBus."));
        eventBus.post(new ChildEvent("This is parent's message from EventBus.", "This is child's message from EventBus."));

        Thread.sleep(1000);
        System.out.println();

        // Test AsyncEventBus
        EventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(10));
        asyncEventBus.register(new Observer1());
        asyncEventBus.register(new Observer2());

        asyncEventBus.post(new ParentEvent("This is the message from AsyncEventBus."));
        asyncEventBus.post(new ChildEvent("This is parent's message from AsyncEventBus.", "This is child's message from AsyncEventBus."));
    }
}
