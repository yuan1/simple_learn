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
    <div class="page page-current" id='contentPage'>
        <header class="bar bar-nav">
            <a class="button button-link button-nav pull-left back" href="#">
                <span class="icon icon-back"></span>
                返回
            </a>
            <h1 class="title">详情</h1>
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
            <div class="card" style="margin-left: 0;margin-right: 0">
                <div class="card-content">
                    <div class="card-content-inner">
                        <div class="text-center" style="font-size: 1.2rem;">${news.title}</div>
                        <p class="text-center color-gray" style="font-size: 0.6rem">
                            作者：${news.author}，发表于${news.creatTime}，赞（${news.laud}）</p>
                        ${news.content}
                    </div>
                </div>
                <div class="card-footer">
                    <a href="#" class="link" id="goodGet">
                        <span class="icon icon-good"></span>
                        <span id="laud" class="color-gray">${news.laud}</span>
                    </a>
                    <a href="#" class="link">更多</a>
                </div>
            </div>

        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
<script type='text/javascript' src='static/app/js/news/index.js' charset='utf-8'></script>
</body>
</html>
