<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: ajayk
  Date: 28-10-2024
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Header</h1>
<%
    String s = new Date().toString();
%>
<%= s %>
</body>
</html>
