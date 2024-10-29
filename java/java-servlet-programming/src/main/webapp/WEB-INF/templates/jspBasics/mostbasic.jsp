<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Random" %> <!--This is to include imports-->
<%@ page isErrorPage="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> <!--This is a jstl core taglib-->
<%@ taglib prefix="t" uri="/WEB-INF/tlds/mylib" %> <!--This is a user defined taglib-->
<%@ page errorPage="errorPage.jsp" %> <!--This custom error page is called when there is any sort of error-->
<!--Check page directive additional parameters-->
<html>
    <head>
        <title>Title</title>
    </head>
    <body style="background:powderblue">

        <%@include file="headerForBasicPage.jsp"%> <!--Demonstration of include directive-->

        <!--This is a declarative tag (declare methods and variables)-->
        <%!
            int a = 10;
            public int seeA(){
            return a;
            }

            int n1 = 10;
            int n2 = 10;
            public int divide(){
                return n1/n2;
            }
        %>
        <!-- This is a scriplet tag (You can write processing logic here)-->
        <%
            out.println(seeA());
            out.println("<br><br>");
            out.println(divide());
        %>
        <!--This is an expression tag alternative of scriplet tag-->
        <h1>Value of a is  <%= seeA()%>
        <%
           Random r = new Random();
           int n = r.nextInt(10);
           out.println(n);
        %>
       <br>
       <h1> Random n is <%= n %> </h1>
        <c:set var="name" value="hello agad"></c:set> <!--One of the many core jstl tag functionalities-->
        <c:out value="${name}"></c:out>
        <br>
        <br>

        <t:my_tag number="10" color="red"></t:my_tag> <!--Custom taglib with attributes-->
    </body>
</html>