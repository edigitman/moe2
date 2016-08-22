<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Acasa</title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>

<body>
<div class="generic-container">
    <%@include file="authheader.jsp" %>

    <div class="col-md-8 col-md-offset-2">

        <c:if test="${success}">
            <div class="alert alert-success lead">
                    ${success}
            </div>
        </c:if>


        <sec:authorize access="hasRole('ADMIN')">
            <%@include file="homeAdmin.jsp" %>
        </sec:authorize>
        <sec:authorize access="hasRole('PROFESSOR')">
            <%@include file="homeProfesor.jsp" %>
        </sec:authorize>
        <sec:authorize access="hasRole('STUDENT')">
            <%@include file="homeStdudent.jsp" %>
        </sec:authorize>
    </div>

</div>
</body>
</html>