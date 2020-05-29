//缓存映射，每加载一个list，将里面order对象转为map存入
//规定，新建订单将以key “temp” 表示
var tempOrderMap = new Map();
var tempCargoMap = new Map();
var preCargoId;
var tempRep;

window.onload = function () {
    this.document.getElementById('search-rep').innerHTML = this.buildWMenuOptionHTML();
    tempRep = queryWarehourseMenu();
    refreshOrderList();
}

//刷新订单列表，传入请求的list对象
function loadOrderList(ol) {
    var editTable = document.getElementById("order-tbody");
    for (var i = 0; i < ol.length; i++) {
        //增加表格
        var tr = document.createElement("tr");
        tr.setAttribute("id", ol[i].id);
        var td0 = document.createElement("td");
        td0.innerHTML = ol[i].id;
        var td1 = document.createElement("td");
        td1.innerHTML = getType(ol[i].type);
        var td2 = document.createElement("td");
        td2.innerHTML = ol[i].clientname;
        var td3 = document.createElement("td");
        td3.innerHTML = ol[i].sumprice;
        var td4 = document.createElement("td");
        td4.innerHTML = getCheckStatus(ol[i]);
        var td5 = document.createElement("td");
        td5.innerHTML = getPayStatus(ol[i]);
        var td6 = document.createElement("td");
        td6.innerHTML = ol[i].margin;
        var td7 = document.createElement("td");
        td7.innerHTML = ol[i].principalname;
        var td8 = document.createElement("td");
        td8.innerHTML = ol[i].warehoursename;
        var td9 = document.createElement("td");
        td9.innerHTML = ol[i].createtime;
        var td10 = document.createElement("td");

        var editButton = document.createElement("button");
        editButton.type = "button";
        editButton.id = "detail-btn";
        editButton.setAttribute("value", ol[i].id); //将货品id封装在value中
        editButton.className = "btn btn-sm btn-primary";
        editButton.innerHTML = "详情";
        td10.appendChild(editButton);

        tr.appendChild(td0);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        tr.appendChild(td9);
        tr.appendChild(td10);
        editTable.appendChild(tr);
    }
}

//刷新模态框，传入list对象,这里对象是转化后的map
function loadMadal(order) {
    var status = order.status.toString();
    $('#status').val(status);
    $('#order-id').val(order.id);
    $('#client-id').val(order.clientid);
    $('#client-name').val(order.clientname);
    $('#order-type').val(order.type);
    $('#order-position').val(order.warehoursename);
    $('#principal-name').val(order.principalname);

    $('.temp-add-btn')[0].setAttribute("style", "display:none;");
    $('.modal-footer')[0].setAttribute("style", "display:none;")

    loadModalTable(order.items);
    //是否显示退货备注
    if (status == "6" || status == "7") {
        $('.return-note')[0].setAttribute("style", "display:block;")
        $('#order-note').val(order.note);
    } else {
        $('.return-note')[0].setAttribute("style", "display:none;")
    }
}

//刷新模态框表格
function loadModalTable(items) {
    var editTable = document.getElementById("temp-cargo-tbody");
    editTable.innerHTML = "";
    tempCargoMap.clear();
    for (var key in items) {
    	tempCargoMap.set(items[key].itemid.toString(), items[key]);
        var tr = document.createElement("tr");
        tr.setAttribute("id", "temp-tr");
        tr.setAttribute("cid", items[key].itemid);
        var td0 = document.createElement("td");
        td0.innerHTML = items[key].itemname;
        var td1 = document.createElement("td");
        td1.innerHTML = items[key].itemid;
        var td2 = document.createElement("td");
        td2.innerHTML = items[key].itemnum;
        var td3 = document.createElement("td");
        td3.innerHTML = items[key].perprice;
        var td4 = document.createElement("td");
        td4.innerHTML = items[key].sumprice;
        tr.appendChild(td0);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);

        editTable.appendChild(tr);
    }
}

//清空表格
function cleanOrderList() {
    document.getElementById("order-tbody").innerHTML = "";
    tempOrderMap.clear();
}

//刷新表格
function refreshOrderList() {
    
    cleanOrderList();
    var pays = $('#search-pay').val();
    var checks = $('#search-check-status').val();
    var sstatus = [];
    if (checks == "任意" && pays == "任意") {
        sstatus.push("");
    } else if (checks == "任意" && pays == "未付款") {
        sstatus.push("2")
        sstatus.push("4");
        sstatus.push("7")
    } else if (checks == "任意" && pays == "已付款") {
        sstatus.push("5");
        sstatus.push("6");
    } else if (checks == "审核中") {
        sstatus.push("2");
    } else if (checks == "已审核" && pays == "任意") {
        sstatus.push("4");
        sstatus.push("5");
    } else if (checks == "已审核" && pays == "未付款") {
        sstatus.push("4");
    } else if (checks == "已审核" && pays == "已付款") {
        sstatus.push("5");
    } else if (checks == "已退货" && pays == "任意") {
        sstatus.push("6");
        sstatus.push("7");
    } else if (checks == "已退货" && pays == "未付款") {
        sstatus.push("6");
    } else if (checks == "已退货" && pays == "已付款") {
        sstatus.push("7");
    }
    var queryList = [];
    console.log("sstatus : ", sstatus);
    //如果是任意状态，即包含草稿状态，请求单独接口，接收剔除草稿状态订单
    if (sstatus[0] == "") {
        queryList = queryOrder({
            orderid: $('#search-order-id').val(),
            clientid: $('#search-client-id').val(),
            warehourseid: $('#search-rep').val(),
            warehoursename: tempRep.get($('#search-rep').val()),
            principalid: $("#search-principal-id").val(),
            type: $('#search-cargo-type').val(),
            status: ''
        });
    } else {
        for (var i = 0; i < sstatus.length; i++) {
            var t = queryOrder({
                orderid: $('#search-order-id').val(),
                clientid: $('#search-client-id').val(),
                warehourseid: $('#search-rep').val(),
                warehoursename: tempRep.get($('#search-rep').val()),
                principalid: $("#search-principal-id").val(),
                principalname: '',
                type: $('#search-cargo-type').val(),
                status: sstatus[i]
            });
            for (var j = 0; j < t.length; j++) {
                if (t[j] == null) continue;
                queryList.push(t[j]);
            }
        }
    }
    console.log("Build queryList : ", queryList);
    var saleAmount = 0;
    var profitAmount = 0;
    for (var i = 0; i < queryList.length; i++) {
    	if(queryList[i].sumprice != null) {
    		saleAmount += parseFloat(queryList[i].sumprice.toString());
            profitAmount += parseFloat(queryList[i].margin.toString());
    	}
        tempOrderMap.set(queryList[i].id.toString(), queryList[i]);
    }
    this.document.getElementById('sale-amount').innerHTML = saleAmount;
    this.document.getElementById('profit-amount').innerHTML = profitAmount;
    console.log("Build temporder Map : ", tempOrderMap);
    loadOrderList(queryList);
}

//条件搜索
$('#search-btn').click(function () {
    refreshOrderList();
});

//弹出添加订单
$('#add-order-btn').click(function () {
    $('#orderModifyModal').modal('show');
    $('.return-note')[0].setAttribute("style", "display:none;");
    $('#principal-name').val(getCookie("principalname"));
    $('#order-postion').val(getCookie("warehoursename")); //获得仓库
});

//弹出订单详情
$(document).on('click', '#detail-btn', function () {
    $('#orderModifyModal').modal('show');
    var orderid = $(this).val();
    loadMadal(tempOrderMap.get(orderid));
});

$('#client-id').blur(function () {
    var clientid = $(this).val();
    var client = queryClientById(clientid);
    if (client == null) {
        alert("客户不存在");
        return;
    }
    $('#client-name').val(client.name);
    $('#client-id').val(client.id);
});

$('#cargo-id').blur(function () {
    var itemid = $(this).val();
    var item = queryCargoById(itemid, getCookie("warehourseid"));
    if (item == null) {
        alert("货品不存在");
        return;
    }
    $('#cargo-name').val(item.name);
    $('#cargo-id').val(item.id);
    $('#cargo-num').val("");
    $('#cargo-perprice').val(item.wholesaleprice);
    $('#cargo-total-price').val("");
});

$('#cargo-num').blur(function () {
    var perprice = parseFloat($('#cargo-perprice').val());
    var itemnum = parseInt($('#cargo-num').val());
    $('#cargo-total-price').val(perprice * itemnum);
});

//获取模态框内点选单元格的值,更新货品信息栏
$(document).on('click', '#temp-tr', function () {
    var td = event.srcElement; // 通过event.srcElement 获取激活事件的对象 td
    console.log("行号：" + (td.parentElement.rowIndex) + "，列号：" + td.cellIndex);
    //填充订单参数
    var itemid = this.getAttribute("cid");
    preCargoId = itemid;
    var item = tempCargoMap.get(itemid);
    $('#cargo-name').val(item.itemname);
    $('#cargo-id').val(item.itemid);
    $('#cargo-num').val(item.itemnum);
    $('#cargo-perprice').val(item.perprice);
    $('#cargo-total-price').val(item.sumprice);
});

$("#search-check-status").change(function () {
    if ($(this).val() == "未审核") {
        $('#search-pay option')[0].style.display = "none";
        $('#search-pay option')[1].style.display = "";
        $("#search-pay").val("未付款");
        $('#search-pay option')[2].style.display = "none";
    } else if ($(this).val() == "审核中") {
        $('#search-pay option')[0].style.display = "none";
        $('#search-pay option')[1].style.display = "";
        $("#search-pay").val("未付款");
        $('#search-pay option')[2].style.display = "none";
    } else if ($(this).val() == "已审核") {
        $('#search-pay option')[0].style.display = "";
        $("#search-pay").val("任意");
        $('#search-pay option')[1].style.display = "";
        $('#search-pay option')[2].style.display = "";
    } else if ($(this).val() == "已退货") {
        $('#search-pay option')[0].style.display = "";
        $("#search-pay").val("任意");
        $('#search-pay option')[1].style.display = "";
        $('#search-pay option')[2].style.display = "";
    } else if ($(this).val() == "任意") {
        $('#search-pay option')[0].style.display = "";
        $("#search-pay").val("任意");
        $('#search-pay option')[1].style.display = "";
        $('#search-pay option')[2].style.display = "";
    }
});


function showCargo(suborder) {
    $('#cargo-name').val(suborder.get("itemname"));
    $('#cargo-id').val(suborder.get("itemid"));
    $('#cargo-num').val(suborder.get("itemnum"));
    $('#cargo-perprice').val(suborder.get("perprice"));
    $('#cargo-total-price').val(suborder.get("sumprice"));
}

//清除模态框内容
$('body').on('hidden.bs.modal', '.modal', function () {
    document.getElementById('order-form').reset();
});

function getCheckStatus(order) {
    var cs = status2checkstatus(order.status.toString());
    if (cs == 0) {
        return "未审核";
    } else if (cs == 1) {
        return "审核中";
    } else if (cs == 2) {
        return "已审核";
    } else if (cs == 3) {
        return "已退货";
    }
}

function getPayStatus(order) {
    var ps = status2paystatus(order.status.toString());
    if (ps == 0) {
        return "未付款";
    } else if (ps == 1) {
        return "已付款";
    }
}

function getType(type) {
    if (type == "1") {
        return "零售";
    } else if (type == "2") {
        return "批发";
    }
}