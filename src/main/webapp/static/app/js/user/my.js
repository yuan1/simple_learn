/**
 * Created by 李明元 on 2017/4/6.
 */
$(function () {
    $(document).on("pageInit", function (e, pageId, $page) {
        if (pageId == "myPage") {
            $("#myInfoEdit").find(".item-content").empty();
            getMyInfo();
        }
        if (pageId == "myEditPage") {

        }
        if (pageId == "orderPage") {
            WKIT.destroy();
        }
        if (pageId == "showPage") {
            WKIT.destroy();
        }
        if (pageId == "orderShowPage") {
            WKIT.destroy();
        }

        if (pageId == "myFindPage") {
            $.showIndicator();
            $("#listContent").find('.card-container').empty();
            addFindToPage(getFinds());
            $.hideIndicator();
        }
        if (pageId == "messagePage") {
            WKIT.init({
                uid: $("#uid").val(),
                appkey: $("#appkey").val(),
                credential: $("#credential").val(),
                touid: $("#touid").val(),
                titleBar: false,
                container: document.getElementById('messageContent'),
                avatar: $("#avatar").val(),
                toAvatar: $("#toAvatar").val(),
                hideLoginSuccess: true
            });
        }
    });
    $(document).on("click", "#myInfoEdit", function () {
        $.router.load("app/user/my/edit", true);
    });
    $(document).on("click", "#goodEva", function () {
        $("#badEva").addClass("button-light");
        $("#goodEva").removeClass("button-light");
        $("#type").val("1");
    });
    $(document).on("click", "#badEva", function () {
        $("#goodEva").addClass("button-light");
        $("#badEva").removeClass("button-light");
        $("#type").val("0");
    });
    $(document).on("click", "#evaAdd", function () {
        var evaContent = $("#evaContent").val();
        var type = $("#type").val();
        var orderId = $("#orderId").val();
        if (evaContent === '') {
            $.toast("请输入评价内容！");
            return
        }
        if (evaContent.length < 5) {
            $.toast("评价内容不能少于五个字！");
            return
        }
        if (type === '') {
            $.toast("请选择评价！");
            return
        }
        if (orderId === '') {
            $.toast("评价失败！");
            return
        }
        $.showIndicator();
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "app/user/eva/do",
            data: {
                content: evaContent,
                type: type,
                orderId: orderId
            },
            success: function (data) {
                $.hideIndicator();
                if (data.icon == 1) {
                    $.toast("评价成功");
                } else {
                    $.toast(data.message);
                }
            }
        });

    });
    $(document).on("click", "#myOrder", function () {
        $.router.load("app/user/my/order", true);
    });
    $(document).on("click", "#orderMy", function () {
        $.router.load("app/user/order/my", true);
    });
    $(document).on("click", "#myPwd", function () {
        $.router.load("app/password", true);
    });
    $(document).on("click", "#myFeedback", function () {
        $.router.load("app/user/feedback", true);
    });
    $(document).on("click", "#feedbackDo", function () {
        var feedback = $("#feedback").val();
        if (feedback == '') {
            $.toast("请输入反馈内容！");
            return;
        }
        $.showIndicator();
        $.ajax({
            type: 'POST',
            url: 'app/user/feedback/do',
            async: false,
            data: {
                feedback: feedback
            },
            success: function (data) {
                $.hideIndicator();
                $.toast(data.message)
                $.router.back()
            }
        });
    });
    $(document).on("click", "#pwdDo", function () {
        var oldPwd = $("#oldPwd").val();
        if (oldPwd == '') {
            $.toast("请输入原始密码！");
            return;
        }
        $.showIndicator();
        $.ajax({
            type: 'POST',
            url: 'app/password/check',
            async: false,
            data: {
                password: oldPwd
            },
            success: function (data) {
                if (data.icon == 1) {
                    $.hideIndicator();
                    var newPwd = $("#newPwd").val();
                    if (newPwd == '') {
                        $.toast("请输入新密码！");
                        return;
                    }
                    var newPwdRe = $("#newPwdRe").val();
                    if (newPwdRe == '') {
                        $.toast("请输入确认密码！");
                        return;
                    }
                    $.ajax({
                        type: "POST",
                        url: "app/password/do",
                        data: {
                            password: newPwd
                        },
                        success: function (data) {
                            $.hideIndicator();
                            $.toast(data.message);
                        }
                    });
                } else {
                    $.hideIndicator();
                    $.toast(data.message)
                }
            }
        });

    });
    $(document).on("click", "#logout", function () {
        localStorage.removeItem("token");
        $.toast("退出成功！");
        setTimeout(function () {
            location.href = "app/login"
        }, 1000);

    });
    $(document).on('click', '#delFind', function () {
        $.confirm('确定删除？', function () {
            $.showIndicator();
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "app/find/del",
                data: {
                    id: $("#findId").val()
                },
                success: function (data) {
                    $.hideIndicator();
                    $.toast(data.message);
                }
            });
        });
    });
    $(document).on("click", "#orderFind", function () {
        $.showIndicator();
        var state = $("#orderFind").data("role");
        if (state != "0") {
            $.hideIndicator();
            $.toast("您已预约！")
        } else {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "app/find/order",
                data: {
                    findId: $("#findId").val()
                },
                success: function (data) {
                    $.hideIndicator();
                    if (data.icon == 1) {
                        $("#orderFind").data("role", "1");
                        $.toast("预约成功！");
                        $("#orderFind").find(".tab-label").empty();
                        $("#orderFind").find(".tab-label").append("已预约");
                    } else {
                        $.toast(data.message);
                    }
                }
            });
        }
    });
    $(document).on("click", "#starFind", function () {
        $.showIndicator();
        var state = $("#starFind").data("role");
        if (state != "0") {
            $("#starIcon").removeClass("icon-favoritesfilling");
            $("#starIcon").addClass("icon-favorite");
            $("#starFind").data("role", "0");
        } else {
            $("#starIcon").removeClass("icon-favorite");
            $("#starIcon").addClass("icon-favoritesfilling");
            $("#starFind").data("role", "1");
        }
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "app/find/star",
            data: {
                findId: $("#findId").val(),
                state: state
            },
            success: function (data) {
                $.hideIndicator();
                $.toast(data.message);

            }
        });

    });
    $(document).on("click", "#myStar", function () {
        $.router.load("app/user/star", true);
    });
    $(document).on("click", "#userImage", function () {
        return $("#imageFile").click();
    });
    $(document).on("click", "#myFind", function () {
        $.router.load("app/user/find", true);
    });
    $(document).on("click", "#saveMyForm", function () {
        $.showIndicator();
        $.ajax({
            type: 'POST',
            url: 'app/user/my/edit/do',
            data: {
                image: $("#imageInput").val(),
                nickname: $("#nickname").val(),
                id: $("#id").val(),
                sex: $("#sex").val(),
                email: $("#email").val()
            },
            success: function (data) {
                $.hideIndicator();
                $.toast(data.message);
            }
        });
    });
    $(document).on('refresh', '.pull-to-refresh-content', function () {
        // 模拟2s的加载过程
        setTimeout(function () {
            location.replace(location.href);
            $.pullToRefreshDone('.pull-to-refresh-content');
        }, 2000);
    });
    $(document).on("click", "#tab1", function () {
        $.showIndicator();
        removeAllTabClass();
        $("#type").val(1);
        $("#listContent").find('.card-container').empty();
        addFindToPage(getFinds());
        $("#tab1").addClass("active");
        $.hideIndicator();


    });
    $(document).on("click", "#tab2", function () {
        $.showIndicator();
        removeAllTabClass();
        $("#type").val(2);
        $("#listContent").find('.card-container').empty();
        addFindToPage(getFinds());
        $("#tab2").addClass("active");
        $.hideIndicator();

    });
    $(document).on("click", "#tab3", function () {
        $.showIndicator();
        removeAllTabClass();
        $("#type").val(3);
        $("#listContent").find('.card-container').empty();
        addFindToPage(getFinds());
        $("#tab3").addClass("active");
        $.hideIndicator();

    });
    $(document).on("click", "#tab4", function () {
        $.showIndicator();
        removeAllTabClass();
        $("#type").val(4);
        $("#listContent").find('.card-container').empty();
        addFindToPage(getFinds());
        $("#tab4").addClass("active");
        $.hideIndicator();
    });
    $(document).on("click", "#backMessage", function () {
        WKIT.destroy();
        $.router.back();
    });
    $.init();
});
function getImageSize(obj) {
    var _file = document.getElementById("imageFile");
    var i = _file.value.lastIndexOf('.');
    var len = _file.value.length;
    var extEndName = _file.value.substring(i + 1, len);
    var extName = "GIF,BMP,JPG,JPEG,PNG";//首先对格式进行验证
    if (extName.indexOf(extEndName.toUpperCase()) == -1) {
        alert("您只能输入" + extName + "格式的文件");
    } else {
        $.showIndicator();
        var data = new FormData($('#imageForm')[0]);
        $.ajax({
            type: 'POST',
            url: 'app/user/upload/image',
            data: data,
            cache: false,
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.icon == 1) {
                    $("#imageNow").attr("src", "http://images.javayuan.cn/" + data.message);
                    $("#imageInput").val(data.message);
                    $.hideIndicator();
                } else {
                    $.hideIndicator();
                    $.toast(data.message)
                }
            }
        });

    }
}
function removeAllTabClass() {
    $("#tab1").removeClass("active");
    $("#tab2").removeClass("active");
    $("#tab3").removeClass("active");
    $("#tab4").removeClass("active");
}
function getMyInfo() {
    $.showIndicator();
    $.ajax({
        type: 'POST',
        url: 'app/user/my/info',
        async: false,
        success: function (data) {
            $.hideIndicator();
            var html = '<div class="item-media"><img src="http://images.javayuan.cn/' + data.image +
                '" style="width:4rem;border-radius:50%"></div><div class="item-inner"><div class="item-title-row"><div class="item-title">' + data.nickname +
                '</div></div><div class="item-subtitle">' + data.username +
                '</div><div class="item-text">' + data.mobile +
                '</div></div><input type="hidden" id="userId" value="' + data.id +
                '">';
            $("#myInfoEdit").find(".item-content").prepend(html);
        }
    });
}
function getFinds(type) {
    var finds = [];
    $.ajax({
        type: "POST",
        dataType: "json",
        cache: true,
        async: false,
        url: "app/user/find/list",
        data: {
            type: $("#type").val(),
            action: $("#action").val()
        },
        success: function (data) {
            finds = data;
        }
    });
    return finds;
}
function addFindToPage(finds) {
    $.each(finds, function (index, item) {
        var html = '<div class="card facebook-card" onclick="findShow(' + item.id +
            ')"><div class="card-header no-border"><div class="pull-left">' +
            '<div class="facebook-avatar"><img src="http://images.javayuan.cn/' + item.image +
            '" style="width:2rem;border-radius:50%">' +
            '</div><div class="facebook-name">' + item.nickname +
            '</div><div class="facebook-date">' + item.findTime +
            '</div>' +
            '</div><div class="pull-right">' + item.gap +
            'm</div></div><div class="card-content"><div class="card-content-inner">' +
            '<div class="text-center"><h4 class="line-height-inline" style="margin: 0">' + item.name +
            '</h4><p class="line-height-inline">' + item.content +
            '</p></div>' +
            '</div></div><div class="card-footer no-border"><span>' + item.createTime +
            '</span> <span>' + item.less +
            '</span></div></div>';
        $("#listContent").find('.card-container').prepend(html);
    });
    $.pullToRefreshDone('.pull-to-refresh-content');
}
function findShow(id) {
    $.router.load("app/find/show/" + id, true);

}
function cancelMyOrder(id) {
    $.confirm('确定取消？', function () {
        $.showIndicator();
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "app/user/order/state",
            data: {
                id: id,
                state: "4"
            },
            success: function (data) {
                $.hideIndicator();
                if (data.icon == 1) {
                    $.toast("取消成功");
                    location.replace(location.href);
                } else {
                    $.toast("取消失败");
                }
            }
        });
    });
}
function cancelOrderMy(id) {
    $.confirm('确定取消？', function () {
        $.showIndicator();
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "app/user/order/state",
            data: {
                id: id,
                state: "5"
            },
            success: function (data) {
                $.hideIndicator();
                if (data.icon == 1) {
                    $.toast("取消成功");
                    location.replace(location.href);
                } else {
                    $.toast("取消失败");
                }
            }
        });
    });
}
function enterOrderMy(id) {
    $.showIndicator();
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "app/user/order/state",
        data: {
            id: id,
            state: "1"
        },
        success: function (data) {
            $.hideIndicator();
            if (data.icon == 1) {
                $.toast("确认成功");
                location.replace(location.href);
            } else {
                $.toast("确认失败");
            }
        }
    });
}
function downMyOrder(id) {
    $.showIndicator();
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "app/user/order/state",
        data: {
            id: id,
            state: "2"
        },
        success: function (data) {
            $.hideIndicator();
            if (data.icon == 1) {
                $.toast("确认完成成功");
                location.replace(location.href);
            } else {
                $.toast("确认完成失败");
            }
        }
    });
}
function downOrderMy(id) {
    $.showIndicator();
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "app/user/order/state",
        data: {
            id: id,
            state: "3"
        },
        success: function (data) {
            $.hideIndicator();
            if (data.icon == 1) {
                $.toast("确认完成成功");
                location.replace(location.href);
            } else {
                $.toast("确认完成失败");
            }
        }
    });
}
function evaluateMyOrder(id) {
    $.router.load("app/user/eva/" + id, true);
}
function showMyOrder(id) {
    $.router.load("app/user/my/order/" + id, true);
}
function showOrderMy(id) {
    $.router.load("app/user/order/my/" + id, true);
}