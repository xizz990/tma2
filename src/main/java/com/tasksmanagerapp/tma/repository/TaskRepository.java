package com.tasksmanagerapp.tma.repository;

import com.tasksmanagerapp.tma.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
