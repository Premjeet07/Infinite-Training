<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.java.lms.model.Employee" %>
<%@ page import="com.java.lms.dao.EmployeeDAOImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
</head>
<body>

    <h2 align="center">Welcome to the Dashboard</h2>
    
    <%
        // Retrieve empId from request parameter to show employee's data
        String empIdStr = request.getParameter("empId");
        
        // Check if empId is null or empty
        if (empIdStr == null || empIdStr.isEmpty()) {
            out.println("<h3>Error: empId parameter is missing or invalid</h3>");
            return;  // Return early to avoid processing further
        }
        
        // Parse the empId safely
        int empId = Integer.parseInt(empIdStr);
        
        // Get employee information
        EmployeeDAOImpl dao = new EmployeeDAOImpl();
        Employee empInfo = dao.searchEmployeeDao(empId);
        
        if (empInfo == null) {
            out.println("<h3>No employee found with ID: " + empId + "</h3>");
            return;
        }
    %>

    <table border="3" align="center">
        <tr>
            <td>Employee Id</td>
            <td><%= empInfo.getEmpId() %></td>
        </tr>
        <tr>
            <td>Employee Name</td>
            <td><%= empInfo.getEmpName() %></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><%= empInfo.getEmpEmail() %></td>
        </tr>
        <tr>
            <td>Department</td>
            <td><%= empInfo.getDept() %></td>
        </tr>
    </table>

    <br/>
    
    <!-- Button to navigate to the combined page showing Employee Info and Leave History -->
    <center>
        <a href="LeaveHistory.jsp?empId=<%= empId %>">
            <button>Show Info </button>
        </a>
    </center>

</body>
</html>
