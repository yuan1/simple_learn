/**
 * Created by 李明元 on 2017/3/29.
 */

$(function () {
    $(document).on("pageInit", function (e, pageId, $page) {
        if (pageId == "addPage") {

            getFindById()
        }
        if (pageId == "addressPage") {
            var addressIframe = document.getElementById('addressContent').contentWindow;
            document.getElementById('addressContent').onload = function () {
                addressIframe.postMessage('hello', 'https://m.amap.com/picker/');
            };
            window.addEventListener("message", function (e) {
                sendMap(e.data.name, e.data.location, $("#findId").val());
            }, false);
        }
    });
    $(document).on("click", "#addressName", function () {
        if(postFindAdd()){
            var findId = $("#findId").val();
            $.router.load("app/find/map/address/" + findId, true);
        }
    });
    $(document).on("click", "#findAdd", function () {
        if ($("#name").val() == '') {
            $.toast("请输入名字！");
            return false;
        }
        if ($("#time").val() == '') {
            $.toast("请输入时间！");
            return false;
        }
        if ($("#type").val() == '') {
            $.toast("请选择类型！");
            return false;
        }
        if ($("#locationMap").val() == '') {
            $.toast("请选择我的位置！");
            return false;
        }
        if ($("#addressName").val() == '') {
            $.toast("请选择地点！");
            return false;
        }
        if ($("#content").val() == '') {
            $.toast("请选输入内容！");
            return false;
        }
        if ($("#attention").val() == '') {
            $.toast("请选输入注意事项！");
            return false;
        }
        $("#state").val("1");
        if (postFindAdd()) {
            $.toast("添加成功！");
            location.href = "app/find/list/index";
        }
    });

    $.init();

});

function sendMap(poiaddress, map, findId) {
    if (poiaddress == '' || map == '') {
        $.toast("请选择位置！");
        return false;
    }
    $.ajax({
        type: 'POST',
        url: "app/find/map/address/select",
        async: false,
        data: {
            poiaddress: poiaddress,
            map: map,
            findId: findId
        },
        success: function (data) {
            if (data.icon == 1) {
                $.toast(data.message);
                $.router.back()
            } else {
                $.toast(data.message);
            }
        }
    });
}

function postFindAdd() {
    var bl = false;
    $.ajax({
        type: 'POST',
        url: 'app/find/add/do',
        async: false,
        data: {
            findId: $("#findId").val(),
            name: $("#name").val(),
            content: $("#content").val(),
            attention: $("#attention").val(),
            findTime: $("#findTime").val(),
            state: $("#state").val(),
            type: $("#type").val()

        },
        success: function (data) {
            if (data.icon == 1) {
                bl = true;
            } else {
                $.toast(data.message);
            }
        }
    });
    return bl;
}
function getFindById() {
    $.ajax({
        type: 'POST',
        url: 'app/find/add/get',
        async: false,
        data: {
            findId: $("#findId").val()
        },
        success: function (data) {
            $("#name").val(data.name);
            $("#addressName").val(data.addressName);
            $("#findTime").val(data.findTime);
            var  d = new Date();
            if(data.findTime!=null){
                d= new Date(data.findTime);
            }
            var vYear = d.getFullYear();
            var vMon = d.getMonth() + 1;
            vMon="0"+vMon;
            var vDay = d.getDate();
            var h = d.getHours();
            var m = d.getMinutes();
            $("#findTime").datetimePicker({
                value: [vYear, vMon, vDay, h, m]
            });
            $("#content").val(data.content);
            $("#type").val(data.type);
            $("#attention").val(data.attention);
            $("#state").val(data.state);
        }
    });
}