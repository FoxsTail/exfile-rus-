<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Лис
  Date: 04.06.2016
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Download</title>
    <link rel="stylesheet" type="text/css" href="../../resources/styles/css/bootstrap.css"/>
</head>
<body>
<div id="container">
    <%@include file="menu.jsp" %>
    <br>
    <form:form action="/web/downloadPerDate" method="post">
        <table>
            <tr>
                <td>Введите дату: </td>
                <td><input type="text" name="date" placeholder="дд\мм\гггг"></td>
                <td>
                    <button type="submit" class="great_btn">Поиск</button>
                </td>
                <br>
            </tr>
        </table>
    </form:form>
    <c:if test="${empty files}">
        <p>В базе данных файлов не найдено</p>
    </c:if>
    <c:if test="${not empty files}">
        <table>
            <tr>
                <td>
                    Информация о файле будет выводиться в таком порядке:<br>
                    1. Имя файла, 2. Подразделение отправителя, 3. Отдел отправителя,<br> 4. Категория(и) файла, 5.
                    Информация о файле <br>
                    <hr>

                </td>
            </tr>
            <tr>
                <th>Файлы:</th>

            </tr>
            <c:forEach items="${files}" var="file">
            <tr>

                <td>
                    1. ${file.name}<br>
                    2. ${file.sender_subdivision.name}<br>
                    3. ${file.sender_department.name}<br>
                    4.<c:forEach var="cat" items="${file.getter_category}">
                    ${cat.name}
                </c:forEach> <br>
                    5. ${file.about}<br>
                    -----------------------------
                </td>
                <td>
                    <a href="/web/load/${file.id}">Загрузить</a>
                </td>
                </c:forEach>
            </tr>

            </tr>
        </table>
    </c:if>
    <div align="right">
        <a href="/web/send">
            <button class="great_btn">Назад</button>
        </a>
    </div>
</div>
</body>
</html>
