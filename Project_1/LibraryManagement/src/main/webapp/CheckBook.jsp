<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List, com.java.lib.model.Books, com.java.lib.dao.LibraryDaoImpl" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Check Book</title>
</head>
<body>

<%
    String name = request.getParameter("name");
    String author = request.getParameter("author");

    boolean formSubmitted = name != null && author != null;
%>

<%-- Only include the menu and form if not submitted --%>
<% if (!formSubmitted) { %>
    <jsp:include page="AdminMenu.jsp"/> 
    <form method="post" action="CheckBook.jsp">
        <center>
            <h2>Check if Book Already Exists</h2>
            Book Name: <input type="text" name="name" required><br/><br/>
            Author: <input type="text" name="author" required><br/><br/>
            <input type="submit" value="Check Book">
        </center>
    </form>
<% } %>

<%-- Process submission separately --%>
<%
    if (formSubmitted) {
        LibraryDaoImpl dao = new LibraryDaoImpl();
        List<Books> list = dao.searchBooks("bookname", name);

        boolean exists = false;
        int bookId = 0;

        for (Books b : list) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                exists = true;
                bookId = b.getId();
                break;
            }
        }

        if (exists) {
%>
            <form method="post" action="UpdateBookQuantity.jsp">
                <input type="hidden" name="bookId" value="<%= bookId %>">
                <center>
                    <h3>Book already exists. Enter additional quantity:</h3>
                    No. of Books to Add: <input type="number" name="quantity" required><br/><br/>
                    <input type="submit" value="Update Quantity">
                </center>
            </form>
<%
        } else {
            // Forwarding correctly to AddNewBook.jsp with parameters
%>
            <jsp:forward page="AddNewBook.jsp">
                <jsp:param name="name" value="<%= name %>" />
                <jsp:param name="author" value="<%= author %>" />
            </jsp:forward>
<%
        }
    }
%>

</body>
</html>
