//进货出货单操作

defaultWareHourseOrderSetting = {
    id : '',  //仓库单id
    sourceid : '',
    sourcename : '',
    targetid : '',
    targetname : '',
    principalid : '',
    principalname : '',
    type : '',
    createtime : '',
    checktime : '',
    status : '',
    ordersumprice : '',

    itemid : '',
    itemnum : '',
    itemname : '',
    perprice : '',
    sumprice : ''
}

function sendWOrderJsonAjax(url, param) {
    var tempdata;
    $.ajax({
        url: url,
        data: param,
        type: "post",
        dataType: "JSON",
        contentType: "application/json;charset=UTF-8",
        success: function (data) {
            console.log("Receive Worder JSON : ", data);
            if (data != null) {
               tempdata = data;
            }
        },
        error: function () {
        }
    });
    return tempdata;
}

//通过id搜索仓库单
function queryWarehourseOrderById(tid) {
    if (tid == "") {
        return;
    }
    worder = {
        id : tid
    }
    param = buildWorderParam(worder);
    url = "/warehourseOrder/queryById";
    console.log("QueryWoederById : ", param);
    $.ajaxSettings.async = false;
    sendWOrderJsonAjax(url, param);
}

//过滤条件搜索,结果为list
function queryWarehourseOrder(worder) {
    if (worder == null) {
        return;
    }
    param = buildWorderParam(worder);
    url = "/warehourseOrder/query";
    console.log("Query Worder : ", param);
    $.ajaxSettings.async = false;
    return sendWOrderJsonAjax(url, param);
}

//插入仓库单
function insertWarehourseOrder(worder) {
    if (worder == null) {
        return;
    }
    param = buildWorderParamList(worder);
    url = "/warehourseOrder/insert";
    console.log("Insert Worder : ", param);
    $.ajaxSettings.async = false;
    return sendWOrderJsonAjax(url, param);
}


//更新仓库单
function updateWarehourseOrder(worder) {
    if (worder == null) {
        return;
    }
    param = buildWorderParamList(worder);
    url = "/warehourseOrder/update";
    console.log("Update Worder : ", param);
    $.ajaxSettings.async = false;
    return sendWOrderJsonAjax(url, param);
}

//删除仓库单
function deleteWarehourseOrder(tid) {
    if(tid == "") {
        return;
    }
    param = buildWorderParam({id:tid})
    url = "/warehourseOrder/delete";
    console.log("Delete Worder : ", param);
    $.ajaxSettings.async = false;
    return sendWOrderJsonAjax(url, param);
}

//将仓库单设置为审核中状态
function applyWarehourseOrder(tid) {
    if(tid == "") {
        return;
    }
    param = buildWorderParam({id:tid});
    url = "/warehourseOrder/apply";
    console.log("Apply Worder : ", param);
    $.ajaxSettings.async = false;
    return sendWOrderJsonAjax(url, param);
}

//将仓库单设置为通过状态
passWarehourseOrder = function(worder) {
    if(worder.id == "") {
        return;
    }
    param = buildWorderParam(worder);
    url = "/warehourseOrder/pass";
    console.log("Passs Worder : ", param);
    $.ajaxSettings.async = false;
    return sendWOrderJsonAjax(url, param);
}

function getCargoStockAmount(hourseid) {
    param = JSON.stringify({
    	warehourseid : hourseid.toString()
    });
    url = "/warehourseOrder/inoutmoney";
    $.ajaxSettings.async = false;
    return sendWOrderJsonAjax(url, param);
}

function buildWorderParam(worder) {
    combineWorder = $.extend({}, defaultWareHourseOrderSetting, worder);
    param = 
        '{'
        + '"id":"' + combineWorder.id + '",'
        + '"sourceid":"' + combineWorder.sourceid + '",'
        + '"sourcename":"' + combineWorder.sourcename + '",'
        + '"targetid":"' + combineWorder.targetid + '",'
        + '"targetname":"' + combineWorder.targetname + '",'
        + '"principalid":"' + combineWorder.principalid + '",'
        + '"principalname":"' + combineWorder.principalname + '",'
        + '"type":"' + combineWorder.type + '",'
        + '"createtime":"' + combineWorder.createtime + '",'
        + '"checktime":"' + combineWorder.checktime + '",'
        + '"ordersumprice":"' + combineWorder.ordersumprice + '",'
        + '"status":"' + combineWorder.status+ '",'
        + '"itemid":"' + combineWorder.itemid + '",'
        + '"itemname":"' + combineWorder.itemname + '",'
        + '"itemnum":"' + combineWorder.itemnum + '",'
        + '"perprice":"' + combineWorder.perprice + '",'
        + '"sumprice":"' + combineWorder.sumprice + '"}';
    return param;
}

function buildWorderParamList(worderL) {
    jsonList = [];
    for (var i = 0 ; i < worderL.length; i++) {
        jsonList.push(buildWorderParam(worderL[i]));
    }
    param = '[';
    for (var i = 0 ; i < jsonList.length-1; i++) {
        param += (jsonList[i] + ',');
    }
    param += (jsonList[jsonList.length-1] + ']');
    return param;
}

/**
 * 将状态码转换为审核状态
 * @param {String} status
 * @returns Int 0 为未审核，1为审核中，2为审核通过
 */
function status2checkstatus(status) {
    if(status == "1") {
        return 0;
    } else if(status == "2") {
        return 1;
    } else if(status == "4") {
        return 2;
    }
    return -1;
}