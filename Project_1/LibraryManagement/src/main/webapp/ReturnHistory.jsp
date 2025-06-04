<%@page import="com.java.lib.model.TranReturn"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	    <jsp:include page="Menu.jsp"></jsp:include>
    <h2 align="center">Return Book History</h2>
    
    <jsp:useBean id="LibraryDao" class="com.java.lib.dao.LibraryDaoImpl" />
    <%
        List<TranReturn> historyList = LibraryDao.returnHistory();
    %>
 
    <table border="2" align="center" cellpadding="10">
        <tr>
            <th>Username</th>
            <th>Book ID</th>
            <th>From Date</th> 
            <th>To Date</th>
        </tr>
        <%
            for (TranReturn tr : historyList) {
        %>
            <tr>
                <td><%= tr.getUsername() %></td>
                <td><%= tr.getBookId() %></td>
                <td><%= tr.getFromDate() %></td>
                <td><%= tr.getToDate() %></td>
            </tr>
        <%
            }
        %>
    </table>

</body>
</html>