<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Users</h2>



<table border="1" cellpadding="8" cellspacing="0">
    <thead>
        <tr>
            <th>Имя</th>
            <th>Мыло</th>
        </tr>
    </thead>
    <c:forEach items="${users}" var="user">
        <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>