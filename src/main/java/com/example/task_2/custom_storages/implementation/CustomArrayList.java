package com.example.task_2.custom_storages.implementation;

import com.example.task_2.custom_storages.CustomList;

import java.util.Arrays;

public class CustomArrayList<T> implements CustomList<T> {

    private int size;
    private T[] storage;

    public CustomArrayList() {
        this(10);
    }

    public CustomArrayList(int initialSize) {
        if (initialSize < 5)
            throw new IllegalArgumentException("Initial size of list should be at least 5");

        storage = (T[]) new Object[initialSize];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean put(T obj) {
        checkCapacity(this.size + 1);
        storage[size++] = obj;

        return true;
    }

    @Override
    public T getByIndex(int index) {
        checkWhetherIndexGreaterThanStorageSize(index);
        return storage[index];
    }

    @Override
    public boolean deleteByIndex(int index) {
        checkWhetherIndexGreaterThanStorageSize(index);

        if (index == 0)
            storage = Arrays.copyOfRange(storage, 1, storage.length);
        else if (index == storage.length - 1)
            storage = Arrays.copyOfRange(storage, 0, storage.length - 1);
        else
            System.arraycopy(storage, index + 1, storage, index, size - index);

        size--;
        return true;
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "size=" + size +
                ", storage=" + Arrays.toString(storage) +
                '}';
    }

    private void checkCapacity(int desiredSize) {
        if (desiredSize > storage.length) {
            T[] currentElements = storage;
            int newSize = currentElements.length * 2;
            this.storage = Arrays.copyOf(currentElements, newSize);
        }
    }

    private void checkWhetherIndexGreaterThanStorageSize(int index) {
        if (index >= storage.length)
            throw new IllegalArgumentException("Index " + index + " is greater than list size");
    }
}
