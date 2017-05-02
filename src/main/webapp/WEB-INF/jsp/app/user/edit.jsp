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
    <div class="page" id="myEditPage">
        <header class="bar bar-nav">
            <a class="button button-link button-nav back pull-left" href="#">
                <span class="icon icon-back"></span>
                返回
            </a>
            <a class="button button-link button-nav pull-right" id="saveMyForm" href="javascript:">
                保存
                <span class="icon icon-right"></span>
            </a>
            <h1 class="title">编辑资料</h1>
        </header>
        <div class="content">
            <form action="" method="post" id="myInfoForm">
                <div class="list-block" style="margin-top: 0.6rem">
                    <ul>
                        <li class="item-content item-link" id="userImage">
                            <div class="item-inner">
                                <div class="item-title label">头像</div>
                                <div class="item-media"><img src="http://images.javayuan.cn/${myInfo.image}"
                                                             style='width: 3rem;;border-radius: 50%' id="imageNow">
                                    <input type="hidden" value="${myInfo.image}" id="imageInput">
                                </div>
                            </div>
                        </li>
                        <li class="item-content item-link">
                            <div class="item-inner">
                                <div class="item-title">昵称</div>
                                <div class="item-after">
                                    <div class="item-input">
                                        <input type="text" placeholder="昵称" id="nickname" value="${myInfo.nickname}"
                                               style="text-align: right">
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="item-content">
                            <div class="item-inner">
                                <div class="item-title">用户名</div>
                                <div class="item-after">
                                    ${myInfo.username}
                                    <input type="hidden" value="${myInfo.id}" id="id">
                                </div>
                            </div>
                        </li>
                        <li class="item-content">
                            <div class="item-inner">
                                <div class="item-title">手机号码</div>
                                <div class="item-after">
                                    ${myInfo.mobile}
                                </div>
                            </div>
                        </li>
                        <li class="item-content item-link">
                            <div class="item-inner">
                                <div class="item-title">性别</div>
                                <div class="item-after">
                                    <div class="item-input">
                                        <select name="sex" id="sex">
                                            <option value="男"
                                                    <c:if test="${myInfo.sex=='男'}">
                                                        selected
                                                    </c:if>
                                            >男
                                            </option>
                                            <option value="女"
                                                    <c:if test="${myInfo.sex=='女'}">
                                                        selected
                                                    </c:if>
                                            >女
                                            </option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="item-content item-link">
                            <div class="item-inner">
                                <div class="item-title">邮箱</div>
                                <div class="item-after">
                                    <div class="item-input">
                                        <input type="text" placeholder="邮箱" value="${myInfo.email}"
                                               style="text-align: right" id="email">
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="item-content">
                            <div class="item-inner">
                                <div class="item-title">微信</div>
                                <div class="item-after">
                                    <c:if test="${myInfo.openid==''}">
                                        未绑定
                                    </c:if>
                                    <c:if test="${myInfo.openid!=''}">
                                        已绑定
                                    </c:if>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </form>
            <form action="app/user/upload/image" method="post" id="imageForm">
                <input type="file" value="" name="file" style="display: none" id="imageFile"
                       onchange="getImageSize(this)">
            </form>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/app/common/_footer.jsp" %>
<script type='text/javascript' src='static/app/js/user/my.js' charset='utf-8'></script>
</body>
</html>
