package com.jonathanbarnett.delta_leadership.service;

import com.jonathanbarnett.delta_leadership.models.Task;
import com.jonathanbarnett.delta_leadership.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findByCompleteIsTrue() {
        return taskRepository.findByCompleteIsTrue();
    }

    @Override
    public List<Task> findByCompleteIsFalse() {
        return taskRepository.findByCompleteIsFalse();
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
}
