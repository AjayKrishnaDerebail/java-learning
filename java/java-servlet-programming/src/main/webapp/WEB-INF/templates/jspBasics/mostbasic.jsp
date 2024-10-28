<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
<body style="background:powderblue">
    <!--This is a declarative tag (declare methods and variables)-->
    <%!
    int a = 10;
    public int seeA(){
        return a;
    }
    %>
    <!-- This is a scriplet tag (You can write processing logic here)-->
    <%
        PrintWriter printWriter = response.getWriter();
        printWriter.println(seeA());
    %>
    <!--This is an expression tag alternative of scriplet tag-->
   <h1>Value of a is  <%= seeA()%>
</body>
</html>
