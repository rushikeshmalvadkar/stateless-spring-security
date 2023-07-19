package com.stateless.security.example.service;

import com.stateless.security.example.dto.JpaGrantedAuthority;
import com.stateless.security.example.dto.JpaUserDetails;
import com.stateless.security.example.entity.UserEntity;
import com.stateless.security.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.awt.print.PageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JpaUserDetailsService implements UserDetailsService {

    private static final String USER_DOES_NOT_EXISTS_MESSAGE = "User does not exists with email %s";
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = this.userRepository.fetchUserWithRole(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_DOES_NOT_EXISTS_MESSAGE, email)));

        JpaGrantedAuthority jpaGrantedAuthority = new JpaGrantedAuthority(userEntity.getRole());
        return new JpaUserDetails(userEntity, List.of(jpaGrantedAuthority));
    }
}
