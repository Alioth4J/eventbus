package com.alioth4j.eventbus.test.event;

public class ParentEvent {

    protected final String message;

    public ParentEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "ParentEvent{" +
                "message='" + message + '\'' +
                '}';
    }

}
