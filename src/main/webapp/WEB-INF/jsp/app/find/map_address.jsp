<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2017/3/28
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/app/common/_header.jsp" %>
    <title>添加位置</title>
</head>
<body>
<div class="page-group">
    <!-- 你的html代码 -->
    <div class="page" id="addressPage">
        <header class="bar bar-nav">
            <a class="button button-link button-nav back pull-left" href="#">
                <span class="icon icon-back"></span>
                返回
            </a>
            <h1 class="title">选择位置</h1>
        </header>
        <input type="hidden" value="${findId}" id="findId">
        <iframe id="addressContent" width="100%" height="100%" frameborder=0 class="content"
                src="https://m.amap.com/picker/?key=f06e55d191da45e76663aab95e191270">

        </iframe>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
<script type='text/javascript' src='static/app/js/find/add.js' charset='utf-8'></script>
</body>
</html>
