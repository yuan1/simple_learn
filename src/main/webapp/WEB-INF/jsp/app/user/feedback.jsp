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
    <title>意见反馈</title>
</head>
<body>
<div class="page-group">
    <!-- 你的html代码 -->
    <div class="page" id="feddbackPage">
        <header class="bar bar-nav">
            <a class="button button-link button-nav back pull-left" href="#">
                <span class="icon icon-back"></span>
                返回
            </a>
            <h1 class="title">意见反馈</h1>
        </header>
        <div class="content">
            <div class="list-block" style="margin-top: 0.6rem">
                <ul>
                    <li class="align-top">
                        <div class="item-content">
                            <div class="item-media"><i class="icon icon-form-comment"></i></div>
                            <div class="item-inner">
                                <div class="item-input">
                                    <textarea placeholder="请输入您的反馈内容" id="feedback"></textarea>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="content-block">
                <a href="javascript:" id="feedbackDo" class="button button-big button-fill">反馈</a>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
<script type='text/javascript' src='static/app/js/user/my.js' charset='utf-8'></script>
</body>
</html>
