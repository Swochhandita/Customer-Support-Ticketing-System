package com.ticketing.system.repository;

import com.ticketing.system.entity.Comment;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Long> {

    //Comment by specific user
    List<Comment> findByUserId(Long userId);

    Long countByTicketId(Long ticketId);//Counts the total number of comments for a specific ticket, useful for analytics and reporting.

    //Shows comments for a ticket ordered by creation date, useful for displaying the most recent comments first.
    List<Comment> findByTicketIdOrderByCreatedAtDesc(Long ticketId);//Fetches comments for a ticket ordered by creation date, useful for displaying the most recent comments first.
}
