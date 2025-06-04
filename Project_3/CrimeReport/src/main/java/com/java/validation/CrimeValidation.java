package com.java.validation;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.java.Dao.CrimeDao;
import com.java.Dao.CrimeDaoImpl;
import com.java.model.CrimeIncidents;
import com.java.model.CrimeReports;

public class CrimeValidation {

    static CrimeDao dao;

    static {
        dao = new CrimeDaoImpl();
    }

    public List<CrimeIncidents> getAllIncidents() throws SQLException, ClassNotFoundException {
        return dao.getAllCrimeRecords();
    }

    public Collection<CrimeIncidents> searchIncidentByType(String incidentType) throws SQLException, ClassNotFoundException {
        return dao.findCrimesByType(incidentType);
    }

    // Method to create an incident
    public String createIncident(CrimeIncidents incident) {
        try {
            boolean result = dao.registerCrimeReport(incident);
            return result ? "Incident registered successfully." : "Incident registration failed.";
        } catch (Exception e) {
            return "Error while registering incident: " + e.getMessage();
        }
    }

    // Method to generate a report by incident ID
    public CrimeReports generateIncidentReport(CrimeIncidents incident) throws SQLException, ClassNotFoundException {
        return dao.generateIncidentReportById(incident);
    }

    // Method to get all report summaries
    public List<CrimeReports> getReportSummary() throws SQLException, ClassNotFoundException {
        return dao.showAllReportSummary();
    }

    // Validate date format (YYYY-MM-DD)
    public Date validateDate(String input) {
        try {
            return Date.valueOf(input); // Converts YYYY-MM-DD to java.sql.Date
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return null;
        }
    }

    // Method to get incidents in a date range
    public Collection<CrimeIncidents> getIncidentsInRange(String startInput, String endInput) throws SQLException, ClassNotFoundException {
        try {
            Date startDate = Date.valueOf(startInput); 
            Date endDate = Date.valueOf(endInput);

            // Check if the start date is after the end date
            if (startDate.after(endDate)) {
                System.out.println("Start date cannot be after end date.");
                return Collections.emptyList();
            }

            // Fetch incidents between the given dates
            return dao.getCrimeReportsByDateRange(startDate, endDate);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Please enter dates in YYYY-MM-DD format.");
            return Collections.emptyList();

        } catch (Exception e) {
            System.out.println("Unexpected error fetching incidents: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // Method to validate and update incident status
    public boolean updateIncidentValid(int incidentId, String status) throws SQLException, ClassNotFoundException {
        if (statusUpdateVal(status)) {
            return dao.updateCrimeCaseStatus(incidentId, status);
        }
        return false;
    }

    // Validate incident status (Open, Under Investigation, Closed, Resolved)
    public boolean statusUpdateVal(String status) {
        List<String> validStatuses = Arrays.asList("Open", "Under Investigation", "Closed", "Resolved");
        if (status == null || !validStatuses.contains(status)) {
            return false;
        }
        return true;
    }
}
