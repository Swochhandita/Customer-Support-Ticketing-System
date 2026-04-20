package com.ticketing.system.dto.request;

import com.ticketing.system.entity.TicketStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketStatusRequest {
    @NotNull(message = "Status is required")
    private TicketStatus status;// helps to accept only valid status
}
