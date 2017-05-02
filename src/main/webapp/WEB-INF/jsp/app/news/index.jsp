<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="page page-current" id='newsPage'>
        <header class="bar bar-nav">
            <a class="button button-link button-nav pull-left external" href="app/index">
                <span class="icon icon-back"></span>
                返回
            </a>
            <h1 class="title">发现</h1>
        </header>
        <nav class="bar bar-tab">
            <a class="tab-item external" href="app/index">
                <span class="icon icon-map"></span>
                <span class="tab-label">附近</span>
            </a>
            <a class="tab-item active external" href="app/news/index">
                <span class="icon icon-all"></span>
                <span class="tab-label">发现</span>
            </a>
            <a class="tab-item external" href="app/find/add">
                <span class="icon icon-edit"></span>
                <span class="tab-label">发布</span>
            </a>
            <a class="tab-item external" href="app/chat/index">
                <span class="icon icon-comments"></span>
                <span class="tab-label">私聊</span>
            </a>
            <a class="tab-item external" href="app/user/my">
                <span class="icon icon-account"></span>
                <span class="tab-label">我</span>
            </a>
        </nav>
        <div class="content">
            <c:forEach items="${newsList}" var="news">
                <div class="card demo-card-header-pic" onclick="newsShow(${news.id})">
                    <div valign="bottom" class="card-header color-white no-border no-padding" style="background:url('http://images.javayuan.cn/${news.breviary}') no-repeat center center;height: 6rem">
                    </div>
                    <div class="card-content">
                        <div class="card-content-inner">
                            <p>${news.title}</p>
                            <p class="color-gray">${news.brief}</p>
                        </div>
                    </div>
                    <div class="card-footer">
                        <a href="#" class="link">赞</a>
                        <a href="#" class="link">更多</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
<script type='text/javascript' src='static/app/js/news/index.js' charset='utf-8'></script>
</body>
</html>
