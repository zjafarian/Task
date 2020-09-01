package com.example.task.repository;

import com.example.task.model.ColorTask;
import com.example.task.model.State;
import com.example.task.model.Task;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TaskRepository {
    private static TaskRepository sInstance;
    private List<Task> mTaskList;
    private static int sIntSize;
    private static String sNameTask;


    private TaskRepository() {
        for (int i = 0; i < sIntSize; i++) {
            State state = generateRandomState();
            ColorTask colorTask;
            if (i % 2 == 0) {
                colorTask = ColorTask.TealLight;
            } else colorTask = ColorTask.TealDark;
            Task task = new Task(sNameTask, state, colorTask);
            mTaskList.add(task);
        }

    }

    private State generateRandomState() {
        State[] states = State.values();
        int length = states.length;
        int rndIndex = new Random().nextInt(length);
        return states[rndIndex];
    }

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

    public static void setSizeTaskAndNameTask(int sSize, String sName) {
        sIntSize = sSize;
        sNameTask = sName;
    }

    public void insertTask (Task task){
        mTaskList.add(task);
    }

    public Task getTask (UUID uuid){
        for (Task task: mTaskList) {
            if (task.getUUIDTask().equals(uuid))
                return task;
        }
        return null;
    }


}
