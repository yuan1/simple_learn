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
    <div class="page page-current" id='chatPage'>
        <header class="bar bar-nav">
            <a class="button button-link button-nav pull-left external" href="app/index">
                <span class="icon icon-back"></span>
                返回
            </a>
            <h1 class="title">私信</h1>
        </header>
        <nav class="bar bar-tab">
            <a class="tab-item external" href="app/index">
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
            <a class="tab-item active external" href="app/chat/index">
                <span class="icon icon-comments"></span>
                <span class="tab-label">私聊</span>
            </a>
            <a class="tab-item external" href="app/user/my">
                <span class="icon icon-account"></span>
                <span class="tab-label">我</span>
            </a>
        </nav>
       <div class="content pull-to-refresh-content" data-ptr-distance="55">
           <!-- 默认的下拉刷新层 -->
           <div class="pull-to-refresh-layer">
               <div class="preloader"></div>
               <div class="pull-to-refresh-arrow"></div>
           </div>
           <div class="list-block media-list" style="margin-top: 0.4rem">
               <ul>
                   <c:forEach items="${chatList}" var="chat">
                       <li>
                           <div class="item-content" onclick="chatShow(${chat.get("toUserId")})">
                               <div class="item-media"><img src="http://images.javayuan.cn/${chat.get("toUserImage")}" style='width: 2.2rem; border-radius: 50%'></div>
                               <div class="item-inner">
                                   <div class="item-title-row">
                                       <div class="item-title">${chat.get("toUserNick")}</div>
                                       <div class="item-after" style="font-size: 0.6rem">${chat.get("toUserTime")}</div>
                                   </div>
                                   <div class="item-subtitle">${chat.get("toUserName")}</div>
                               </div>
                           </div>
                       </li>
                   </c:forEach>
               </ul>
           </div>
       </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
<script src="https://g.alicdn.com/aliww/??h5.imsdk/2.1.0/scripts/yw/wsdk.js,h5.openim.kit/0.3.7/scripts/kit.js"
        charset="utf-8"></script>
<script type='text/javascript' src='static/app/js/chat/index.js' charset='utf-8'></script>
</body>
</html>
