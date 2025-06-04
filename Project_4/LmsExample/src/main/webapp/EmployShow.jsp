<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>
<style>
	table {
		border-collapse: collapse;
		width: 90%;
		margin: 20px auto;
	}
	th, td {
		padding: 10px;
		text-align: center;
	}
	th {
		background-color: #f2f2f2;
	}
	tr:nth-child(even) {
		background-color: #f9f9f9;
	}
</style>
</head>
<body>
	<jsp:useBean id="employDao" class="com.java.lms.dao.EmployeeDAOImpl" />
	<c:set var="employList" value="${employDao.showEmployeeDao()}" />

	<h3 align="center">Total Employees: ${fn:length(employList)}</h3>

	<table border="3" align="center">
		<tr>
			<th>Employ Id</th>
			<th>Employee Name</th>
			<th>Employee Email</th>
			<th>Mobile No</th><%@ page import="com.java.lms.model.Employee, com.java.lms.model.LeaveHistory" %>
<%@ page import="java.util.*, com.java.lms.dao.EmployeeDAOImpl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Details</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }
        th, td {
            padding: 10px;
            border: 1px solid #aaa;
        }
        h2, h3 {
            text-align: center;
        }
    </style>
</head>
<body>

<%
    int empId = Integer.parseInt(request.getParameter("empId"));
    int mgrId = Integer.parseInt(request.getParameter("mgrId"));

    EmployeeDAOImpl dao = new EmployeeDAOImpl();
    Employee emp = dao.searchEmployeeDao(empId);
    Employee mgr = dao.searchEmployeeDao(mgrId);
    List<LeaveHistory> leaveList = dao.getLeaveHistory(empId);

    request.setAttribute("emp", emp);
    request.setAttribute("mgr", mgr);
    request.setAttribute("leaveList", leaveList);
%>

<h2>Employee and Manager Details</h2>

<table>
    <tr><th colspan="2">Employee Info</th></tr>
    <tr><td>Emp ID</td><td>${emp.empId}</td></tr>
    <tr><td>Name</td><td>${emp.empName}</td></tr>
    <tr><td>Email</td><td>${emp.empEmail}</td></tr>
    <tr><td>Mobile</td><td>${emp.mobileNo}</td></tr>
    <tr><td>Dept</td><td>${emp.dept}</td></tr>
    <tr><td>Join Date</td><td>${emp.dateOfJoin}</td></tr>
    <tr><td>Manager ID</td><td>${emp.managerId}</td></tr>
    <tr><td>Leave Balance</td><td>${emp.leaveAvail}</td></tr>

    <tr><th colspan="2">Manager Info</th></tr>
    <tr><td>Mgr ID</td><td>${mgr.empId}</td></tr>
    <tr><td>Name</td><td>${mgr.empName}</td></tr>
    <tr><td>Email</td><td>${mgr.empEmail}</td></tr>
    <tr><td>Mobile</td><td>${mgr.mobileNo}</td></tr>
    <tr><td>Dept</td><td>${mgr.dept}</td></tr>
</table>

<h3>Leave History</h3>

<table>
    <tr>
        <th>Leave ID</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Type</th>
        <th>Status</th>
    </tr>
    <c:forEach var="leave" items="${leaveList}">
        <tr>
            <td>${leave.leaveId}</td>
            <td>${leave.startDate}</td>
            <td>${leave.endDate}</td>
            <td>${leave.leaveType}</td>
            <td>${leave.leaveStatus}</td>
        </tr>
    </c:forEach>
</table>

<br><center><a href="EmployShow.jsp">Back to List</a></center>

</body>
</html>
			
			<th>Date of Join</th>
			<th>Department</th>
			<th>Manager Id</th>
			<th>Employee Available Balance</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="employ" items="${employList}">
			<tr>
				<td>${employ.empId}</td>
				<td>${employ.empName}</td>
				<td>${employ.empEmail}</td>
				<td>${employ.mobileNo}</td>
				<td>${employ.dateOfJoin}</td>
				<td>${employ.dept}</td>
				<td>${employ.managerId}</td>
				<td>${employ.leaveAvail}</td>
				<td>
					<a href="DashBoard.jsp?empId=${employ.empId}&mgrId=${employ.managerId}">Show Info</a> |
					<a href="LeaveHistory.jsp?empId=${employ.empId}">Leave History</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
