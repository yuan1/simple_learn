<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2017/3/15
  Time: 18:56
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
    <form action="learn/find/edit/do" method="post" class="form form-horizontal" id="form-edit">
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">标题：</label>
            <div class="formControls col-xs-9 col-sm-10">
                <input class="input-text" autocomplete="off" placeholder="标题" type="text" name="name" id="name" value="${find.name}" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">类型：</label>
            <div class="formControls col-xs-9 col-sm-10">
            <span class="select-box inline">
            <select name="type" class="select" id="type">
                <option value="" selected>全部</option>
                <option value="2"
                        <c:if test="${find.type==2}">
                            selected
                        </c:if>
                >约·学</option>
                 <option value="3"
                         <c:if test="${find.type==3}">
                             selected
                         </c:if>
                 >约·行</option>
                  <option value="4"
                          <c:if test="${find.type==4}">
                              selected
                          </c:if>
                  >约·商</option>
            </select>
            </span>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">截至时间：</label>
            <div class="formControls col-xs-9 col-sm-10">
                <input class="input-text" autocomplete="off" placeholder="截至时间" type="datetime" name="findTime" id="findTime" value="${find.findTime}" required>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">具体内容：</label>
            <div class="formControls col-xs-9 col-sm-10">
                   <textarea rows="10" name="content"  class="textarea" id="content">${find.content}</textarea>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">注意事项：</label>
            <div class="formControls col-xs-9 col-sm-10">
                <textarea rows="10" name="attention" class="textarea"  id="attention">${find.attention}</textarea>
                <input type="hidden" value="${find.id}" name="id">
                <input type="hidden" value="${find.addressName}" name="addressName">
                <input type="hidden" value="${find.addressMap}" name="addressMap">
                <input type="hidden" value="${find.locationMap}" name="locationMap">
                <input type="hidden" value="${find.findId}" name="findId">
                <input type="hidden" value="${find.state}" name="state">
                <input type="hidden" value="${find.createTime}" name="createTime">
                <input type="hidden" value="${find.appUserId}" name="appUserId">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-3 col-sm-offset-2">
                <input class="btn btn-primary radius" value="提交" type="submit">
            </div>
        </div>
    </form>

</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
<script>
    $(document).ready(function () {
        $("#form-edit").validate({
            rules: {
                name: {
                    required: true
                },
                type: {
                    required: true
                },
                findTime: {
                    required: true
                },
                content: {
                    required: true
                },
                attention: {
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