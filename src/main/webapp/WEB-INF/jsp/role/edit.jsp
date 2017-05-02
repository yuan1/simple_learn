<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/15
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/common/_meta.jsp" %>
</head>
<body>
<div class="page-container">
    <form action="role/edit/do" method="post" class="form" id="form-edit">
        <table class="table table-border table-bordered table-hover ">
            <tr>
                <td width="100">父节点：</td>
                <td>
                    ${roleEdit.topRole}
                    <input type="hidden" value="${roleEdit.role.parentid}" name="parentid">
                    <input type="hidden" value="${roleEdit.role.id}" name="id">
                </td>
            </tr>
            <tr>
                <td width="100">名称：</td>
                <td>
                    <input type="text" class="input-text" value="${roleEdit.role.name}" placeholder="角色名称" id="name"
                           name="name">
                </td>
            </tr>
            <tr>
                <td>排序：</td>
                <td><input type="text" class="input-text" value="${roleEdit.role.sort}"
                                                      placeholder="排序" id="sort"
                                                      name="sort"></td>
            </tr>
            <tr id="tricon">
                <td>备注：</td>
                <td>
                    <input type="text" class="input-text" placeholder="备注" name="remark" value="${roleEdit.role.remark}"></td>
            </tr>
            <tr>
                <td>操作：</td>
                <td>
                    <shiro:hasPermission name="role:edit">
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