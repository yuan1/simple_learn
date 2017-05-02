<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/13
  Time: 14:57
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

        <c:if test="${menuShow.menu.type==1}">
            <tr>
                <td width="100">父菜单：</td>
                <td>
                        ${menuShow.topMenu}
                </td>
            </tr>
        </c:if>
        <tr>
            <td width="100">名称：</td>
            <td>${menuShow.menu.name}</td>
        </tr>


        <tr>
            <td>类型：</td>
            <td>
                <c:if test="${menuShow.menu.type==0}">
                    主菜单
                </c:if>
                <c:if test="${menuShow.menu.type==1}">
                    子菜单
                </c:if></td>
        </tr>
        <c:if test="${menuShow.menu.type==1}">
            <tr>
                <td>链接：</td>
                <td>${menuShow.menu.url}</td>
            </tr>
        </c:if>
        <shiro:hasPermission name="permission:edit">
            <c:if test="${menuShow.menu.type==1}">
                <tr>
                    <td>权限：</td>
                    <td>
                        <c:forEach items="${menuShow.permissionList}" var="pmls">
                            <a href="javascript:" class="btn  btn-primary-outline size-MINI radius"
                               onclick="layer_show('权限编辑','permission/edit/${pmls.id}')">${pmls.name}</a>
                        </c:forEach>
                    </td>
                </tr>
            </c:if>
        </shiro:hasPermission>
        <tr>
            <td>排序：</td>
            <td>${menuShow.menu.sort}</td>
        </tr>
        <tr>
            <td>操作：</td>
            <td>
                <shiro:hasPermission name="menu:add">
                    <c:if test="${menuShow.menu.type==0}">
                        <a class="btn btn-primary radius size-S" href="javascript:" onclick="layer_show('添加菜单','menu/add')"">
                        添加菜单
                        </a>

                    </c:if>
                </shiro:hasPermission>
                <shiro:hasPermission name="permission:add">
                    <c:if test="${menuShow.menu.type==1}">
                        <a class="btn btn-primary radius size-S" href="javascript:" onclick="layer_show('添加权限','permission/add/${menuShow.menu.id}')">
                        添加权限
                        </a>
                    </c:if>
                </shiro:hasPermission>
                <shiro:hasPermission name="menu:edit">
                    <a class="btn btn-primary radius size-S" href="javascript:" onclick="layer_show('修改菜单','menu/edit/${menuShow.menu.id}')">修改菜单</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="menu:del">
                    <button class="btn btn-primary radius 	size-S"
                            onclick="ajaxDelSubmit('menu/del/${menuShow.menu.id}')">
                        删除
                    </button>
                </shiro:hasPermission>

            </td>
        </tr>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
</body>
</html>
