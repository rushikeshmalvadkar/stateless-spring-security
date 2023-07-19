package com.stateless.security.example.repository;

import com.stateless.security.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity , Integer> {

    @Query(value = "SELECT ue FROM UserEntity ue JOIN FETCH ue.role WHERE ue.email = :email")
    Optional<UserEntity> fetchUserWithRole(@Param("email") String email);

}
