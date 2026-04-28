package com.ticketing.system.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//don't include null fields in json
//* Generic response wrapper for all API endpoints
public class ApiResponse<T> {
    private Boolean success;
    /**
     * Message to send to client
     */
    private String message;
    /**
     * Actual response data (generic type T)
     */
    private T data;
    /**
     * When response was generated
     */
    private String timestamp;
}
