package com.example.ticket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ticket.model.Ticket;

@Repository
public interface TicketRepository extends MongoRepository<Ticket,String> {
	
	Ticket getByTicketId(String ticketId);
	
	 boolean existsByAssignToAndStatus(String assignTo, String status);
	
	

}
