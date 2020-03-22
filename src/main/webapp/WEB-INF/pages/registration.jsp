<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>

<form action="/registration" method="post">
    Имя: <input type="text" name="username" value="${param.username}" placeholder="Имя" required>
        ${usernameError}
    <br>
    Пароль: <input type="text" name="password" value="${param.password}" placeholder="Пароль" required><br>
    Подтверждение пароля: <input type="text" name="passwordConfirm" value="${param.passwordConfirm}"
                                 placeholder="Подтверждение пароля" required>
        ${passwordError}
<br>
    <input type="submit" value="Зарегистрироваться">
</form>

</body>
</html>
