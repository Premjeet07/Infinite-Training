<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="AddAdmin.jsp">
		Admin Name : 
		<input type="text" name="username" /> <br/><br/>
		Password : 
		<input type="password" name="passcode" /> <br/><br/>
		Re-Type Password : 
		<input type="password" name="retypePassCode" /> <br/><br/> 
		<input type="submit" value="Create Admin Account" />
	</form>
	<%
		if (request.getParameter("username")!=null && 
			request.getParameter("passcode")!=null
				) {
			String pwd = request.getParameter("passcode");
			String reType = request.getParameter("retypePassCode");
			if (pwd.equals(reType)) {
	%>
	<jsp:useBean id="AdminLogin" class="com.java.lib.model.AdminLogin"/>
	<jsp:useBean id="AdminDao" class="com.java.lib.dao.LibraryDaoImpl"/>
		<jsp:setProperty property="*" name="AdminLogin"/>
	<%=AdminDao.CreateAdmin(AdminLogin) %>
	<%
			}
		}
	%>

</body>
</html>