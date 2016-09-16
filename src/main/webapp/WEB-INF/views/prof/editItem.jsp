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

    <script src="https://vuejs.org/js/vue.js"></script>

    <sec:csrfMetaTags/>
</head>
<body id="vueApp">
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
                                <textarea class="form-control" name="assertion" rows="5"
                                          id="assertion">${item.assertion}</textarea>
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
                        <button type="submit" class="btn btn-primary">Salveaza</button>
                    </div>
                    <c:if test="${not empty item.id}">
                        <div class="row">
                            <a href="/item/clean-${exam.id}" class="btn btn-link">Curata</a>
                        </div>
                        <div class="row" style="margin-top: 25px">
                            <button type="button"
                                    @click="loadAnswers(${item.id})"
                                    class="btn btn-primary"
                                    data-toggle="modal"
                                    data-target="#answerModal">
                                Raspunsuri
                            </button>
                        </div>
                    </c:if>
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
                            <th style="width: 70%">Enunt</th>
                            <th style="width: 10%">Pct.</th>
                            <th style="width: 10%">Tip</th>
                            <th style="width: 5%">&nbsp;</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${items}" var="item" varStatus="idx">
                            <tr class="clickableRow isEdited-${item.selected}">
                                <td> ${idx.index + 1} </td>
                                <td><a class="btn btn-link" href="/item/edit-${item.id}-${exam.id}">${item.title}</a>
                                </td>
                                <td> ${item.points} </td>
                                <td> ${item.type} </td>
                                <td><a href="/item/remove-${item.id}-${exam.id}">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </a></td>
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

<!-- Modal Add Exam -->
<div class="modal fade" id="answerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Raspunsuri pentru subiect</h4>
            </div>
            <div class="modal-body">

                <div class="row" style="width: 99%; margin-left: 1px;">
                    <div class="form-group">
                        <label for="type">Raspuns</label>
                        <div>
                            <input id="answerValue" type="text" class="form-control"
                                   v-model="value"
                                   style="display: inline-block; width: 85%"/>
                            <span>
                                <input id="answerCorrect"
                                       v-model="correct"
                                       type="checkbox"> Corect
                            </span>
                        </div>

                        <button @click="saveAnswer" class="btn btn-success">Adauga</button>
                    </div>
                </div>

                <div class="row">
                    <table id="answerTable" class="table table-hover">
                        <caption>lista de raspunsuri</caption>
                        <tr>
                            <th>Valoare</th>
                            <th>Corect</th>
                            <th></th>
                        </tr>
                        <tr v-for="answer in answers">
                            <td>{{ answer.value }}</td>
                            <td>{{ answer.correct }}</td>
                            <td> <a class="btn btn-link" @click="deleteAnswer(answer.id)" href="#">X</a></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-link" data-dismiss="modal">Inchide</button>
                <%--<button type="submit" class="btn btn-primary">Salveaza</button>--%>
            </div>

        </div>
    </div>
</div>

<%-- manage answers to item --%>
<script type="text/javascript">

    new Vue({
        el: '#vueApp',
        data: {
            value: '',
            correct: false,

            itemId: {},

            answers: []
        },
        methods: {
            loadAnswers: function (itemId) {
                var self = this;
                self.itemId = itemId;
                $.get('/item/as-' + itemId, function (data, status) {
                    self.answers = $.parseJSON( data );
                });
            },

            saveAnswer: function () {
                var self = this;

                var csrfHeader = $("meta[name='_csrf_header']").attr("content");
                var csrfToken = $("meta[name='_csrf']").attr("content");

                var headers = {};
                headers[csrfHeader] = csrfToken;

                $.ajax({
                    method: 'POST',
                    url: '/item/as-' + self.itemId,
                    headers: headers,
                    data: {value: self.value, correct: self.correct},
                    success: function (data, status) {
                        console.log('save answer: ' + status);
                        self.answers.push($.parseJSON( data ));
                        self.value = '';
                        self.correct = false;
                    }
                }).done(function() {
                    console.log('done');
                });
            },

            deleteAnswer: function (answer) {
                var self = this;

                var csrfHeader = $("meta[name='_csrf_header']").attr("content");
                var csrfToken = $("meta[name='_csrf']").attr("content");

                var headers = {};
                headers[csrfHeader] = csrfToken;

                $.ajax({
                    type: 'DELETE',
                    url: '/item/as-' + answer,
                    headers: headers,
                    success: function (result, status) {

                        self.answers = $.grep(self.answers, function(value, index){
                            return value.id != answer;
                        });

                        console.log(status);
                    }
                });
            }
        }
    });
</script>


<%-- edit title and difficulty --%>
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
