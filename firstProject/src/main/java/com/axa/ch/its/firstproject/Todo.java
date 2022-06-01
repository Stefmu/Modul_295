package com.axa.ch.its.firstproject;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Todo {


    private int id;

    private long userId;

    private String title;

    private boolean completed;

    LocalDateTime deadline;

    public Todo(int id,  long userId, String title, boolean completed, LocalDateTime deadline) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.completed = completed;
        this.deadline = deadline;
    }
}
