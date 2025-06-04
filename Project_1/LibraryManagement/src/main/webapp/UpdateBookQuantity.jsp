<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.java.lib.dao.LibraryDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Update Book Quantity</title>
</head>
<body>
<%
    String bookIdParam = request.getParameter("bookId");
    String quantityParam = request.getParameter("quantity");

    if (bookIdParam != null && quantityParam != null) {
        int bookId = Integer.parseInt(bookIdParam);
        int quantity = Integer.parseInt(quantityParam);

        LibraryDaoImpl dao = new LibraryDaoImpl();
        String result = dao.updateBookQuantity(bookId, quantity);

        out.println("<h3>" + result + "</h3>");
    } else {
        out.println("<h3>Missing book ID or quantity!</h3>");
    }
%>
</body>
</html>
