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
    <div class="page page-current" id='listPage'>
        <header class="bar bar-nav">
            <a class="button button-link button-nav pull-left external" href="app/index">
                <span class="icon icon-back"></span>
                返回
            </a>
            <a class="button button-link button-nav pull-right external" href="app/find/search">
                <span class="icon icon-search"></span>
                &nbsp;
            </a>
            <h1 class="title">列表</h1>
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
        <div class="buttons-tab fixed-tab" data-offset="44" style="z-index: 1;top:2.2rem">
            <a href="#tab1" class="tab-link active button" id="tab1">全部</a>
            <a href="#tab2" class="tab-link button" id="tab2">约·学</a>
            <a href="#tab3" class="tab-link button" id="tab3">约·行</a>
            <a href="#tab4" class="tab-link button" id="tab4">约·商</a>
            <input type="hidden" value="${type}" id="type">
        </div>
        <div class="content  pull-to-refresh-content" data-ptr-distance="55" id="listContent" style="margin-top: 2rem">
            <!-- 默认的下拉刷新层 -->
            <div class="pull-to-refresh-layer">
                <div class="preloader"></div>
                <div class="pull-to-refresh-arrow"></div>

            </div>
            <div class="card-container">

            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
<script src="https://g.alicdn.com/aliww/??h5.imsdk/2.1.0/scripts/yw/wsdk.js,h5.openim.kit/0.3.7/scripts/kit.js"
        charset="utf-8"></script>
<script type='text/javascript' src='static/app/js/find/list.js' charset='utf-8'></script>
</body>
</html>
