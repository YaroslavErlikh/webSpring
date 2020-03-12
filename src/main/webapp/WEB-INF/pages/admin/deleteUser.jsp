<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete user - ${user.id}</title>
</head>
<body>
<h3>Удалить пользователя - </h3>
<br>id - ${user.id}
<br>Имя - ${user.name}
<br>Пароль - ${user.pass}
<br>Права - ${user.role}
<br>
Вы уверены?
<c:url value="/admin/deleteUser/" var="var"/>
<form action="${var}" method="get">
    <input type="hidden" name="id" value="${user.id}">
    <input type="submit" value="Удалить">
</form>
</body>
</html>
