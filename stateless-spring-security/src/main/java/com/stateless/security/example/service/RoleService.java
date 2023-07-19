package com.stateless.security.example.service;

import com.stateless.security.example.entity.RoleEntity;
import com.stateless.security.example.exception.ResourceNotFoundException;
import com.stateless.security.example.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    private static final String ROLE_DOES_NOT_EXISTS_MESSAGE = "Role does not exists with id %d";
    private final RoleRepository roleRepository;

    public RoleEntity fetchRoleEntityById(Integer roleId) {
        return this.roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                String.format(ROLE_DOES_NOT_EXISTS_MESSAGE, roleId)));
    }

}
