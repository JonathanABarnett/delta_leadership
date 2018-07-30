package com.jonathanbarnett.delta_leadership.service;

import com.jonathanbarnett.delta_leadership.models.Priority;
import com.jonathanbarnett.delta_leadership.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {

    @Autowired
    private PriorityRepository priorityRepository;

    @Override
    public List<Priority> findAll() {
        return priorityRepository.findAll();
    }
}
