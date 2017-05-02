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
    <title>查看</title>
</head>
<body>
<div class="page-group">
    <!-- 你的html代码 -->
    <div class="page" id="showPage">
        <input type="hidden" id="findId" value="${find.get("findId")}">
        <c:if test="${find.get('edit')!=0}">
            <nav class="bar bar-tab">
                <div class="row">
                    <div class="col-20">
                        <a class="tab-item" href="app/chat/message/${find.get("userId")}">
                            <span class="icon icon-comments" style="color: #0894ec"></span>
                        </a>
                    </div>
                    <div class="col-20">
                        <a class="tab-item" href="javascript:" id="starFind" data-role=${find.get("star")}>
                        <span class="icon
                        <c:if test="${find.get('star')=='0'}">
                        icon-favorite
                        </c:if>
                       <c:if test="${find.get('star')!='0'}">
                        icon-favoritesfilling
                        </c:if>
                        " style="color: #0894ec" id="starIcon"></span>
                        </a>
                    </div>
                    <div class="col-60" style="background-color: #0894ec">
                        <a class="tab-item" href="javascript:" id="orderFind" data-role=${find.get("order")}>
                        <span class="tab-label" style="color: #FFFFFF">
                             <c:if test="${find.get('order')=='0'}">
                                 立即预约
                             </c:if>
                       <c:if test="${find.get('order')!='0'}">
                           已预约
                       </c:if>
                            </span>
                        </a>
                    </div>
                </div>
            </nav>
        </c:if>
        <c:if test="${find.get('edit')==0}">
            <nav class="bar bar-tab">
                <div class="pull-left" style="background-color: #0894ec;width: 60%;border-radius: 0.25rem">
                    <a class="tab-item external" href="app/find/edit/${find.get("findNum")}">
                        <span class="icon icon-edit" style="color: #FFFFFF"></span>
                        <span class="tab-label" style="color: #FFFFFF">
                                修改
                        </span>
                    </a>
                </div>
                <div class="pull-right" style="background-color: red;width: 40%;border-radius: 0.25rem">
                    <a class="tab-item" href="javascript:" id="delFind">
                        <span class="icon icon-delete" style="color: #FFFFFF"></span>
                        <span class="tab-label" style="color: #FFFFFF">
                            删除
                        </span>
                    </a>
                </div>
            </nav>
        </c:if>
        <header class="bar bar-nav">
            <a class="button button-link button-nav back pull-left" href="#">
                <span class="icon icon-back"></span>
                返回
            </a>
            <h1 class="title">查看</h1>
        </header>
        <div class="content">
            <div class="list-block media-list" style="margin-top: 0.6rem;margin-bottom: 0.4rem">
                <ul>
                    <li>
                        <div class="item-content">
                            <div class="item-media"><img
                                    src="http://images.javayuan.cn/${find.get("userImage")}"
                                    style='width: 3rem;border-radius: 50%'></div>
                            <div class="item-inner">
                                <div class="item-title-row">
                                    <div class="item-title">${find.get("userNick")}</div>
                                </div>
                                <div class="item-subtitle">${find.get("userName")}</div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="card" style="margin: 0">
                <div class="card-content">
                    <div class="card-content-inner" style="word-break: break-all">
                        <h4>${find.get("findName")}</h4>
                    </div>
                </div>
                <div class="card-footer" style="font-size: 0.6rem">
                   截至时间：${find.get("findTime")}<br>
                    剩余：${find.get("findLess")}<br>
                    地点：${find.get("findAddr")}<br>
                    评价：好评（${find.get("evaGoodSize")}）差评（${find.get("evaBadSize")}）
                </div>
            </div>
            <div class="card" style="margin: 0.6rem 0 0;">
                <div class="card-header">
                    <span class="text-center" style="width: 100%">具体内容<br>
                      <span style="font-size: 0.2rem">CONTENT</span>
                    </span>
                </div>
                <div class="card-content">
                    <div class="card-content-inner" style="word-break: break-all">
                        <p>${find.get("findCont")}</p>
                    </div>
                </div>
            </div>
            <div class="card" style="margin: 0.6rem 0 0;">
                <div class="card-header">
                    <span class="text-center" style="width: 100%">注意事项<br>
                      <span style="font-size: 0.2rem">ATTENTION</span>
                    </span>

                </div>
                <div class="card-content">
                    <div class="card-content-inner" style="word-break: break-all">
                        <p>${find.get("findAtte")}</p>
                    </div>
                </div>
            </div>
            <div class="card" style="margin: 0.6rem 0 0; border-bottom: 0.08rem #e1e1e1 solid">
                <div class="card-content">
                    <div class="list-block">
                        <ul>
                            <li>
                                <div class="item-content">
                                    <div class="item-inner">
                                        <span style="font-size: 0.8rem">评价（${find.get("evaSize")}）</span>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <c:forEach items='${find.get("evas")}' var="eva">
                <div class="card facebook-card" style="margin: 0 0 0.1rem; box-shadow:none">
                    <div class="card-header no-border">
                        <div class="pull-left">
                            <div class="facebook-avatar"><img src="http://images.javayuan.cn/${eva.get("userImage")}"
                                                              style="width:2rem;border-radius:50%"></div>
                            <div class="facebook-name">${eva.get("userNick")}</div>
                            <div class="facebook-date">${eva.get("evaType")}</div>
                        </div>
                        <div class="pull-right" style="font-size: 0.6rem">${eva.get("evaTime")}</div>
                    </div>
                    <div class="card-content" style="padding-left: 2rem;word-break: break-all">
                        <div class="card-content-inner">
                            <p>${eva.get("evaContent")}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
</body>
</html>
