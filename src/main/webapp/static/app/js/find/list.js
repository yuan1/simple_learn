/**
 * Created by 李明元 on 2017/3/29.
 */
$(function () {
    $(document).on("pageInit", function(e, pageId, $page) {
        if(pageId == "listPage") {
            $.showIndicator();
            $("#listContent").find('.card-container').empty();
            addFindToPage(getFinds($("#type").val()));
            $.hideIndicator();
        }
        if(pageId == "showPage") {
            WKIT.destroy();
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
                hideLoginSuccess:true
            });
        }
    });
    $(document).on("click","#backMessage",function () {
        WKIT.destroy();
        $.router.back();
    });
    $(document).on('click','#delFind', function () {
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
    $(document).on("click","#orderFind",function () {
        $.showIndicator();
        var state = $("#orderFind").data("role");
        if (state != "0") {
            $.hideIndicator();
            $.toast("您已预约！")
        }else {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "app/find/order",
                data: {
                    findId: $("#findId").val()
                },
                success: function (data) {
                    $.hideIndicator();
                    if(data.icon==1){
                        $("#orderFind").data("role", "1");
                        $.toast("预约成功！");
                        $("#orderFind").find(".tab-label").empty();
                        $("#orderFind").find(".tab-label").append("已预约");
                    }else {
                        $.toast(data.message);
                    }
                }
            });
        }
    });
    $(document).on("click", "#tab1", function () {
        $.showIndicator();
        removeAllTabClass();
        $("#type").val(1);
        $("#listContent").find('.card-container').empty();
        addFindToPage(getFinds($("#type").val()));
        $("#tab1").addClass("active");
        $.hideIndicator();


    });
    $(document).on("click", "#tab2", function () {
        $.showIndicator();
        removeAllTabClass();
        $("#type").val(2);
        $("#listContent").find('.card-container').empty();
        addFindToPage(getFinds($("#type").val()));
        $("#tab2").addClass("active");
        $.hideIndicator();

    });
    $(document).on("click", "#tab3", function () {
        $.showIndicator();
        removeAllTabClass();
        $("#type").val(3);
        $("#listContent").find('.card-container').empty();
        addFindToPage(getFinds($("#type").val()));
        $("#tab3").addClass("active");
        $.hideIndicator();

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
    $(document).on("click", "#tab4", function () {
        $.showIndicator();
        removeAllTabClass();
        $("#type").val(4);
        $("#listContent").find('.card-container').empty();
        addFindToPage(getFinds($("#type").val()));
        $("#tab4").addClass("active");
        $.hideIndicator();
    });

// 添加'refresh'监听器
    $(document).on('refresh', '.pull-to-refresh-content', function () {
        // 模拟2s的加载过程
        setTimeout(function () {
            addFindToPage(getFindsRef($("#type").val()));
            // 加载完毕需要重置
            $.pullToRefreshDone('.pull-to-refresh-content');
        }, 2000);
    });
    $.init();

});
function removeAllTabClass() {
    $("#tab1").removeClass("active");
    $("#tab2").removeClass("active");
    $("#tab3").removeClass("active");
    $("#tab4").removeClass("active");
}

function getFinds(type, page) {
    var finds = [];
    $.ajax({
        type: "POST",
        dataType: "json",
        cache: true,
        async: false,
        url: "app/find/list",
        data: {
            type: type
        },
        success: function (data) {
            finds = data;
        }
    });
    return finds;
}

function getFindsRef(type) {
    var finds = [];
    $.ajax({
        type: "POST",
        dataType: "json",
        cache: true,
        async: false,
        url: "app/find/list",
        data: {
            type: type,
            state: 1
        },
        success: function (data) {
            finds = data;
        }
    });
    return finds;
}

function findShow(id) {
    $.router.load("app/find/show/"+id,true);

}
function addFindToPage(finds) {
    $.each(finds, function (index, item) {
        var html = '<div class="card facebook-card" onclick="findShow(' +item.id+
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
            '</span> <span>剩余' +item.less+
            '</span></div></div>';
        $("#listContent").find('.card-container').prepend(html);
    });
    $.pullToRefreshDone ('.pull-to-refresh-content');
}