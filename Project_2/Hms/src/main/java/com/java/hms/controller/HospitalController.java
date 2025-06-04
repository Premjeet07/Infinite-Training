package com.java.hms.controller;

import java.util.List;
import com.java.hms.dao.HospitalDao;
import com.java.hms.model.DoctorDetails;

public class HospitalController {

    private HospitalDao hospitalDao;
    private DoctorDetails doctorDetails;
    private String selectedCategory;
    private List<DoctorDetails> filteredDoctors;
    private String searchId;
    private DoctorDetails foundDoctor;
    private boolean searchPerformed;
    private String successMessage;

    // Setters (required for JSF managed properties)
    public void setHospitalDao(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    public void setDoctorDetails(DoctorDetails doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    // Getters
    public HospitalDao getHospitalDao() {
        return hospitalDao;
    }

    public DoctorDetails getDoctorDetails() {
        return doctorDetails;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public List<DoctorDetails> getFilteredDoctors() {
        return filteredDoctors;
    }

    public void setFilteredDoctors(List<DoctorDetails> filteredDoctors) {
        this.filteredDoctors = filteredDoctors;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public DoctorDetails getFoundDoctor() {
        return foundDoctor;
    }

    public void setFoundDoctor(DoctorDetails foundDoctor) {
        this.foundDoctor = foundDoctor;
    }

    public boolean isSearchPerformed() {
        return searchPerformed;
    }

    public void setSearchPerformed(boolean searchPerformed) {
        this.searchPerformed = searchPerformed;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    // Controller Actions
    public List<DoctorDetails> showDoctor() {
        return hospitalDao.showDoctorDao();
    }

    public String filterByCategory() {
        if (selectedCategory != null && !selectedCategory.isEmpty()) {
            filteredDoctors = hospitalDao.findBySpecialization(selectedCategory);
        } else {
            filteredDoctors = null;
        }
        return null;
    }

    public String searchDoctorById() {
        searchPerformed = true;
        foundDoctor = hospitalDao.findById(searchId);
        return null;
    }

    public String addDoctor() {
        hospitalDao.addDoctor(doctorDetails);
        successMessage = "Doctor added successfully. Doctor ID: " + doctorDetails.getDoctorId();
        doctorDetails = new DoctorDetails();  // Reset form
        return "Home.jsp?faces-redirect=true";
    }
}
