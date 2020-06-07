//销售业绩
defaultPerformanceSetting = {
    warehourseid: '',
    warehoursename: '',
    principalid: '',
    principalname: '',
    performancedetail: '',
    clientnum: '',
    orderNum: '',
    starttime: '',
    endtime: ''
}

var serverAddr = "http://localhost:8080";

function sendPerJsonAjax(url, param) {
    var tempdata;
    $.ajax({
        url: url,
        data: param,
        type: "post",
        dataType: "JSON",
        contentType: "application/json;charset=UTF-8",
        success: function (data) {
            if (data != null) {
                console.log("PerformanceJson : ", data);
                tempdata = data;
            }
        },
        error: function () {
        }
    });
    return tempdata;
 }

function querySMPerformance(per) {
    param = buildPerformanceParam(per);
    url = serverAddr +  "/performance/staffperformance";
    console.log("QueryPerformance : ", param);
    $.ajaxSettings.async = false;
    return sendPerJsonAjax(url, param);
}

function queryGMPerformance(per) {
    param = buildPerformanceParam(per);
    url = serverAddr +  "/performance/warehourseperformance";
    console.log("QueryPerformance : ", param);
    $.ajaxSettings.async = false;
    return sendPerJsonAjax(url, param);
}

function buildPerformanceParam(per) {
    combinePer = $.extend({}, defaultPerformanceSetting, per);
    combinePer = {
        warehourseid: combinePer.warehourseid.toString(),
        warehoursename: combinePer.warehoursename.toString(),
        principalid: combinePer.principalid.toString(),
        principalname: combinePer.principalname.toString(),
        performancedetail: combinePer.performancedetail.toString(),
        clientnum: combinePer.clientnum.toString(),
        orderNum: combinePer.orderNum.toString(),
        starttime: combinePer.starttime.toString(),
        endtime: combinePer.endtime.toString()
    }
    return JSON.stringify(combinePer);
}