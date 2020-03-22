<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
 <form action="/login" method="post">
     Имя: <input type="text" name="username" placeholder="Имя" required> <br>
     Пароль: <input type="text" name="password" placeholder="Пароль" required><br>
     <input type="submit" value="Авторизация">
 </form>
 <br>
 <jsp:include page="butons_back.jsp"/>
</body>
</html>
