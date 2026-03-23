package com.ticketing.system.utils;

/**
 * Constants - All constant values used across the application
 * Centralizing constants makes them easy to change globally
 */
public class Constants {
    // JWT Constants
    public static final String JWT_SECRET = "your-secret-key-change-this-in-production-at-least-32-characters-long";
    public static final long JWT_EXPIRATION = 86400000; // 24 hours in milliseconds
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_PREFIX = "Bearer ";

    // Role Constants
    public static final String ROLE_CUSTOMER = "CUSTOMER";
    public static final String ROLE_AGENT = "AGENT";
    public static final String ROLE_ADMIN = "ADMIN";

    // Ticket Status Constants
    public static final String TICKET_STATUS_OPEN = "OPEN";
    public static final String TICKET_STATUS_IN_PROGRESS = "IN_PROGRESS";
    public static final String TICKET_STATUS_RESOLVED = "RESOLVED";
    public static final String TICKET_STATUS_CLOSED = "CLOSED";

    // Message Constants
    public static final String USER_CREATED_SUCCESS = "User registered successfully";
    public static final String LOGIN_SUCCESS = "Login successful";
    public static final String TICKET_CREATED_SUCCESS = "Ticket created successfully";
    public static final String TICKET_UPDATED_SUCCESS = "Ticket updated successfully";
    public static final String NOT_FOUND = "Resource not found";
    public static final String UNAUTHORIZED = "Unauthorized access";
    public static final String BAD_REQUEST = "Invalid request";

    // Pagination Constants
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;
}
