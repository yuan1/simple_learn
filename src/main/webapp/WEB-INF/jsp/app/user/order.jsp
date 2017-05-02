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
    <div class="page" id="orderPage">
        <header class="bar bar-nav">
            <a class="button button-link button-nav back pull-left" href="#">
                <span class="icon icon-back"></span>
                返回
            </a>
            <h1 class="title">
                <c:if test="${action=='myOrder'}">
                    我的预约
                </c:if>
                <c:if test="${action=='orderMy'}">
                    预约我的
                </c:if>
            </h1>
        </header>
        <div class="content  pull-to-refresh-content" data-ptr-distance="55" id="orderListContent">
            <!-- 默认的下拉刷新层 -->
            <div class="pull-to-refresh-layer">
                <div class="preloader"></div>
                <div class="pull-to-refresh-arrow"></div>
            </div>
            <div class="list-block cards-list" style="margin-top: 0.6rem">
                <ul>
                    <c:forEach items="${orderInfo}" var="order">
                        <li class="card">
                            <div class="card-content">
                                <div class="card-content-inner">
                                    <p>${order.get("findName")}</p>
                                    <p class="color-danger">
                                        剩余${order.get("findLess")}
                                    </p>
                                    <p class="color-gray">
                                        <span class="icon icon-success"></span>
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
                                    </p>
                                </div>
                            </div>
                            <div class="card-footer">
                                <c:if test="${action=='myOrder'}">
                                    <a href="app/chat/message/${order.get("findUserId")}"
                                       style="color: #5f646e;font-size: 0.8rem">
                                        <span class="icon icon-comments"></span>
                                        联系
                                    </a>
                                    <c:if test='${order.get("orderStateNum")=="0"}'>
                                        <a href="javascript:" onclick="cancelMyOrder(${order.get("orderId")})"
                                           style="color: #5f646e;font-size: 0.8rem">
                                            <span class="icon icon-cry"></span>
                                            取消预约
                                        </a>
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="1"}'>
                                        <a href="javascript:" onclick="downMyOrder(${order.get("orderId")})"
                                           style="color: #5f646e;font-size: 0.8rem">
                                            <span class="icon icon-selected"></span>
                                            完成
                                        </a>
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="3"}'>
                                        <a href="javascript:" onclick="evaluateMyOrder(${order.get("orderId")})"
                                           style="color: #5f646e;font-size: 0.8rem">
                                            <span class="icon icon-survey1"></span>
                                            评价
                                        </a>
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="6"}'>
                                        <a href="javascript:" onclick="findShow(${order.get("findId")})"
                                           style="color: #5f646e;font-size: 0.8rem">
                                            <span class="icon icon-survey1"></span>
                                            查看评价
                                        </a>
                                    </c:if>
                                    <a href="javascript:" onclick="showMyOrder(${order.get("orderId")})"
                                       style="color: #5f646e;font-size: 0.8rem">
                                        <span class="icon icon-browse"></span>
                                        查看
                                    </a>
                                </c:if>
                                <c:if test="${action=='orderMy'}">
                                    <a href="app/chat/message/${order.get("orderUserId")}"
                                       style="color: #5f646e;font-size: 0.8rem">
                                        <span class="icon icon-comments"></span>
                                        联系
                                    </a>
                                    <c:if test='${order.get("orderStateNum")=="0"}'>
                                        <a href="javascript:" onclick="cancelOrderMy(${order.get("orderId")})"
                                           style="color: #5f646e;font-size: 0.8rem">
                                            <span class="icon icon-cry"></span>
                                            预约取消
                                        </a>
                                        <a href="javascript:" onclick="enterOrderMy(${order.get("orderId")})"
                                           style="color: #5f646e;font-size: 0.8rem">
                                            <span class="icon icon-smile"></span>
                                            预约确认
                                        </a>
                                    </c:if>
                                    <c:if test='${order.get("orderStateNum")=="2"}'>
                                        <a href="javascript:" onclick="downOrderMy(${order.get("orderId")})"
                                           style="color: #5f646e;font-size: 0.8rem">
                                            <span class="icon icon-selected"></span>
                                            完成
                                        </a>
                                    </c:if>

                                    <c:if test='${order.get("orderStateNum")=="6"}'>
                                        <a href="javascript:" onclick="findShow(${order.get("findId")})"
                                           style="color: #5f646e;font-size: 0.8rem">
                                            <span class="icon icon-templatedefault"></span>
                                            查看评价
                                        </a>
                                    </c:if>
                                    <a href="javascript:" onclick="showOrderMy(${order.get("orderId")})"
                                       style="color: #5f646e;font-size: 0.8rem">
                                        <span class="icon icon-browse"></span>
                                        查看
                                    </a>
                                </c:if>
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
<script type='text/javascript' src='static/app/js/user/my.js' charset='utf-8'></script>
</body>
</html>
