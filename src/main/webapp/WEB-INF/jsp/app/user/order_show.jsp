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
    <title>预约详情</title>
</head>
<body>
<div class="page-group">
    <!-- 你的html代码 -->
    <div class="page" id="orderShowPage">
        <header class="bar bar-nav">
            <a class="button button-link button-nav back pull-left" href="#">
                <span class="icon icon-back"></span>
                返回
            </a>
            <h1 class="title">预约详情</h1>
        </header>
        <div class="content">
            <div class="list-block" style="margin-top: 0.6rem;margin-bottom: 0">
                <ul>
                    <li class="item-content" style="height: 4rem">
                        <div class="item-inner">
                            <div class="item-title" style="font-size: 1rem">
                                <c:if test="${action=='myOrder'}">
                                    <c:if test='${order.get("orderStateNum")=="0"}'>
                                        等待预约确认
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="4"}'>
                                        您已取消
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="5"}'>
                                        对方取消，预约失败
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="1"}'>
                                        对方已确认
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="2"}'>
                                        您已确认完成
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="3"}'>
                                        对方已确认完成
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="6"}'>
                                        已评价
                                    </c:if>
                                </c:if>
                                <c:if test="${action=='orderMy'}">
                                    <c:if test='${order.get("orderStateNum")=="0"}'>
                                        等待您确认
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="5"}'>
                                        您已取消
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="1"}'>
                                        您已确认
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="2"}'>
                                        对方已确认完成
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="3"}'>
                                        您已确认完成
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="6"}'>
                                        对方已评价
                                    </c:if>
                                </c:if>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <c:if test="${action=='myOrder'}">
                <div class="list-block media-list" style="margin-top: 0.6rem;margin-bottom: 0">
                    <ul>
                        <li>
                            <div class=" item-content">
                                <div class="item-inner">
                                    <div class="item-title-row">
                                        <div class="item-title">
                                            对方信息：
                                        </div>
                                        <div class="item-after">
                                                ${order.get("toUserNick")}(${order.get("toUserName")})
                                            <a href="app/chat/message/${order.get("toUserId")}"
                                               style="color: #5f646e;margin-left: 1rem;border-left: 0.08rem #afb5bf solid;padding-left: 1rem">
                                                <span class="icon icon-comments"></span>
                                                联系
                                            </a>
                                        </div>
                                    </div>
                                    <c:if test='${order.get("orderStateNum")=="1"||order.get("orderStateNum")=="2"||order.get("orderStateNum")=="3"||order.get("orderStateNum")=="6"}'>
                                        <div class="item-title-row"
                                             style="border-top: 0.08rem #afb5bf solid;padding-top: 0.6rem;margin-top: 0.6rem">
                                            <div class="item-title">
                                                联系方式：
                                            </div>
                                            <div class="item-after">
                                                    ${order.get("toUserMobile")}
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </c:if>
            <c:if test="${action=='orderMy'}">
                <div class="list-block media-list" style="margin-top: 0.6rem;margin-bottom: 0">
                    <ul>
                        <li>
                            <div class=" item-content">
                                <div class="item-inner">
                                    <div class="item-title-row">
                                        <div class="item-title">
                                            对方信息：
                                        </div>
                                        <div class="item-after">
                                                ${order.get("fromUserNick")}(${order.get("fromUserName")})
                                            <a href="app/chat/message/${order.get("fromUserId")}"
                                               style="color: #5f646e;margin-left: 1rem;border-left: 0.08rem #afb5bf solid;padding-left: 1rem">
                                                <span class="icon icon-comments"></span>
                                                联系
                                            </a>
                                        </div>
                                    </div>
                                    <c:if test='${order.get("orderStateNum")=="1"||order.get("orderStateNum")=="2"||order.get("orderStateNum")=="3"||order.get("orderStateNum")=="6"}'>
                                        <div class="item-title-row"
                                             style="border-top: 0.08rem #afb5bf solid;padding-top: 0.6rem;margin-top: 0.6rem">
                                            <div class="item-title">
                                                联系方式：
                                            </div>
                                            <div class="item-after">
                                                    ${order.get("fromUserMobile")}
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </c:if>
            <div class="list-block media-list" style="margin-top: 0.6rem;font-size: 0.8rem;margin-bottom: 0">
                <ul>
                    <li>
                        <a href="app/find/show/${order.get("findId")}" class="item-link item-content">
                            <div class="item-inner">
                                <div class="item-title-row">
                                    <div class="item-title">${order.get("findName")}</div>
                                </div>
                                <div class="item-title-row" style="margin-top: 0.6rem;color: red">
                                    <div class="item-title">剩余：${order.get("findLess")}</div>
                                </div>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="list-block media-list color-gray" style="margin-top: 0.6rem;margin-bottom: 0;font-size: 0.6rem">
                <ul>
                    <li>
                        <div class=" item-content">
                            <div class="item-inner">
                                <div class="item-title-row">
                                    <div class="item-title">
                                        预约时间：
                                    </div>
                                    <div class="item-after">
                                        ${order.get("orderCreate")}
                                    </div>
                                </div>
                                <div class="item-title-row"
                                     style="border-top: 0.08rem #afb5bf solid;padding-top: 0.6rem;margin-top: 0.6rem">
                                    <div class="item-title">
                                        预约编号：
                                    </div>
                                    <div class="item-after">
                                        ${order.get("orderNum")}
                                    </div>
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
<script src="https://g.alicdn.com/aliww/??h5.imsdk/2.1.0/scripts/yw/wsdk.js,h5.openim.kit/0.3.7/scripts/kit.js"
        charset="utf-8"></script>
<script type='text/javascript' src='static/app/js/user/my.js' charset='utf-8'></script>
</body>
</html>
