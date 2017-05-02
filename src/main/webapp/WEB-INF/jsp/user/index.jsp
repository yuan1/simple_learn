<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/14
  Time: 8:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/common/_meta.jsp" %>
    <link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span
        class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>

<div style="height: 90%">
    <div class="col-xs-0 col-sm-2">
        <ul id="treeUser" class="ztree"></ul>

    </div>
    <div class=" col-xs-12 col-sm-10">
        <IFRAME id="userIframe" name="userIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100% height=100%
                src="user/info"></IFRAME>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick
        }
    };

    var zNodes =${zNodes};

    $(document).ready(function () {
        $.fn.zTree.init($("#treeUser"), setting, zNodes);
    });

    function onClick(event, treeId, treeNode, clickFlag) {
        loadIframe('userIframe', 'user/show/' + treeNode.id)
    }
    function loadIframe(iframeName, url) {
        var iframe = $('#' + iframeName);
        if (iframe.length) {
            iframe.attr('src', url);
            return false;
        }
        return true;
    }
</script>
</body>
</html>