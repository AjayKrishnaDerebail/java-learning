<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>File uploaded successfully</h1>
    <img alt="altTextForFileUpload" width="1500px" height="1500px" src="<c:url value="/static/images/${fileName}" />">
</body>
</html>
