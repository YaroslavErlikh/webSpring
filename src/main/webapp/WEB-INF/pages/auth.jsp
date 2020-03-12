<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
 <form action="auth" method="get">
     Имя: <input type="text" name="name" value="${param.name}" placeholder="Имя"> <br>
     Пароль: <input type="text" name="pass" value="${param.pass}" placeholder="Пароль"><br>
     <input type="submit" value="Авторизация">
 </form>
</body>
</html>
