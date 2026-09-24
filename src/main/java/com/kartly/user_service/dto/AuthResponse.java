package com.kartly.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {

    public String accessToken;
    public String refreshToken;
}

