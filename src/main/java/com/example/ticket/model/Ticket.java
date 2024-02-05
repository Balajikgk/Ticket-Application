package com.example.ticket.model;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("ticket")
public class Ticket {


	private String ticketId;
	private String createdBy;
	private String assignTo;
	private TicketStatus status;
	private UpdatedInfo updateInfo;
}
