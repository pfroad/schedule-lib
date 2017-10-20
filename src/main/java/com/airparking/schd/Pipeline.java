package com.airparking.schd;

import java.util.List;

public interface Pipeline {
    Pipeline addLast(String name, AbstractTaskHandler taskHandler);

    Pipeline addFirst(String name, AbstractTaskHandler taskHandler);

    Pipeline addBefore(String baseName, String name, AbstractTaskHandler taskHandler);

    Pipeline addAfter(String baseName, String name, AbstractTaskHandler taskHandler);

    Pipeline replace(String name, AbstractTaskHandler oldTaskHandler, AbstractTaskHandler newTaskHandler);

    Pipeline remove(String name);

    TaskHandler first();

    TaskHandler last();

    TaskHandler get(String name);

    List<String> names();
}
