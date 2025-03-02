<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <title>Index page</title>
    <link href="<c:url value="/static/styles/styles.css" />" rel="stylesheet" />
    <script src="<c:url value="/static/javascript/script.js" />"></script>
</head>
<body>
<h2>Hello World!</h2>
    <h1>Message is ${message}</h1>
    <form action="${pageContext.request.contextPath}/searchApp" method="post">
        <h1> Click the submit button to go search page</h1>
        <button type="submit">Click to go to search page</button>
    </form>
</body>
</html>