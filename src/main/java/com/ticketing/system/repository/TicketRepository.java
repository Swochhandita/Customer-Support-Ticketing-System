package com.ticketing.system.repository;

import com.ticketing.system.entity.Ticket;
import com.ticketing.system.entity.TicketPriority;
import com.ticketing.system.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByUserId(Long userId);
    List<Ticket> findByAssignedAgentId(Long agentId);
    List<Ticket> findByStatus(TicketStatus status);
    List<Ticket> findByPriority(TicketPriority priority);
    Long countByAssignedAgentId(Long agentId);
    /**
     * Count tickets with specific status
     * Useful for analytics
     * SQL: SELECT COUNT(*) FROM tickets WHERE status = ?
     */
    Long countByStatus(TicketStatus status);

    //Using the JPQL query to fetch tickets for a user with a specific status
    /**
     * Custom query using JPQL (Java Persistence Query Language)
     * Equivalent to SQL but using entity names
     * Query: SELECT t FROM Ticket t WHERE t.user.id = :userId AND t.status = :status
     * SQL: SELECT * FROM tickets WHERE user_id = ? AND status = ?
     */
    //MAIN JIST:- @Param is used in order to connect the query variables with the method parameters.
    @Query("SELECT t FROM Ticket t WHERE t.user.id = :userId AND t.status = :status")
    List<Ticket> findUserTicketsByStatus(@Param("userId") Long userId, @Param("status") TicketStatus status);
    //Finding the tickets assigned to a specific agent with a specific status
    List<Ticket> findByAssignedAgentIdAndStatus(Long agentId, TicketStatus status);
}

