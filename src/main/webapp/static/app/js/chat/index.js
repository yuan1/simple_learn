/**
 * 首页js
 * Created by 李明元 on 2017/3/28.
 */
$(function () {
    $(document).on("pageInit", function(e, pageId, $page) {
        if(pageId == "chatPage") {
            WKIT.destroy();
        }
        if (pageId == "messagePage") {
            WKIT.init({
                uid: $("#uid").val(),
                appkey: $("#appkey").val(),
                credential: $("#credential").val(),
                hideLoginSuccess:true,
                touid: $("#touid").val(),
                titleBar: false,
                container: document.getElementById('messageContent'),
                avatar: $("#avatar").val(),
                toAvatar: $("#toAvatar").val()
            });
        }
    });
    // 添加'refresh'监听器
    $(document).on('refresh', '.pull-to-refresh-content',function(e) {
        // 模拟2s的加载过程
        setTimeout(function() {
            // 加载完毕需要重置
            $.pullToRefreshDone('.pull-to-refresh-content');
        }, 2000);
    });

    $(document).on("click","#backMessage",function () {
        WKIT.destroy();
        $.router.back();
    });

    $.init();
});
function chatShow(id) {
    $.router.load("app/chat/message/"+id);

}