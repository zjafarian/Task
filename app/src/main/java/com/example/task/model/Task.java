package com.example.task.model;

import java.io.Serializable;
import java.util.UUID;

public class Task implements Serializable {
    private UUID mUUIDTask;
    private String mStringNameTask;
    private State mStateTask;



    public Task(String stringNameTask, State stateTask) {
        mUUIDTask = UUID.randomUUID();
        mStringNameTask = stringNameTask;
        mStateTask = stateTask;

    }

    public UUID getUUIDTask() {
        return mUUIDTask;
    }

    public String getStringNameTask() {
        return mStringNameTask;
    }

    public void setStringNameTask(String stringNameTask) {
        mStringNameTask = stringNameTask;
    }

    public State getStateTask() {
        return mStateTask;
    }

    public void setStateTask(State stateTask) {
        mStateTask = stateTask;
    }

}
