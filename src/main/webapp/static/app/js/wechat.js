/**
 * Created by 李明元 on 2017/3/30.
 */
$(function () {
    $(document).on("pageInit", function () {

    });
    $(document).on("click", "#bind", function () {
        var username = $("#username").val();
        var password = $("#password").val();
        if (username === '') {
            $.toast("请输入用户名或者手机号！");
            return
        }
        if (password === '') {
            $.toast("请输入密码！");
            return
        }
        $.showIndicator();
        $.ajax({
            type: 'POST',
            url: 'app/wechat/login/do',
            data: {username: username, password: password},
            success: function (data) {
                $.hideIndicator();
                $.toast(data.message);
            }
        });
    });
    $.init();
});


