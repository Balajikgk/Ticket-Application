package com.example.ticket.helper;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ticket.model.TicketStatus;
import com.example.ticket.model.UpdatedInfo;
import com.example.ticket.repository.TicketRepository;

@Component
public class Helper {
	
	@Autowired 
	TicketRepository ticketRepository;

	public UpdatedInfo updateInfo(String loggedInUser) {
		UpdatedInfo updateInfo = new UpdatedInfo();
		updateInfo.setCreatedBy(loggedInUser);
		updateInfo.setCreatedOn(LocalDate.now());
		updateInfo.setUpdatedBy(loggedInUser);
		updateInfo.setUpdatedOn(LocalDate.now());
		return updateInfo;
	}
	
	public UpdatedInfo currentUpdateInfo(String loggedInUser) {
		UpdatedInfo updateInfo = new UpdatedInfo();
		updateInfo.setUpdatedBy(loggedInUser);
		updateInfo.setUpdatedOn(LocalDate.now());
		return updateInfo;
	}
	
	public boolean isAdminAssigned(String adminUsername) {
	    return ticketRepository.existsByAssignToAndStatus(adminUsername, TicketStatus.ASSIGNED_TO_ADMIN.toString());
	}
}
