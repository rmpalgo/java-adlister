<%--
  Created by IntelliJ IDEA.
  User: rpal
  Date: 6/4/20
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(request.getMethod().equalsIgnoreCase("post")) {
        if(request.getParameter("username").equalsIgnoreCase("admin") && request.getParameter("password").equalsIgnoreCase("password")) {
            String redirectURL = "/profile.jsp";
            response.sendRedirect(redirectURL);
        }
    }
%>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<!-- This is an HTML comment, will render on page source, form structure-->
<form method="POST" action="/login.jsp">

    <label for="username">Username</label>
    <input id="username" name="username" type="text">
    <br>

    <label for="password">Password</label>
    <input id="password" name="password" type="password">
    <br>

    <input type="submit">



</form>

</body>
</html>
