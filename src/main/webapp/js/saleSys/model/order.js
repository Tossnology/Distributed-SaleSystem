/**
 * 订单有关请求和操作
 */

defaultOrderSetting = {
    orderid: '',
    viceid: '',  //订单编号
    warehourseid: '',
    clientid: '',
    clientname : '',
    principalid: '',
    principalname : '',
    itemid : '',
    itemname : '',
    itemnum: '',
    perprice: '',
    sumprice: '',
    ordersumprice: '',
    gather: '',
    change: '',
    margin: '',
    createtime: '',
    checktime: '',
    gathertime: '',
    returntime: '',
    postime: '',
    status: '',
    type: '',
    exception: '',
    note: ''
};

function sendOrderJsonAjax(url, param) {
    var tempdata;
    $.ajax({
        url: url,
        data: param,
        type: "post",
        dataType: "JSON",
        contentType: "application/json;charset=UTF-8",
        success: function (data) {
            if (data != null) {
            	console.log("Receive JSON order : ", data);
                tempdata = data;
            }
            console.log("Order Json : ", data);
        },
        error: function () {
        	console.log("Receive JSON order failed");
        }
    });
    return tempdata;
}

//查询订单,结果为list
function queryOrder(order) {
    if (order == null) {
        return;
    }
    param = buildOrderParam(order);
    url = "/order/query";
    console.log("QueryOrder : " + param);
    $.ajaxSettings.async = false;
    return sendOrderJsonAjax(url, param);
}

/**
 * 插入新建订单
 * @param {List} order 列表类型对象,每一个对象都是一个子订单
 */
function insertOrder(orderL) {
    if (orderL == null) {
        return;
    }
    //param = buildParamList(order);
    jsonList = [];
    for (var i = 0 ; i < orderL.length; i++) {
        combineOrder = $.extend({}, defaultOrderSetting, orderL[i]);
        param = 
        '{'
        + '"orderid":"' + combineOrder.orderid + '",'
        + '"viceid":"' + combineOrder.viceid + '",'
        + '"warehourseid":"' + combineOrder.warehourseid + '",'
        + '"warehoursename":"' + combineOrder.warehoursename + '",'
        + '"clientid":"' + combineOrder.clientid + '",'
        + '"clientname":"' + combineOrder.clientname + '",'
        + '"principalid":"' + combineOrder.principalid + '",'
        + '"principalname":"' + combineOrder.principalname + '",'
        + '"itemid":"' + combineOrder.itemid + '",'
        + '"itemname":"' + combineOrder.itemname + '",'
        + '"itemnum":"' + combineOrder.itemnum + '",'  //货品数量
        + '"perprice":"' + combineOrder.perprice + '",'
        + '"sumprice":"' + combineOrder.sumprice + '",'
        + '"ordersumprice":"' + combineOrder.ordersumprice + '",'
        + '"gather":"' + combineOrder.gather + '",'
        + '"change":"' + combineOrder.change + '",'
        + '"margin":"' + combineOrder.margin + '",'
        + '"createtime":"' + combineOrder.createtime + '",'
        + '"checktime":"' + combineOrder.checktime + '",'
        + '"gathertime":"' + combineOrder.gathertime + '",'
        + '"returntime":"' + combineOrder.returntime + '",'
        + '"postime":"' + combineOrder.postime + '",'
        + '"status":"' + combineOrder.status + '",'
        + '"type":"' + combineOrder.type + '",'
        + '"exception":"' + combineOrder.exception + '",'
        + '"note":"' + combineOrder.note + '"}';
        jsonList.push(param);
    }
    param = '[';
    for (var i = 0 ; i < jsonList.length-1; i++) {
        param += (jsonList[i] + ',');
    }
    param += (jsonList[jsonList.length-1] + ']');
    url = "/order/insert";
    console.log("InsertOrder : " + param);
    $.ajaxSettings.async = false;
    return sendOrderJsonAjax(url, param);
}

/**
 * 更新订单信息
 * @param {List} order 子订单列表 
 */
function updateOrder(order) {
    if (order == null) {
        return;
    }
    param = buildOrderParamList(order);
    console.log("UpdataOrder : " + param);
    url = "/order/update";
    $.ajaxSettings.async = false;
    return sendOrderJsonAjax(url, param);
}

//删除订单
function deleteOrder(order) {
    if (order.id == "") {
        return;
    }
    param = buildOrderParam(order);
    console.log("DeleteOrder : " + param);
    url = "/order/delete";
    $.ajaxSettings.async = false;
    return sendOrderJsonAjax(url, param);
}

//将订单审核通过
function checkOrder(order) {
    if (order.id == "") {
        return;
    }
    param = buildOrderParam(order);
    url = "/order/check";
    console.log("CheckOrder : " + param);
    $.ajaxSettings.async = false;
    return sendOrderJsonAjax(url, param);
}

//将订单付款
function payOrder(order) {
    if (order.id == "") {
        return;
    }
    param = buildOrderParam(order);
    url = "/order/pay";
    console.log("PayOrder : " + param);
    $.ajaxSettings.async = false;
    return sendOrderJsonAjax(url, param);
}

//将订单退货
function returnOrder(order) {
    if (order.viceid == "") {
        return;
    }
    param = buildOrderParam(order);
    url = "/order/return";
    console.log("ReturnOrder : " + param);
    $.ajaxSettings.async = false;
    return sendOrderJsonAjax(url, param);
}

function getSaleAmount(order) {
    param = buildOrderParam(order);
    url = "";
    console.log("GetSaleAmount : ", param);
    $.ajaxSettings.async = false;
    return sendOrderJsonAjax(url, param);
}

function getProfitAmount(order) {
    param = buildOrderParam(order);
    url = "";
    console.log("GetProfitAmount : ", param);
    $.ajaxSettings.async = false;
    return sendOrderJsonAjax(url, param);
}

function buildOrderParam(order) {
    combineOrder = $.extend({}, defaultOrderSetting, order);
    param =
        '{'
        + '"orderid":"' + combineOrder.orderid + '",'
        + '"viceid":"' + combineOrder.viceid + '",'
        + '"warehourseid":"' + combineOrder.warehourseid + '",'
        + '"clientid":"' + combineOrder.clientid + '",'
        + '"principalid":"' + combineOrder.principalid + '",'
        + '"itemid":"' + combineOrder.itemid + '",'
        + '"itemname":"' + combineOrder.itemname + '",'
        + '"itemnum":"' + combineOrder.itemnum + '",'  //货品数量
        + '"perprice":"' + combineOrder.perprice + '",'
        + '"sumprice":"' + combineOrder.sumprice + '",'
        + '"ordersumprice":"' + combineOrder.ordersumprice + '",'
        + '"gather":"' + combineOrder.gather + '",'
        + '"change":"' + combineOrder.change + '",'
        + '"margin":"' + combineOrder.margin + '",'
        + '"createtime":"' + combineOrder.createtime + '",'
        + '"checktime":"' + combineOrder.checktime + '",'
        + '"gathertime":"' + combineOrder.gathertime + '",'
        + '"returntime":"' + combineOrder.returntime + '",'
        + '"postime":"' + combineOrder.postime + '",'
        + '"status":"' + combineOrder.status + '",'
        + '"type":"' + combineOrder.type + '",'
        + '"exception":"' + combineOrder.exception + '",'
        + '"note":"' + combineOrder.note + '"}';
        return param;
}

function buildOrderParamList(orderL) {
    jsonList = [];
    for (var i = 0 ; i < orderL.length; i++) {
        combineOrder = $.extend({}, defaultOrderSetting, orderL[i]);
        param = 
        '{'
        + '"orderid":"' + combineOrder.orderid + '",'
        + '"viceid":"' + combineOrder.viceid + '",'
        + '"warehourseid":"' + combineOrder.warehourseid + '",'
        + '"clientid":"' + combineOrder.clientid + '",'
        + '"principalid":"' + combineOrder.principalid + '",'
        + '"itemid":"' + combineOrder.itemid + '",'
        + '"itemname":"' + combineOrder.itemname + '",'
        + '"itemnum":"' + combineOrder.itemnum + '",'  //货品数量
        + '"perprice":"' + combineOrder.perprice + '",'
        + '"sumprice":"' + combineOrder.sumprice + '",'
        + '"ordersumprice":"' + combineOrder.ordersumprice + '",'
        + '"gather":"' + combineOrder.gather + '",'
        + '"change":"' + combineOrder.change + '",'
        + '"margin":"' + combineOrder.margin + '",'
        + '"createtime":"' + combineOrder.createtime + '",'
        + '"checktime":"' + combineOrder.checktime + '",'
        + '"gathertime":"' + combineOrder.gathertime + '",'
        + '"returntime":"' + combineOrder.returntime + '",'
        + '"postime":"' + combineOrder.postime + '",'
        + '"status":"' + combineOrder.status + '",'
        + '"type":"' + combineOrder.type + '",'
        + '"exception":"' + combineOrder.exception + '",'
        + '"note":"' + combineOrder.note + '"}';
        jsonList.push(param);
    }
    param = '[';
    for (var i = 0 ; i < jsonList.length-1; i++) {
        param += (jsonList[i] + ',');
    }
    param += (jsonList[jsonList.length-1] + ']');
    console.log("order param", param);
    return param;
}

/**
 * 将状态码转换为付款状态
 * @param {String}} status 
 * @returns 1 为已付款 0为未付款
 */
function status2paystatus(status) {
    if(status == "1" 
        || status == "2"
        || status == "4"
        || status == "7") {
            return 0;
        }
    else if (status == "5" || status == "6") {
        return 1;
    }
    return -1;
}

/**
 * 将状态码转换为审核状态
 * @param {String} status
 * @returns Int 0 为未审核，1为审核中，2为审核通过，3为退货 
 */
function status2checkstatus(status) {
    if(status == "1") {
        return 0;
    } else if(status == "2") {
        return 1;
    } else if(status == "4" || status == "5") {
        return 2;
    } else if(status == "6" || status == "7") {
        return 3;
    }
    return -1;
 }