<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>${  student }</h1>
  <form action="${pageContext.request.contextPath}/goBackToIndexFromStudentSuccessPage" method="post">
      <button type="submit" class="btn btn-primary">Go Back to index</button>
  </form>
</body>
</html>
