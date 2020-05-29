//总经理对象操作

defaultGManagerSetting = {
    id : '',
    password : '',
    hourseid : '',
    name : '',
    gender : '',
    phone : '',
    email : ''
}

function sendGeneralJsonAjax(url, param) {
    var tempdata;
    $.ajax({
        url: url,
        data: param,
        type: "post",
        dataType: "JSON",
        contentType: "application/json;charset=UTF-8",
        success: function (data) {
            if (data != null) {
                console.log("Receive JSON general : ", data);
                tempdata = data;
            }
        },
        error: function () {
        }
    });
    return tempdata;
 }

//通过id查找总经理
function queryGManagerById(id) {
    if (id == "") {
        return;
    }
    param = 
       '{"id":"' + id + '"}';
    url = "/generalmanager/queryById";
    $.ajaxSettings.async = false;
    console.log("Query GManager : ", param);
    return sendGeneralJsonAjax(url, param);
}

function updateGManager(manager) {
    if(manager.id == "") {
        return;
    }
    param = buildGManagerParam(manager);
    url = "";
    $.ajaxSettings.async = false;
    console.log("updata Manager : ", param);
    return sendGeneralJsonAjax(url, param);
}

function changeGPwd(manager) {
    param =
    '{'
    + '"id":"' + manager.id + '",'
    + '"password":"' + manager.password + '"}';
    var url = "";
    $.ajaxSettings.async = false;
    console.log("ChangePwd Manager : ", param);
    return sendGeneralJsonAjax(url, param);
}

function buildGManagerParam(manager) {
    if (manager == null) {
        return null;
    }
    combineGManager = $.extend({},defaultGManagerSetting, manager);
    param = 
    '{'
    + '"id":"' + combineGManager.id + '",'
    + '"name":"' + combineGManager.name + '",'
    + '"gender":"' + combineGManager.gender + '",'
    + '"phone":"' + combineGManager.phone + '",'
    + '"email":"' + combineGManager.email + '"}'
    return param;
}
