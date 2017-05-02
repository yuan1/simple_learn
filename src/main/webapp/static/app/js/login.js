/**
 * Created by 李明元 on 2017/3/30.
 */
$(function () {
    $(document).on("pageInit", function () {
        $(".page").hide();
        autoDo();
    });
    $(document).on("click", "#login", function () {
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
            url: 'app/login/do',
            data: {username: username, password: password},
            success: function (data) {
                if (data.icon === 1) {
                    localStorage.token = data.message;
                    window.location.href = "app/index";
                } else {
                    $.hideIndicator();
                    $.toast(data.message);
                }
            }
        });
    });
    $(document).on("click", "#regist-1", function () {
        var mobile = $("#mobile").val();
        if (mobile === '') {
            $.toast("请输入手机号！");
            return
        }
        if (checkMobile(mobile)) {
            sendMessage(mobile, "app/regist/2");
        }
    });
    $(document).on("click", "#regist-2", function () {
        var code = $("#checkMobile").val();
        if (code === '') {
            $.toast("请输入验证码！");
            return
        }
        if (checkCodeMobile(code)) {
            var regUname = $("#regUname").val();
            if (regUname === '') {
                $.toast("请输入用户名！");
                return
            }
            if (checkUname(regUname)) {
                var regNick=$("#regUnick").val();
                if (regNick === '') {
                    $.toast("请输入昵称！");
                    return
                }
                var regPWD = $("#regPWD").val();
                if (regPWD === '') {
                    $.toast("请输入密码！");
                    return
                }
                $.showIndicator();
                $.ajax({
                    type: 'POST',
                    url: 'app/regist/do',
                    data: {username: regUname, password: regPWD,usernick:regNick},
                    success: function (data) {
                        $.hideIndicator();
                        if (data.icon == 1) {
                            $.toast("注册成功！");
                            setTimeout(function () {
                                window.location.href = "app/login";
                            }, 2000);
                        } else {
                            $.toast(data.message);
                        }
                    }
                });
            }
        }
    });
    $(document).on("click", "#rem-2", function () {
        var code = $("#checkMobile").val();
        if (code === '') {
            $.toast("请输入验证码！");
            return
        }
        if (checkCodeMobile(code)) {
            var remPWD = $("#remPWD").val();
            if (remPWD === '') {
                $.toast("请输入密码！");
                return
            }
            $.ajax({
                type: 'POST',
                url: 'app/rem/do',
                data: {password: remPWD},
                success: function (data) {
                    if (data.icon == 1) {
                        $.toast(data.message);
                        setTimeout(function () {
                            window.location.href = "app/login";
                        }, 2000);
                    } else {
                        $.toast(data.message);
                    }
                }
            });
        }
    });
    $(document).on("click", "#rem-1", function () {
        var mobile = $("#remMobile").val();
        if (mobile === '') {
            $.toast("请输入手机号！");
            return
        }
        if (checkRemMobile(mobile)) {
            sendMessage(mobile, "app/rem/2");
        }
    });
    $.init();

});
function checkUname(username) {
    var uname_check = false;
    $.ajax({
        type: 'POST',
        url: 'app/check/username',
        async: false,
        data: {username: username},
        success: function (data) {
            if (data.icon === 1) {
                uname_check = true;
            } else {
                $.toast(data.message);
            }
        }
    });
    return uname_check;
}
function autoDo() {
    if (localStorage.userMap) {
        $.ajax({
            type: 'POST',
            url: 'app/save/location',
            async: false,
            data: {
                map: localStorage.userMap
            }
        });
    }
    if (localStorage.token) {
        $.ajax({
            type: 'POST',
            url: 'app/login/token',
            data: {token: localStorage.token},
            async: false,
            success: function (data) {
                if (data.icon == 2) {
                    $.toast(data.message);
                    $(".page").show();
                }else {
                    location.href="app/index";
                }
            }
        });
    }else {
        $(".page").show();
    }

}
function checkMobile(mobile) {
    var bl = false;
    $.ajax({
        type: 'POST',
        url: 'app/check/mobile',
        async: false,
        data: {mobile: mobile},
        success: function (data) {
            if (data.icon === 1) {
                bl = true;
            } else {
                $.toast(data.message);
            }
        }
    });
    return bl;
}

function checkRemMobile(mobile) {
    var bl = false;
    $.ajax({
        type: 'POST',
        url: 'app/check/mobile',
        async: false,
        data: {mobile: mobile},
        success: function (data) {
            if (data.icon === 2) {
                bl = true;
            } else {
                $.toast("手机号未注册！");
            }
        }
    });
    return bl;
}

function checkCodeMobile(code) {
    var bl = false;
    $.ajax({
        type: 'POST',
        url: 'app/message/check',
        data: {message: code},
        async: false,
        success: function (data) {
            if (data.icon === 1) {
                bl = true;
            } else {
                $.toast(data.message);
            }
        }
    });

    return bl;
}

function sendMessage(mobile, url) {
    $.ajax({
        type: 'POST',
        url: 'app/message',
        data: {mobile: mobile},
        success: function (data) {
            if (data.icon === 1) {
                $.router.load(url);
            } else {
                $.toast(data.message);
            }
        }
    });
}