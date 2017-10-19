package com.airparking.schd;

import java.util.List;

public class TaskPipeline implements Pipeline {
    private AbstractTaskHandler head;
    private AbstractTaskHandler tail;

    public AbstractTaskHandler getHead() {
        return head;
    }

    public void setHead(AbstractTaskHandler head) {
        this.head = head;
    }

    public AbstractTaskHandler getTail() {
        return tail;
    }

    public void setTail(AbstractTaskHandler tail) {
        this.tail = tail;
    }

    @Override
    public Pipeline addLast(String name, TaskHandler taskHandler) {
        return null;
    }

    @Override
    public Pipeline addFirst(String name, TaskHandler taskHandler) {
        return null;
    }

    @Override
    public Pipeline addBefore(String baseName, String name, TaskHandler taskHandler) {
        return null;
    }

    @Override
    public Pipeline addLast(String baseName, String name, TaskHandler taskHandler) {
        return null;
    }

    @Override
    public Pipeline replace(String name, TaskHandler oldTaskHandler, TaskHandler newTaskHandler) {
        return null;
    }

    @Override
    public Pipeline remove(String name) {
        return null;
    }

    @Override
    public Pipeline removeFirst() {
        return null;
    }

    @Override
    public Pipeline removeLast() {
        return null;
    }

    @Override
    public TaskHandler first() {
        return null;
    }

    @Override
    public TaskHandler last() {
        return null;
    }

    @Override
    public TaskHandler get(String name) {
        return null;
    }

    @Override
    public List<String> names() {
        return null;
    }

    @Override
    public TaskHandler findNextHandler() {
        return null;
    }
}
