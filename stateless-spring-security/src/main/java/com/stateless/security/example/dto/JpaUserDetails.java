package com.stateless.security.example.dto;

import com.stateless.security.example.entity.RoleEntity;
import com.stateless.security.example.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class JpaUserDetails implements UserDetails {

    private final UserEntity userEntity;

    private final JpaGrantedAuthority jpaGrantedAuthority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(jpaGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
