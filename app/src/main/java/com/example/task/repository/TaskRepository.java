package com.example.task.repository;

import com.example.task.model.Task;

import java.util.List;

public class TaskRepository {
    private static TaskRepository sInstance;
    private List<Task> mTaskList;
    private static int sIntSize;
    private static String sNameTask;


    public static TaskRepository getInstance() {
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

    public static int getIntSize() {
        return sIntSize;
    }

    public static void setIntSize(int intSize) {
        sIntSize = intSize;
    }

    public static String getNameTask() {
        return sNameTask;
    }

    public static void setNameTask(String nameTask) {
        sNameTask = nameTask;
    }
}
