<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading">
        <span class="lead">Lista cu cele mai folosite Concepte</span>
    </div>

    <c:choose>
        <c:when test="${empty exams}">
            <h4>Nu sunt concepte</h4> <br/>
            <a href="/exam" class="btn btn-primary">Adaugati</a>
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
                        <td>${exam.name}</td>
                        <td>${exam.items}</td>
                        <td>${exam.points}</td>
                        <td>${exam.difficulty}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

</div>
