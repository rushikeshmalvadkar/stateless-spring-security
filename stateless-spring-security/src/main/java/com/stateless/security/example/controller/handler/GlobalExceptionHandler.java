package com.stateless.security.example.controller.handler;

import com.stateless.security.example.dto.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex){
        log.info("<<<<<<<<<<< handleAuthenticationException");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessages(List.of("Invalid credentials"));
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED);
        log.info("handleAuthenticationException >>>>>>>>");
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }

    @ExceptionHandler(value = {JwtException.class})
    public ResponseEntity<ErrorResponse> handleJwtException(JwtException ex){
        log.info("<<<<<<<<<<< handleJwtException");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessages(List.of(ex.getMessage()));
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED);
        log.info("handleJwtException >>>>>>>>");
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException ex){
        log.info("<<<<<<<<<<< handleExpiredJwtException");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessages(List.of("Token expired"));
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED);
        log.info("handleExpiredJwtException >>>>>>>>");
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }



}
