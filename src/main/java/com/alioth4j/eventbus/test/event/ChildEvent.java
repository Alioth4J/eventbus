package com.alioth4j.eventbus.test.event;

public class ChildEvent extends ParentEvent {

    private String childMessage;

    public ChildEvent(String message, String childMessage) {
        super(message);
        this.childMessage = childMessage;
    }

    public String getChildMessage() {
        return this.childMessage;
    }

    @Override
    public String toString() {
        return "ChildEvent{" +
                "childMessage='" + childMessage + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
