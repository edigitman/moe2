<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">Lista de utilizatori </span></div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Email</th>
            <th>Rol</th>
            <th width="100"></th>
            <%--<th width="100"></th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>
                    <c:forEach items="${user.userProfiles}" var="profile">
                        ${profile.type}
                    </c:forEach>
                </td>
                <td>
                    <a href="<c:url value='/admin/edit-user-${user.ssoId}' />"
                       class="btn btn-link custom-width">
                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                        modifica</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="well">
    <a class="btn btn-link" href="<c:url value='/admin/newuser' />">
        <i class="fa user-plus" aria-hidden="true"></i>
        Adauga utilizator nou</a>
</div>
