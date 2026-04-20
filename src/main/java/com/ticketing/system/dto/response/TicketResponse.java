package com.ticketing.system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivilegedAction;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {
    /**
     * Ticket ID
     */
    private long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private UserResponse user;//Customer who created the ticket
    private UserResponse assignedAgent;//Support agent assigned to the ticket
    private List<CommentResponse> comments;//List of comments on the ticket
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;
    private LocalDateTime closedAt;
}
