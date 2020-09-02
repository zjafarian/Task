package com.example.task.repository;

import com.example.task.model.ColorTask;
import com.example.task.model.State;
import com.example.task.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TaskRepository {
    private static TaskRepository sInstance;
    private List<Task> mTaskList = new ArrayList<>();
    private static int sIntSize;
    private static String sNameTask;


    private TaskRepository() {
        for (int i = 0; i < sIntSize; i++) {
            State state = generateRandomState();
            Task task = new Task(sNameTask, state);
            mTaskList.add(task);
        }

    }

    public State generateRandomState() {
        State[] states = State.values();
        int length = states.length - 1;
        int rndIndex = (int) (Math.random() * (length - 0 + 1) + 0);
        return states[rndIndex];
    }

    public static TaskRepository getInstance() {
        if (sInstance == null)
            sInstance = new TaskRepository();

        return sInstance;
    }


    public static void setInstance(TaskRepository instance) {
        sInstance = instance;
    }

    public List<Task> getTaskList() {
        return mTaskList;
    }

    public void setTaskList(List<Task> taskList) {
        mTaskList = taskList;
    }

    public static void setSizeTaskAndNameTask(int sSize, String sName) {
        sIntSize = sSize;
        sNameTask = sName;
    }

    public void insertTask(Task task) {
        mTaskList.add(task);
    }

    public Task getTask(UUID uuid) {
        for (Task task : mTaskList) {
            if (task.getUUIDTask().equals(uuid))
                return task;
        }
        return null;
    }

}
