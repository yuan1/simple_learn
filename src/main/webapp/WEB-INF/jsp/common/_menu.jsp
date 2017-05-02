<%--
  Created by IntelliJ IDEA.
  User: 李明元
  Date: 2016/11/12
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<aside class="Hui-aside">
    <div class="menu_dropdown bk_2">
        <c:forEach items="${useUserDto.menus}" var="mapMu">
            <dl id="menu-article">
                    <dt><i class="Hui-iconfont">&#xe616;</i> ${mapMu.key.name}<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <c:forEach var="listMu" items="${mapMu.value}">
                            <li><a data-href="${listMu.url}" data-title="${listMu.name}" href="javascript:void(0)">${listMu.name}</a></li>
                        </c:forEach>
                    </ul>
                </dd>
            </dl>
        </c:forEach>
    </div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>