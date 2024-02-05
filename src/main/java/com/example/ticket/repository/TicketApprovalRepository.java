package com.example.ticket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ticket.model.TicketApproval;

public interface TicketApprovalRepository extends MongoRepository<TicketApproval,String> {

}
