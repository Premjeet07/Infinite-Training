package com.java.model;

import java.sql.Date;

import lombok.Data;

@Data
public class CrimeIncidents {

	private int incidentId;
	private String incidentType;
	private Date incidentDate;
	private double latitude;
	private double longitude;
	private String description;
	private String status;
	private int victimId;
	private int suspectId;
	private int officerId;

}
