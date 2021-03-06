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
    <form action="learn/wechat/reply/edit/do" method="POST" class="form" id="form-edit">
        <table class="table table-border table-bordered table-hover ">
            <tbody>
            <tr>
                <td width="100">关键字：</td>
                <td>
                    <input type="text" class="input-text" value="${replyEdit.name}" placeholder="关键字" id="name" name="name" >
                    <input type="hidden" value="${replyEdit.id}" name="id">
                    <input type="hidden" value="${replyEdit.state}" name="state">
                    <input type="hidden" value="${replyEdit.creat}" name="creat">
                </td>
            </tr>
            <tr>
                <td width="100">回复：</td>
                <td>
                    <textarea class="textarea" placeholder="说点什么..." rows="" cols="" id="reply" name="reply">${replyEdit.reply}</textarea>
                </td>
            </tr>
            <tr>
                <td>操作：</td>
                <td>
                    <shiro:hasPermission name="learn:wechat:reply:edit">
                        <input type="submit" class="btn btn-primary radius size-S" value="修改">
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
        $("#form-edit").validate({
            rules: {
                name: {
                    required: true
                },
                reply: {
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