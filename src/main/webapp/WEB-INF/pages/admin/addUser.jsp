<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<c:url value="/admin/add" var="var"/>
<form action="${var}" method="post">
    <label for="name">Имя:</label>
    <input type="text" name="name" id="name">
    <br>
    <label for="pass">Пароль:</label>
    <input type="text" name="pass" id="pass">
    <br>
    <input type="hidden" name="role" id="role" value="user">
    <input type="submit" value="Добавить">
</form>
</body>
</html>
