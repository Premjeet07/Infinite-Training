package com.java.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.java.model.CrimeIncidents;
import com.java.model.CrimeReports;
import com.java.util.ConnectionHelper;

public class CrimeDaoImpl implements CrimeDao {

    Connection conn;
    PreparedStatement pst;
    String sql;

    @Override
    public List<CrimeIncidents> getAllCrimeRecords() throws SQLException, ClassNotFoundException {
        List<CrimeIncidents> list = new ArrayList<>();
        sql = "SELECT * FROM Incidents";
        conn = ConnectionHelper.getConnection();
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            CrimeIncidents inc = new CrimeIncidents();
            inc.setIncidentId(rs.getInt("IncidentID"));
            inc.setIncidentType(rs.getString("IncidentType"));
            inc.setIncidentDate(rs.getDate("IncidentDate"));
            inc.setLatitude(rs.getDouble("Latitude"));
            inc.setLongitude(rs.getDouble("Longitude"));
            inc.setDescription(rs.getString("Description"));
            inc.setStatus(rs.getString("Status"));
            inc.setVictimId(rs.getInt("VictimID"));
            inc.setSuspectId(rs.getInt("SuspectID"));
            inc.setOfficerId(rs.getInt("OfficerID"));
            list.add(inc);
        }
        return list;
    }

    @Override
    public Collection<CrimeIncidents> findCrimesByType(String incidentType) throws ClassNotFoundException, SQLException {
        List<CrimeIncidents> resultList = new ArrayList<>();
        sql = "SELECT * FROM Incidents WHERE IncidentType LIKE ?";
        conn = ConnectionHelper.getConnection();
        pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + incidentType + "%");

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            CrimeIncidents inc = new CrimeIncidents();
            inc.setIncidentId(rs.getInt("IncidentID"));
            inc.setIncidentType(rs.getString("IncidentType"));
            inc.setIncidentDate(rs.getDate("IncidentDate"));
            inc.setLatitude(rs.getDouble("Latitude"));
            inc.setLongitude(rs.getDouble("Longitude"));
            inc.setDescription(rs.getString("Description"));
            inc.setStatus(rs.getString("Status"));
            inc.setVictimId(rs.getInt("VictimID"));
            inc.setSuspectId(rs.getInt("SuspectID"));
            inc.setOfficerId(rs.getInt("OfficerID"));
            resultList.add(inc);
        }
        return resultList;
    }

    @Override
    public boolean registerCrimeReport(CrimeIncidents incident) throws SQLException, ClassNotFoundException {
        sql = "INSERT INTO Incidents (IncidentID, IncidentType, IncidentDate, Latitude, Longitude, Description, Status, VictimID, SuspectID, OfficerID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        conn = ConnectionHelper.getConnection();
        pst = conn.prepareStatement(sql);
        pst.setInt(1, incident.getIncidentId());
        pst.setString(2, incident.getIncidentType());
        pst.setDate(3, incident.getIncidentDate());
        pst.setDouble(4, incident.getLatitude());
        pst.setDouble(5, incident.getLongitude());
        pst.setString(6, incident.getDescription());
        pst.setString(7, incident.getStatus());
        pst.setInt(8, incident.getVictimId());
        pst.setInt(9, incident.getSuspectId());
        pst.setInt(10, incident.getOfficerId());

        int rows = pst.executeUpdate();
        return rows > 0;
    }

    @Override
    public Collection<CrimeIncidents> getCrimeReportsByDateRange(Date startDate, Date endDate) throws SQLException, ClassNotFoundException {
        List<CrimeIncidents> list = new ArrayList<>();
        sql = "SELECT * FROM Incidents WHERE IncidentDate BETWEEN ? AND ?";
        conn = ConnectionHelper.getConnection();
        pst = conn.prepareStatement(sql);
        pst.setDate(1, startDate);
        pst.setDate(2, endDate);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            CrimeIncidents inc = new CrimeIncidents();
            inc.setIncidentId(rs.getInt("IncidentID"));
            inc.setIncidentType(rs.getString("IncidentType"));
            inc.setIncidentDate(rs.getDate("IncidentDate"));
            inc.setLatitude(rs.getDouble("Latitude"));
            inc.setLongitude(rs.getDouble("Longitude"));
            inc.setDescription(rs.getString("Description"));
            inc.setStatus(rs.getString("Status"));
            inc.setVictimId(rs.getInt("VictimID"));
            inc.setSuspectId(rs.getInt("SuspectID"));
            inc.setOfficerId(rs.getInt("OfficerID"));
            list.add(inc);
        }
        return list;
    }

    @Override
    public boolean updateCrimeCaseStatus(int incidentId, String status) throws ClassNotFoundException, SQLException {
        sql = "UPDATE Incidents SET Status = ? WHERE IncidentID = ?";
        conn = ConnectionHelper.getConnection();
        pst = conn.prepareStatement(sql);
        pst.setString(1, status);
        pst.setInt(2, incidentId);
        return pst.executeUpdate() > 0;
    }

    @Override
    public CrimeReports generateIncidentReportById(CrimeIncidents incident) throws SQLException, ClassNotFoundException {
        sql = "SELECT * FROM Reports WHERE IncidentID = ?";
        conn = ConnectionHelper.getConnection();
        pst = conn.prepareStatement(sql);
        pst.setInt(1, incident.getIncidentId());

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            CrimeReports report = new CrimeReports();
            report.setReportId(rs.getInt("ReportID"));
            report.setIncidentId(rs.getInt("IncidentID"));
            report.setReportingOfficer(rs.getInt("ReportingOfficer"));
            report.setReportDate(rs.getDate("ReportDate"));
            report.setReportDetails(rs.getString("ReportDetails"));
            report.setStatus(rs.getString("Status"));
            return report;
        }
        return null;
    }

    @Override
    public List<CrimeReports> showAllReportSummary() throws SQLException, ClassNotFoundException {
        List<CrimeReports> list = new ArrayList<>();
        sql = "SELECT * FROM Reports";
        conn = ConnectionHelper.getConnection();
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            CrimeReports report = new CrimeReports();
            report.setReportId(rs.getInt("ReportID"));
            report.setIncidentId(rs.getInt("IncidentID"));
            report.setReportingOfficer(rs.getInt("ReportingOfficer"));
            report.setReportDate(rs.getDate("ReportDate"));
            report.setReportDetails(rs.getString("ReportDetails"));
            report.setStatus(rs.getString("Status"));
            list.add(report);
        }
        return list;
    }
}
