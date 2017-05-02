<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2017/3/28
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/app/common/_header.jsp" %>
    <title>主页</title>
</head>
<body>
<div class="page-group">
    <!-- 你的html代码 -->
    <div class="page" id="messagePage">
        <header class="bar bar-nav">
            <a class="button button-link button-nav pull-left back" href="javascript:" id="backMessage">
                <span class="icon icon-back"></span>
                返回
            </a>
            <h1 class="title">${toUser.nickname}</h1>
        </header>
        <input type="hidden" value="${fromUser.username}" id="uid">
        <input type="hidden" value="${appKey}" id="appkey">
        <input type="hidden" value="${fromUser.password}" id="credential">
        <input type="hidden" value="${toUser.username}" id="touid">
        <input type="hidden" value="http://images.javayuan.cn/${fromUser.image}" id="avatar">
        <input type="hidden" value="http://images.javayuan.cn/${toUser.image}" id="toAvatar">
        <div class="content" id="messageContent">

        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
</body>
</html>
