package com.tasksmanagerapp.tma.service;

import java.util.List;

import com.tasksmanagerapp.tma.model.Task;
import com.tasksmanagerapp.tma.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public Task getTaskById(long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public void saveOrUpdate(Task Task) {
        taskRepository.save(Task);
    }

    @Override
    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

}
