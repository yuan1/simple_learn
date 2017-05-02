<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/13
  Time: 1:17
  To change tdis template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/common/_meta.jsp" %>
</head>
<body>
<div class="page-container">
    <form action="permission/edit/do" method="post" class="form" id="form-edit">
        <table class="table table-border table-bordered table-hover ">
            <tr>
                <td width="100">父菜单：</td>
                <td>
                    ${permissionMenu.name}
                </td>
            </tr>
            <tr>
                <td width="100">名称：</td>
                <td>
                    <input type="hidden" value="${permissionEdit.id}" name="id">
                    <input type="hidden" value="${permissionEdit.type}" name="type">
                    <input type="hidden" value="${permissionEdit.parentid}" name="parentid">
                    <input type="text" class="input-text" value="${permissionEdit.name}" placeholder="权限名称" id="name"
                           name="name">
                </td>
            </tr>
            <tr>
                <td>标识：</td>
                <td>
                    ${permissionEdit.percode}
                    <input type="hidden" value="${permissionEdit.percode}" name="percode">
                </td>
            </tr>
            <tr>
                <td>排序：</td>
                <td><input type="text" class="input-text" value="${permissionEdit.sort}"
                                                      placeholder="排序" id="sort"
                                                      name="sort"></td>
            </tr>
            <tr>
                <td>操作：</td>
                <td><input type="submit" class="btn btn-primary radius size-S" value="提交"
                ></td>
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
