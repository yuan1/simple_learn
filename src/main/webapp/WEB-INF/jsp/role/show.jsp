<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/15
  Time: 12:35
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
        <c:if test="${roleShow.role.parentid!=0}">
        <tr>
            <td width="100">父节点：</td>
            <td>
                        ${roleShow.topRole}
            </td>
        </tr>
            </c:if>
        <tr>
            <td width="100">名称：</td>
            <td>${roleShow.role.name}</td>
        </tr>
        <tr>
            <td>排序：</td>
            <td>${roleShow.role.sort}</td>
        </tr>
        <tr>
            <td>备注：</td>
            <td>
                ${roleShow.role.remark}</td>
        </tr>
        <tr>
            <td>操作：</td>
            <td>
                <shiro:hasPermission name="role:add">
                <a class="btn btn-primary radius size-S" onclick="layer_show('添加子节点','role/add/${roleShow.role.id}')" href="javascript:">添加子节点</a>
                </shiro:hasPermission>
                <c:if test="${roleNow.id!=roleShow.role.id}">
                    <shiro:hasPermission name="permission:roleEdit">
                        <button class="btn btn-primary radius size-S" onclick="layer_show('权限修改','role/permission/edit/${roleShow.role.id}')">修改权限</button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="role:edit">
                    <a class="btn btn-primary radius size-S" onclick="layer_show('修改','role/edit/${roleShow.role.id}')" href="javascript:">修改</a>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="role:del">
                    <button class="btn btn-primary radius size-S" onclick="ajaxDelSubmit('role/del/${roleShow.role.id}')">删除</button>
                    </shiro:hasPermission>
                </c:if>

            </td>
        </tr>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
</body>
</html>