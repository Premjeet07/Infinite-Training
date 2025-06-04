package com.java.main;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.java.Dao.CrimeDao;
import com.java.Dao.CrimeDaoImpl;
import com.java.validation.CrimeValidation;
import com.java.model.CrimeIncidents;
import com.java.model.CrimeReports;

public class CrimeMain {

    static CrimeValidation valid = new CrimeValidation();
    static Scanner sc = new Scanner(System.in);
    static CrimeDao d = new CrimeDaoImpl();

    public static void main(String[] args) {
        int choice = 0 ;

        do {
            try {
                System.out.println("======================================");
                System.out.println("  Crime Analysis & Reporting System");
                System.out.println("======================================");
                System.out.println("1. Show All Incidents");
                System.out.println("2. Search Incident by Type");
                System.out.println("3. Create an Incident");
                System.out.println("4. View Incident in Date Range");
                System.out.println("5. Update Incident Status");
                System.out.println("6. Show Report Summary by Incident");
                System.out.println("7. Show all Incident report");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        showAllIncidentsMain();
                        break;
                    case 2:
                        searchIncidentByTypeMain();
                        break;
                    case 3:
                        createIncidentMain();
                        break;
                    case 4:
                        sc.nextLine(); // Clear the buffer
                        showIncidentInDateRange();
                        break;
                    case 5:
                        updateIncidentTypeMain();
                        break;
                    case 6:
                        showReportSummary();
                        break;
                    case 7:
                        showAllIncidentReport();
                        break;
                    case 8:
                        System.out.println("Exiting application...");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        } while (choice != 8);
    }

    // Show All Incidents
    public static void showAllIncidentsMain() throws ClassNotFoundException, SQLException {
        Collection<CrimeIncidents> incList = valid.getAllIncidents();
        System.out.println("===========All Incident Details==========");
        for (CrimeIncidents inc : incList) {
            printIncident(inc);
        }
    }

    // Search Incident by Type
    public static void searchIncidentByTypeMain() throws SQLException, ClassNotFoundException {
        sc.nextLine(); // consume leftover newline
        System.out.print("Enter Incident Type: ");
        String type = sc.nextLine();
        Collection<CrimeIncidents> incidents = valid.searchIncidentByType(type);
        System.out.println("===========Incident By Type============");
        for (CrimeIncidents inc : incidents) {
            printIncident(inc);
        }
    }

    // Create an Incident
    public static void createIncidentMain() throws ClassNotFoundException, SQLException {
        CrimeIncidents inc = new CrimeIncidents();

        System.out.println("Enter Incident Id: ");
        inc.setIncidentId(sc.nextInt());
        sc.nextLine();

        System.out.println("Enter Incident Type: ");
        inc.setIncidentType(sc.nextLine());

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String dateInput = sc.nextLine();
        Date incidentDate = valid.validateDate(dateInput);
        if (incidentDate == null) {
            return;
        }
        inc.setIncidentDate(incidentDate);

        System.out.println("Enter Latitude: ");
        inc.setLatitude(sc.nextDouble());

        System.out.println("Enter Longitude: ");
        inc.setLongitude(sc.nextDouble());
        sc.nextLine();

        System.out.println("Enter Description: ");
        inc.setDescription(sc.nextLine());

        System.out.println("Enter Status of Incident: ");
        inc.setStatus(sc.nextLine());

        System.out.println("Enter Victim Id: ");
        inc.setVictimId(sc.nextInt());

        System.out.println("Enter Suspect Id: ");
        inc.setSuspectId(sc.nextInt());

        System.out.println("Enter Officer Id: ");
        inc.setOfficerId(sc.nextInt());

        String result = valid.createIncident(inc);
        System.out.println(result);
    }

    // View Incident in Date Range
    public static void showIncidentInDateRange() throws ClassNotFoundException, SQLException {
        System.out.println("Enter the start date in this format: (yyyy-mm-dd) ");
        String startDate = sc.nextLine();

        System.out.println("Enter the end date in this format: (yyyy-mm-dd)");
        String endDate = sc.nextLine();

        Collection<CrimeIncidents> inclist = valid.getIncidentsInRange(startDate, endDate);

        for (CrimeIncidents incident : inclist) {
            printIncident(incident);
        }
    }

    public static void updateIncidentTypeMain() {
        CrimeIncidents inc = new CrimeIncidents();
        System.out.println("Enter Incident Id:");
        int id = sc.nextInt();
        sc.nextLine(); 

        System.out.println("Enter Incident Type: (Open, Closed, Under Investigation) ");
        String status = sc.nextLine();

        try {
            boolean res = valid.updateIncidentValid(id, status);

            if (res) {
                System.out.println("Status updated successfully");
            } else {
                System.out.println("Status update failed");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error while updating status: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void showReportSummary() throws ClassNotFoundException, SQLException {
        System.out.print("Enter Incident ID to generate report: ");
        int incId = sc.nextInt();
        CrimeIncidents inc = new CrimeIncidents();
        inc.setIncidentId(incId);
        CrimeReports report = valid.generateIncidentReport(inc);
        printReport(report);
    }

    public static void showAllIncidentReport() throws ClassNotFoundException, SQLException {
        List<CrimeReports> reportlist = valid.getReportSummary();
        for (CrimeReports report : reportlist) {
            printReport(report);
        }
    }

    public static void printReport(CrimeReports report) {
        System.out.println("--------------Incident Report--------------");
        System.out.println("Report ID          : " + report.getReportId());
        System.out.println("Incident ID        : " + report.getIncidentId());
        System.out.println("Reporting Officer  : " + report.getReportingOfficer());
        System.out.println("Report Date        : " + report.getReportDate());
        System.out.println("Report Details     : " + report.getReportDetails());
        System.out.println("Status             : " + report.getStatus());
    }

    public static void printIncident(CrimeIncidents inc) {
        System.out.println("--------------------------------------------------");
        System.out.println("Incident ID    : " + inc.getIncidentId());
        System.out.println("Type           : " + inc.getIncidentType());
        System.out.println("Date           : " + inc.getIncidentDate());
        System.out.println("Latitude       : " + inc.getLatitude());
        System.out.println("Longitude      : " + inc.getLongitude());
        System.out.println("Description    : " + inc.getDescription());
        System.out.println("Status         : " + inc.getStatus());
        System.out.println("Victim ID      : " + inc.getVictimId());
        System.out.println("Suspect ID     : " + inc.getSuspectId());
        System.out.println("Officer ID     : " + inc.getOfficerId());
    }
}
