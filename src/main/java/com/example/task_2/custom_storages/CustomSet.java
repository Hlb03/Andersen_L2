package com.example.task_2.custom_storages;

import java.util.Iterator;

public interface CustomSet<T> {

    boolean put(T obj);

    boolean contains(T obj);

    boolean delete(T obj);

    int size();

    Iterator<T> iterator();
}
