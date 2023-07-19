package com.stateless.security.example.dto;

import com.stateless.security.example.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class JpaGrantedAuthority implements GrantedAuthority {

    private final RoleEntity roleEntity;

    @Override
    public String getAuthority() {
        return roleEntity.getName();
    }
}
