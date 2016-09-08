<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Examene</title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>

    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
            integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
    <script src="<c:url value='/static/js/bootstrap.min.js' />"></script>

    <sec:csrfMetaTags/>
</head>

<body>
<div class="generic-container">
    <%@include file="../authheader.jsp" %>

    <div class="col-md-8 col-md-offset-2">

        <h2>Lista de examen</h2>
        <c:choose>
            <c:when test="${empty exams}">
                <h4>Nu sunt concepte</h4> <br/>
            </c:when>
            <c:otherwise>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Nume</th>
                        <th>Nr. Subiecte</th>
                        <th>Nr. Puncte</th>
                        <th>Dificultate</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${exams}" var="exam">
                        <tr class="clickableRow">
                            <td><a href="/exam/edit-${exam.id}">${exam.name}</a></td>
                            <td>${exam.items}</td>
                            <td>${exam.points}</td>
                            <td>${exam.difficulty}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <div class="row">
            <!-- Button trigger modal -->

        </div>

        <div class="row">
            <a href="/home" class="btn btn-link">Inapoi</a>

            <button type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#examModal">
                Adauga Concept
            </button>
        </div>
    </div>
</div>

<!-- Modal Add Exam -->
<div class="modal fade" id="examModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form:form method="post" modelAttribute="exam">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">Adauga Examen</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="nume">Nume</label>
                        <input type="text" class="form-control" name="name" id="nume"/>
                    </div>
                        <%--<div class="form-group">--%>
                        <%--<label for="cloneId">Copie dupa examen (optional)</label>--%>
                        <%--<select class="form-control" name="cloneId" id="cloneId">--%>
                        <%--<option value="0"></option>--%>
                        <%--<c:forEach items="${concepts}" var="c">--%>
                        <%--<option value="${c.id}">${c.name}</option>--%>
                        <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--</div>--%>
                    <div class="form-group">
                        <label for="difficulty">Dificultate</label>
                        <select class="form-control" name="difficulty" id="difficulty">
                            <option value="EASY">Usor</option>
                            <option value="MEDIUM">Mediu</option>
                            <option value="HARD">Dificil</option>
                        </select>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-link" data-dismiss="modal">Inchide</button>
                    <button type="submit" class="btn btn-primary">Salveaza</button>
                </div>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>