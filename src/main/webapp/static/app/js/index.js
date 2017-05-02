/**
 * 首页js
 * Created by 李明元 on 2017/3/28.
 */
$(function () {
    $(document).on("pageInit", function(e, pageId, $page) {
        if(pageId == "mapPage") {
            $.showIndicator();
            if(!isWeiXin()){
                if (localStorage.token) {
                    $.ajax({
                        type: 'POST',
                        url: 'app/login/token',
                        data: {token: localStorage.token},
                        async: false,
                        success: function (data) {
                            if (data.icon == 2) {
                                $.toast(data.message);
                                location.href="app/login";
                            }
                        }
                    });
                }else {
                    location.href="app/login";
                }
            }else{
                var openid=$("#openid").val();
                if(openid!='0'){
                    $.ajax({
                        type: 'POST',
                        url: 'app/login/openid',
                        data: {openid: openid},
                        async: false,
                        success: function (data) {
                            if (data.icon == 2) {
                                $.toast(data.message);
                                location.href="app/login";
                            }
                        }
                    });
                }
            }
            $("#mapContent").empty();
            initMap($("#type").val());
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
    $(document).on("click", "#tab1", function () {
        $.showIndicator();
        removeAllTabClass();
        $("#type").val(1);
        initMap($("#type").val());
        $("#tab1").addClass("active");
        $.hideIndicator();
    });
    $(document).on("click", "#tab2", function () {
        $.showIndicator();
        removeAllTabClass();
        $("#type").val(2);
        initMap($("#type").val());
        $("#tab2").addClass("active");
        $.hideIndicator();

    });
    $(document).on("click", "#tab3", function () {
        $.showIndicator();
        removeAllTabClass();
        $("#type").val(3);
        initMap($("#type").val());
        $("#tab3").addClass("active");
        $.hideIndicator();
    });
    $(document).on("click", "#tab4", function () {
        $.showIndicator();
        removeAllTabClass();
        $("#type").val(4);
        initMap($("#type").val());
        $("#tab4").addClass("active");
        $.hideIndicator();
    });
    $.init();

});
function removeAllTabClass() {
    $("#tab1").removeClass("active");
    $("#tab2").removeClass("active");
    $("#tab3").removeClass("active");
    $("#tab4").removeClass("active");
}
function getFinds(type) {
    var finds = [];
    $.ajax({
        type: "GET",
        dataType: "json",
        cache: true,
        async: false,
        url: "app/find/map/" + type,
        success: function (data) {
            finds = data;
        }
    });
    return finds;
}

function initMap(type) {
    //初始化地图对象，加载地图
    var map = new AMap.Map("mapContent", {
        resizeEnable: true,
        zoom: 15
    });
    map.plugin('AMap.Geolocation', function () {
        geolocation = new AMap.Geolocation({
            enableHighAccuracy: true,//是否使用高精度定位，默认:true
            timeout: 10000,          //超过10秒后停止定位，默认：无穷大
            maximumAge: 1000000000,           //定位结果缓存0毫秒，默认：0
            convert: true,           //自动偏移坐标，偏移后的坐标为高德坐标，默认：true
            showButton: true,        //显示定位按钮，默认：true
            buttonPosition: 'LB',    //定位按钮停靠位置，默认：'LB'，左下角
            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            showMarker: true,        //定位成功后在定位到的位置显示点标记，默认：true
            showCircle: true,        //定位成功后用圆圈表示定位精度范围，默认：true
            panToLocation: true,     //定位成功后将定位到的位置作为地图中心点，默认：true
            zoomToAccuracy: true      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
        });

        map.addControl(geolocation);
        geolocation.getCurrentPosition();
        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
        AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
    });
    //解析定位结果
    function onComplete(data) {
        localStorage.userMap = data.position.getLng() + ',' + data.position.getLat();
        $.ajax({
            type: 'POST',
            url: 'app/save/location',
            data: {
                map: data.position.getLng() + ',' + data.position.getLat()
            },
            success: function (data) {
                if(data.icon===2){
                    $.toast(data.message);
                }
            }
        });
    }

    //解析定位错误信息
    function onError(data) {
        $.toast("定位失败！");
    }

    var finds = getFinds(type);
    var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(10, -30)});
    $.each(finds, function (index, item) {
        var pot = item.map.split(',');
        var marker = new AMap.Marker({
            position: new AMap.LngLat(pot[0], pot[1]),
            content: '<div  class="marker-icon"><img src="http://images.javayuan.cn/' + item.image + '" class="image-icon"></div>',
            map: map
        });
        var strVar = "";
        strVar += "<div class=\"card facebook-card\" style='box-shadow: 0 0 0 rgba(0, 0, 0, 0);margin: 0'><div class=\"card-content\" onclick='findShow(" + item.id +
            ")' style=\"padding:.6rem;width: 13rem\"><div class='line-height-inline'>" +
            item.name + "<\/div><div class='line-height-inline'>" +
            item.content + "<\/div><\/div><div class=\"card-footer no-border\" style=\"padding:0;margin:0\"><div class=\"pull-left\"><div class=\"facebook-avatar\">" +
            "<img src=\"http://images.javayuan.cn/" + item.image +
            "\" style=\"width:2rem;border-radius:50%\"><\/div><div class=\"facebook-name\">" + item.nickname +
            "<\/div><div class=\"facebook-date\">" + item.time +
            "<\/div><\/div><\/div><\/div>\n";
        marker.content = strVar;
        marker.on('click', markerClick);
        marker.emit('click', {target: marker});
    });
    function markerClick(e) {
        infoWindow.setContent(e.target.content);
        infoWindow.open(map, e.target.getPosition());
    }

    map.setFitView();
}


function findShow(id) {
    $.router.load("app/find/show/" + id,true);
}
function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}