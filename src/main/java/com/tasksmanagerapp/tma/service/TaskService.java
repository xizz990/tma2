package com.tasksmanagerapp.tma.service;

import com.tasksmanagerapp.tma.model.Task;

import java.util.List;

public interface TaskService {

    public List<Task> getAllTasks();

    public Task getTaskById(long id);

    public void saveOrUpdate(Task Task);

    public void deleteTask(long id);
}
