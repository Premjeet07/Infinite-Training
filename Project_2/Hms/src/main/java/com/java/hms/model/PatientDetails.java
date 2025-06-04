package com.java.hms.model;
 
import java.sql.Date;
 
public class PatientDetails {
//
//    PatientId varchar(30) primary key,
//    PatientName varchar(30) not null,
//    DoctorId varchar(30) REFERENCES Doctor(DoctorId),
//    DateOfBirth Date not null
	
	private String patientId;
	private String patientName;
	private String doctorId;
	private Date dob;
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", doctorId=" + doctorId + ", dob="
				+ dob + "]";
	}
	public PatientDetails(String patientId, String patientName, String doctorId, Date dob) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.doctorId = doctorId;
		this.dob = dob;
	}
	public PatientDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
}
 
 