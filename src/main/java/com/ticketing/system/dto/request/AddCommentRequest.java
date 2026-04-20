package com.ticketing.system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentRequest {
    @NotBlank(message = "Comment message is required")//Always use the validation in the dto fields
    @Size(min = 1, max = 2000, message = "Comment must be between 1 and 2000 characters")
    private String message;
}
