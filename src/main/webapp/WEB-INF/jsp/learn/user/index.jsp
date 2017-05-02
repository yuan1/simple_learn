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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 注册用户 <span
        class="c-gray en">&gt;</span> 用户列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="page-container">
    <div class="text-c">
        <form action="learn/user/index" method="post">
            <label for="username"> 用户名：</label>
            <input type="text" class="input-text" style="width:120px;" name="username" id="username"
                   value="${queryParam.username}">
            <input type="hidden" name="rows" value="${pageInfo.pageSize}">
            <label for="mobile">手机号：</label><input type="text" class="input-text" style="width:120px;" name="mobile"
                                                   id="mobile" value="${queryParam.mobile}">
            <label for="nickname">昵称：</label><input type="text" class="input-text" style="width:120px;" name="nickname"
                                                    id="nickname" value="${queryParam.nickname}">
            <label for="type">用户状态</label>
            <span class="select-box inline">
            <select name="type" class="select" id="type">
                <option value="" selected>全部</option>
                <option value="0"
                        <c:if test="${queryParam.type==0}">
                            selected
                        </c:if>
                >正常</option>
                <option value="1"
                        <c:if test="${queryParam.type==1}">
                            selected
                        </c:if>
                >禁用</option>
            </select>
        </span>
            <label for="creat">注册时间：</label>
            <input type="date" name="creat" id="creat" value="${queryParam.creat}"
                   class="input-text" style="width:200px;">
            <button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <shiro:hasPermission name="learn:user:add">
            <span class="l">
             <a href="javascript:" onclick="layer_show('添加用户','learn/user/add')"
                class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a>
                 </span>
        </shiro:hasPermission>
        <span class="r">共有数据：<strong>${pageInfo.total}</strong> 条</span></div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="200">用户名</th>
                <th width="200">昵称</th>
                <th width="160">手机号码</th>
                <th width="50" class="hidden-xs">性别</th>
                <th width="100" class="hidden-xs">注册时间</th>
                <th width="100">状态</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="usl">
                <tr class="text-c">
                    <td>${usl.username}</td>
                    <td>${usl.nickname}</td>
                    <td>${usl.mobile}</td>
                    <td class="hidden-xs">${usl.sex}</td>
                    <td  class="hidden-xs">${usl.creat}</td>
                    <td>
                        <c:if test="${usl.type==0}">
                            <span class="label label-success radius">正常</span>
                        </c:if>
                        <c:if test="${usl.type==1}">
                            <span class="label label-danger radius">禁用</span>
                        </c:if>
                    </td>

                    <td>
                        <c:if test="${usl.type==1}">
                            <shiro:hasPermission name="learn:user:start">
                                <a style="text-decoration:none" onClick="ajaxStart('learn/user/start/${usl.id}')"
                                   href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>
                            </shiro:hasPermission>
                        </c:if>
                        <c:if test="${usl.type==0}">
                            <shiro:hasPermission name="learn:user:stop">
                                <a style="text-decoration:none" onClick="ajaxStop('learn/user/stop/${usl.id}')"
                                   href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>
                            </shiro:hasPermission>
                        </c:if>
                        <shiro:hasPermission name="learn:user:show">
                            <a title="查看" href="javascript:;" onclick="layer_show('查看','learn/user/show/${usl.id}')"
                               class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe695;</i></a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="learn:user:edit">
                            <a title="编辑" href="javascript:;" onclick="layer_show('编辑','learn/user/edit/${usl.id}')"
                               class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="learn:user:del">
                            <a title="删除" href="javascript:;"
                               onclick="ajaxDelSubmitLocationReload('learn/user/del/${usl.id}')"
                               class="ml-5"
                               style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
                        </shiro:hasPermission>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <form action="learn/user/index" method="post" id="page_form">
            <input type="hidden" name="username" value="${queryParam.username}">
            <input type="hidden" name="mobile" value="${queryParam.mobile}">
            <input type="hidden" name="type" value="${queryParam.type}">
            <input type="hidden" name="creat" value="${queryParam.creat}">
            <input type="hidden" name="nickname" value="${queryParam.nickname}">
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