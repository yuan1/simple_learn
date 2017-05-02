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
    <style>
        .image-icon {
            width: 2rem;
            border-radius: 50%;
            border: 0.1rem solid #2D95FF;
        }

        .marker-icon:after {
            content: "\00a0";
            width: 0;
            height: 0;
            display: block;
            border: 0.5rem solid transparent;
            border-top-color: #2D95FF;
            position: absolute;
            z-index: -1;
            bottom: -0.8rem;
            left: 0.5rem;
        }

        .amap-info-content {
            padding: 0;
            min-width: 10rem;
        }

        .amap-info-close {
            right: 0.2rem !important;
        }

        #mapContent .facebook-name, #mapContent .facebook-date {
            line-height: 1rem;
            height: 1rem;
            overflow: hidden;
            width: 11rem;
        }
    </style>
</head>
<body>
<div class="page-group">
    <!-- 你的html代码 -->

    <div class="page page-current" id="mapPage">
        <header class="bar bar-nav">
            <a class="button button-link button-nav pull-left external" href="app/find/list/index" data-no-cache="true">
                <span class="icon icon-back"></span>
                列表
            </a>
            <a class="button button-link button-nav pull-right external" href="app/find/search">
                <span class="icon icon-search"></span>
                &nbsp;
            </a>
            <h1 class="title">地图</h1>
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
        <div class="content">
            <div class="buttons-tab fixed-tab" data-offset="44">
                <a href="#tab1" class="tab-link active button" id="tab1">全部</a>
                <a href="#tab2" class="tab-link button" id="tab2">约·学</a>
                <a href="#tab3" class="tab-link button" id="tab3">约·行</a>
                <a href="#tab4" class="tab-link button" id="tab4">约·商</a>
                <input type="hidden" value="${type}" id="type">
                <input type="hidden" value="${openid}" id="openid">
            </div>
            <div class="content" id="mapContent" style="margin-top: 2rem">
                <!-- 这里是页面内容区 -->

            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
<script type='text/javascript' src='static/app/js/index.js' charset='utf-8'></script>
<script src="https://g.alicdn.com/aliww/??h5.imsdk/2.1.0/scripts/yw/wsdk.js,h5.openim.kit/0.3.7/scripts/kit.js"
        charset="utf-8"></script>
<script type="text/javascript"
        src="http://webapi.amap.com/maps?v=1.3&key=f06e55d191da45e76663aab95e191270"></script>

</body>
</html>
