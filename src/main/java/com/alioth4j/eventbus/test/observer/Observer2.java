package com.alioth4j.eventbus.test.observer;

import com.alioth4j.eventbus.Subscribe;

import com.alioth4j.eventbus.test.event.ChildEvent;

public class Observer2 {

    @Subscribe
    public void onMessage(ChildEvent childEvent) {
        System.out.println(childEvent.toString());
    }

}
