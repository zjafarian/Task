package com.example.task.model;

import java.io.Serializable;
import java.util.UUID;

public class Task implements Serializable {
    private UUID mUUIDTask;
    private String mStringNameTask;
    private State mStateTask;
    private ColorTask mColorTask;


    public Task(String stringNameTask, State stateTask, ColorTask colorTask) {
        mUUIDTask = UUID.randomUUID();
        mStringNameTask = stringNameTask;
        mStateTask = stateTask;
        mColorTask = colorTask;
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

    public ColorTask getColorTask() {
        return mColorTask;
    }

    public void setColorTask(ColorTask colorTask) {
        mColorTask = colorTask;
    }
}
