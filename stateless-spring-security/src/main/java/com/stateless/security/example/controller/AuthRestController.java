package com.stateless.security.example.controller;

import com.stateless.security.example.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthenticationManager authenticationManager;

    @PostMapping(value = "/accessToken")
    public String authenticate(@RequestBody LoginRequestDto loginRequestDto){

        // authenticate user using authentication manager with help of email and password

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        /**
         * This will throw AuthenticationException if authentication failed which will be handled by our
         * AuthenticationEntryPointJwt class , we can also handle with global exception handler
         */
        this.authenticationManager.authenticate(authentication);

        // if everything happen good till now mean user is authenticated

        return "User authenticated successfully";


        // if user is authenticated then generate access token ( JWT token) and return it

    }

}
