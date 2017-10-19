package com.airparking.schd;

import com.airparking.schd.data.TaskData;
import com.airparking.schd.data.TaskDataStore;

import java.util.List;

public class TaskWorker {
    private TaskDataStore taskDataStore;

    private TaskPipeline taskPipeline;

    private Integer takeCount = 100;

    public void process() {
        List<TaskData> dataList = taskDataStore.take(takeCount);

        if (dataList == null) return;

        dataList.stream().forEach(it -> doWork(it));
    }

    private void doWork(final TaskData taskData) {

    }

    public TaskDataStore getTaskDataStore() {
        return taskDataStore;
    }

    public void setTaskDataStore(TaskDataStore taskDataStore) {
        this.taskDataStore = taskDataStore;
    }

    public TaskPipeline getTaskPipeline() {
        return taskPipeline;
    }

    public void setTaskPipeline(TaskPipeline taskPipeline) {
        this.taskPipeline = taskPipeline;
    }

    public Integer getTakeCount() {
        return takeCount;
    }

    public void setTakeCount(Integer takeCount) {
        this.takeCount = takeCount;
    }
}
