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
        class="c-gray en">&gt;</span> 发布信息 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="page-container">
    <div class="text-c">
        <form action="learn/find/index" method="post">
            <label for="name"> 名称：</label>
            <input type="text" class="input-text" style="width:120px;" name="name" id="name"
                   value="${queryParam.name}">
            <input type="hidden" name="rows" value="${pageInfo.pageSize}">
            <label for="state">状态</label>
            <span class="select-box inline">
            <select name="state" class="select" id="state">
                <option value="" selected>全部</option>
                <option value="1"
                        <c:if test="${queryParam.state==1}">
                            selected
                        </c:if>
                >正常</option>
                 <option value="2"
                         <c:if test="${queryParam.state==2}">
                             selected
                         </c:if>
                 >禁止</option>
            </select>
        </span>
            <label for="type">类型</label>
            <span class="select-box inline">
            <select name="type" class="select" id="type">
                <option value="" selected>全部</option>
                <option value="2"
                        <c:if test="${queryParam.type==2}">
                            selected
                        </c:if>
                >约·学</option>
                 <option value="3"
                         <c:if test="${queryParam.type==3}">
                             selected
                         </c:if>
                 >约·行</option>
                  <option value="4"
                          <c:if test="${queryParam.type==4}">
                              selected
                          </c:if>
                  >约·商</option>
            </select>
        </span>
            <label for="createTime">添加时间：</label>
            <input type="date" name="createTime" id="createTime" value="${queryParam.createTime}"
                   class="input-text" style="width:200px;">
            <button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
        </form>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="r">共有数据：<strong>${pageInfo.total}</strong> 条</span></div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="200">名称</th>
                <th width="300">地点</th>
                <th width="100" class="hidden-xs">类型</th>
                <th width="150" class="hidden-xs">添加时间</th>
                <th width="100">状态</th>
                <th width="100">发布用户</th>
                <th width="100">评价数</th>
                <th width="100">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageInfo.list}" var="pl">
                <tr class="text-c">
                    <td>${pl.find.name}</td>
                    <td>${pl.find.addressName}</td>
                    <td class="hidden-xs">
                        <c:if test="${pl.find.type==2}">
                            约·学
                        </c:if>
                        <c:if test="${pl.find.type==3}">
                            约·行
                        </c:if>
                        <c:if test="${pl.find.type==4}">
                            约·商
                        </c:if>
                    </td>
                    <td class="hidden-xs">${pl.find.createTime}</td>
                    <td>
                        <c:if test="${pl.find.state==1}">
                            <span class="label label-success radius">正常</span>
                        </c:if>
                        <c:if test="${pl.find.state==2}">
                            <span class="label label-danger radius">禁止</span>
                        </c:if>
                        <c:if test="${pl.find.state==3}">
                            <span class="label label-danger radius">用户删除</span>
                        </c:if>
                    </td>
                    <td>
                        <a title="查看" href="javascript:;" onclick="layer_show('查看发布用户信息','learn/user/show/${pl.user.id}')"
                           class="ml-5" style="text-decoration:none">${pl.user.nickname}</a>
                    </td>
                    <td>${pl.evas.size()}</td>
                    <td>
                        <c:if test="${pl.find.state==2}">
                            <shiro:hasPermission name="learn:find:start">
                                <a style="text-decoration:none" onClick="ajaxStart('learn/find/start/${pl.find.id}')"
                                   href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>
                            </shiro:hasPermission>
                        </c:if>
                        <c:if test="${pl.find.state==1}">
                            <shiro:hasPermission name="learn:find:stop">
                                <a style="text-decoration:none" onClick="ajaxStop('learn/find/stop/${pl.find.id}')"
                                   href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>
                            </shiro:hasPermission>
                        </c:if>
                        <shiro:hasPermission name="learn:find:show">
                            <a title="查看" href="javascript:;" onclick="layer_show('查看','learn/find/show/${pl.find.id}')"
                               class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe695;</i></a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="learn:find:edit">
                            <a title="编辑" href="javascript:;" onclick="layer_show('编辑','learn/find/edit/${pl.find.id}')"
                               class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="learn:find:del">
                            <a title="删除" href="javascript:;"
                               onclick="ajaxDelSubmitLocationReload('learn/find/del/${pl.find.id}')"
                               class="ml-5"
                               style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
                        </shiro:hasPermission>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <form action="learn/find/index" method="post" id="page_form">
            <input type="hidden" name="name" value="${queryParam.name}">
            <input type="hidden" name="state" value="${queryParam.state}">
            <input type="hidden" name="createTime" value="${queryParam.createTime}">
            <input type="hidden" name="type" value="${queryParam.type}">
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