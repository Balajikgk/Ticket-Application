package com.example.ticket.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("ticketApproval")
public class TicketApproval {

	@Id
	private String id;
	private String ticketId;
	private String userId;
	private String userName;
	private UpdatedInfo approvalTime;
	private TicketStatus status;

}
