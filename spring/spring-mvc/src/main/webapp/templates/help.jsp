<%@ page import="org.springframework.web.servlet.ModelAndView" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Help page</h1>
    <%
        String messageFromHelp = (String) request.getAttribute("messageFromHelp");
    %>
    <h1>Message is <%= messageFromHelp %></h1>
</body>
</html>
