package com.java.hms.model;
 
public class DoctorDetails {
	
	private String doctorId;
	private String doctorName;
	private Specialization specialization;
	private String location;
	private String mobNo;
	private Status status;
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Specialization getSpecialization() {
		return specialization;
	}
	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", specialization=" + specialization
				+ ", location=" + location + ", mobNo=" + mobNo + ", status=" + status + "]";
	}
	public DoctorDetails(String doctorId, String doctorName, Specialization specialization, String location, String mobNo,
			Status status) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.location = location;
		this.mobNo = mobNo;
		this.status = status;
	}
	public DoctorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
 
}
 
 