package com.java.hms.dao;

import java.util.List;

import com.java.hms.model.DoctorDetails;

public interface HospitalDao {
	
	List<DoctorDetails> showDoctorDao();
	List<DoctorDetails> findBySpecialization(String category);
	DoctorDetails findById(String docId);
	String addDoctor(DoctorDetails doctor);

}
