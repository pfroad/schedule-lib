package com.airparking.schd;

import java.util.List;

public interface Pipeline {
    Pipeline addLast(String name, TaskHandler taskHandler);

    Pipeline addFirst(String name, TaskHandler taskHandler);

    Pipeline addBefore(String baseName, String name, TaskHandler taskHandler);

    Pipeline addLast(String baseName, String name, TaskHandler taskHandler);

    Pipeline replace(String name, TaskHandler oldTaskHandler, TaskHandler newTaskHandler);

    Pipeline remove(String name);

    Pipeline removeFirst();

    Pipeline removeLast();

    TaskHandler first();

    TaskHandler last();

    TaskHandler get(String name);

    List<String> names();

    TaskHandler findNextHandler();
}
