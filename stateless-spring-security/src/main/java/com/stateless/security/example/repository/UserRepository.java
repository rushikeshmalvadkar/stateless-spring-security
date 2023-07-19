package com.stateless.security.example.repository;

import com.stateless.security.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity , Integer> {
}
