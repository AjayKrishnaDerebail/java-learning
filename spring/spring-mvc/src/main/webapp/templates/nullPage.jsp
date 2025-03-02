<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
    <div> This is error page </div>
    <form action="${pageContext.request.contextPath}/goBackToIndex" method="post">
        <h4>Click below button to go back to index page</h4>
        <div class="container text-center">
            <button class="btn btn-outline-light">Go Back</button>
        </div>
    </form>
    <div>
        ${nullException}
    </div>
</body>
</html>
