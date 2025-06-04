<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Signup</title>
</head>
<body>
	<form method="post" action="AdminLogin.jsp">
		<center>
			<h2>Admin Signup</h2>
			Username : <input type="text" name="username" size="50"> <br />
			<br /> Password : <input type="password" name="passcode" size="50">
			<br />
			<br /> <input type="submit" value="Signup">
		</center>
	</form>

	<%
	if (request.getParameter("username") != null && request.getParameter("passcode") != null) {
		String username = request.getParameter("username");
		String passcode = request.getParameter("passcode");
	%>

	// Use JSP beans to handle the AdminLogin data // Create an instance of
	AdminLogin bean and set the properties from the form
	<jsp:useBean id="adminLogin" class="com.java.lib.model.AdminLogin" />
	<jsp:setProperty property="*" name="adminLogin" />
	<jsp:useBean id="AdminDao" class="com.java.lib.dao.LibraryDaoImpl" />

	<%
	int count = AdminDao.AdminSignup(adminLogin);
	if (count == 1) {
		session.setAttribute("user", request.getParameter("username"));
	%>
	<jsp:forward page="AdminMenu.jsp" />
	<%
	} else {
	out.println("Invalid Credentials...");
	}
	}
	%>

</body>
</html>
