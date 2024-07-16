package com.example.task_2.spring_jdbc_templates.exception;

public class OperationIsNotAvailableException extends RuntimeException {
    public OperationIsNotAvailableException(String message) {
        super(message);
    }
}
