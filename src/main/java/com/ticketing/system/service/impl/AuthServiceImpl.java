package com.ticketing.system.service.impl;

import com.ticketing.system.dto.request.LoginRequest;
import com.ticketing.system.dto.request.RegisterRequest;
import com.ticketing.system.dto.response.ApiResponse;
import com.ticketing.system.dto.response.LoginResponse;
import com.ticketing.system.entity.Role;
import com.ticketing.system.entity.RoleType;
import com.ticketing.system.entity.User;
import com.ticketing.system.exception.DuplicateResourceException;
import com.ticketing.system.exception.ResourceNotFoundException;
import com.ticketing.system.mapper.UserMapper;
import com.ticketing.system.repository.RoleRepository;
import com.ticketing.system.repository.UserRepository;
import com.ticketing.system.security.JwtUtils;
import com.ticketing.system.service.AuthService;
import com.ticketing.system.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Override
    public ApiResponse<?> register(RegisterRequest request) {
        log.debug("Registering new user with email: {}", request.getEmail());
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already registered: " + request.getEmail());
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username already taken: " + request.getUsername());
        }
        Role userRole = roleRepository.findByName(RoleType.USER).orElseThrow(() -> new ResourceNotFoundException("Default role USER not found"));
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = userMapper.toEntity(request, encodedPassword, userRole);
        User savedUser = userRepository.save(user);
        String token = jwtUtils.generateToken(savedUser.getEmail(), savedUser.getRole().getName().name());
        LoginResponse authResponse = userMapper.toLoginResponse(savedUser, token);
        log.info("User registered successfully with email: {}", savedUser.getEmail());
        return ResponseUtil.getCreatedResponseWithData(authResponse, "Registration successful. Welcome, " + savedUser.getUsername() + "!"
        );
    }

    @Override
    public ApiResponse<?> login(LoginRequest request) {
        log.debug("Login attempt for email: {}", request.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + request.getEmail()));
        String token = jwtUtils.generateToken(user.getEmail(), user.getRole().getName().name());
        LoginResponse authResponse = userMapper.toLoginResponse(user, token);
        log.info("User logged in successfully with email: {}", user.getEmail());
        return ResponseUtil.getSuccessResponseWithData(authResponse, "Login successful. Welcome back, " + user.getUsername() + "!");
    }
}
