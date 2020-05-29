//供应商
defaultProviderSetting = {
    id : "",
    name : "",
    address : "",
    principalname : "",
    account : "",
    time : ""
}

function sendProviderJsonAjax(url, param) {
    var tempdata;
    $.ajax({
        url: url,
        data: param,
        type: "post",
        dataType: "JSON",
        contentType: "application/json;charset=UTF-8",
        success: function (data) {
            if (data != null) {
                console.log("Receive Provider Json : ", data);
                tempdata = data;
            }
        },
        error: function () {
        }
    });
    return tempdata;
 }

//通过id查找供应商
function queryProviderById(tid) {
    if (tid == "") {
        return;
    }
    provider = {
        id : tid
    }
    param = buildProviderParam(provider);
    url = "/provider/queryById";
    console.log("QueryProviderById : ", param);
    $.ajaxSettings.async = false;
    return sendProviderJsonAjax(url, param);
}

//通过条件筛选供应商
function queryProvider(provider) {
    if (provider == null) {
        return null;
    }
    param = buildProviderParam(provider);
    console.log("QueryProvider : ", param);
    url = "/provider/query";
    $.ajaxSettings.async = false;
    return sendProviderJsonAjax(url, param);
}

//增加供应商
function insertProvider(provider) {
    if (provider == null) {
        return null;
    }

    param = buildProviderParam(provider);
    url = "/provider/insert";
    console.log("InsertProvider : ", param);
    $.ajaxSettings.async = false;
    return sendProviderJsonAjax(url, param);
}

//删除供应商
function deleteProvider(provider) {
    if (provider.id == "") {
        return null;
    }
    param = buildProviderParam(provider);
    url = "/provider/delete";
    console.log("DeleteProvider : ", param);
    $.ajaxSettings.async = false;
    return sendProviderJsonAjax(url, param);
}

//更新供应商
function updateProvider(provider) {
    if (provider == null) {
        return null;
    }
    param = buildProviderParam(provider);
    url = "/provider/update";
    console.log("UpdataProvider : ", param);
    $.ajaxSettings.async = false;
    return sendProviderJsonAjax(url, param);
}

//请求id与供应商映射
function queryProviderMenu() {
	url = "/provider/providermenu";
	param = "";
    console.log("QueryMenu Provider : ", param);
    $.ajaxSettings.async = false;
    var map = new Map();
    var obj = sendWarehourseJsonAjax(url, param);
    var keys = Object.keys(obj);
    for(var i in keys) {
    	map.set(keys[i].toString(), obj[keys[i]]);
    }
    return map;
}

function buildPMenuOptionHTML() {
    var html;
    var map = queryProviderMenu();
    map.forEach(function(value, key) {
        html += '<option value="' + key + '">' + value + '</option>';
    })
    return html;
}

function buildProviderParam(provider) {
    combineProvider = $.extend({},defaultProviderSetting, provider);
    param = 
       '{'
        + '"id":"' + combineProvider.id + '",'
        + '"name":"' + combineProvider.name + '",'
        + '"address":"' + combineProvider.address + '",'
        + '"principalname":"' + combineProvider.principalname + '",'
        + '"account":"' + combineProvider.account + '",'
        + '"time":"' + combineProvider.time + '"}';
    return param;
}