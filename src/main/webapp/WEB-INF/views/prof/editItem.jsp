<%--
  Created by IntelliJ IDEA.
  User: edi
  Date: 8/22/16
  Time: 8:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Editeaza examen</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>

    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
            integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
    <script src="<c:url value='/static/js/bootstrap.min.js' />"></script>

    <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css"
          rel="stylesheet"/>
    <script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>

    <sec:csrfMetaTags/>
</head>
<body>
<div class="generic-container">
    <%@include file="../authheader.jsp" %>

    <div class="col-md-8 col-md-offset-2">

        <div class="row">

            <div class="col-md-4">
                Examen:
                <a href="#" id="examName"
                   data-type="text"
                   data-pk="${exam.id}"
                   data-url="/exam/editProperty"
                   data-title="Modifica numele">
                    ${exam.name}
                </a>
            </div>
            <div class="col-md-4">
                Dificultate:
                <a href="#" id="examDiff"
                   data-source="[{value: 'EASY', text: 'EASY'}, {value: 'MEDIUM', text: 'MEDIUM'}, {value: 'HARD', text: 'HARD'}]"
                   data-type="select"
                   data-pk="${exam.id}"
                   data-url="/exam/editProperty"
                   data-title="Modifica dificultatea">
                    ${exam.difficulty}
                </a>
            </div>
            <div class="col-md-4">
                Puncte: ${exam.points}
            </div>
        </div>

        <form:form action="/item" modelAttribute="item">
            <input type="hidden" name="examId" value="${exam.id}">
            <input type="hidden" name="id" value="${item.id}">

            <div class="row">
                <div class="col-md-10">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="assertion">Enunt</label>
                                <textarea class="form-control" value="${item.assertion}" name="assertion"
                                          id="assertion"></textarea>
                            </div>
                        </div>
                    </div><!-- END OF ROW -->
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group" id="pointsDiv">
                                <label for="points">Puncte</label>
                                <input type="number" class="form-control" value="${item.points}" name="points"
                                       id="points" required/>
                            </div>
                        </div>  <!-- END OF COLUMN -->
                        <div class="col-md-4">
                            <div class="form-group">
                                <label for="type">Tip item</label>
                                <form:select path="type" class="form-control" items="${itemTypes}"/>
                            </div>
                        </div> <!-- END OF COLUMN -->
                        <div class="col-md-4">
                                <%-- TODO Add resources --%>
                        </div>
                    </div> <!-- END OF ROW -->
                </div><!-- END OF COLUMN -->

                <div class="col-md-2" style="margin-top: 25px">
                    <div class="row">
                        <button type="submit" class="btn btn-primary">Adauga</button>
                    </div>
                    <div class="row">
                        <button type="reset" class="btn btn-link">Curata</button>
                    </div>
                </div>

            </div>
        </form:form>

        <div class="row">

            <c:choose>
                <c:when test="${empty items}">
                    <h4>Nu sunt subiecte</h4> <br/>
                </c:when>
                <c:otherwise>
                    <table class="table table-hover">
                        <caption>Lista cu subiecte</caption>
                        <thead>
                        <tr>
                            <th style="width: 5%">#</th>
                            <th style="width: 75%">Enunt</th>
                            <th style="width: 10%">Pct.</th>
                            <th style="width: 10%">Tip</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${items}" var="item" varStatus="idx">
                            <tr class="clickableRow">
                                <td> ${idx.index + 1} </td>
                                <td> ${item.title} </td>
                                <td> ${item.points} </td>
                                <td> ${item.type} </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>

        </div>


        <div class="row">
            <a href="/exam" class="btn btn-link">Inapoi</a>
        </div>
    </div>
</div>


<script type="text/javascript">

    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");

    var headers = {};
    headers[csrfHeader] = csrfToken;

    $.fn.editable.defaults.mode = 'inline';
    $.fn.editable.defaults.ajaxOptions = {headers: headers};
    $(document).ready(function () {
        $('#examName').editable();
        $('#examDiff').editable();
    });
</script>

</body>
</html>
