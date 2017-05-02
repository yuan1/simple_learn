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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 微信平台 <span
        class="c-gray en">&gt;</span> 自定义回复 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="page-container">
    <div class="text-c">
        <form action="learn/wechat/reply/index" method="post">
            <label for="name"> 关键字：</label>
            <input type="text" class="input-text" style="width:120px;" name="name" id="name"
                   value="${queryParam.name}">
            <input type="hidden" name="rows" value="${pageInfo.pageSize}">
            <label for="reply">回复：</label><input type="text" class="input-text" style="width:120px;" name="reply"
                                                    id="reply" value="${queryParam.reply}">
            <label for="state">状态</label>
            <span class="select-box inline">
            <select name="state" class="select" id="state">
                <option value="" selected>全部</option>
                <option value="0"
                        <c:if test="${queryParam.state==0}">
                            selected
                        </c:if>
                >正常</option>
                <option value="1"
                        <c:if test="${queryParam.state==1}">
                            selected
                        </c:if>
                >禁用</option>
            </select>
        </span>
            <label for="creat">添加时间：</label>
            <input type="date" name="creat" id="creat" value="${queryParam.creat}"
                   class="input-text" style="width:200px;">
            <button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <shiro:hasPermission name="learn:wechat:reply:add">
            <span class="l">
             <a href="javascript:" onclick="layer_show('添加','learn/wechat/reply/add')"
                class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加</a>
                 </span>
        </shiro:hasPermission>
        <span class="r">共有数据：<strong>${pageInfo.total}</strong> 条</span></div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="200">关键字</th>
                <th width="300">回复</th>
                <th width="150" class="hidden-xs">添加时间</th>
                <th width="100">状态</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="wrl">
                <tr class="text-c">
                    <td>${wrl.name}</td>
                    <td>${wrl.reply}</td>
                    <td  class="hidden-xs">${wrl.creat}</td>
                    <td>
                        <c:if test="${wrl.state==0}">
                            <span class="label label-success radius">正常</span>
                        </c:if>
                        <c:if test="${wrl.state==1}">
                            <span class="label label-danger radius">禁用</span>
                        </c:if>
                    </td>

                    <td>
                        <c:if test="${wrl.state==1}">
                            <shiro:hasPermission name="learn:wechat:reply:start">
                                <a style="text-decoration:none" onClick="ajaxStart('learn/wechat/reply/start/${wrl.id}')"
                                   href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>
                            </shiro:hasPermission>
                        </c:if>
                        <c:if test="${wrl.state==0}">
                            <shiro:hasPermission name="learn:wechat:reply:stop">
                                <a style="text-decoration:none" onClick="ajaxStop('learn/wechat/reply/stop/${wrl.id}')"
                                   href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>
                            </shiro:hasPermission>
                        </c:if>
                        <shiro:hasPermission name="learn:wechat:reply:edit">
                            <a title="编辑" href="javascript:;" onclick="layer_show('编辑','learn/wechat/reply/edit/${wrl.id}')"
                               class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="learn:wechat:reply:del">
                            <a title="删除" href="javascript:;"
                               onclick="ajaxDelSubmitLocationReload('learn/wechat/reply/del/${wrl.id}')"
                               class="ml-5"
                               style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
                        </shiro:hasPermission>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <form action="learn/wechat/reply/index" method="post" id="page_form">
            <input type="hidden" name="name" value="${queryParam.name}">
            <input type="hidden" name="state" value="${queryParam.state}">
            <input type="hidden" name="reply" value="${queryParam.reply}">
            <input type="hidden" name="creat" value="${queryParam.creat}">
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