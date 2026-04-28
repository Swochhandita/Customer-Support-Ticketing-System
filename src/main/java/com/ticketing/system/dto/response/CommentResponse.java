package com.ticketing.system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    /**
     * Comment message
     */
    private String message;
    /**
     * Who wrote the comment
     */
    private UserResponse user;
    /**
     * When written
     */
    private LocalDateTime createdAt;
    /**
     * When last edited
     */
    private LocalDateTime updatedAt;
}

