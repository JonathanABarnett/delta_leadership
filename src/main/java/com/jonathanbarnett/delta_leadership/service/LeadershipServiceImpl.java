package com.jonathanbarnett.delta_leadership.service;

import com.jonathanbarnett.delta_leadership.models.Leadership;
import com.jonathanbarnett.delta_leadership.repository.LeadershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadershipServiceImpl implements LeadershipService {

    @Autowired
    private LeadershipRepository leadershipRepository;

    @Override
    public List<Leadership> findAll() {
        return leadershipRepository.findAll();
    }
}
