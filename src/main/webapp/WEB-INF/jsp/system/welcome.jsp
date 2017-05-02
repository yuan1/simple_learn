<%--
  ~ Copyright (c) 2016. 德州学院 李明元 http://www.javayuan.cn
  --%>

<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/12/3
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <%@ include file="/WEB-INF/jsp/common/_meta.jsp" %>
    <title>我的桌面</title>
</head>
<body>
<div class="page-container">
    <p class="f-20 text-success">欢迎使用${systemInfo.title} <span class="f-14">v1.0</span></p>
    <table class="table table-border table-bordered table-bg mt-20">
        <thead>
        <tr>
            <th colspan="2" scope="col">服务器信息</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th width="30%">服务器名</th>
            <td><span id="lbServerName">${system.server_name}</span></td>
        </tr>
        <tr>
            <td>服务器地址</td>
            <td>${system.server_addr}</td>
        </tr>
        <tr>
            <td>服务器端口 </td>
            <td>${system.server_port}</td>
        </tr>
        <tr>
            <td>服务协议 </td>
            <td>${system.server_protocol}</td>
        </tr>
        <tr>
            <td>服务器操作系统 </td>
            <td>${system.os_name}</td>
        </tr>
        <tr>
            <td>系统架构 </td>
            <td>${system.os_arch }</td>
        </tr>
        <tr>
            <td>系统IP </td>
            <td>${system.os_ip  }</td>
        </tr>
        <tr>
            <td>系统MAC地址 </td>
            <td>${system.os_mac  }</td>
        </tr>
        <tr>
            <td>系统时间 </td>
            <td>${system.os_date  }</td>
        </tr>
        <tr>
            <td>系统CPU个数 </td>
            <td>${system.os_cpus   }</td>
        </tr>
        <tr>
            <td>系统用户名 </td>
            <td>${system.os_user_name  }</td>
        </tr>
        <tr>
            <td>用户的当前工作目录 </td>
            <td>${system.os_user_dir  }</td>
        </tr>
        <tr>
            <td>用户的主目录 </td>
            <td>${system.os_user_home  }</td>
        </tr>
        <tr>
            <td>Java的运行环境版本 </td>
            <td>${system.java_version}</td>
        </tr>
        <tr>
            <td>Java默认的临时文件路径 </td>
            <td>${system.java_io_tmpdir}</td>
        </tr>
        <tr>
            <td>java 平台 </td>
            <td>${system.sun_desktop}</td>
        </tr>

        </tbody>
    </table>
</div>
<footer class="footer mt-20">
    <div class="container">
        <p>感谢jQuery、layer、laypage、Validform、UEditor、My97DatePicker、iconfont、Datatables、WebUploaded、icheck、highcharts、bootstrap-Switch<br>
            Copyright &copy;2016 ${systemInfo.copyright} All Rights Reserved.<br>
            本后台系统由<a href="http://www.h-ui.net/" target="_blank" title="H-ui前端框架">H-ui前端框架</a>提供前端技术支持</p>
    </div>
</footer>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
</body>
</html>