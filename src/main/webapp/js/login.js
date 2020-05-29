/**
 * login function, use for login
 * login.html
 */

window.onload=function() {
    
}
$("#login-btn").click(function () {
    top.location.href = "http://localhost:8080/exam/StudentRegister.html";
});
$("#btn3").click(function () {
    top.location.href = "http://localhost:8080/exam/TeacherRegister.html"
});
$("#login-btn").click(
    function () {
        top.location.href = "HomeIndex.html";
        var account = $("#account").val();
        var password = $("#password").val();
        var param;
        var url = "http://localhost:8080/exam/"; //TODO waiturl
        var destination = "/exam/" //TODO 系统名
        var type = $('#type').val();
        
        if (type == "clerk") {
            //TODO 登陆器
            url = url + "administrator/login"
            destination = destination + "ManagerHome.html"
            param = '{"account":"' + account + '","password":"'
                + password + '", "type":"clerk"}';
        } else if (type == "shop-manager") {
            url = url + "teacher/login"
            destination = destination + "TeacherHome.html"
            param = '{"id":"' + account + '","password":"' + password
                + '", "type":"shop-manager"}';
        } else {
            url = url + "student/login"
            destination = destination + "StudentHome.html"
            param = '{"id":"' + account + '","password":"' + password
                + '", "type":"general-manager"}';
        }
        if (account == null || account == "") {
            alert("账户不能为空");
        } else {
            $.ajax({
                url: url,
                data: param,
                type: "post",
                dataType: "text",
                contentType: "application/json;charset=UTF-8",
                success: function (str) {
                    if (str == "true") {
                        top.location.href = destination;
                    } else if (str == "false") {
                        alert("密码错误，请重新输入");
                    } else {
                        alert("没有此账号")
                    }
                },
                error: function () {
                }
            });
        }
    });

$('.input input').on('focus', function () {
    $(this).parent().addClass('focus');
})

$('.input input').on('blur', function () {
    if ($(this).val() === '')
        $(this).parent().removeClass('focus');
})