package com.tasksmanagerapp.tma.controller;

import com.tasksmanagerapp.tma.model.Task;

import java.util.Comparator;

public class ObjectComparator implements Comparator<Task> {
    public int compare(Task obj1, Task obj2) {
        return obj1.getDateCreated().compareTo(obj2.getDateCreated());
    }

}
