<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/11
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <%@ include file="/WEB-INF/jsp/common/_meta.jsp" %>
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.login.css" />
    <title>${sysConfig.title} - 登录</title>
</head>
<body>
<header class="header text-shadow ">
    <h2>${sysConfig.title}</h2>
</header>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form class="form form-horizontal" action="login" method="post">
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <label class="col-xs-8 c-error">${info}</label>
                </div>
            </div>

            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input id="username" name="username" type="text" placeholder="账户" required class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="password" name="password" type="password" placeholder="密码" required class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <label for="online">
                        <input type="checkbox" name="rememberMe" id="online" value="">
                        使我保持登录状态</label>
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input name="" type="submit" class="btn btn-success radius size-L"
                           value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                    <input name="" type="reset" class="btn btn-default radius size-L"
                           value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>
<footer class="footer">Copyright &copy;2016 ${sysConfig.copyright} All Rights Reserved.&nbsp;&nbsp;备案号：${sysConfig.icp}</footer>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.js"></script>
</body>
</html>
