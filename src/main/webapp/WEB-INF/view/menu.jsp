<%--
  Created by IntelliJ IDEA.
  User: Лис
  Date: 05.06.2016
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="default-menu">
  <a href="/web/profile/">Профиль</a> |
  <a href="/web/send">Отправить файл</a>  |
  <a href="/web/download">Загрузить файл</a>
  <sec:authorize access="hasAuthority('ADMIN')">
   | <a href="/web/users">Пользователи</a> |
    <a href="/web/files">Файлы</a>
  </sec:authorize>
  <a href="/web/logout" style="float: right">Выйти</a>
</div>