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
    <title>添加</title>
</head>
<body>
<div class="page-group">
    <!-- 你的html代码 -->
    <div class="page" id="addPage">
        <header class="bar bar-nav">
            <a class="button button-link button-nav external pull-left" href="app/index">
                <span class="icon icon-back"></span>
                返回
            </a>
            <h1 class="title">发布</h1>
        </header>
        <nav class="bar bar-tab">
            <a class="tab-item" href="#" id="findAdd" style="background-color:#0894ec;border-radius: 0.25rem ">
                <span class="tab-label" style="color: #FFFFFF">发布</span>
            </a>
        </nav>
        <div class="content">
            <div class="list-block" style="margin-top: 0.6rem">
                <ul>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">标题</div>
                                <div class="item-input">
                                    <input type="text" placeholder="标题" id="name" value="">
                                    <input type="hidden" id="findId" value="${findId}">
                                    <input type="hidden" id="state" value="0">
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label"> 类型</div>
                                <div class="item-input">
                                    <select id="type">
                                        <option value="2">约·学</option>
                                        <option value="3">约·行</option>
                                        <option value="4">约·商</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label"> 地点</div>
                                <div class="item-input">
                                    <input type="text" placeholder="点击选择" readonly id="addressName">
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label">截至时间</div>
                                <div class="item-input">
                                    <input type="text" placeholder="点击选择" id="findTime">
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label"> 具体内容</div>
                                <div class="item-input">
                                    <textarea id="content"></textarea>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title label"> 注意事项</div>
                                <div class="item-input">
                                    <textarea id="attention"></textarea>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
<script type='text/javascript' src='static/app/js/find/add.js' charset='utf-8'></script>
</body>
</html>
