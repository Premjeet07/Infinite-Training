<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Admin Account Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f9f9f9;
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #4CAF50;
            color: white;
            padding: 20px 40px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .welcome {
            font-size: 20px;
        }

        .logout-btn {
            background-color: #e60000;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            font-size: 14px;
            border-radius: 5px;
        }

        .logout-btn:hover {
            background-color: #cc0000;
        }

        .menu-container {
            margin: 60px auto;
            text-align: center;
        }

        .menu-button {
            background-color: #ffffff;
            border: 2px solid #4CAF50;
            color: #4CAF50;
            padding: 15px 30px;
            margin: 15px;
            font-size: 18px;
            border-radius: 8px;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-block;
        }

        .menu-button:hover {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>

<%
    String user = (String) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.html");
        return;
    }
%>

<div class="header">
    <div class="welcome">Welcome, <b><%= user %></b></div>
    <form action="index.html" method="get">
        <input type="submit" value="Logout" class="logout-btn" />
    </form>
</div>

<div class="menu-container">
    <a href="CheckBook.jsp" class="menu-button">Add Book</a>
    <a href="BookStatus.jsp" class="menu-button">Book Status</a>
    <a href="UserIssuedBook.jsp" class="menu-button">User Issued Books</a>
</div>

</body>
</html>
