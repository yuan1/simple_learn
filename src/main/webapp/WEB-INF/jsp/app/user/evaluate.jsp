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
    <title>发表评价</title>
</head>
<body>
<div class="page-group">
    <!-- 你的html代码 -->
    <div class="page" id="evaPage">
        <header class="bar bar-nav">
            <a class="button button-link button-nav back pull-left" href="#">
                <span class="icon icon-back"></span>
                返回
            </a>
            <h1 class="title">发表评价</h1>
        </header>
        <nav class="bar bar-tab">
            <a class="tab-item" href="#" id="evaAdd" style="background-color:#0894ec;border-radius: 0.25rem">
                <span class="tab-label" style="color: #FFFFFF">发表评价</span>
            </a>
        </nav>
        <div class="content">
            <div class="list-block" style="margin-top: 0.6rem;margin-bottom: 0.6rem">
                <ul>
                    <li>
                        <div class="item-content">
                            <div class="item-media"><i class="icon icon-form-name"></i></div>
                            <div class="item-inner">
                                <div class="item-input">
                                    <textarea placeholder="字数不能少于五个字" id="evaContent"></textarea>
                                    <input type="hidden" id="type" value="">
                                    <input type="hidden" id="orderId" value="${orderId}">
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="row" style="margin: 0.6rem">
                <div class="col-50" style="margin-left: 0">
                    <button class="button button-fill  button-light" style="width: 100%;font-size: 0.8rem;height: 2rem;" id="goodEva">赏好评</button>
                </div>
                <div class="col-50" style="margin-left: 8%">
                    <button class="button button-fill  button-light" style="width: 100%;font-size: 0.8rem;height: 2rem" id="badEva">不赏</button>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
<script type='text/javascript' src='static/app/js/user/my.js' charset='utf-8'></script>
</body>
</html>
