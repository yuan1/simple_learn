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
    <title>错误</title>
</head>
<body>
<div class="page-group">
    <!-- 你的html代码 -->
    <div class="page">
        <header class="bar bar-nav">
            <h1 class="title">错误页面</h1>
        </header>
        <div class="content">
            <div class="content-block">
               <span class="icon icon-warning" style="color:red;font-size: 5rem; text-align: center;width: 100%"></span>
                <p style="text-align: center">${info}</p>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
</body>
</html>
