<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Acasa</title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>

    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
            integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
    <script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
</head>

<body>
<div class="generic-container">
    <%@include file="../authheader.jsp" %>

    <div class="col-md-8 col-md-offset-2">
        <form:form action="/group/stud">
            <div class="row">
                <div class="col-md-6">
                    Toti studentii neasignati
                    <form:select path="selected">
                        <form:options items="${allStuds}"/>
                    </form:select>
                </div>

                <div class="col-md-6">
                    <c:choose>
                        <c:when test="${empty studs}">
                            <h4>Nu sunt studenti</h4> <br/>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Nume</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${studs}" var="stud" varStatus="idx">
                                    <tr class="clickableRow">
                                        <td>${idx.index + 1}</td>
                                        <td>${stud.name}</td>
                                        <td>
                                            <a href="/group/stud/remove-${stud.id}-${group.id}">
                                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
                    <%-- students div --%>
            </div>
        </form:form>
    </div>

</div>

</body>
</html>
