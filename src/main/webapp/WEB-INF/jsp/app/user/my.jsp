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
    <title>我的信息</title>
</head>
<body>
<div class="page-group">
    <!-- 你的html代码 -->
    <div class="page page-current" id="myPage">
        <header class="bar bar-nav">
            <a class="button button-link button-nav back pull-left" href="#">
                <span class="icon icon-back"></span>
                返回
            </a>
            <h1 class="title">我的信息</h1>
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
            <a class="tab-item external" href="app/chat/index">
                <span class="icon icon-comments"></span>
                <span class="tab-label">私聊</span>
            </a>
            <a class="tab-item active external" href="app/user/my">
                <span class="icon icon-account"></span>
                <span class="tab-label">我</span>
            </a>
        </nav>
        <div class="content">
            <div class="page-settings">
                <div class="list-block media-list person-card" style="margin: 1rem 0" id="myInfoEdit">
                    <ul>
                        <li>
                            <div class="item-content item-link">
                                <div class="item-media">
                                    <img src="http://images.javayuan.cn/default.png" style="width: 4rem;border-radius: 50%;">
                                </div>
                                <div class="item-inner">
                                    <div class="item-title-row">
                                        <div class="item-title">
                                                未设置昵称
                                        </div>
                                    </div>
                                    <div class="item-subtitle">username</div>
                                    <div class="item-text">mobile</div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="list-block list" style="margin: 1rem 0">
                    <ul>
                        <li class="item-content item-link" id="myOrder">
                            <div class="item-media"><i class="icon icon-success"></i></div>
                            <div class="item-inner">
                                <div class="item-title">我的预约</div>
                            </div>
                        </li>
                        <li class="item-content item-link" id="orderMy">
                            <div class="item-media"><i class="icon icon-smile"></i></div>
                            <div class="item-inner">
                                <div class="item-title">预约我的</div>
                            </div>
                        </li>
                        </li>
                    </ul>
                </div>
                <div class="list-block list" style="margin: 1rem 0">
                    <ul>
                        <li class="item-content item-link" id="myFind">
                            <div class="item-media"><i class="icon icon-category"></i></div>
                            <div class="item-inner">
                                <div class="item-title">我发布的</div>
                            </div>
                        </li>

                        <li class="item-content item-link" id="myStar">
                            <div class="item-media"><i class="icon icon-favorite"></i></div>
                            <div class="item-inner">
                                <div class="item-title">我的收藏</div>
                            </div>
                        </li>
                        <li class="item-content item-link" id="myPwd">
                            <div class="item-media"><i class="icon icon-set"></i></div>
                            <div class="item-inner">
                                <div class="item-title">修改密码</div>
                            </div>
                        </li>

                        <li class="item-content item-link" id="myFeedback">
                            <div class="item-media"><i class="icon icon-information"></i></div>
                            <div class="item-inner">
                                <div class="item-title">意见反馈</div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="content-block">
                    <a href="javascript:" data-transition='slide-out' id="logout" class="button button-big button-fill button-danger">退出登录</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
<script src="https://g.alicdn.com/aliww/??h5.imsdk/2.1.0/scripts/yw/wsdk.js,h5.openim.kit/0.3.7/scripts/kit.js"
        charset="utf-8"></script>
<script type='text/javascript' src='static/app/js/user/my.js' charset='utf-8'></script>
</body>
</html>
