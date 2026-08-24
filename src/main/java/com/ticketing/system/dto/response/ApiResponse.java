package com.ticketing.system.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//don't include null fields in json
//* Generic response wrapper for all API endpoints
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private T data;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
}
