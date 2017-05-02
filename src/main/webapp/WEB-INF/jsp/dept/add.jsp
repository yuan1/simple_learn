<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/14
  Time: 0:02
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
    <form action="dept/add/do" method="post" class="form" id="form-add">
        <table class="table table-border table-bordered table-hover ">
            <tr>
                <td width="100">上级节点：</td>
                <td>
                    ${deptAdd.name}
                    <input type="hidden" value="${deptAdd.id}" name="parentid">
                </td>
            </tr>
            <tr>
                <td width="100">名称：</td>
                <td>
                    <input type="text" class="input-text" value="" placeholder="组织机构名称" id="name" name="name">
                </td>
            </tr>
            <tr>
                <td>联系人：</td>
                <td>
                    <input type="text" class="input-text" value="" placeholder="联系人" id="contact"
                           name="contact">
                </td>
            </tr>
            <tr >
                <td>电话：</td>
                <td>
                    <input type="text" class="input-text" value="" placeholder="联系人" id="phone"
                           name="phone">
                </td>
            </tr>
            <tr>
                <td>备注：</td>
                <td><input type="text" class="input-text" value=""
                                                      placeholder="备注" id="remark"
                                                      name="remark"></td>
            </tr>
            <tr>
                <td>排序：</td>
                <td><input type="text" class="input-text" value=""
                                                      placeholder="排序" id="sort"
                                                      name="sort"></td>
            </tr>
            <tr>
                <td>操作：</td>
                <td>
                    <shiro:hasPermission name="dept:add">
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
        $("#form-add").validate({
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
                ajaxFormSubmit('form-add');
            }
        });
    })
</script>
</body>
</html>

