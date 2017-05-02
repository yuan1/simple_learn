<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2017/3/15
  Time: 22:00
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
    <form action="learn/user/add/do" method="POST" class="form" id="form-add" enctype="multipart/form-data">
        <table class="table table-border table-bordered table-hover ">
            <tbody>
            <tr>
                <td width="100">头像：</td>
                <td>
                    <span class="btn-upload form-group">
                        <input class="input-text upload-url radius" type="text" name="uploadfile-1" id="uploadfile-1" readonly>
                        <a href="javascript:" class="btn btn-primary radius">浏览文件</a>
                        <input type="file" multiple name="file" class="input-file">
                    </span>
                </td>
            </tr>
            <tr>
                <td width="100">昵称：</td>
                <td>
                    <input type="text" class="input-text" value="" placeholder="昵称" id="nickname" name="nickname">
                </td>
            </tr>
            <tr>
                <td width="100">用户名：</td>
                <td>
                    <input type="text" class="input-text" value="" placeholder="用户名" id="username" name="username">
                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input type="password" class="input-text" value="" placeholder="密码" id="password"
                           name="password">
                </td>
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
                <td>邮箱：</td>
                <td><input type="text" class="input-text" value=""
                           placeholder="邮箱" id="email"
                           name="email">
                    <input type="hidden" value="0" name="type">
                </td>
            </tr>
            <tr>
                <td>手机号码：</td>
                <td><input type="text" class="input-text" value=""
                           placeholder="手机号码" id="mobile"
                           name="mobile"></td>
            </tr>
            <tr>
                <td>操作：</td>
                <td>
                    <shiro:hasPermission name="learn:user:add">
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
                username: {
                    required: true
                },
                password: {
                    required: true,
                    minlength: 2,
                    maxlength: 16
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