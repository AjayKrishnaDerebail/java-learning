<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Random" %> <!--This is to include imports-->
<%@ page isErrorPage="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> <!--This is a jstl core taglib-->
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
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

        <h1>Jstl tags</h1>
        <%--JSTL tags--%>

        <%--1. Set tag--%>
        <c:set var="i" value="10"></c:set>

        <%--2. Out tag--%>
        <h1><c:out value="${i}"></c:out> </h1>

        <%--3. Remove tag--%>
        <c:remove var="i"></c:remove>

        <h1><c:out value="${i}">Default value</c:out> </h1>

        <hr>

        <c:set var="i" value="10"></c:set>

        <%--4. If tag--%>
        <c:if test="${i>9}">
            <h1>I am greater than 9</h1>
        </c:if>

        <%--5. For each--%>

        <c:forEach var="j" begin="1" end="10">
        <%--6. Switch tag (here choose)--%>
            <c:choose>
                <c:when test="${j == 9}">
                    <h2>Hello 9th iteration</h2>
                </c:when>
                <c:otherwise>
                    <p><c:out value="${j}"></c:out></p>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <%--7. Function tags--%>
        <c:set var="a" value="Hello Pink Floyd"></c:set>

        <p><c:out value="${fn:contains(a,'Pink')}"></c:out></p>

        <p><c:out value="${fn:toUpperCase(a)}"></c:out></p>
    </body>
</html>