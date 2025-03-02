<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File uploading</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
    <h1>Upload image here</h1>
    <div class="container p-5">
        <form class="" action="${pageContext.request.contextPath}/uploadImage" method="post"
              enctype="multipart/form-data">
        <div class="form-group">
                <div><label for="image">Upload image</label></div>
                <br>
                <div><input type="file" name="profile" class="form-control-file" id="image"></div>
                <br>
                <button class="btn btn-outline-success" type="submit">Submit</button>
            </div>
        </form>
    </div>
</body>
</html>
