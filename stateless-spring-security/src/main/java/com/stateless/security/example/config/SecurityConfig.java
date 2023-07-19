package com.stateless.security.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    public static final String[] PUBLIC_URLS = {"/api/users/**"};

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                /**
                 * Need to disable csrf otherwise we will get 403 or 401
                 * on public POST types urls
                 */
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_URLS).permitAll()
                .anyRequest().authenticated()
                .and()
                .build();
    }

}
