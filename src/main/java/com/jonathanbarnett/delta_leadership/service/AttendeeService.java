package com.jonathanbarnett.delta_leadership.service;

import com.jonathanbarnett.delta_leadership.models.Attendee;

import java.util.List;

public interface AttendeeService {

    Attendee save(Attendee attendee);

    List<Attendee> findAll();
}
