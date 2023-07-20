package com.stateless.security.example.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


public record TokenResponse(String accessToken) {
}
