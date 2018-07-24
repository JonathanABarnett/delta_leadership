package com.jonathanbarnett.delta_leadership.repository;

import com.jonathanbarnett.delta_leadership.models.Leadership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LeadershipRepository extends JpaRepository<Leadership, Integer> {


}
