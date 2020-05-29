//个人信息页面
window.onload = function () {
    refreshProfile();
}

var tempPerson;

function refreshProfile() {
	console.log(getCookie("position"))
    document.getElementById('name').innerHTML = getCookie("principalname");
    if (getCookie("position") == "clerk") {
        var staff = queryStaffById(getCookie("principalid"));
        tempPerson = staff;
        document.getElementById('gender').innerHTML = staff.gender;
        document.getElementById('id').innerHTML = staff.id;
        document.getElementById('position').innerHTML = (getCookie("position") == "clerk") ? "店员" : (getCookie("position") == "shop-manager") ? "店长" : "总经理";
        document.getElementById('rep').innerHTML = getCookie("#warehoursename");
        document.getElementById('phone').innerHTML = staff.phone;
        document.getElementById('email').innerHTML = staff.email;
    } else if (getCookie("position") == "shop-manager") {
        var shopmanager = queryManagerById(getCookie("principalid"));
        tempPerson = shopmanager;
        document.getElementById('gender').innerHTML = shopmanager.gender;
        document.getElementById('id').innerHTML = shopmanager.id;
        document.getElementById('position').innerHTML = (getCookie("position") == "clerk") ? "店员" : (getCookie("position") == "shop-manager") ? "店长" : "总经理";
        document.getElementById('rep').innerHTML = getCookie("#warehoursename");
        document.getElementById('phone').innerHTML = shopmanager.phone;
        document.getElementById('email').innerHTML = shopmanager.email;
    } else if (getCookie("position") == "general-manager") {
        var general = queryGManagerById(getCookie("principalid"));
        tempPerson = general;
        document.getElementById('gender').innerHTML = general.gender;
        document.getElementById('id').innerHTML = general.id;
        document.getElementById('position').innerHTML = (getCookie("position") == "clerk") ? "店员" : (getCookie("position") == "shop-manager") ? "店长" : "总经理";
        document.getElementById('rep').innerHTML = getCookie("#warehoursename");
        document.getElementById('phone').innerHTML = general.phone;
        document.getElementById('email').innerHTML = general.email;
    }
}

function loadModal() {
    $('#modal-id').val(tempPerson.id);
    $('#modal-name').val(tempPerson.name);
    $('#modal-gender').val(tempPerson.gender);
    $('#modal-phone').val(tempPerson.phone);
    $('#modal-email').val(tempPerson.email);
}

$('#edit-btn').click(function () {
    $('#profileModal').modal('show');
    loadModal();
});

$('#save-btn').click(function () {
    var obj = {
        id: $('#modal-id').val(),
        name: $('#modal-name').val(),
        gender: $('#modal-gender').val(),
        phone: $('#modal-phone').val(),
        email: $('#modal-email').val(),
        hourseid: getCookie("warehourseid")
    }
    $('#profileModal').modal('hide');

    (getCookie("type") == "clerk") ?
        (alert(updateStaff(obj)).info)
        : (getCookie("type") == "shop-manager") ?
            (alert(updateManager(obj)).info)
            : (alert(updateGManager(obj)).info);
});

$('#check-pwd').blur(function() {
    if($('#new-pwd').val() != $('#check-pwd').val()) {
        alert("两次密码应一致");
    }
});

$('#modify-btn').click(function() {
    if($('#new-pwd').val() != $('#check-pwd').val()) {
        alert("两次密码应一致");
        return;
    }
    var obj = {
        id : getCookie("principalid"),
        password : $('#new-pwd').val()
    }
    (getCookie("type") == "clerk") ?
        (alert(changeCPwd(obj)).info)
        : (getCookie("type") == "shop-manager") ?
            (alert(changeSPwd(obj)).info)
            : (alert(changeGPwd(obj)).info);
});