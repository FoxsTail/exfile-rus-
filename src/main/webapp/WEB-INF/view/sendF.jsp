<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Лис
  Date: 03.06.2016
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send file</title>
    <link rel="stylesheet" type="text/css" href="../../resources/styles/css/bootstrap.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</head>
<body>
<div id="container">
    <%@include file="menu.jsp" %>
    <br>
    <table>
        <tr>
            <td><b>Подразделение отправителя:</b></td>
            <td>
                ${uploadForm.user.subdivision.name}
            </td>
            <td>|</td>
            <td><b>Отдел отправителя: </b></td>
            <td>
                ${uploadForm.user.department.name}
            </td>
        </tr>
    </table>
    <hr>
    <table>
        <form:form method="post" action="/web/sendF" modelAttribute="uploadForm" enctype="multipart/form-data">


            <tr>
                <td>Категория(и) документа:</td>
                <td><form:select path="value_categories" multiple="multiple">
                    <form:option value="0" label="Выберите категорию(и)" disabled="true"/>
                    <form:options items="${cat}"/>
                </form:select>
                </td>
            </tr>

            <tr>
                <td>Подразделение получателя:</td>
                <td><form:select path="value_subdivisions" multiple="multiple">
                    <form:option value="0" label="Выберите подразделение(я)" disabled="true"/>
                    <form:options items="${sub}"/>
                </form:select>
                </td>
            </tr>
            <tr>
                <td>Отдел получателя:</td>
                <td><form:select path="value_departments" multiple="multiple">
                    <form:option value="0" label="Выберите отдел(ы)" disabled="true"/>
                    <form:options items="${dep}"/>
                </form:select>
                </td>
            </tr>
            <tr>
                <td> Выберите файл для отправки:</td>
                <td>
                    <div class="mask-wrapper">
                        <div class="mask">
                            <input class="fileInputText" type="text" disabled>
                            <button class="send-file">Загрузить</button>
                        </div>
                        <form:input id="my_file" class="custom-file-input" type="file" name="multipartFilefile"
                                    path="multipartFilefile"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td><span><form:errors path="multipartFilefile" cssClass="error"/>
		</span></td>
            </tr>


            <tr>
                <td>Введите информацию о файле: </td>
                <td><form:input type="text" name="about" path="about" placeholder="О файле"/>
                    <br> <form:errors path="about" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td ><input type="submit" align="right" class="great_btn" value="Отправить файл"/></td>

            </tr>
        </form:form>

    </table>
    <script>$(document).ready(function () {
        $('.custom-file-input').on('change', function () {
            realVal = $(this).val();
            lastIndex = realVal.lastIndexOf('\\') + 1;
            if (lastIndex !== -1) {
                realVal = realVal.substr(lastIndex);
                $(this).prev('.mask').find('.fileInputText').val(realVal);
            }
        });
    });

    $('.custom-file-input').on('mouseenter mouseleave', function () {
        $(this).prev('.mask').find('.send-file').toggleClass('hovered');
    });
    </script>
</div>
</body>
</html>
