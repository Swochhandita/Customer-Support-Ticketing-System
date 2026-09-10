package com.ticketing.system.core;

import com.ticketing.system.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class BaseController {
    protected ResponseEntity<ApiResponse<?>> ok(ApiResponse<?> response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    protected ResponseEntity<ApiResponse<?>> created(ApiResponse<?> response) {
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
