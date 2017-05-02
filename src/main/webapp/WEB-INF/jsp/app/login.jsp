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
    <title>登录</title>
</head>
<body>
<div class="page-group">
    <!-- 你的html代码 -->
    <div class="page">
        <header class="bar bar-nav">
            <h1 class="title">登录</h1>
        </header>
        <div class="content">
            <div class="page-login">
                <div class="list-block inset">
                    <ul>
                        <!-- Text inputs -->
                        <li>
                            <div class="item-content">
                                <div class="item-media"><i class="icon icon-account"></i></div>
                                <div class="item-inner">
                                    <div class="item-input">
                                        <input type="text" placeholder="用户名/手机号" required id="username" value="17853481294">
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="item-content">
                                <div class="item-media"><i class="icon icon-mima"></i></div>
                                <div class="item-inner">
                                    <div class="item-input">
                                        <input type="password" placeholder="密码" required id="password" value="123456">
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="content-block">
                    <p><a class="button button-big button-fill" href='javascript:' id="login" data-transition='fade'>登录</a></p>
                    <p class='text-center signup'>
                        <a href="app/regist/1" class='pull-left'>免费注册</a>
                        <a href="app/rem/1" class='pull-right'>忘记密码？</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
<script type='text/javascript' src='static/app/js/login.js' charset='utf-8'></script>
</body>
</html>
