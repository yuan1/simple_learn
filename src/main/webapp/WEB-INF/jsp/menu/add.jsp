<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/13
  Time: 17:00
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
    <form action="menu/add/do" method="post" class="form" id="form-add">
        <table class="table table-border table-bordered table-hover ">
            <tr>
                <td width="100">类型：</td>
                <td>
                    <span class="select-box">
				<select name="type" id="type" class="select" onchange="checkMenuType()">
					<option value="0">主菜单</option>
                    <option value="1">子菜单</option>
				</select>
				</span>
                </td>
            </tr>
            <tr id="trparentid">
                <td width="100">父菜单：</td>
                <td>
                        <span class="select-box">
				<select name="parentid" class="select" id="parentid">
                    <c:forEach items="${menuTopTypeList}" var="mttl">
                        <option value="${mttl.id}"
                        >${mttl.name}</option>
                    </c:forEach>
				</select>
				</span>
                </td>
            </tr>
            <tr>
                <td width="100">名称：</td>
                <td>
                    <input type="text" class="input-text" value="" placeholder="菜单名称" id="name"
                           name="name">
                </td>
            </tr>

            <tr id="trurl">
                <td>链接：</td>
                <td>
                    <input type="text" class="input-text" value="" placeholder="菜单链接" id="url"
                           name="url">
                </td>
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
                    <shiro:hasPermission name="menu:add">
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
        checkMenuType();
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
    function checkMenuType() {
        var type = $('#type').val();
        if (type == 0) {
            $("#trparentid").addClass("hidden");
            $("#trurl").addClass("hidden");
            $("#tricon").removeClass("hidden")
        }
        if (type == 1) {
            $("#trparentid").removeClass("hidden")
            $("#trurl").removeClass("hidden")
            $("#tricon").addClass("hidden")
        }
    }
</script>
</body>
</html>
