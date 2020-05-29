//仓库对象操作

defaultWarehourseSetting = {
    id : '',
    name : '',
    location : '',
    principalid : '',
    time : '',
    label : ''
}

function sendWarehourseJsonAjax(url, param) {
    var tempdata;
    $.ajax({
        url: url,
        data: param,
        type: "post",
        dataType: "JSON",
        contentType: "application/json;charset=UTF-8",
        success: function (data) {
            if (data != null) {
                console.log("Receive JSON Warehourse : ", data);
                tempdata = data;
            }
        },
        error: function () {
        }
    });
    return tempdata;
 }

//通过id查找仓库
function queryWarehourseById(id) {
    if (id == "") {
        return;
    }
    param = buildWarehourseParam({
        id : id
    })
    url = "/warehourse/queryById";
    console.log("Query Warehourse By Id : ", param);
    $.ajaxSettings.async = false;
    return sendWarehourseJsonAjax(url, param);
}

//通过条件筛选仓库
function queryWarehourse(warehourse) {
    if (warehourse == null) {
        return null;
    }
    param = buildWarehourseParam(warehourse);
    url = "/warehourse/query";
    console.log("Query Warehourse : ", param);
    $.ajaxSettings.async = false;
    return sendWarehourseJsonAjax(url, param);
}

//增加仓库
function insertWarehourse(warehourse) {
    if (warehourse == null) {
        return null;
    }
    param = buildWarehourseParam(warehourse);
    url = "/warehourse/add";
    console.log("Add Warehourse : ", param);
    $.ajaxSettings.async = false;
    return sendWarehourseJsonAjax(url, param);
}

//删除仓库
function deleteWarehourse(warehourse) {
    if (warehourse.id == "") {
        return null;
    }
    param = buildWarehourseParam(warehourse);
    url = "/warehourse/delete";
    console.log("Delete Warehourse : ", param);
    $.ajaxSettings.async = false;
    return sendWarehourseJsonAjax(url, param);
}

//更新仓库
function updateWarehourse(warehourse) {
    if (warehourse == null) {
        return null;
    }
    param = buildWarehourseParam(warehourse);
    url = "/warehourse/update";
    console.log("Update Warehourse : ", param);
    $.ajaxSettings.async = false;
    return sendWarehourseJsonAjax(url, param);
}


//请求id与仓库映射
function queryWarehourseMenu() {
	url = "/warehourse/typemenu";
	param = "";
    console.log("QueryMenu Warehourse : ", param);
    $.ajaxSettings.async = false;
    var map = new Map();
    var obj = sendWarehourseJsonAjax(url, param);
    var keys = Object.keys(obj);
    for(var i in keys) {
    	map.set(keys[i].toString(), obj[keys[i]]);
    }
    return map;
}

function buildWarehourseParam(warehourse) {
    combineWarehourse = $.extend({},defaultWarehourseSetting, warehourse);
    param = 
       '{'
        + '"id":"' + combineWarehourse.id + '",'
        + '"name":"' + combineWarehourse.name + '",'
        + '"location":"' + combineWarehourse.location + '",'
        + '"principalid":"' + combineWarehourse.principalid + '",'
        + '"time":"' + combineWarehourse.time + '",'
        + '"label":"' + combineWarehourse.label + '"}';
    return param;
}

function buildWMenuOptionHTML() {
    var html;
    var map = queryWarehourseMenu();
    map.forEach(function(value, key) {
        html += '<option value="' + key + '">' + value + '</option>';
    })
    return html;
}

function buildWMenuOptionNoBaseHTML() {
    var html;
    var map = queryWarehourseMenu();
    map.forEach(function(value, key) {
    	if(key != "-1") {
    		html += '<option value="' + key + '">' + value + '</option>';
    	}
    })
    return html;
}
