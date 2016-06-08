<%--  Created by IntelliJ IDEA.
  User: Лис
  Date: 26.05.2016
  Time: 17:44
  To change this template use File | Settings | File Templates.--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Success</title>
    <link rel="stylesheet" type="text/css" href="../../resources/styles/css/bootstrap.css"/>

</head>
<body>
<div id="container">
    <form:form action="/auth/login" commandName="user">
        <div align="center"><h2>${user.login}, ваша регистрация была успешной!<br/></h2></div>

        <hr>
        Имя : ${user.name} <br/>
        Фамилия : ${user.surname} <br/>
        Отчество : ${user.patronymic} <br/>
        Подразделение : ${user.subdivision.name}<br/>
        Отдел : ${user.department.name} <br/>
        ИНН :  ${user.inn}<br/>
        Логин : ${user.login} <br/>
        Пароль : ${user.password}<br/>

        <br>
        <button type="submit" class="great_btn">Войти</button>

    </form:form>
</div>
</body>
</html>
