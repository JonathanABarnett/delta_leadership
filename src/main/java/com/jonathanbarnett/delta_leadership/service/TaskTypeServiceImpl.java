package com.jonathanbarnett.delta_leadership.service;

import com.jonathanbarnett.delta_leadership.models.TaskType;
import com.jonathanbarnett.delta_leadership.repository.TaskTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskTypeServiceImpl implements TaskTypeService {

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    @Override
    public List<TaskType> findAll() {
        return taskTypeRepository.findAll();
    }
}
