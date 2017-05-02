<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/13
  Time: 20:40
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
        <c:if test="${deptShow.sysDept.parentid!=0}">
            <tr>
                <td width="100">父节点：</td>
                <td>
                        ${deptShow.topDept}
                </td>
            </tr>
        </c:if>
        <tr>
            <td width="100">名称：</td>
            <td>${deptShow.sysDept.name}</td>
        </tr>
        <tr>
            <td>联系人：</td>
            <td>${deptShow.sysDept.contact}</td>
        </tr>
        <tr>
            <td>电话：</td>
            <td>${deptShow.sysDept.phone}</td>
        </tr>
        <tr>
            <td>排序：</td>
            <td>${deptShow.sysDept.sort}</td>
        </tr>
        <tr>
            <td>备注：</td>
            <td>
                ${deptShow.sysDept.remark}</td>
        </tr>
        <tr>
            <td>操作：</td>
            <td>
                <shiro:hasPermission name="dept:add">
                    <a class="btn btn-primary radius size-S" href="javascript:" onclick="layer_show('添加子节点','dept/add/${deptShow.sysDept.id}')">添加子节点</a>
                </shiro:hasPermission>
                <c:if test="${deptNow.id != deptShow.sysDept.id}">
                    <shiro:hasPermission name="dept:edit">
                        <a class="btn btn-primary radius size-S" href="javascript:" onclick="layer_show('修改','dept/edit/${deptShow.sysDept.id}')">修改</a>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="dept:del">
                        <button class="btn btn-primary radius size-S"
                                onclick="ajaxDelSubmit('dept/del/${deptShow.sysDept.id}')">删除
                        </button>
                    </shiro:hasPermission>
                </c:if>

            </td>
        </tr>
    </table>
</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
</body>
</html>

