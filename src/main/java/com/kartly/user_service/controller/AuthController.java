package com.kartly.user_service.controller;

import com.kartly.user_service.dto.AuthResponse;
import com.kartly.user_service.dto.LoginRequest;
import com.kartly.user_service.dto.RefreshTokenRequest;
import com.kartly.user_service.dto.RegisterRequest;
import com.kartly.user_service.entity.UserEntity;
import com.kartly.user_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "User registration, login, and token management")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public UserEntity register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @Operation(summary = "Log in, returns access and refresh tokens")
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @Operation(summary = "Exchange a refresh token for a new access token")
    @PostMapping("/refresh")
    public AuthResponse refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return authService.refreshAccessToken(request.getRefreshToken());
    }

    @Operation(summary = "Get the currently authenticated user")
    @GetMapping("/me")
    public UserEntity getCurrentUser(Authentication authentication) {
        return authService.getUserByEmail(authentication.getName());
    }
}

