/**
 * 店员历史订单记录
 */

window.onload = function () {
    this.refreshHistoryOrderList();
}

var tempOrderMap = new Map();
var tempItemList = [];

//加载订单列表
function loadHistoryOrderList(orderList) {
    var editTable = document.getElementById("order-tbody");
    for (var i = 0; i < orderList.length; i++) {
        //增加表格
        var tr = document.createElement("tr");
        tr.setAttribute("id", orderList[i].id);
        var td0 = document.createElement("td");
        td0.innerHTML = orderList[i].id;
        var td1 = document.createElement("td");
        td1.innerHTML = orderList[i].type;
        var td2 = document.createElement("td");
        td2.innerHTML = orderList[i].clientname;
        var td3 = document.createElement("td");
        td3.innerHTML = orderList[i].sumprice;
        var td4 = document.createElement("td");
        td4.innerHTML = orderList[i].principalname;
        var td5 = document.createElement("td");
        td5.innerHTML = orderList[i].warehoursename;
        var td6 = document.createElement("td");
        td6.innerHTML = orderList[i].createtime;
        var td7 = document.createElement("td");
        var button = document.createElement("button");
        button.type = "button";
        button.id = "detail-btn";
        button.setAttribute("value", orderList[i].id); //将货品id封装在value中
        button.className = "btn btn-sm btn-primary";
        button.innerHTML = "详情";
        td7.appendChild(button);
        tr.appendChild(td0);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        editTable.appendChild(tr);

        tempOrderMap.set(orderList[i].id.toString(), orderList[i]);
    }
}

//POS机点击跳转
$('#pos-btn').click(function () {
    var r = confirm("是否打开POS机？");
    if (r == true) {
        window.open("PosTerminal.html")
    }
});

//搜索处理
$('#search-btn').click(function () {
    refreshHistoryOrderList();
});

//详情按钮
$(document).on('click', '#detail-btn', function () {
    $('#orderDetailModal').modal('show'); //show modal
    console.log("Click HOrder : ", $(this).attr("value")); //获取按钮value

    var order = tempOrderMap.get($(this).val());
    $('#client-name').val(order.clientname);
    $('#client-id').val(order.clientid);
    $('#cargo-sale-position').val(order.warehoursename);
    $('#owner').val(order.principalname);
    $('#gross-profit').innerHTML = order.margin;
    //填充订单货品列表
    console.log("aaa", order.items);
    var editTable = document.getElementById("temp-cargo-list");
    for (var i = 0; i < order.items.length; i++) {
        var tr = document.createElement("tr");
        tr.setAttribute("id", "temp-tr");
        var td0 = document.createElement("td");
        td0.innerHTML = order.items[i].itemname;
        var td1 = document.createElement("td");
        td1.innerHTML = order.items[i].itemid;
        var td2 = document.createElement("td");
        td2.innerHTML = order.items[i].itemnum;
        var td3 = document.createElement("td");
        td3.innerHTML = order.items[i].perprice;
        var td4 = document.createElement("td");
        td4.innerHTML = order.items[i].sumprice;
        tr.appendChild(td0);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        editTable.appendChild(tr);
    }
    tempItemList = order.items;
});

//获取模态框内点选单元格的值,更新货品信息栏
$(document).on('click', '#temp-tr', function () {
    var td = event.srcElement; // 通过event.srcElement 获取激活事件的对象 td
    console.log("行号：" + (td.parentElement.rowIndex) + "，列号：" + td.cellIndex);
    //填充订单参数
    $('#cargo-name').val(tempItemList[td.parentElement.rowIndex-1].itemname);
    $('#cargo-id').val(tempItemList[td.parentElement.rowIndex-1].itemid);
    $('#cargo-single-price').val(tempItemList[td.parentElement.rowIndex-1].perprice);
    $('#cargo-num').val(tempItemList[td.parentElement.rowIndex-1].itemnum);
    $('#cargo-total-price').val(tempItemList[td.parentElement.rowIndex-1].sumprice);
});

//清除模态框内容
$('body').on('hidden.bs.modal', '.modal', function () {
    // $(this).removeData('bs.modal');
    window.location.reload();
});

function cleanHistoryOrderList() {
    document.getElementById("temp-cargo-list").innerHTML = "";
}

function refreshHistoryOrderList() {
    cleanHistoryOrderList();
    loadHistoryOrderList(queryOrder(
        {
            viceid : $('#search-order-id').val(),
            clientid : $('#search-client-id').val(),
            warehourseid : getCookie("warehourseid"),
            warehoursename : getCookie("warehoursename"),
            principalid : getCookie("principalid"),
            principalname : getCookie("principalname")
        }
    ));
}
