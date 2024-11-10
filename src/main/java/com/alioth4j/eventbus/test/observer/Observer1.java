package com.alioth4j.eventbus.test.observer;

import com.alioth4j.eventbus.Subscribe;

import com.alioth4j.eventbus.test.event.ParentEvent;

public class Observer1 {

    @Subscribe
    public void onEvent(ParentEvent parentEvent) {
        System.out.println(parentEvent.toString());
    }

}
