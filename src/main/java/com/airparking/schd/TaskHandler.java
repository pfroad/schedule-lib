package com.airparking.schd;

import com.airparking.schd.data.TaskData;

public interface TaskHandler {
    void fireHandle(TaskData taskData);

    TaskHandler findNext(String tag);
}
