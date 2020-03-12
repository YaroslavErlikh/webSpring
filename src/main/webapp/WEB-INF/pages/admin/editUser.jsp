<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit user - ${user.id}</title>
</head>
<body>
<h5>Edit user - ${user.id}</h5>
<c:url value="/admin/edit" var="var"/>
<form action="${var}" method="post">

    <input type="hidden" name="id" value="${user.id}">

    <label for="name">Имя:</label>
    <input type="text" name="name" id="name" value="${user.name}">
    <br>
    <label for="pass">Пароль:</label>
    <input type="text" name="pass" id="pass" value="${user.pass}">
    <br>
    <label for="role">Права:</label>
    <select id="role" name="role">
        <option value="user" <c:if test="${user.role.equals('user')}" >selected</c:if>>user</option>
        <option value="admin" <c:if test="${user.role.equals('admin')}" >selected</c:if>>admin</option>
    </select>
<%--    <p id="role"><input type="radio" name="role" value="admin" placeholder=${param.role}>admin<br>--%>
<%--    <input type="radio" checked name="role" value="user" placeholder=${param.role}>user</p>--%>
    <br>
    <input type="submit" value="Изменить">
</form>
</body>
</html>
