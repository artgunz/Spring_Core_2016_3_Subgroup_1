<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring 4 MVC File Upload Example</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/core.css' />" rel="stylesheet"></link>
</head>
<body>
<div class="form-container">
    <h1>Welcome to Auditorium FileUploader</h1>

    ONLY CSV!!!! <br/><br/>

    <a href="<c:url value='/singleUpload' />">Single File Upload</a>  OR  <a href="<c:url value='multiUpload' />">Multi File Upload</a>

    <br/><br/>
    <a href="<c:url value='/cinema/testinit' />">Home</a>
</div>
</body>
</html>