package com.airparking.schd;

import com.airparking.schd.data.TaskData;
import com.airparking.schd.data.TaskDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TaskWorker {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskWorker.class);

    private TaskDataStore taskDataStore;

    private TaskPipeline taskPipeline;

    private Integer takeCount = 100;

    public TaskWorker(TaskDataStore taskDataStore, TaskPipeline taskPipeline) {
        if (taskPipeline == null || taskDataStore == null) {
            LOGGER.error("Worker miss required properties.");
            throw new ExceptionInInitializerError();
        }

        this.taskDataStore = taskDataStore;
        this.taskPipeline = taskPipeline;

        taskPipeline.setTaskDataStore(taskDataStore);
    }

    public void process() {
        LOGGER.info("Start to process queued tasks......");
        List<TaskData> dataList = taskDataStore.take(takeCount);

        if (dataList == null) return;

        dataList.stream().forEach(it -> taskPipeline.first().fireHandle(it));
        LOGGER.info("Done to handle tasks!");
    }

    public TaskDataStore getTaskDataStore() {
        return taskDataStore;
    }

    public TaskPipeline getTaskPipeline() {
        return taskPipeline;
    }

    public Integer getTakeCount() {
        return takeCount;
    }

    public void setTakeCount(Integer takeCount) {
        this.takeCount = takeCount;
    }
}
