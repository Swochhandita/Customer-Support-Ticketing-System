package com.ticketing.system.utils;

import com.ticketing.system.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ResponseUtil {
    public static ApiResponse<?> getSuccessResponse(String message) {
        return ApiResponse.builder()
                .success(true)
                .message(message)
                .httpStatus(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> getSuccessResponseWithData(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .httpStatus(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> getCreatedResponseWithData(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .httpStatus(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ApiResponse<Object> getNotFoundResponse(String message) {
        return ApiResponse.builder()
                .success(false)
                .message(message)
                .httpStatus(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ApiResponse<Object> getConflictResponse(String message) {
        return ApiResponse.builder()
                .success(false)
                .message(message)
                .httpStatus(HttpStatus.CONFLICT)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ApiResponse<Object> getBadRequestResponse(String message) {
        return ApiResponse.builder()
                .success(false)
                .message(message)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ApiResponse<Object> getInternalServerError(String message) {
        return ApiResponse.builder()
                .success(false)
                .message(message)
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ApiResponse<Object> getUnauthorizedResponse(String message) {
        return ApiResponse.builder()
                .success(false)
                .message(message)
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
