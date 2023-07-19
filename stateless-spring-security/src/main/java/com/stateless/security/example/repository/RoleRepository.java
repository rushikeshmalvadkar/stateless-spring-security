package com.stateless.security.example.repository;

import com.stateless.security.example.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity , Integer> {
}
