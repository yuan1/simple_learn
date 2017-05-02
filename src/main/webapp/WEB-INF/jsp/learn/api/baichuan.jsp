<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2017/3/27
  Time: 8:40
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 接口配置 <span
        class="c-gray en">&gt;</span>淘宝百川接口 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                             href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form class="form form-horizontal" id="form-edit" action="learn/api/baichuan/edit" method="post">
        <c:forEach items="${apis}" var="api" varStatus="sta">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">${api.apiName}</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" name="apis[${sta.index}].apiValue" value="${api.apiValue}" class="input-text">
                    <input type="hidden" name="apis[${sta.index}].id" value="${api.id}">
                    <input type="hidden" name="apis[${sta.index}].apiType" value="${api.apiType}">
                    <input type="hidden" name="apis[${sta.index}].apiKey" value="${api.apiKey}">
                    <input type="hidden" name="apis[${sta.index}].apiName" value="${api.apiName}">
                </div>
            </div>
        </c:forEach>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <shiro:hasPermission name="learn:api:baichuan:edit">
                    <input type="submit" value="保存" class="btn btn-primary radius">
                </shiro:hasPermission>
                <input type="reset" value="取消" class="btn btn-default radius">
            </div>
        </div>

    </form>
</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#form-edit").validate({
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                ajaxFormSubmitLocationReload('form-edit');
            }
        });
    })
</script>
</body>
</html>
