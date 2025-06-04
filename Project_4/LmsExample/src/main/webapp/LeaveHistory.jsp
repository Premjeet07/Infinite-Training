<%@ page import="com.java.lms.model.Employee, com.java.lms.model.LeaveHistory" %>
<%@ page import="java.util.*, com.java.lms.dao.EmployeeDAOImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Info and Leave History</title>
</head>
<body>
    <%
        // Retrieve empId from request parameter
        String empIdStr = request.getParameter("empId");
        int empId = Integer.parseInt(empIdStr);

        // DAO instance to fetch employee data
        EmployeeDAOImpl dao = new EmployeeDAOImpl();

        // Get employee information and leave history
        Employee empInfo = dao.searchEmployeeDao(empId);
        List<LeaveHistory> leaveHistoryList = dao.getLeaveHistory(empId);
    %>

    <h2 align="center">Employee Information</h2>
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
            <td>Mobile No</td>
            <td><%= empInfo.getMobileNo() %></td>
        </tr>
        <tr>
            <td>Department</td>
            <td><%= empInfo.getDept() %></td>
        </tr>
        <tr>
            <td>Date of Join</td>
            <td><%= empInfo.getDateOfJoin() %></td>
        </tr>
        <tr>
            <td>Manager Id</td>
            <td><%= empInfo.getManagerId() %></td>
        </tr>
        <tr>
            <td>Leave Available Balance</td>
            <td><%= empInfo.getLeaveAvail() %></td>
        </tr>
    </table>

    <br/>

    <h2 align="center">Leave History</h2>
    <table border="3" align="center">
        <tr>
            <th>Leave Id</th>
            <th>Employee Id</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Leave Type</th>
            <th>Leave Status</th>
        </tr>

        <c:forEach var="leave" items="${leaveHistoryList}">
            <tr>
                <td>${leave.leaveId}</td>
                <td>${leave.empId}</td>
                <td>${leave.startDate}</td>
                <td>${leave.endDate}</td>
                <td>${leave.leaveType}</td>
                <td>${leave.leaveStatus}</td>
            </tr>
        </c:forEach>
    </table>

    <br/>

    <center>
        <!-- Button to go back to dashboard -->
        <a href="DashBoard.jsp?empId=<%= empId %>">
            <button>Back to Dashboard</button>
        </a>
    </center>

</body>
</html>
