package com.jonathanbarnett.delta_leadership.repository;

import com.jonathanbarnett.delta_leadership.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
