<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/14
  Time: 8:46
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
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <shiro:hasPermission name="user:add">
            <span class="l">
             <a href="javascript:;" onclick="layer_show('添加用户','user/add/${userShowList.get(0).sysDept.id}')"
                class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a>
                 </span>
        </shiro:hasPermission>
        <span
                class="r">共有数据：<strong>${userShowList.size()}</strong> 条</span></div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="100">用户名</th>
                <th width="100">姓名</th>
                <th width="40">性别</th>
                <th width="90" class="hidden-xs">联系方式</th>
                <th width="150" class="hidden-xs">身份证号</th>
                <th width="100">组织机构</th>
                <th width="100">角色</th>
                <th width="50">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userShowList}" var="usl">
                <tr class="text-c">
                    <td>${usl.sysUser.usercode}</td>
                    <td>${usl.sysUser.username}</td>
                    <td>${usl.sysUser.sex}</td>
                    <td class="hidden-xs">${usl.sysUser.tel}</td>
                    <td class="hidden-xs">${usl.sysUser.idcard}</td>
                    <td>${usl.sysDept.name}</td>
                    <td>${usl.sysRole.name}</td>
                    <td>
                        <shiro:hasPermission name="user:edit">
                            <a title="编辑" href="javascript:;" onclick="layer_show('编辑','user/edit/${usl.sysUser.id}')"
                               class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="user:del">
                            <a title="删除" href="javascript:;" onclick="ajaxDelSubmit('user/del/${usl.sysUser.id}')" class="ml-5"
                               style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
                        </shiro:hasPermission>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    $(function () {
        $('.table-sort').dataTable({
            "aaSorting": [[0, "desc"]],//默认第几个排序
            "bStateSave": true,//状态保存
            "aoColumnDefs": [
                //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
                {"orderable": false, "aTargets": [6]}// 制定列不参与排序
            ]
        });
        $('.table-sort tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
    });
</script>
</body>
</html>
