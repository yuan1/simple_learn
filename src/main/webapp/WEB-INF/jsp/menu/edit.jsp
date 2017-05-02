<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/13
  Time: 1:17
  To change tdis template use File | Settings | File Templates.
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
    <form action="menu/edit/do" method="post" class="form" id="form-edit">
        <table class="table table-border table-bordered table-striped">

            <c:if test="${menuEdit.menu.type==1}">
                <tr>
                    <td width="100">父菜单：</td>
                    <td>
                                ${menuEdit.topMenu}
                    </td>
                </tr>
            </c:if>
            <tr>
                <td width="100">名称：</td>
                <td>
                    <input type="hidden" value="${menuEdit.menu.id}" name="id">
                    <input type="text" class="input-text" value="${menuEdit.menu.name}" placeholder="菜单名称" id="name"
                           name="name">
                </td>
            </tr>
            <tr>
                <td>类型：</td>
                <td>
                    <input type="hidden" value="${menuEdit.menu.type}" name="type">
                    <input type="hidden" value="${menuEdit.menu.parentid}" name="parentid">
                    <c:if test="${menuEdit.menu.type==0}">
                        主菜单
                    </c:if>
                    <c:if test="${menuEdit.menu.type==1}">
                        子菜单
                    </c:if>
                </td>
            </tr>
            <c:if test="${menuEdit.menu.type==1}">
                <tr>
                    <td>链接：</td>
                    <td>
                        <input type="text" class="input-text" value="${menuEdit.menu.url}" placeholder="菜单链接" id="url"
                               name="url">
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>排序：</td>
                <td><input type="text" class="input-text" value="${menuEdit.menu.sort}"
                                                      placeholder="排序" id="sort"
                                                      name="sort"></td>
            </tr>
            <tr>
                <td>操作：</td>
                <td>
                    <shiro:hasPermission name="menu:edit">
                    <input type="submit" class="btn btn-primary radius size-S" value="提交">
                    </shiro:hasPermission>
                </td>
            </tr>
        </table>
    </form>
</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#form-edit").validate({
            rules: {
                name: {
                    required: true,
                },
                sort: {
                    required: true,
                }
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                ajaxFormSubmit('form-edit');
            }
        });
    })
</script>
</body>
</html>
