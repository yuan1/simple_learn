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
            <td width="100">头像：</td>
            <td><img  class="avatar radius size-XXXL" src="http://images.javayuan.cn/${userShow.image}"></td>
        </tr>
        <tr>
            <td>昵称：</td>
            <td>${userShow.nickname}</td>
        </tr>
        <tr>
            <td>用户名：</td>
            <td>
                ${userShow.username}</td>
        </tr>
        <tr>
            <td>性别：</td>
            <td>
                ${userShow.sex}</td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td>
                ${userShow.email}</td>
        </tr>
        <tr>
            <td>手机号码：</td>
            <td>
                ${userShow.mobile}</td>
        </tr>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
</body>
</html>