<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2017/3/16
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/common/_meta.jsp" %>
</head>
<body>
<div class="page-container">
    <table class="table table-border table-bordered table-hover ">
        <tr>
            <td>发布用户：</td>
            <td> <a title="查看" href="javascript:;" onclick="layer_show('查看发布用户信息','learn/user/show/${find.user.id}')"
                    class="ml-5" style="text-decoration:none">${find.user.nickname}</a></td>
        </tr>
        <tr>
            <td>名称：</td>
            <td>${find.find.name}</td>
        </tr>
        <tr>
            <td>类型：</td>
            <td>
                <c:if test="${find.find.type==2}">
                    约·学
                </c:if>
                <c:if test="${find.find.type==3}">
                    约·行
                </c:if>
                <c:if test="${find.find.type==4}">
                    约·商
                </c:if>
            </td>
        </tr>
        <tr>
            <td>地点：</td>
            <td>${find.find.addressName}</td>
        </tr>
        <tr>
            <td>截至时间：</td>
            <td>${find.find.findTime}</td>
        </tr>
        <tr>
            <td>具体内容：</td>
            <td>${find.find.content}</td>
        </tr>
        <tr>
            <td>注意事项：</td>
            <td>${find.find.attention}</td>
        </tr>
        <tr>
            <td>评价：</td>
            <td>
                <ul class="commentList">
                    <c:forEach items="${find.evas}" var="evas">
                        <li class="item cl">
                            <a title="查看" href="javascript:;" onclick="layer_show('查看用户信息','learn/user/show/${evas.user.id}')"
                               class="ml-5" style="text-decoration:none"><i class="avatar size-L radius">
                                <img alt="" src="http://images.javayuan.cn/${find.user.image}"></i>
                            </a>
                            <div class="comment-main">
                                <header class="comment-header">
                                    <div class="comment-meta">
                                        <a title="查看" class="comment-author ml-5" href="javascript:;" onclick="layer_show('查看用户信息','learn/user/show/${evas.user.id}')"
                                          style="text-decoration:none">${evas.user.nickname}
                                        </a>
                                       评论于
                                        <time title="${evas.eva.createTime}" datetime="${evas.eva.createTime}">${evas.eva.createTime}</time>
                                        <c:if test='${evas.eva.type=="0"}'>
                                            差评
                                        </c:if>
                                        <c:if test='${evas.eva.type=="1"}'>
                                            好评
                                        </c:if>
                                    </div>
                                </header>
                                <div class="comment-body">
                                    <p> ${evas.eva.content}</p>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>

            </td>
        </tr>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
</body>
</html>