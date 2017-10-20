package com.airparking.schd.data;

import java.util.List;

public interface TaskDataStore<T> {
    T take();

    List<T> take(Integer count);

    void add(T... obj);
}