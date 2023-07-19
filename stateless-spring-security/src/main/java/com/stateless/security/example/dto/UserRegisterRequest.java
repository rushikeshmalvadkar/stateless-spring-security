package com.stateless.security.example.dto;

import com.stateless.security.example.entity.RoleEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserRegisterRequest {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private Integer roleId;


}
