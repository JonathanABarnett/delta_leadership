package com.jonathanbarnett.delta_leadership.repository;

import com.jonathanbarnett.delta_leadership.models.Attendee;
import com.jonathanbarnett.delta_leadership.models.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Integer> {


}
