<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/home">MOE</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <sec:authorize access="hasRole('PROFESSOR')">
                    <li><a href="/exam">Concepte</a></li>
                    <li><a href="/instance">Examene</a></li>
                    <li><a href="/group">Grupuri</a></li>
                </sec:authorize>
                <%--<li><a href="#">Home</a></li>--%>
                <%--<li><a href="#about">About</a></li>--%>
                <%--<li><a href="#contact">Contact</a></li>--%>
                <%--<li class="dropdown">--%>
                    <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>--%>
                    <%--<ul class="dropdown-menu">--%>
                        <%--<li><a href="#">Action</a></li>--%>
                        <%--<li><a href="#">Another action</a></li>--%>
                        <%--<li><a href="#">Something else here</a></li>--%>
                        <%--<li role="separator" class="divider"></li>--%>
                        <%--<li class="dropdown-header">Nav header</li>--%>
                        <%--<li><a href="#">Separated link</a></li>--%>
                        <%--<li><a href="#">One more separated link</a></li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-menu-down" aria-hidden="true"></span><strong>${loggedinuser}</strong></a></li>
                <li><a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Iesire</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<div style="height: 55px">
</div>