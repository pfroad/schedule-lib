package com.airparking.schd;

import com.airparking.schd.data.TaskData;
import com.airparking.schd.data.TaskDataStore;

public class TaskProducer {
    private TaskDataStore<TaskData> taskDataStore;

    public TaskProducer(TaskDataStore<TaskData> taskDataStore) {
        this.taskDataStore = taskDataStore;
    }

    public void add(TaskData... tasks) {
        this.taskDataStore.add(tasks);
    }
}
