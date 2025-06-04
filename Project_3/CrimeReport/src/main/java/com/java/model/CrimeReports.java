package com.java.model;

import java.sql.Date;

import lombok.Data;

@Data
public class CrimeReports {

	private int reportId;
	private int incidentId;
	private int reportingOfficer;
	private Date reportDate;
	private String reportDetails;
	private String status;

}
