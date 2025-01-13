<html>
<body>
<h2>Hello World!</h2>
<%
    String message = (String) request.getAttribute("message");
%>
    <h1>Message is <%= message %></h1>
</body>
</html>
