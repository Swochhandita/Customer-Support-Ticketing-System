package com.ticketing.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tickets")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 200)
    private String title;
    @Column(columnDefinition = "TEXT")// USE TEXT FOR LONGER CONTENT
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status = TicketStatus.OPEN;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketPriority priority =TicketPriority.MEDIUM;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_agent_id", nullable = true)// nullable is true cause initially no user is assigned.
    private User assignedAgent;// User's instant which is agent to resolve the tickets.
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    /**
     * When was this ticket resolved (status changed to RESOLVED)
     * Can be null if still open
     */
    private LocalDateTime resolvedAt;
    /**
     * When was this ticket closed
     * Can be null if still open
     */
    private LocalDateTime closedAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = TicketStatus.OPEN;
        }
        if (priority == null) {//priority is not touched so it will be null and if it will be null then it will be medium priority.
            priority = TicketPriority.MEDIUM;
        }

    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }



}
