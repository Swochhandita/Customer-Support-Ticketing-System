package com.ticketing.system.controller;

import com.ticketing.system.dto.request.LoginRequest;
import com.ticketing.system.dto.request.RegisterRequest;
import com.ticketing.system.dto.response.ApiResponse;
import com.ticketing.system.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody RegisterRequest request) {
        ApiResponse<?> response = authService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@Valid @RequestBody LoginRequest request) {
        ApiResponse<?> response = authService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
