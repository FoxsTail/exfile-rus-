<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Лис
  Date: 31.05.2016
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ok</title>
    <link rel="stylesheet" type="text/css" href="../../resources/styles/css/bootstrap.css"/>
</head>
<body>
<div id="container">
    <%@include file="menu.jsp" %>
    <br>
    <div align="center">
    Вы успешно отправили файл ${files.name} подразделению(ям)
    <c:forEach var="cat" items="${files.getter_subdivisions}">
        ${cat.name}
    </c:forEach>!
    <a href="/web/send">
        <button class="great_btn">Назад</button>
    </a>
    </div>
</div>
</body>
</html>
