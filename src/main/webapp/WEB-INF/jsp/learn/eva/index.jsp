<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2017/3/15
  Time: 18:56
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> APP平台 <span
        class="c-gray en">&gt;</span> 评价信息 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="r">共有数据：<strong>${pageInfo.total}</strong> 条</span></div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="300">信息</th>
                <th width="300">用户</th>
                <th width="300">评价时间</th>
                <th width="300">评价内容</th>
                <th width="300">评分</th>
                <th width="100">状态</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="pl">
                <tr class="text-c">
                    <td>
                        <a title="查看" href="javascript:;" onclick="layer_show('查看','learn/find/show/${pl.find.id}')"
                           class="ml-5" style="text-decoration:none">${pl.find.name}</a>
                    </td>
                    <td>
                        <a title="查看" href="javascript:;" onclick="layer_show('查看','learn/user/show/${pl.user.id}')"
                           class="ml-5" style="text-decoration:none">${pl.user.nickname}</a>
                    </td>
                    <td>${pl.eva.createTime}</td>
                    <td>${pl.eva.content}</td>
                    <td>
                        <c:if test='${pl.eva.type=="0"}'>
                           差评
                        </c:if>
                        <c:if test='${pl.eva.type=="1"}'>
                            好评
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${pl.eva.state==0}">
                            <span class="label label-success radius">正常</span>
                        </c:if>
                        <c:if test="${pl.eva.state==1}">
                            <span class="label label-danger radius">禁止</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${pl.eva.state==1}">
                            <shiro:hasPermission name="learn:eva:start">
                                <a style="text-decoration:none" onClick="ajaxStart('learn/eva/start/${pl.eva.id}')"
                                   href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>
                            </shiro:hasPermission>
                        </c:if>
                        <c:if test="${pl.eva.state==0}">
                            <shiro:hasPermission name="learn:find:stop">
                                <a style="text-decoration:none" onClick="ajaxStop('learn/eva/stop/${pl.eva.id}')"
                                   href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>
                            </shiro:hasPermission>
                        </c:if>
                        <shiro:hasPermission name="learn:eva:del">
                            <a title="删除" href="javascript:;"
                               onclick="ajaxDelSubmitLocationReload('learn/eva/del/${pl.eva.id}')"
                               class="ml-5"
                               style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
                        </shiro:hasPermission>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <form action="learn/eva/index" method="post" id="page_form">
            <input type="hidden" name="rows" value="${pageInfo.pageSize}" id="row">
            <input type="hidden" name="page" id="page">
        </form>
        <div class="mt-20 f-l">
            <label for="pageSizeNum">每页显示</label><input type="number" min="1"
                                                        onkeyup="this.value=this.value.replace(/\D/, '');"
                                                        value="${pageInfo.pageSize}"
                                                        id="pageSizeNum" class="input-text">条
            <button type="button" class="btn btn-info " onclick="setPageSize()">确定</button>
        </div>
        <div class="mt-20 f-r">
            当前为第${pageInfo.pageNum}页，共${pageInfo.pages}页，共${pageInfo.total}条数据
            <c:if test="${pageInfo.hasNextPage}">
                <a href="javascript:" class="btn btn-info" onclick="nextPage()">下一页</a>
            </c:if>
            <c:if test="${pageInfo.hasPreviousPage}">
                <a href="javascript:" class="btn btn-info" onclick="prevPage()">上一页</a>
            </c:if>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
<script type="text/javascript">
    function setPageSize() {
        var pageSizeNum = $("#pageSizeNum").val();
        $("#row").val(pageSizeNum);
        $("#page").val(${pageInfo.pageNum});
        $("#page_form").submit();
    }
    function nextPage() {
        var page = ${pageInfo.pageNum +1};
        var pageSizeNum = $("#pageSizeNum").val();
        $("#row").val(pageSizeNum);
        $("#page").val(page);
        $("#page_form").submit();
    }
    function prevPage() {
        var page = ${pageInfo.pageNum -1};
        var pageSizeNum = $("#pageSizeNum").val();
        $("#row").val(pageSizeNum);
        $("#page").val(page);
        $("#page_form").submit();
    }
</script>
</body>
</html>