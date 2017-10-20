package com.airparking.schd;

import com.airparking.schd.annotations.APTaskHandler;
import com.airparking.schd.data.TaskData;

abstract public class AbstractTaskHandler implements TaskHandler {
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

    @Override
    public void fireHandle(TaskData taskData) {
        doHandle(taskData);

        TaskHandler taskHandler = this.findNext(taskData.getTag());

        if (taskHandler != null)
            taskHandler.fireHandle(taskData);
    }

    @Override
    public TaskHandler findNext(String tag) {
        AbstractTaskHandler taskHandler = this;
        do {
            taskHandler = taskHandler.next;
        } while (!this.getHandlerTag(taskHandler).equals(tag));
        return taskHandler;
    }

    private String getHandlerTag(TaskHandler taskHandler) {
        APTaskHandler apTaskHandler = taskHandler.getClass().getAnnotation(APTaskHandler.class);

        if (apTaskHandler != null)
            return apTaskHandler.tag();

        return "";
    }

    abstract protected void doHandle(TaskData taskData);
}