<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>File Upload Success</title>
    <link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/core.css' />" rel="stylesheet"></link>
</head>
<body>
<div class="success">
    <c:forEach var="fileName" items="${fileNames}">
        File  <strong>${fileName}</strong> uploaded successfully<br/>
    </c:forEach>
    <br/>

    <c:forEach var="auditorium" items="${auditoriums}">
        Auditorium  <strong>${auditorium.name}</strong> uploaded successfully<br/>
    </c:forEach>
    <a href="<c:url value='/cinema/testinit' />">Home</a>
</div>
</body>
</html>