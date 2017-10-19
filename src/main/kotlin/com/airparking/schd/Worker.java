package com.airparking.schd;

import com.airparking.schd.data.TaskData;
import com.airparking.schd.data.TaskDataStore;

import java.util.List;

public class Worker {
    private TaskDataStore taskDataStore;

    private List<ScheduleHandler> scheduleHandlers;

    private Integer takeCount = 100;

    public void process() {
        List<TaskData> dataList = taskDataStore.take(takeCount);

        if (dataList == null) return;

        dataList.stream().forEach(it -> doWork(it));
    }

    private void doWork(final TaskData taskData) {
        if (scheduleHandlers == null || scheduleHandlers.size() == 0)
            return;
        
    }

    public TaskDataStore getTaskDataStore() {
        return taskDataStore;
    }

    public void setTaskDataStore(TaskDataStore taskDataStore) {
        this.taskDataStore = taskDataStore;
    }

    public List<ScheduleHandler> getScheduleHandlers() {
        return scheduleHandlers;
    }

    public void setScheduleHandlers(List<ScheduleHandler> scheduleHandlers) {
        this.scheduleHandlers = scheduleHandlers;
    }

    public Integer getTakeCount() {
        return takeCount;
    }

    public void setTakeCount(Integer takeCount) {
        this.takeCount = takeCount;
    }
}
