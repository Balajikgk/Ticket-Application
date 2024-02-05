package com.example.ticket.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedInfo {
	
	private String createdBy;
	private LocalDate createdOn;
	private String updatedBy;
	private LocalDate updatedOn;
	

}
