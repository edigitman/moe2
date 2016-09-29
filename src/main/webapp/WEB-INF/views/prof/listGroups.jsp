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

        <h2>Lista grupe</h2>
        <c:choose>
            <c:when test="${empty groups}">
                <h4>Nu sunt grupe</h4> <br/>
            </c:when>
            <c:otherwise>
                <table class="table table-hover">
                    <caption>Lista cu Grupe</caption>
                    <thead>
                    <tr>
                        <th style="width: 5%">#</th>
                        <th style="width: 70%">Nume</th>
                        <th style="width: 10%">Nr. stud</th>
                        <th style="width: 5%">&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${groups}" var="group" varStatus="idx">
                        <tr class="clickableRow">
                            <td> ${idx.index + 1} </td>
                            <td><a class="btn btn-link" href="/group/edit-${group.id}">${group.name}</a>
                            </td>
                            <td> ${group.studentsNr} </td>
                            <td><a href="/group/remove-${group.id}">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>


        <div class="row">
            <a href="/home" class="btn btn-link">Inapoi</a>

            <button type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#examModal">
                Adauga Grup
            </button>
        </div>
    </div>

</div>


<!-- Modal Add Exam -->
<div class="modal fade" id="examModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form:form method="post">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">Adauga Grup</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="groupName">Nume</label>
                        <input type="text" class="form-control" name="groupName" id="groupName"/>
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