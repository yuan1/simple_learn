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
    <title>找回</title>
</head>
<body>
<div class="page-group">
    <!-- 你的html代码 -->
    <div class="page">
        <header class="bar bar-nav">
            <a class="button button-link button-nav back pull-left" href="#">
                <span class="icon icon-back"></span>
                返回
            </a>
            <h1 class="title">找回2/2</h1>
        </header>
        <div class="content">
            <div class="page-login">
                <div class="list-block inset">
                    <ul>
                        <li>
                            <div class="item-content">
                                <div class="item-media"><i class="icon icon-inquirytemplate"></i></div>
                                <div class="item-inner">
                                    <div class="item-input">
                                        <input type="text" placeholder="验证码" required id="checkMobile">
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="item-content">
                                <div class="item-media"><i class="icon icon-mima"></i></div>
                                <div class="item-inner">
                                    <div class="item-input">
                                        <input type="password" placeholder="新密码" required  id="remPWD">
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="content-block">
                    <p><a class="button button-big button-fill" href='javascript:' id="rem-2" data-transition='fade'>完成</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
<script type='text/javascript' src='static/app/js/login.js' charset='utf-8'></script>
</body>
</html>
