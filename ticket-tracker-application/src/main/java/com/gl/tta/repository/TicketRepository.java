package com.gl.tta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gl.tta.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

	// Implementation of Search Functionality
	@Query(value = "SELECT * FROM tickets t WHERE t.ticket_title LIKE %:query% or t.ticket_short_description LIKE %:query% or t.ticket_created_on LIKE %:query%", nativeQuery = true)
	List<Ticket> findByKeyword(@Param("query") String query);
}
