<%--
  Created by IntelliJ IDEA.
  User: Енот
  Date: 31.05.2016
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Files</title>
    <link rel="stylesheet" type="text/css" href="../../resources/styles/css/bootstrap.css"/>
</head>
<body>
<div id="container">
    <%@include file="menu.jsp" %>
    <br>
    <table>
        <sec:authentication var="principal" property="principal"/>

        <form:form action="/web/files/sort" method="post">
            <table>
                <tr>
                    <td><h2>Файлы</h2></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>Введите категорию:</td>
                    <td><input type="text" name="category" placeholder="Категория"></td>

                    <td>
                        <button type="submit" class="great_btn">Поиск</button>
                    </td>

                    <br>
                </tr>
            </table>
        </form:form>
        <hr/>
        <c:if test="${empty files}">
            <p>В базе данных файлов не найдено</p>
        </c:if>
        <c:if test="${not empty files}">
            <table>
                <tr>
                    <th>Имя файла\категория\подразделение отправителя\отдел отправителя\информация о файле</th>
                </tr>
                <tr></tr>
                <c:set value="1" var="i"/>
                <c:forEach items="${files}" var="file">
                    <c:if test='${principal.username}'>
                        <c:set value="authUser" var="authUser"/>
                    </c:if>
                    <tr>
                        <td><a href="/web/files/${file.id}" class="${authUser}">
                                ${i}. ${file.name}\ <c:forEach
                                var="cat" items="${file.getter_category}">
                            ${cat.name}
                        </c:forEach>\ ${file.sender_subdivision.name} \ ${file.sender_department.name}\
                             ${file.about}</a></td>
                    </tr>
                    <c:set value="" var="authUser"/>
                    <c:set var="i" value="${i+1}"/>
                </c:forEach>

            </table>
        </c:if>
    </table>
</div>
</body>
</html>
