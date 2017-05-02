<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/15
  Time: 14:26
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
    <form action="role/permission/edit/do" method="post" class="form" id="form-edit">
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">权限名称：</label>
            <div class="formControls col-xs-9 col-sm-10">
                ${rolePermissionEdit.name}
                <input type="hidden" value="${rolePermissionEdit.id}" name="roleid">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">菜单分配：</label>
            <div class="formControls col-xs-9 col-sm-10">

                <c:forEach items="${userPermissionList}" var="mapMu">
                    <c:forEach items="${mapMu.key}" var="listTopMu">
                        <dl class="permission-list">
                            <dt>
                                <label>

                                        <input type="checkbox" value="${listTopMu.id}"
                                        <c:forEach items="${permissionList}" var="rmelt">
                                        <c:if test="${rmelt==listTopMu.id}">
                                               checked
                                        </c:if>
                                        </c:forEach>
                                               name="permissionid">
                                            ${listTopMu.name}</label>

                            </dt>
                            <c:forEach var="listMu" items="${mapMu.value}">
                                <c:if test="${listMu.key.parentid==listTopMu.id}">
                            <dd>
                                <dl class="cl permission-list2">
                                        <dt>
                                            <label class="">
                                                <input type="checkbox" name="permissionid" value="${listMu.key.id}"
                                                <c:forEach items="${permissionList}" var="rmelt">
                                                <c:if test="${rmelt==listMu.key.id}">
                                                       checked
                                                </c:if>
                                                </c:forEach>
                                                >
                                                    ${listMu.key.name}</label>：
                                            <c:forEach items="${listMu.value}" var="listMuVal">
                                                <label class="">
                                                    <input type="checkbox" name="permissionid" value="${listMuVal.id}"
                                                    <c:forEach items="${permissionList}" var="rmelt">
                                                    <c:if test="${rmelt==listMuVal.id}">
                                                           checked
                                                    </c:if>
                                                    </c:forEach>
                                                    >
                                                        ${listMuVal.name}</label>

                                            </c:forEach>

                                        </dt>
                                </dl>
                            </dd>
                                </c:if>
                            </c:forEach>
                        </dl>
                        </c:forEach>
                    </c:forEach>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">修改说明：</label>
            <div class="formControls col-xs-9 col-sm-10">
                <label class="c-warning">勾选子菜单权限必须同时勾选父菜单！</label>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-5 col-sm-2 col-xs-offset-3 col-sm-offset-2">
                <shiro:hasPermission name="permission:roleEdit">
                <input type="button" class="btn btn-success radius" id="admin-role-save" onclick="ajaxFormSubmit('form-edit')" name="admin-role-save" value="确定">
                </shiro:hasPermission>
            </div>
        </div>
    </form>
</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
</body>
</html>