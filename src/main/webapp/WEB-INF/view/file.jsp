<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Лис
  Date: 04.06.2016
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File</title>
    <link rel="stylesheet" type="text/css" href="../../resources/styles/css/bootstrap.css"/>
</head>
<body>
<div id="container">
    <%@include file="menu.jsp" %>
    <br>
<table>
    <tr>
        <td>Имя файла:</td>
        <td>${file.name}</td>
    </tr>
    <tr>
        <td>Подразделение и <br>отдел отправителя: </td>
        <td>${file.sender_subdivision.name},
       ${file.sender_department.name}</td>
    </tr>
    <tr>
        <td>Категория файла:</td>
        <td><c:forEach var="cat" items="${file.getter_category}">
            ${cat.name}
        </c:forEach> <br></td>
    </tr>
    <tr>
        <td>Информация о файле:</td>
        <td>${file.about}</td>
    </tr>
    <tr>
        <td>Файл был сохранен в:</td>
        <td>${file.path}</td>
    </tr>
    <tr>
        <td>Хотите загрузить файл?</td>
        <td>
            <a href="/web/load/${file.id}">Загрузить</a>
        </td>

    </tr>
</table>
<a href="/web/files">
    <button class="great_btn">Назад</button>
</a>
    <a href="/web/files/${file.id}/delete">
        <button class="great_btn">Удалить файл</button>
    </a>
    </div>
</body>
</html>
