package com.jonathanbarnett.delta_leadership.service;


import com.jonathanbarnett.delta_leadership.models.Task;

import java.util.List;

public interface TaskService {

    Task save(Task task);

    List<Task> findByCompleteIsTrue();

    List<Task> findByCompleteIsFalse();

    List<Task> findAll();
}
