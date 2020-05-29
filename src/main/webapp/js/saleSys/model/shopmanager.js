//店长对象操作

defaultShopManagerSetting = {
    id : '',
    password : '',
    hourseid : '',
    name : '',
    gender : '',
    phone : '',
    email : '',
    label : ''
}

function sendShopManagerJsonAjax(url, param) {
    var tempdata;
    $.ajax({
        url: url,
        data: param,
        type: "post",
        dataType: "JSON",
        contentType: "application/json;charset=UTF-8",
        success: function (data) {
            if (data != null) {
                console.log("Receive JSON manager : ", data);
                tempdata = data;
            }
        },
        error: function () {
        }
    });
    return tempdata;
 }

//通过id查找店长
function queryManagerById(id) {
    if (id == "") {
        return;
    }
    param = 
       '{"id":"' + id + '"}';
    url = "/shopmanager/queryById";
    $.ajaxSettings.async = false;
    console.log("Query ManagerById : ", param);
    return sendShopManagerJsonAjax(url, param);
}

//通过条件筛选店长
function queryManager(manager) {
    if (manager == null) {
        return null;
    }
    combineManager = $.extend({},defaultShopManagerSetting, manager);
    param = 
       '{'
        + '"id":"' + combineManager.id + '",'
        + '"name":"' + combineManager.name + '",'
        + '"gender":"' + combineManager.gender + '",'
        + '"phone":"' + combineManager.phone + '",'
        + '"email":"' + combineManager.email + '",'
        + '"label":"' + combineManager.label + '",'
        + '"hourseid":"' + combineManager.hourseid + '"}';
    url = "/shopmanager/query";
    $.ajaxSettings.async = false;
    console.log("Query Manager : ", param);
    return sendShopManagerJsonAjax(url, param);
}

//增加店长
function insertManager(manager) {
    if (manager == null) {
        return null;
    }
    combineManager = $.extend({},defaultShopManagerSetting, manager);
    param = 
       '{'
        + '"id":"' + combineManager.id + '",'
        + '"name":"' + combineManager.name + '",'
        + '"gender":"' + combineManager.gender + '",'
        + '"phone":"' + combineManager.phone + '",'
        + '"email":"' + combineManager.email + '",'
        + '"label":"' + combineManager.label + '",'
        + '"hourseid":"' + combineManager.hourseid + '"}';
    url = "/shopmanager/insert";
    $.ajaxSettings.async = false;
    console.log("Insert Manager : ", param);
    return sendShopManagerJsonAjax(url, param);
}

//删除店长
function deleteManager(manager) {
    if (manager.id == "") {
        return null;
    }
    combineManager = $.extend({},defaultShopManagerSetting, manager);
    param = 
       '{'
        + '"id":"' + combineManager.id + '",'
        + '"name":"' + combineManager.name + '",'
        + '"gender":"' + combineManager.gender + '",'
        + '"phone":"' + combineManager.phone + '",'
        + '"email":"' + combineManager.email + '",'
        + '"label":"' + combineManager.label + '",'
        + '"hourseid":"' + combineManager.hourseid + '"}';
    url = "/shopmanager/delete";
    $.ajaxSettings.async = false;
    console.log("Delete Manager : ", param);
    return sendShopManagerJsonAjax(url, param);
}

//更新店长
function updateManager(manager) {
    if (manager == null) {
        return null;
    }
    combineClient = $.extend({},defaultShopManagerSetting, manager);
    param = 
    '{'
    + '"id":"' + combineClient.id + '",'
    + '"name":"' + combineClient.name + '",'
    + '"gender":"' + combineClient.gender + '",'
    + '"phone":"' + combineClient.phone + '",'
    + '"email":"' + combineClient.email + '",'
    + '"hourseid":"' + combineClient.hourseid + '"}';
    url = "/shopmanager/update";
    $.ajaxSettings.async = false;
    console.log("Update Manager : ", param);
    return sendShopManagerJsonAjax(url, param);
}

//通过店长查找管理仓库id
function queryWareIdByManagerId(id) {
    if (id == "") {
        return null;
    }
    param = 
       '{"id":"' + id + '"}';
    url = "/shopmanager/queryWare";
    $.ajaxSettings.async = false;
    console.log("Query WareIdByManager : ", param);
    return sendShopManagerJsonAjax(url, param);
}

function changeSPwd(manager) {
    param =
    '{'
    + '"id":"' + manager.id + '",'
    + '"password":"' + manager.password + '"}';
    var url = "";
    $.ajaxSettings.async = false;
    console.log("ChangePwd SManager : ", param);
    return sendShopManagerJsonAjax(url, param);
}

function assignManager(manager) {
    if (manager == null) {
        return null;
    }
    combineClient = $.extend({},defaultShopManagerSetting, manager);
    param = 
    '{'
    + '"id":"' + combineClient.id + '",'
    + '"name":"' + combineClient.name + '",'
    + '"gender":"' + combineClient.gender + '",'
    + '"phone":"' + combineClient.phone + '",'
    + '"email":"' + combineClient.email + '",'
    + '"hourseid":"' + combineClient.hourseid + '"}';
    url = "/shopmanager/assign";
    $.ajaxSettings.async = false;
    console.log("Assign Manager : ", param);
    return sendShopManagerJsonAjax(url, param);
}