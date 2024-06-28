package com.example.task_2.custom_storages;

public interface CustomList<T> {

    boolean isEmpty();

    boolean put(T obj);

    T getByIndex(int index);

    boolean deleteByIndex(int index);
}
