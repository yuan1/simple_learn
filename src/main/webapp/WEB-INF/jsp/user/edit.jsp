<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/15
  Time: 0:59
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
    <form action="user/edit/do" method="POST" class="form" id="form-edit">
        <table class="table table-border table-bordered table-hover ">
            <tr>
                <td width="100">组织机构：</td>
                <td>
                    <span class="select-box">
                        <select class="select" name="deptid">
                            <c:forEach items="${deptList}" var="dlt">
                                <option value="${dlt.id}"
                                        <c:if test="${userEdit.sysDept.id==dlt.id}">
                                            selected
                                        </c:if>
                                >${dlt.name}</option>
                            </c:forEach>
                        </select>
                    </span>
                    <input type="hidden" name="id" value="${userEdit.sysUser.id}">
                </td>
            </tr>
            <tr>
                <td width="100">角色：</td>
                <td>
                    <span class="select-box">
                        <select class="select" name="roleid">
                            <c:forEach items="${roleEditList}" var="ral">
                                <option value="${ral.id}"
                                        <c:if test="${userEdit.sysRole.id==ral.id}">
                                            selected
                                        </c:if>
                                >${ral.name}</option>
                            </c:forEach>
                        </select>
                    </span>
                </td>
            </tr>
            <tr>
                <td>用户名：</td>
                <td>
                    <input type="text" class="input-text" value="${userEdit.sysUser.usercode}" placeholder="用户名"
                           id="usercode" name="usercode">
                </td>
            </tr>
            <tr id="trurl">
                <td>密码：</td>
                <td>
                    <input type="password" class="input-text" value="" placeholder="如需修改请输入新密码" name="password"
                           id="passwordNew">
                </td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td><input type="text" class="input-text" value="${userEdit.sysUser.username}"
                           placeholder="姓名" id="username"
                           name="username"></td>
            </tr>
            <tr>
                <td>性别：</td>
                <td>
                    <span class="select-box">
                        <select class="select" name="sex" id="sex">
                            <option value="男"
                                    <c:if test="${userEdit.sysUser.sex=='男'}">
                                        selected
                                    </c:if>
                            >男</option>
                            <option value="女"
                                    <c:if test="${userEdit.sysUser.sex=='女'}">
                                        selected
                                    </c:if>
                            >女</option>
                        </select>

                    </span>
            </tr>
            <tr>
                <td>联系方式：</td>
                <td><input type="text" class="input-text" value="${userEdit.sysUser.tel}"
                           placeholder="联系方式" id="tel"
                           name="tel"></td>
            </tr>
            <tr>
                <td>身份证号码：</td>
                <td><input type="text" class="input-text" value="${userEdit.sysUser.idcard}"
                           placeholder="身份证号码" id="idcard"
                           name="idcard"></td>
            </tr>
            <tr>
                <td>备注：</td>
                <td><input type="text" class="input-text" value="${userEdit.sysUser.remark}"
                           placeholder="备注" id="remark"
                           name="remark"></td>
            </tr>
            <tr>
                <td>排序：</td>
                <td><input type="text" class="input-text" value="${userEdit.sysUser.sort}"
                           placeholder="排序" id="sort"
                           name="sort"></td>
            </tr>
            <tr>
                <td>操作：</td>
                <td>
                    <shiro:hasPermission name="user:edit">
                        <input type="submit" class="btn btn-primary radius size-S" value="提交"/>
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
                username: {
                    required: true,
                    minlength: 2,
                    maxlength: 16
                },
                idcard: {
                    required: true
                }
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                ajaxFormSubmit('form-edit');
            }
        });
    });
</script>
</body>
</html>