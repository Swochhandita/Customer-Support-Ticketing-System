package com.ticketing.system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    /**
     * JWT Token - Client uses this for subsequent requests
     * Header: Authorization: Bearer <token>
     */
    private String token;
    /**
     * User information
     */
    private UserResponse user;
}
