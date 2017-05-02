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
    <link href="lib/wangEditor/css/wangEditor.min.css" type="text/css" rel="stylesheet">
</head>
<body>

<div class="page-container">
    <form action="learn/news/edit/do" method="post" class="form form-horizontal" id="form-edit">
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">标题：</label>
            <div class="formControls col-xs-9 col-sm-10">
                <input class="input-text" autocomplete="off" placeholder="标题" type="text" name="title" id="title" value="${newsEdit.title}" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">简介：</label>
            <div class="formControls col-xs-9 col-sm-10">
                <input class="input-text" autocomplete="off" placeholder="简介" type="text" name="brief" id="brief" value="${newsEdit.brief}" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">作者昵称：</label>
            <div class="formControls col-xs-9 col-sm-10">
                <input class="input-text" autocomplete="off" placeholder="作者昵称" type="text" name="author" id="author" value="${newsEdit.author}" required>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">缩略图：</label>
            <div class="formControls col-xs-9 col-sm-10">
                    <span class="btn-upload form-group">
							<input class="input-text upload-url" id="uploadFile" readonly=""
                                   type="text">
							<a href="javascript:" class="btn btn-primary upload-btn" onclick="showFile();">
                                <i class="Hui-iconfont"></i> 浏览文件</a>
							<input name="breviary" class="input-file" type="hidden" id="imageInput" value="${newsEdit.breviary}">
                    </span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">缩略图预览：</label>
            <div class="formControls col-xs-9 col-sm-10">
                <img src="http://images.javayuan.cn/${newsEdit.breviary}" id="imageNow" width="240px" height="160px" alt="..." class="radius">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">内容：</label>
            <div class="formControls col-xs-9 col-sm-10">
                   <textarea rows="20" name="content" id="content">
                       <p>${newsEdit.content}</p>
                    </textarea>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3 col-sm-2">赞数量：</label>
            <div class="formControls col-xs-2 col-sm-3">
                <input class="input-text" autocomplete="off" placeholder="赞数量" type="number" name="laud" id="laud" value="${newsEdit.laud}" required>
                <input type="hidden" value="${newsEdit.id}" name="id">
                <input type="hidden" value="${newsEdit.state}" name="state">
                <input type="hidden" value="${newsEdit.creatTime}" name="creatTime">
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-3 col-sm-offset-2">
                <input class="btn btn-primary radius" value="提交" type="submit">
            </div>
        </div>
    </form>
    <form action="app/user/upload/image" method="post" id="imageForm">
        <input type="file" value="" name="file" style="display: none" id="imageFile"
               onchange="getImageSize(this)">
    </form>

</div>
<%@ include file="/WEB-INF/jsp/common/_footer.jsp" %>
<script type="text/javascript" src="lib/wangEditor/js/wangEditor.min.js"></script>
<!--这里引用jquery和wangEditor.js-->
<script type="text/javascript">
    var editor = new wangEditor('content');
    // 上传图片（举例）
    editor.config.uploadImgUrl = 'learn/news/upload/image';

    // 配置自定义参数（举例）
    editor.config.uploadParams = {
        token: 'abcdefg',
        user: 'wangfupeng1988'
    };

    // 设置 headers（举例）
    editor.config.uploadHeaders = {
        'Accept': 'text/x-json'
    };

    // 隐藏掉插入网络图片功能。该配置，只有在你正确配置了图片上传功能之后才可用。
    editor.config.hideLinkImg = true;

    editor.create();
</script>
<script>
    $(document).ready(function () {
        $("#form-edit").validate({
            rules: {
                title: {
                    required: true
                },
                brief: {
                    required: true
                },
                author: {
                    required: true
                },
                content: {
                    required: true
                },
                laud: {
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
    function showFile() {
        return $("#imageFile").click();
    }
    function getImageSize(obj) {
        var _file = document.getElementById("imageFile");
        var i = _file.value.lastIndexOf('.');
        var len = _file.value.length;
        var extEndName = _file.value.substring(i + 1, len);
        var extName = "GIF,BMP,JPG,JPEG,PNG";//首先对格式进行验证
        if (extName.indexOf(extEndName.toUpperCase()) == -1) {
            alert("您只能输入" + extName + "格式的文件");
        } else {
            var data = new FormData($('#imageForm')[0]);
            $.ajax({
                type: 'POST',
                url: 'learn/news/upload/image/breviary',
                data: data,
                cache: false,
                processData: false,
                contentType: false,
                success: function (data) {
                    if (data.icon == 1) {
                        layer.msg("上传成功！", {
                            icon: data.icon,
                            time: 2000
                        });
                        $("#imageNow").attr("src", "http://images.javayuan.cn/" + data.message);
                        $("#imageInput").val(data.message);
                    } else {
                        layer.msg(data.message, {
                            icon: data.icon,
                            time: 2000
                        })
                    }
                }
            });

        }
    }
</script>
</body>
</html>