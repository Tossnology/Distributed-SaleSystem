//库存对象及其操作
defaultStockSetting = {
    hourseid : '',
    itemid : '',
    itemname : '',
    itemnum : '',
    type : '',
    label : '',
    time : ''
}

function sendStockJsonAjax(url, param) {
    var tempdata;
    $.ajax({
        url: url,
        data: param,
        type: "post",
        dataType: "JSON",
        contentType: "application/json;charset=UTF-8",
        success: function (data) {
            if (data != null) {
            	console.log("Receive JSON stock : ", data);
                tempdata = data;
            }
        },
        error: function () {
        	console.log("Receive stock failed");
        }
    });
    return tempdata;
 }

//通过仓库id查找库存
function queryStockByWarehourseId(thourseid) {
    if(thourseid == "") {
        return;
    }
    stock = {
        hourseid : thourseid 
    }
    param = buildParam(stock);
    url = "/stock/queryById";
    console.log("QueryStockByWareId : ", param);
    return sendStockJsonAjax(url, param);
}

//通过条件过滤查找库存
function queryStock(stock) {
    if(stock == null) {
        return;
    }
    console.log("qqqqqqqqq", stock);
    url = '/stock/query'
    param = buildParam(stock);
    console.log("QueryStock : ", param);
    $.ajaxSettings.async = false;
    return sendStockJsonAjax(url, param);
}

//修改库存，更新盘点
//stock是list格式
function updateStock(stockL) {
    if(stockL == null) {
        return;
    }
    param = buildParamList(stockL);
    url = "/stock/update";
    console.log("UpdataStock : ", param);
    $.ajaxSettings.async = false;
    return sendStockJsonAjax(url, param);
}

//获取该仓库总积压金额
function getOverStockAmount(hourseid) {
    stocklist = queryStock({
    	hourseid : hourseid
    });
    var sum = 0;
    for (i in stocklist) {
        sum += parseFloat(stocklist[i].overstock);
    }
    return sum;
} 

function buildParam(stock) {
    combineStock = $.extend({}, defaultStockSetting, stock);
    param = 
        '{"hourseid":"' + combineStock.hourseid + '",'
        + '"itemid":"' + combineStock.itemid + '",'
        + '"itemname":"' + combineStock.itemname + '",'
        + '"itemnum":"' + combineStock.itemnum + '",'
        + '"type":"' + combineStock.type + '"}';
    return param;
}

function buildParamList(stockL) {
    jsonList = [];
    for(var i = 0; i < stockL.length; i++) {
        combineStock = $.extend({}, defaultStockSetting, stockL[i]);
        param = 
        '{"hourseid":"' + combineStock.hourseid + '",'
        + '"itemid":"' + combineStock.itemid + '",'
        + '"itemname":"' + combineStock.itemname + '",'
        + '"itemnum":"' + combineStock.itemnum + '",'
        + '"type":"' + combineStock.type + '"}';
        jsonList.push(param);
    }
    param = '[';
    for (var i = 0 ; i < jsonList.length-1; i++) {
        param += (jsonList[i] + ',');
    }
    param += (jsonList[jsonList.length-1] + ']');
    return param;
}