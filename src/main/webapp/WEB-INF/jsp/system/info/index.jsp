<%--
  ~ Copyright (c) 2016. 德州学院 李明元 http://www.javayuan.cn
  --%>

<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/12/3
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/common/_meta.jsp" %>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统设置 <span
        class="c-gray en">&gt;</span>基本信息 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                             href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form class="form form-horizontal" id="form-edit" action="system/info/edit" method="post">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>网站名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="title" name="title" placeholder="控制在25个字、50个字节以内" value="${systemInfo.title}" class="input-text">
                <input type="hidden" name="id" value="${systemInfo.id}">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>关键词：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="keywords" name="keywords" placeholder="5个左右,8汉字以内,用英文,隔开" value="${systemInfo.keywords}" class="input-text">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>描述：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="description" name="description" placeholder="空制在80个汉字，160个字符以内" value="${systemInfo.description}" class="input-text">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>底部版权信息：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="copyright" name="copyright" placeholder="&copy; 2016 德州学院 李明元" value="${systemInfo.copyright}"
                       class="input-text">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">备案号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" id="icp" name="icp" placeholder="京ICP备00000000号" value="${systemInfo.icp}" class="input-text">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">菜单管理使用说明：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea class="textarea" name="menuInfo" id="menuInfo">${systemInfo.menuInfo}</textarea>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">角色管理使用说明：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea class="textarea" name="roleInfo" id="roleInfo">${systemInfo.roleInfo}</textarea>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">组织机构使用说明：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea class="textarea" name="deptInfo" id="deptInfo">${systemInfo.deptInfo}</textarea>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">用户管理使用说明：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea class="textarea" name="userInfo" id="userInfo">${systemInfo.userInfo}</textarea>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <shiro:hasPermission name="system:info:edit">
                <input type="submit" value="保存" class="btn btn-primary radius">

                </shiro:hasPermission>

                <input type="reset" value="取消" class="btn btn-default radius">
            </div>
        </div>

    </form>
</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $("#form-edit").validate({
            rules: {
                title: {
                    required: true
                },
                keywords: {
                    required: true
                },
                description: {
                    required: true
                },
                copyright: {
                    required: true
                }
            },
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
