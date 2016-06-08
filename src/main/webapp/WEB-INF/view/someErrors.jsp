<%--
  Created by IntelliJ IDEA.
  User: Лис
  Date: 31.05.2016
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="../../resources/styles/css/bootstrap.css"/>
</head>
<body>
<div id="container">
    <%@include file="menu.jsp" %>
    <br>
    Упс, ${message}
    <a href="/web/send">
        <button class="great_btn">Назад</button>
    </a>
</div>
</body>
</html>
