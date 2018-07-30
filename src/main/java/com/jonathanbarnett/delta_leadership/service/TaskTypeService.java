package com.jonathanbarnett.delta_leadership.service;

import com.jonathanbarnett.delta_leadership.models.TaskType;

import java.util.List;

public interface TaskTypeService {

    List<TaskType> findAll();
}
