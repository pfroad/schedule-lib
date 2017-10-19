package com.airparking.schd;

public class AbstractTaskHandler implements TaskHandler {
    private AbstractTaskHandler prev;
    private AbstractTaskHandler next;

    public AbstractTaskHandler getPrev() {
        return prev;
    }

    public void setPrev(AbstractTaskHandler prev) {
        this.prev = prev;
    }

    public AbstractTaskHandler getNext() {
        return next;
    }

    public void setNext(AbstractTaskHandler next) {
        this.next = next;
    }
}