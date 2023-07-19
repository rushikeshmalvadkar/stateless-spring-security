package com.stateless.security.example.service;

import com.stateless.security.example.dto.UserRegisterRequest;
import com.stateless.security.example.entity.RoleEntity;
import com.stateless.security.example.entity.UserEntity;
import com.stateless.security.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Integer register(UserRegisterRequest userRegisterRequest){
        RoleEntity roleEntity = this.roleService.fetchRoleEntityById(userRegisterRequest.getRoleId());
        String encodedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname(userRegisterRequest.getFirstname());
        userEntity.setLastname(userRegisterRequest.getLastname());
        userEntity.setEmail(userRegisterRequest.getEmail());
        userEntity.setRole(roleEntity);
        userEntity.setPassword(encodedPassword);

        UserEntity savedUser = this.userRepository.save(userEntity);

        return savedUser.getId();
    }


}
