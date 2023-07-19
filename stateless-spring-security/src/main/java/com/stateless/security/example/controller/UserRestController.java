package com.stateless.security.example.controller;

import com.stateless.security.example.dto.UserRegisterRequest;
import com.stateless.security.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<Integer> register(@RequestBody UserRegisterRequest userRegisterRequest){
        Integer userId = this.userService.register(userRegisterRequest);
        return ResponseEntity.ok(userId);
    }


}
