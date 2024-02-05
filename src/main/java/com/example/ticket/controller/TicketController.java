package com.example.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticket.model.Role;
import com.example.ticket.model.Ticket;
import com.example.ticket.model.TicketApproval;
import com.example.ticket.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	TicketService service;

   @PostMapping(path = "/create")
   public Ticket createTicket(@RequestBody Ticket ticket,@RequestHeader("loggedInUser")String loggedInUser) {
	   return service.createTicket(ticket, loggedInUser);
	
   }
   
   @PutMapping(path = "/assignTicket")
   public Ticket assignTicket(@RequestParam String ticketId,@RequestParam String userId,@RequestParam Role userType,@RequestHeader("loggedInUser")String loggedInUser) {
	   return service.assignTicket(ticketId, userId,userType,loggedInUser);
   }
   @PutMapping(path = "/approve")
   public TicketApproval approveTicket(@RequestBody TicketApproval ticket,@RequestHeader("loggedInUser")String loggedInUser) {
	   return service.approvalTicket(ticket, loggedInUser);
   }
   
   @GetMapping(path = "/getTicketById/{ticketId}")
   public Ticket getTicketById(@PathVariable String ticketId) {
	   return service.getTicketById(ticketId);
   }
   
   @GetMapping(path = "/getAllTickets")
   public List<Ticket> getAllTickets() {
	   return service.getAllTickets();
   }
}

