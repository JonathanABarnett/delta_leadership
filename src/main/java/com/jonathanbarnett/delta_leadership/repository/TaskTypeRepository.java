package com.jonathanbarnett.delta_leadership.repository;

import com.jonathanbarnett.delta_leadership.models.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTypeRepository extends JpaRepository<TaskType, Integer> {
}
