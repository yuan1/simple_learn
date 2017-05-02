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
    <div class="page page-current" id='searchPage'>
        <header class="bar bar-nav">
            <a class="button button-link button-nav pull-left external" href="app/index">
                <span class="icon icon-back"></span>
                返回
            </a>
            <h1 class="title">搜索</h1>
        </header>
        <nav class="bar bar-tab">
            <a class="tab-item active external" href="app/index">
                <span class="icon icon-map"></span>
                <span class="tab-label">附近</span>
            </a>
            <a class="tab-item external" href="app/news/index">
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
        <div class="bar bar-header-secondary" style="top:2.1rem">
            <div class="searchbar">
                <a class="searchbar-cancel">取消</a>
                <div class="search-input">
                    <label class="icon icon-search" for="search"></label>
                    <input type="search" id='search' placeholder='输入关键字...' />
                </div>
            </div>
        </div>
        <div class="content" id="listContent">
            <div class="card-container">
                <p class="text-center">没有搜索到!</p>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
<script src="https://g.alicdn.com/aliww/??h5.imsdk/2.1.0/scripts/yw/wsdk.js,h5.openim.kit/0.3.7/scripts/kit.js"
        charset="utf-8"></script>
<script type='text/javascript' src='static/app/js/find/search.js' charset='utf-8'></script>
</body>
</html>
