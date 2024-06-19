package com.example.task_2;

public interface Printer {

    default void print() {
        System.out.println("print content in console");
    }
}
