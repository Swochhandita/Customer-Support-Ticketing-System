package com.ticketing.system.service;

import com.ticketing.system.dto.request.LoginRequest;
import com.ticketing.system.dto.request.RegisterRequest;
import com.ticketing.system.dto.response.ApiResponse;

public interface AuthService {
    ApiResponse<?> register(RegisterRequest request);
    ApiResponse<?> login(LoginRequest request);
}
