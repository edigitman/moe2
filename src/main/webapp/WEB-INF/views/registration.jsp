<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>

<body>
<div class="generic-container">
    <%@include file="authheader.jsp" %>

    <div class="col-md-8 col-md-offset-2">

        <div class="well lead">Formular adaugare utilizator nou</div>
        <form:form method="POST" modelAttribute="user" class="form-horizontal">
            <form:input type="hidden" path="id" id="id"/>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="firstName">Prenume</label>
                    <div class="col-md-7">
                        <form:input type="text" path="firstName" id="firstName" class="form-control"/>
                        <div class="has-error">
                            <form:errors path="firstName" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="lastName">Nume</label>
                    <div class="col-md-7">
                        <form:input type="text" path="lastName" id="lastName" class="form-control"/>
                        <div class="has-error">
                            <form:errors path="lastName" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="email">Email</label>
                    <div class="col-md-7">
                        <form:input type="text" path="email" id="email" class="form-control"/>
                        <div class="has-error">
                            <form:errors path="email" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-3 control-lable" for="userProfiles">Roles</label>
                    <div class="col-md-7">
                        <form:select path="userProfiles" items="${roles}" itemValue="id"
                                     itemLabel="type" class="form-control"/>
                        <div class="has-error">
                            <form:errors path="userProfiles" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="form-actions floatRight">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Modifica" class="btn btn-primary"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Adauga" class="btn btn-primary"/>
                        </c:otherwise>
                    </c:choose>
                    <span>sau</span> <a href="<c:url value='/home' />">Anuleaza</a>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>