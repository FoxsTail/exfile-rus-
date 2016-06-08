<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Лис
  Date: 26.05.2016
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
  <link rel="stylesheet" type="text/css" href="../../resources/styles/css/bootstrap.css"/>
</head>
<body>
<div id="container">
  <form action="/auth/login" method="post">
    <h2>Пожалуйста, войдите в систему</h2>
    <hr/>
    <table>
      <tr>
        <td>
          <label for="username">Логин</label>
        </td>
        <td>
          <input type="text" id="username" name="username" placeholder="Логин" required autofocus/>
        </td>
      </tr>
      <tr>
        <td>
          <label for="password">Пароль</label>
        </td>
        <td>
          <input type="password" id="password" name="password" placeholder="Пароль" required/>
        </td>
      </tr>
      <tr>
        <td>
          <button type="submit" class="great_btn" >Войти</button>
        </td>
        <td>
        </td>
      </tr>
    </table>
    <p>
      <c:if test="${param.error != null}">
            <span style="color: #8b0000">
                Неправильное имя или пароль.
            </span>
      </c:if>
      <c:if test="${param.logout != null}">
            <span>
                Вы вышли из системы.
            </span>
      </c:if>
    </p>
  </form>
  <a href="/auth/login/registration" ><button class="great_btn">Регистрация</button></a>
  </div>
</body>
</html>
