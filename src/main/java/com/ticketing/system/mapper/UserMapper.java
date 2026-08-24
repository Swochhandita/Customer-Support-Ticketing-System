package com.ticketing.system.mapper;

import com.ticketing.system.dto.request.RegisterRequest;
import com.ticketing.system.dto.response.LoginResponse;
import com.ticketing.system.dto.response.UserResponse;
import com.ticketing.system.entity.Role;
import com.ticketing.system.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(RegisterRequest request, String encodedPassword, Role role) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword); // the password is encoded before saving in the database
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(role); // role is decided by AuthService, looked up from the database
        return user;
    }

    public UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setRole(user.getRole().getName().name());
        response.setIsActive(user.getIsActive());
        return response;
    }

    public LoginResponse toLoginResponse(User user, String token) {
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUser(toUserResponse(user));
        return response;
    }
}
