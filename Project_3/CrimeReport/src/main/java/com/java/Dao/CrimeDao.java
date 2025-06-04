package com.java.Dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.java.model.CrimeIncidents;
import com.java.model.CrimeReports;

public interface CrimeDao {

    // Existing method to get all crime records
    List<CrimeIncidents> getAllCrimeRecords() throws SQLException, ClassNotFoundException;

    // Existing method to find crimes by type
    Collection<CrimeIncidents> findCrimesByType(String incidentType) throws ClassNotFoundException, SQLException;

    // Method to register crime reports
    boolean registerCrimeReport(CrimeIncidents incident) throws SQLException, ClassNotFoundException;

    // Updated method to get crimes in a date range
    Collection<CrimeIncidents> getCrimeReportsByDateRange(Date startDate, Date endDate) throws SQLException, ClassNotFoundException;

    // Existing method to update crime case status
    boolean updateCrimeCaseStatus(int incidentId, String status) throws ClassNotFoundException, SQLException;

    // Existing method to generate a report by incident ID
    CrimeReports generateIncidentReportById(CrimeIncidents incident) throws SQLException, ClassNotFoundException;

    // Method to show all report summaries
    List<CrimeReports> showAllReportSummary() throws SQLException, ClassNotFoundException;

}


