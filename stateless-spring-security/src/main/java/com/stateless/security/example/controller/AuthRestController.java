package com.stateless.security.example.controller;

import com.stateless.security.example.dto.LoginRequestDto;
import com.stateless.security.example.dto.response.TokenResponse;
import com.stateless.security.example.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    private final JwtUtil jwtUtil;

    @PostMapping(value = "/accessToken")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody LoginRequestDto loginRequestDto){

        // authenticate user using authentication manager with help of email and password

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        /**
         * This will throw AuthenticationException if authentication failed which will be handled by our
         * AuthenticationEntryPointJwt class , we can also handle with global exception handler
         */
        Authentication authenticationObj = this.authenticationManager.authenticate(authentication);

        // if everything happen good till now mean user is authenticated



        // if user is authenticated then generate access token ( JWT token) and return it
        String accessToken = jwtUtil.generateToken(authenticationObj);
        return ResponseEntity.ok(new TokenResponse(accessToken));

    }

}
