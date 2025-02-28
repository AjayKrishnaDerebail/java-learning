<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Successful registration</h1>
    <h3>Welcome ${user.userName}</h3>
    <h3>Your email is ${user.email}</h3>
    <h3>Your password is ${user.password}</h3>
    <h3>Your user is ${successMessage}</h3>

    <p>${helpSection}</p>
</body>
</html>
