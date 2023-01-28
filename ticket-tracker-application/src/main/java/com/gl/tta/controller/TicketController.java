package com.gl.tta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gl.tta.entity.Ticket;
import com.gl.tta.service.TicketService;

@Controller
public class TicketController {

	private TicketService ticketService;

	public TicketController(TicketService ticketService) {
		super();
		this.ticketService = ticketService;
	}

	// handler method to handle list tickets and return model and view
	@GetMapping("/admin/tickets")
	public String listTickets(Model model) {
		model.addAttribute("tickets", ticketService.getAllTickets());
		return "tickets";
	}

	@GetMapping("/admin/tickets/newTicket")
	public String createTicketForm(Model model) {

		// creating ticket object go hold ticket form data
		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		return "create_ticket";
	}

	@PostMapping("/admin/tickets")
	public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
		ticketService.saveTicket(ticket);
		return "redirect:/admin/tickets";
	}

	@GetMapping("/admin/tickets/edit/{id}")
	public String editTicketForm(@PathVariable Long id, Model model) {
		model.addAttribute("ticket", ticketService.getTicketById(id));
		return "edit_ticket";
	}

	@PostMapping("/admin/tickets/{id}")
	public String editTicket(@PathVariable Long id, @ModelAttribute("ticket") Ticket ticket, Model model) {

		// To get ticket from database by id
		Ticket existingTicket = ticketService.getTicketById(id);
		existingTicket.setId(id);
		existingTicket.setTicketTitle(ticket.getTicketTitle());
		existingTicket.setTicketShortDescription(ticket.getTicketShortDescription());
		existingTicket.setTicketContent(ticket.getTicketContent());

		// To save updated ticket object
		ticketService.editTicket(existingTicket);
		return "redirect:/admin/tickets";
	}

	// handler method to handle delete ticket request
	@GetMapping("/admin/tickets/{id}")
	public String deleteTicket(@PathVariable Long id) {
		ticketService.deleteTicketById(id);
		return "redirect:/admin/tickets";
	}

	public String viewTicket(@PathVariable Long id) {
		ticketService.getTicketById(id);
		return "redirect:/admin/tickets";
	}

	@GetMapping("/admin/tickets/view/{id}")
	public String viewTicketForm(@PathVariable Long id, Model model) {
		model.addAttribute("ticket", ticketService.getTicketById(id));
		return "view_ticket";
	}

	// handler method to handle search functionality
	@GetMapping("/admin/tickets/search")
	public String listTickets(Model model, String query) {
		if (query != null) {
			model.addAttribute("tickets", ticketService.findByKeyword(query));
		} else {
			model.addAttribute("tickets", ticketService.getAllTickets());
		}
		return "tickets";
	}

}