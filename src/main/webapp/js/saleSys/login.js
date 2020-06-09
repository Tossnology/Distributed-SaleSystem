/**
 * login function, use for login
 * login.html
 */

window.onload = function () {
}

var serverAddr = "/api";

$("#login-btn").click(
    function () {
        var account = $("#account").val();
        var password = $("#password").val();
        var param;
        var url = serverAddr + "/login/"; //TODO waiturl
        var destination = "" //网页跳转
        var type = $('#type').val();

        if (type == "clerk") {
            //TODO 登陆器
            url = url + "administrator"
            destination = destination + "ShopAssistant/HomeIndex.html"
            param = 
                '{"id":"' + account + '",'
                + '"password":"' + password + '",'
                + '"type":"clerk"}';
        } else if (type == "shop-manager") {
            url = url + "teacher"
            destination = destination + "ShopManager/HomeIndex.html"
            param = 
            '{"id":"' + account + '",'
            + '"password":"' + password + '",'
            + '"type":"shop-manager"}';
        } else {
            url = url + "student"
            destination = destination + "GeneralManager/HomeIndex.html"
            param = 
            '{"id":"' + account + '",'
            + '"password":"' + password + '",'
            + '"type":"general-manager"}';
        }
        if (account == null || account == "") {
            alert("账户不能为空");
            return;
        }
         
        loginajax = $.ajax({
            url: url,
            data: param,
            async: false,
            type: "post",
            dataType: "text",
            contentType: "application/json;charset=UTF-8",
            success: function (str) {
                if (str == "true") {
                    
                } else if (str == "wrongpwd") {
                    alert("密码错误，请重新输入");
                    return;
                } else {
                    alert("没有此账号");
                    return;
                }
            },
            error: function () {
            }
        });
        var secondrequest = 
            '{"id":"' + account +'"}';
        var url = serverAddr + "/login/secondRequest";
        dataajax = $.ajax({
            url: url,
            data: secondrequest,
            async: false,
            type: "post",
            dataType: "JSON",
            contentType: "application/json;charset=UTF-8",
            success: function (data) {
                if (data != null) {
                    //填充cookie
                    //会话 sessionid
                    //账号 id
                    //职位 position
                	
                    setCookie("id", data.principalid);
                    setCookie("position", type);
                    setCookie("warehourseid", data.warehourseid);
                    setCookie("warehoursename", data.warehoursename);
                    setCookie("warehourselocation", data.warehourselocation);
                    setCookie("principalid", data.principalid);
                    setCookie("principalname", data.principalname);
                    setCookie("saleorderitemtable", data.saleorderitemtable);
                    setCookie("saleordercommontable", data.saleorderitemtable);
                    setCookie("stafftable", data.stafftable);
                    setCookie("warehoursedetailtable", data.warehoursedetailtable);
                    setCookie("itemtable", data.itemtable);
                    setCookie("time", data.time);
                } else {
                    alert("请求失败");
                    return;
                }
            },
            error: function () {
            	console.log("receive fail");
            }
        });

        $.when(loginajax, dataajax).done(function() {
            top.location.href = destination;
        });
    });

$('.input input').on('focus', function () {
    $(this).parent().addClass('focus');
});

$('.input input').on('blur', function () {
    if ($(this).val() === '')
        $(this).parent().removeClass('focus');
});