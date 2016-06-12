<%@ page import="org.springframework.web.multipart.MultipartFile" %>
<%@ page import="ua.alice.entity.ExFile" %>
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
    <script type="text/javascript" src="../../resources/styles/js/jquery.min.js"></script>
  <%--  <script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/sha1.js"></script>--%>
   <%-- <script src='http://crypto-js.googlecode.com/svn/tags/3.1.2/build/components/lib-typedarrays-min.js'></script>--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/sha1.js"></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/components/lib-typedarrays-min.js'></script>
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
                        <form:input id="my_file" type="file" name="multipartFilefile" class="custom-file-input"
                                    path="multipartFilefile" onchange='sha1sum()'/>
                        <form:input id="sha" type="text" path="sender_sha" class ="hide"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td><span><form:errors path="multipartFilefile" cssClass="error"/>
		</span></td>
                <td>

                </td>
            </tr>
            <tr>
                <td>Введите информацию о файле:</td>
                <td><form:input type="text" id="file_info" name="about" path="about" placeholder="О файле"/>
                    <br> <form:errors path="about" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td><input type="submit" align="right" class="great_btn" value="Отправить файл"/></td>

            </tr>
        </form:form>

    </table>
    <script>
        function sha1sum() {
            var oFile = document.getElementById('my_file').files[0];
            var sha1 = CryptoJS.algo.SHA1.create();
            var read = 0;
            var unit = 1024 * 1024;
            var blob;
            var reader = new FileReader();
            reader.readAsArrayBuffer(oFile.slice(read, read + unit));
            reader.onload = function(e) {
                var bytes = CryptoJS.lib.WordArray.create(e.target.result);
                sha1.update(bytes);
                read += unit;
                if (read < oFile.size) {
                    blob = oFile.slice(read, read + unit);
                    reader.readAsArrayBuffer(blob);
                } else {
                    var hash = sha1.finalize();
                    var zi = hash.toString(CryptoJS.enc.Hex);
                    console.log(zi); // print the result
                    $('#sha').val(zi);

                }
            }
        }

        $(document).ready(function () {
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
