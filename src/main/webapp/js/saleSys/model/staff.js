//员工对象操作
defaultStaffSetting = {
    id : '',
    password : '',
    hourseid : '',
    hoursename : '',
    name : '',
    gender : '',
    phone : '',
    email : '',
    label : ''
}

function sendJsonAjax(url, param) {
    var tempdata;
    $.ajax({
        url: url,
        data: param,
        type: "post",
        dataType: "JSON",
        contentType: "application/json;charset=UTF-8",
        success: function (data) {
            if (data != null) {
                console.log("staff json receive : ", data);
               tempdata = data;
            }
            else {
                console.log("staff json receive null");
            }
        },
        error: function () {
        }
    });
    return tempdata;
 }

//通过id查找员工
function queryStaffById(tid, thourseid) {
    if (tid == "") {
        return;
    }
    staff = {
        id : tid,
        hourseid : thourseid
    }
    param = buildParam(staff);
    url = "";
    console.log("QueryStaffById : ", param);
    $.ajaxSettings.async = false;
    return sendJsonAjax(url, param);
}

//通过条件筛选员工
function queryStaff(staff) {
    if (staff == null) {
        return null;
    }

    param = buildParam(staff);
    url = "/staff/query";
    console.log("QueryStaff : ", param);
    $.ajaxSettings.async = false;
    return sendJsonAjax(url, param);
}

//增加员工
function insertStaff(staff) {
    if (staff == null) {
        return null;
    }

    param = buildParam(staff);
    url = "/staff/add";
    console.log("InsertStaff : ", param);
    $.ajaxSettings.async = false;
    return sendJsonAjax(url, param);
}

//删除员工
function deleteStaff(staff) {
    if (staff.id == "") {
        return null;
    }
    param = buildParam(staff);
    url = "/staff/delete";
    console.log("DeleteStaff : ", param);
    $.ajaxSettings.async = false;
    return sendJsonAjax(url, param);
}

//更新员工
function updateStaff(staff) {
    if (staff == null) {
        return null;
    }
    param = buildParam(staff);
    url = "/staff/update";
    console.log("UpdateStaff : ", param);
    $.ajaxSettings.async = false;
    return sendJsonAjax(url, param);
}

function changeCPwd(staff) {
    param =
    '{'
    + '"id":"' + staff.id + '",'
    + '"password":"' + staff.password + '"}';
    var url = "";
    $.ajaxSettings.async = false;
    console.log("ChangePwd Clerk : ", param);
    return sendJsonAjax(url, param);
}

function buildParam(staff) {
    combineStaff = $.extend({},defaultStaffSetting, staff);
    param = 
       '{'
        + '"id":"' + combineStaff.id + '",'
        + '"name":"' + combineStaff.name + '",'
        + '"gender":"' + combineStaff.gender + '",'
        + '"phone":"' + combineStaff.phone + '",'
        + '"email":"' + combineStaff.email + '",'
        + '"hourseid":"' + combineStaff.hourseid + '",'
        + '"hoursename":"' + combineStaff.hoursename + '",'
        + '"label":"' + combineStaff.label + '"}'
    return param;
}