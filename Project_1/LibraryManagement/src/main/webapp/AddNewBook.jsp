<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.java.lib.dao.LibraryDaoImpl, com.java.lib.model.Books" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add New Book</title>
</head>
<body>
    <jsp:include page="AdminMenu.jsp"/> 

    <center>
        <h2>Add New Book</h2>
        <form method="post" action="AddNewBook.jsp">
            Book Name: <input type="text" name="name" value="<%= request.getParameter("name") %>" required><br/><br/>
            Author: <input type="text" name="author" value="<%= request.getParameter("author") %>" required><br/><br/>
            Edition: <input type="text" name="edition" required><br/><br/>
            Department: <input type="text" name="dept" required><br/><br/>
            Total Books: <input type="number" name="totalBooks" required><br/><br/>
            <input type="submit" value="Add New Book">
        </form>

<%
    String name = request.getParameter("name");
    String author = request.getParameter("author");
    String edition = request.getParameter("edition");
    String dept = request.getParameter("dept");
    String totalBooksParam = request.getParameter("totalBooks");

    if (name != null && author != null && edition != null && dept != null && totalBooksParam != null) {
        int totalBooks = Integer.parseInt(totalBooksParam);
        Books newBook = new Books();
        newBook.setName(name);
        newBook.setAuthor(author);
        newBook.setEdition(edition);
        newBook.setDept(dept);
        newBook.setNoOfBooks(totalBooks);

        LibraryDaoImpl dao = new LibraryDaoImpl();
        String result = dao.addOrUpdateBook(newBook); // Method to add a new book or update the quantity
        out.println("<h3>" + result + "</h3>");
    }
%>

    </center>
</body>
</html>
