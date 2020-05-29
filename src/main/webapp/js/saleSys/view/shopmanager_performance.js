var tempClientMap = new Map();

window.onload = function () {
    this.refreshPerformanceList();
}

//搜索
$('#search-btn').click(function () {
    refreshPerformanceList();
});

$("#start-date input").datetimepicker({
    language: "zh-CN",
    format: 'yyyy-mm-dd'
});

$("#end-date input").datetimepicker({
    language: "zh-CN",
    format: 'yyyy-mm-dd'
});


function cleanList() {
	var editTable = document.getElementById("per-tbody");
	editTable.innerHTML = "";
}

//刷新页面
function refreshPerformanceList() {
    cleanList();
    var st = "";
    var et = "";
    console.log($('#start-date').val())
    if($('#search-start-date').val()!="") {
    	st = $('#search-start-date').val() + " 00:00:00"
    }
    if($('#search-end-date').val()!="") {
    	et = $('#search-end-date').val() + " 00:00:00";
    }
    per = {
        warehourseid: getCookie("warehourseid"),
        warehoursename: getCookie("warehoursename"),
        principalid : $('#search-staff-id').val(),
        principalname : $('#search-staff-name').val(),
        starttime : st,
        endtime : et
    }
    var perList = this.querySMPerformance(per);
    console.log("refresh clientlist : ", perList);
    console.log("refresh clientType : ", typeof(perList));
    for (var i = 0; i < perList.length; i++) {
        this.tempClientMap.set(perList[i].principalid.toString(), perList[i]);
    }
    loadClientList(perList);
}

//加载列表
function loadClientList(cl) {
    var editTable = document.getElementById("per-tbody");
    for (var i = 0; i < cl.length; i++) {
        var tr = document.createElement("tr");
        tr.setAttribute("id", cl[i].id);
        var td0 = document.createElement("td");
        td0.innerHTML = cl[i].principalid;
        var td1 = document.createElement("td");
        td1.innerHTML = cl[i].principalname;
        var td7 = document.createElement("td");
        td7.innerHTML = "店员";
        var td2 = document.createElement("td");
        td2.innerHTML = cl[i].warehoursename;
        var td3 = document.createElement("td");
        td3.innerHTML = cl[i].orderNum;
        var td4 = document.createElement("td");
        td4.innerHTML = cl[i].clientnum;
        var td5 = document.createElement("td");
        td5.innerHTML = cl[i].sumPrice;
        var td6 = document.createElement("td");
        td6.innerHTML = cl[i].performancedetail;
        var td8 = document.createElement("td");
        td8.innerHTML = cl[i].starttime;
        var td9 = document.createElement("td");
        td9.innerHTML = cl[i].endtime;

        tr.appendChild(td0);
        tr.appendChild(td1);
        tr.appendChild(td7);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td8);
        tr.appendChild(td9);
        editTable.appendChild(tr);
    }
}