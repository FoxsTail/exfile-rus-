<%--
  Created by IntelliJ IDEA.
  User: Лис
  Date: 27.05.2016
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" type="text/css" href="../../resources/styles/css/bootstrap.css"/>
</head>
<body>
<div id="container">
    <%@include file="menu.jsp" %>
    <br>
    <sec:authentication var="principal" property="principal"/>
    <form:form action="/web/users/sort" method="post">
        <table>
            <tr>
                <td><h2>Пользователи</h2></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>

                <td>Введите фамилию:
                    <input type="text" name="surname" placeholder="Фамилия">

                    <button type="submit" class="great_btn">Поиск</button>
                </td>

                <br>
            </tr>
        </table>
    </form:form>
    <hr/>
    <c:if test="${empty users}">
        <p>В базе данных пользователей не найдено</p>
    </c:if>
    <c:if test="${not empty users}">
        <table>
            <tr>
                <th>ФИО</th>
            </tr>

            <c:set value="1" var="i"/>
            <c:forEach items="${users}" var="user">
                <c:if test='${principal.username}'>
                    <c:set value="authUser" var="authUser"/>
                </c:if>
                <tr>
                    <td><a href="/web/users/${user.id}" class="${authUser}">
                            ${user.surname} ${user.name} ${user.patronymic}
                    </a></td>
                    <td>${fn:toLowerCase(user.role)}</td>
                </tr>
                <c:set value="" var="authUser"/>
                <c:set var="i" value="${i+1}"/>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
