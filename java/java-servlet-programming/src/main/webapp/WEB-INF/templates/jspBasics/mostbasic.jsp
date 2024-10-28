<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Random" %>
<%@ page isErrorPage="false" %>
<!--Check page directive additional parameters-->
<html>
    <head>
        <title>Title</title>
    </head>
<body style="background:powderblue">
    <!--This is a declarative tag (declare methods and variables)-->
    <%@include file="headerForBasicPage.jsp"%>

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

    <%
           Random r = new Random();
           int n = r.nextInt(10);
           printWriter.println(n);
    %>
       <br>
    <%= n %>
</body>
</html>
