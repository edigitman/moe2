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
</head>
<body>
<div class="generic-container">
    <%@include file="../authheader.jsp" %>

    <div class="col-md-8 col-md-offset-2">

        <div class="row">

            <a href="#" id="name"
               data-type="text"
               data-pk="${exam.id}"
               data-url="/exam/editProperty"
               data-title="Modifica numele">
                ${exam.name}
            </a>

            <br/>
            ${exam.difficulty}
        </div>


        <div class="row">
            <a href="/exam" class="btn btn-link">Inapoi</a>
        </div>
    </div>
</div>

</body>
</html>
