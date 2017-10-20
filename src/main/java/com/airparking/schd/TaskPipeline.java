package com.airparking.schd;

import com.airparking.schd.data.TaskData;
import com.airparking.schd.data.TaskDataStore;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class TaskPipeline implements Pipeline {
    private AbstractTaskHandler head;
    private AbstractTaskHandler tail;

    private TaskDataStore<TaskData> taskDataStore;

    private ConcurrentMap<String, AbstractTaskHandler> handlerMap = new ConcurrentHashMap<>();

    @Override
    public Pipeline addLast(String name, AbstractTaskHandler taskHandler) {
        handlerMap.put(name, taskHandler);

        if (this.head == null) {
            this.head = taskHandler;
            this.tail = head;
        } else {
            this.tail.setNext(taskHandler);
            taskHandler.setPrev(this.tail);
            this.tail = taskHandler;
        }
        return this;
    }

    @Override
    public Pipeline addFirst(String name, AbstractTaskHandler taskHandler) {
        handlerMap.put(name, taskHandler);
        if (this.head == null) {
            this.head = taskHandler;
            this.tail = head;
        } else {
            taskHandler.setNext(this.head);
            this.head.setPrev(taskHandler);
            this.head = taskHandler;
        }
        return this;
    }

    @Override
    public Pipeline addBefore(String baseName, String name, AbstractTaskHandler taskHandler) {
        AbstractTaskHandler baseTaskHandler = this.handlerMap.get(baseName);

        if (baseTaskHandler == null) {
            return this;
        }

        this.handlerMap.put(name, taskHandler);

        baseTaskHandler.getPrev().setNext(taskHandler);
        taskHandler.setPrev(baseTaskHandler.getPrev());
        taskHandler.setNext(baseTaskHandler);
        baseTaskHandler.setPrev(taskHandler);

        return this;
    }

    @Override
    public Pipeline addAfter(String baseName, String name, AbstractTaskHandler taskHandler) {
        AbstractTaskHandler baseTaskHandler = this.handlerMap.get(baseName);

        if (baseTaskHandler == null) {
            return this;
        }

        this.handlerMap.put(name, taskHandler);

        baseTaskHandler.getNext().setPrev(taskHandler);
        taskHandler.setNext(baseTaskHandler.getNext());
        baseTaskHandler.setNext(taskHandler);
        taskHandler.setPrev(baseTaskHandler);

        return this;
    }

    @Override
    public Pipeline replace(String name, AbstractTaskHandler oldTaskHandler, AbstractTaskHandler newTaskHandler) {
        AbstractTaskHandler taskHandler = this.handlerMap.get(name);

        if (taskHandler == null) {
            return this;
        }

        this.handlerMap.put(name, newTaskHandler);

        newTaskHandler.setPrev(oldTaskHandler.getPrev());
        newTaskHandler.setNext(oldTaskHandler.getNext());
        newTaskHandler.getPrev().setNext(newTaskHandler);
        newTaskHandler.getNext().setPrev(newTaskHandler);

        return this;
    }

    @Override
    public Pipeline remove(String name) {
        AbstractTaskHandler taskHandler = this.handlerMap.remove(name);

        if (taskHandler != null) {
            taskHandler.getPrev().setNext(taskHandler.getNext());
            taskHandler.getNext().setPrev(taskHandler.getPrev());
        }

        return this;
    }

    @Override
    public TaskHandler first() {
        return this.head;
    }

    @Override
    public TaskHandler last() {
        return this.tail;
    }

    @Override
    public TaskHandler get(String name) {
        return this.handlerMap.get(name);
    }

    @Override
    public List<String> names() {
        return this.handlerMap.keySet().stream().collect(Collectors.toList());
    }

    public TaskDataStore<TaskData> getTaskDataStore() {
        return taskDataStore;
    }

    public void setTaskDataStore(TaskDataStore<TaskData> taskDataStore) {
        this.taskDataStore = taskDataStore;
    }

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
}
