package com.ticketing.system.controller;

import com.ticketing.system.core.BaseController;
import com.ticketing.system.dto.request.LoginRequest;
import com.ticketing.system.dto.request.RegisterRequest;
import com.ticketing.system.dto.response.ApiResponse;
import com.ticketing.system.service.AuthService;
import com.ticketing.system.utils.ApiConstant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.AbstractController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(ApiConstant.AUTH)
@RequiredArgsConstructor
public class AuthController extends BaseController {
    private final AuthService authService;

    @PostMapping(ApiConstant.REGISTER)
    public ResponseEntity<ApiResponse<?>> register(@RequestBody @Valid RegisterRequest request) {
        return created(authService.register(request));
    }

    @PostMapping(ApiConstant.LOGIN)
    public ResponseEntity<ApiResponse<?>> login(@RequestBody @Valid LoginRequest request) {
        return ok(authService.login(request));
    }
}
