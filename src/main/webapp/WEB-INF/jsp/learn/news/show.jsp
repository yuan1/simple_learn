<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2017/3/16
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/common/_meta.jsp" %>
</head>
<body>
<div class="page-container">
    <table class="table table-border table-bordered table-hover ">
        <tr>
            <td>缩略图预览：</td>
            <td>
                <img src="http://images.javayuan.cn/${newsShow.breviary}" id="imageNow" width="240px" height="160px" alt="..." class="radius">
            </td>
        </tr>
        <tr>
            <td>标题：</td>
            <td>${newsShow.title}</td>
        </tr>
        <tr>
            <td>简介：</td>
            <td>
                ${newsShow.brief}</td>
        </tr>
        <tr>
            <td>作者昵称：</td>
            <td>
                ${newsShow.author}</td>
        </tr>

        <tr>
            <td>内容：</td>
            <td>
                ${newsShow.content}</td>
        </tr>
        <tr>
            <td>赞数量：</td>
            <td>
                ${newsShow.laud}</td>
        </tr>

    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
</body>
</html>