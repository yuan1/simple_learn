<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/14
  Time: 9:14
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
    <form action="user/add/do" method="POST" class="form" id="form-add">
        <table class="table table-border table-bordered table-hover ">
            <tbody>

            <tr>
                <td width="100">组织机构：</td>
                <td>
                    ${deptAdd.name}
                    <input type="hidden" value="${deptAdd.id}" name="deptid">
                </td>
            </tr>
            <tr>
                <td width="100">角色：</td>
                <td>
                    <span class="select-box">
                        <select class="select" name="roleid">
                            <c:forEach items="${roleAddList}" var="ral">
                                <option value="${ral.id}">${ral.name}</option>
                            </c:forEach>
                        </select>
                    </span>
                </td>
            </tr>
            <tr>
                <td width="100">用户名：</td>
                <td>
                    <input type="text" class="input-text" value="" placeholder="用户名" id="usercode" name="usercode">
                </td>
            </tr>
            <tr id="trurl">
                <td>密码：</td>
                <td>
                    <input type="password" class="input-text" value="" placeholder="密码" id="password"
                           name="password">
                </td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td><input type="text" class="input-text" value=""
                                                      placeholder="姓名" id="username"
                                                      name="username"></td>
            </tr>
            <tr>
                <td>性别：</td>
                <td>
                    <span class="select-box">
                        <select class="select" name="sex" id="sex">
                            <option value="男" selected>男</option>
                            <option value="女">女</option>
                        </select>

                    </span>
            </tr>
            <tr>
                <td>联系方式：</td>
                <td><input type="text" class="input-text" value=""
                                                      placeholder="联系方式" id="tel"
                                                      name="tel"></td>
            </tr>
            <tr>
                <td>身份证号码：</td>
                <td><input type="text" class="input-text" value=""
                                                      placeholder="身份证号码" id="idcard"
                                                      name="idcard"></td>
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
                    <shiro:hasPermission name="user:add">
                    <input type="submit" class="btn btn-primary radius size-S" value="提交">
                    </shiro:hasPermission>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#form-add").validate({
            rules: {
                usercode: {
                    required: true,
                    minlength: 2,
                    maxlength: 16
                },
                username: {
                    required: true,
                },
                password: {
                    required: true,
                    minlength: 2,
                    maxlength: 16
                },
                idcard: {
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
    });
</script>
</body>
</html>