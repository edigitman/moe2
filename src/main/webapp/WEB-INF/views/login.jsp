<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Pagina de conecate</title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />"  rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>

<body>
<div id="mainWrapper">

    <div class="login-container col-md-3 col-md-offset-4">
        <div class="row textAlignCenter">
            <h4>My online Exams</h4>
        </div>
        <div class="login-card">
            <div class="login-form">
                <c:url var="loginUrl" value="/login" />
                <form action="${loginUrl}" method="post" class="form-horizontal">
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <p>Utilizator si parola invalide.</p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">
                            <p>Ai fost deconectat cu succes.</p>
                        </div>
                    </c:if>
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="username">
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span></label>
                        <input type="text" class="form-control" id="username" name="ssoId" placeholder="Utilizator" required autofocus>
                    </div>
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="password"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Parola" required>
                    </div>
                    <div class="input-group input-sm">
                        <div class="checkbox">
                            <label><input type="checkbox" id="rememberme" name="remember-me"> Tine-ma minte</label>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />

                    <div class="form-actions">
                        <input type="submit"
                               class="btn btn-block btn-primary btn-default"
                               value="Conectare">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>