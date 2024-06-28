package com.example.task_2.custom_storages.implementation;

import com.example.task_2.custom_storages.CustomSet;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomHashSet<T> implements CustomSet<T> {

    private final Entry[] storageBuckets;
    private int size;


    public CustomHashSet(int initialSize) {
        storageBuckets = new Entry[initialSize];
    }

    @Override
    public boolean put(T obj) {
        int index = customHash(obj.hashCode());
        Entry currentElement = storageBuckets[index];

        while (currentElement != null) {
            if (currentElement.key.equals(obj))
                return false;

            currentElement = currentElement.next;
        }

        Entry entry = new Entry();
        entry.key = obj;
        entry.next = storageBuckets[index];
        storageBuckets[index] = entry;
        size++;
        return true;
    }

    @Override
    public boolean contains(T obj) {
        int index = customHash(obj.hashCode());
        Entry currentElement = storageBuckets[index];

        while (currentElement != null) {
            if (currentElement.key.equals(obj))
                return true;

            currentElement = currentElement.next;
        }


        return false;
    }

    @Override
    public boolean delete(T obj) {
        int index = customHash(obj.hashCode());
        Entry currentElement = storageBuckets[index];
        Entry previous = null;

        while (currentElement != null) {
            if (currentElement.key.equals(obj)) {
                if (previous == null)
                    storageBuckets[index] = currentElement.next;
                else previous.next = currentElement.next;

                size--;
                return true;
            }
            previous = currentElement;
            currentElement = currentElement.next;
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomHashSetIterator();
    }

    @Override
    public String toString() {
        Entry currentEntry;
        StringBuilder sb = new StringBuilder();

        for (Entry storageBucket : storageBuckets) {
            if (storageBucket != null) {
                currentEntry = storageBucket;
                sb.append(currentEntry.key.toString());
                while (currentEntry.next != null) {
                    currentEntry = currentEntry.next;
                    sb.append(" --> ").append(currentEntry.key.toString());
                }
                sb.append("; ");
            }
        }

        return sb.toString();
    }

    private int customHash(int hashCode) {
        int index = hashCode;
        if (index < 0)
            index = -index;

        return index % storageBuckets.length;
    }

    private static class Entry {
        Object key;
        Entry next;
    }

    class CustomHashSetIterator implements Iterator<T> {

        private int currentBucket;
        private int previousBucket;
        private Entry currentElement;
        private Entry previousElement;

        public CustomHashSetIterator() {
            currentBucket = -1;
            previousBucket = -1;
            currentElement = null;
            previousElement = null;
        }

        @Override
        public boolean hasNext() {
            if (currentElement != null && currentElement.next != null)
                return true;

            for (int index = currentBucket + 1; index < storageBuckets.length; index++) {
                if (storageBuckets[index] != null)
                    return true;
            }

            return false;
        }

        @Override
        public T next() {
            previousElement = currentElement;
            previousBucket = currentBucket;

            if (currentElement == null || currentElement.next == null) {
                currentBucket++;

                while (currentBucket < storageBuckets.length && storageBuckets[currentBucket] == null) {
                    currentBucket++;
                }

                if (currentBucket < storageBuckets.length)
                    currentElement = storageBuckets[currentBucket];
                else throw new NoSuchElementException("Set has no more elements");
            } else currentElement = currentElement.next;

            return (T) currentElement.key;
        }
    }
}
