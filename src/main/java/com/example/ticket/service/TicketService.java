package com.example.ticket.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticket.Exception.CustomException;
import com.example.ticket.helper.Helper;
import com.example.ticket.model.Role;
import com.example.ticket.model.Ticket;
import com.example.ticket.model.TicketApproval;
import com.example.ticket.model.TicketStatus;
import com.example.ticket.model.User;
import com.example.ticket.repository.TicketApprovalRepository;
import com.example.ticket.repository.TicketRepository;
import com.example.ticket.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TicketService {

	@Autowired
	Helper helper;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	TicketApprovalRepository ticketApprovalRepository;

	public Ticket createTicket(Ticket ticket, String loggedInUser) {
		User assignedUser = userRepository.findByUserName(ticket.getAssignTo());
		log.info("assignedUser:" + assignedUser);
		if (assignedUser == null) {
			throw new CustomException("User does not Exsist");
		}

		Ticket generateTicket = new Ticket();
		UUID uuid = UUID.randomUUID();
		generateTicket.setTicketId(uuid.toString());
		generateTicket.setCreatedBy(ticket.getCreatedBy());
		generateTicket.setAssignTo(ticket.getAssignTo());
		generateTicket.setStatus(TicketStatus.OPEN);
		generateTicket.setUpdateInfo(helper.updateInfo(loggedInUser));
		return ticketRepository.save(generateTicket);
	}

	public Ticket assignTicket(String ticketId, String userId, Role userType, String loggedInUser) {
		Ticket ticket = new Ticket();
		if (userType.equals(Role.MANAGER)) {
			User manager = userRepository.findByUserId(userId);
			if (manager == null || !manager.getRole().equals(Role.MANAGER)) {
				throw new CustomException("Manager does not Exsist");
			}

			log.info("Manager:" + manager);
			ticket = ticketRepository.getByTicketId(ticketId);
			if (ticket == null) {
				throw new CustomException("Ticket does not Exsist");
			}
			log.info("ticket" + ticket);

			if (!ticket.getStatus().equals(TicketStatus.OPEN)) {
				throw new CustomException("Ticket Does Not in Open State");
			}
			ticket.setTicketId(ticketId);
			ticket.setAssignTo(manager.getUserName());
			ticket.setStatus(TicketStatus.ASSIGNED_TO_MANAGER);
			ticket.setUpdateInfo(helper.updateInfo(loggedInUser));

			return ticketRepository.save(ticket);
		} if (userType.equals(Role.ADMIN)) {
			List<User> admins = userRepository.findByRole(Role.ADMIN.toString());
			List<User> availableAdmins = admins.stream().filter(admin -> !helper.isAdminAssigned(admin.getUserName()))
					.collect(Collectors.toList());

			if (availableAdmins.isEmpty()) {
				throw new CustomException("No available admins to assign the ticket");
			}

			User assignedAdmin = availableAdmins.get(new Random().nextInt(availableAdmins.size()));
			ticket.setTicketId(ticketId);
			ticket.setAssignTo(assignedAdmin.getUserName());
			ticket.setStatus(TicketStatus.ASSIGNED_TO_ADMIN);
			ticket.setUpdateInfo(helper.currentUpdateInfo(loggedInUser));
			return ticketRepository.save(ticket);

		} if(userType.equals(Role.SUPER_ADMIN)) {
			User superAdmin = userRepository.findByUserId(userId);
			if (superAdmin == null || !superAdmin.getRole().equals(Role.SUPER_ADMIN)) {
				throw new CustomException("Super Admin does Not exsist");
			}

			ticket = getTicketById(ticketId);
			ticket.setTicketId(ticketId);
			ticket.setAssignTo(superAdmin.getUserName());
			ticket.setStatus(TicketStatus.ASSIGNED_TO_SUPER_ADMIN);
			ticket.setUpdateInfo(helper.currentUpdateInfo(loggedInUser));
			return ticketRepository.save(ticket);
		}
		return null;
	}
	
	public TicketApproval approvalTicket(TicketApproval approval,String loggedInUser) {
		Ticket ticket = getTicketById(approval.getTicketId());
		approval.setUserId(approval.getUserId());
		approval.setUserName(approval.getUserName());
		approval.setApprovalTime(helper.currentUpdateInfo(loggedInUser));
		approval.setStatus(TicketStatus.APPROVED);
		ticket.setTicketId(approval.getTicketId());
		ticket.setStatus(TicketStatus.CLOSED);
		ticketRepository.save(ticket);
		return ticketApprovalRepository.save(approval);
		
	}

	public Ticket getTicketById(String ticketId) {
		return ticketRepository.findById(ticketId).orElseThrow(() -> new CustomException("Ticket Does Not Exist"));
	}

	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}
}
