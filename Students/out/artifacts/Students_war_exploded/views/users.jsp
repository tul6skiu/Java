<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <title>Title</title>
    <style>
        <%@include file="/views/css/style.css"%>
    </style>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script type="text/javascript">
        <%@include file="/views/main.js"%>
    </script>

</head>
<body>
<div>
    <div class="form-style-2-heading">
        Already registered!
    </div>
        <table class="w3-table-all">
            <tr class="w3-red">
                <td>Имя</td>
                <td>Фамилия</td>
                <td>Возраст</td>
                <td>Действия</td>
                <td>
            </tr>
            <c:forEach items="${usersFromServer}" var = "user">
                <tr>
                    <td class=w3-round-small>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.birthDate}</td>
                    <td>
                        <form method="post">
                            <input type="hidden" name="id" value="${user.firstName}">
                            <input type="hidden" name="name" value="${user.lastName}">
                            <input type="hidden" name="name" value="${user.birthDate}">
                            <button  type="submit" name="delete" value="${user.id}">Удалить</button>
                        </form>
                        <button  class="editUser" onclick="disp(document.getElementById('form'))" data-userId="${user.id}">Изменить2</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    <div id="form"  style="display:none;">
        <form method="post" >
            <div class="row">
                <div class="col">
                    <input type="text" id="first-name" name="first-name" naemclass="form-control" placeholder="First name">
                </div>
                <div class="col">
                    <input type="text" id="last-name" name="last-name" class="form-control" placeholder="Last name">
                </div>
                <button type="submit" name="update" value="${user.id}">Изменить</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>


