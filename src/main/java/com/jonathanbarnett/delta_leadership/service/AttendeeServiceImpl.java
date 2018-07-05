package com.jonathanbarnett.delta_leadership.service;

import com.jonathanbarnett.delta_leadership.models.Attendee;
import com.jonathanbarnett.delta_leadership.models.FamilyMember;
import com.jonathanbarnett.delta_leadership.repository.AttendeeRepository;
import com.jonathanbarnett.delta_leadership.repository.FamilyMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImpl implements AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    @Override
    public Attendee save(Attendee attendee) {

        return attendeeRepository.save(attendee);
    }

    @Override
    public List<Attendee> findAll() {
        return attendeeRepository.findAll();
    }
}
