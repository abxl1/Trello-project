package com.sparta.trelloproject.domain.auth.controller;

import com.sparta.trelloproject.domain.auth.dto.SigninRequest;
import com.sparta.trelloproject.domain.auth.dto.SigninResponse;
import com.sparta.trelloproject.domain.auth.dto.SignupRequest;
import com.sparta.trelloproject.domain.auth.dto.SignupResponse;
import com.sparta.trelloproject.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public SignupResponse signup(@Valid @RequestBody SignupRequest signupRequest) {
        return authService.signup(signupRequest);
    }

    @PostMapping("/auth/signin")
    public SigninResponse signin(@Valid @RequestBody SigninRequest signinRequest) {
        return authService.signin(signinRequest);
    }
}