package com.example.task_2;

public abstract class Identifier {
    private String id;

    public Identifier(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
