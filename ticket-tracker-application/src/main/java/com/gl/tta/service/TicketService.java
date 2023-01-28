package com.gl.tta.service;

import java.util.List;

import com.gl.tta.entity.Ticket;

public interface TicketService {

	List<Ticket> getAllTickets();

	Ticket saveTicket(Ticket ticket);

	Ticket getTicketById(Long id);

	Ticket editTicket(Ticket ticket);

	void deleteTicketById(Long id);

	Ticket viewTicket(Long id);

	List<Ticket> findByKeyword(String query);

}
