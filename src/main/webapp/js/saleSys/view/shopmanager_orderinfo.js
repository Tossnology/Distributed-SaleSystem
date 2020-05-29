//缓存映射，每加载一个list，将里面order对象转为map存入
//规定，新建订单将以key “temp” 表示
var tempOrderMap = new Map();
var tempCargoMap = new Map();
var preCargoId;
var tempCargo;
var tempStatus

window.onload = function () {
	 $('#order-type').val("2");
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
        //草稿
        //添加审核按钮,编辑按钮,删除按钮
        if (ol[i].status == "1") {
            var editButton = document.createElement("button");
            editButton.type = "button";
            editButton.id = "check-btn";
            editButton.setAttribute("value", ol[i].id); //将货品id封装在value中
            editButton.className = "btn btn-sm btn-primary";
            editButton.innerHTML = "审核";
            td10.appendChild(editButton);

            var applyButton = document.createElement("button");
            applyButton.type = "button";
            applyButton.id = "edit-btn";
            applyButton.setAttribute("value", ol[i].id); //将货品id封装在value中
            applyButton.className = "btn btn-sm btn-primary";
            applyButton.innerHTML = "编辑";
            td10.appendChild(applyButton);

            var deleButton = document.createElement("button");
            deleButton.type = "button";
            deleButton.id = "delete-btn";
            deleButton.setAttribute("value", ol[i].id); //将货品id封装在value中
            deleButton.className = "btn btn-sm btn-danger";
            deleButton.innerHTML = "删除";
            td10.appendChild(deleButton);
        }
        //审核中
        //添加详情按钮
        else if (ol[i].status == "2") {
            var editButton = document.createElement("button");
            editButton.type = "button";
            editButton.id = "detail-btn";
            editButton.setAttribute("value", ol[i].id); //将货品id封装在value中
            editButton.className = "btn btn-sm btn-primary";
            editButton.innerHTML = "详情";
            td10.appendChild(editButton);
        }
        //已审核，未付款
        //添加详情按钮，付款按钮，退货按钮
        else if (ol[i].status == "4") {
            var editButton = document.createElement("button");
            editButton.type = "button";
            editButton.id = "detail-btn";
            editButton.setAttribute("value", ol[i].id); //将货品id封装在value中
            editButton.className = "btn btn-sm btn-primary";
            editButton.innerHTML = "详情";
            td10.appendChild(editButton);

            var applyButton = document.createElement("button");
            applyButton.type = "button";
            applyButton.id = "pay-btn";
            applyButton.setAttribute("value", ol[i].id); //将货品id封装在value中
            applyButton.className = "btn btn-sm btn-primary";
            applyButton.innerHTML = "付款";
            td10.appendChild(applyButton);

            var deleButton = document.createElement("button");
            deleButton.type = "button";
            deleButton.id = "return-btn";
            deleButton.setAttribute("value", ol[i].id); //将货品id封装在value中
            deleButton.className = "btn btn-sm btn-danger";
            deleButton.innerHTML = "退货";
            td10.appendChild(deleButton);
        }
        //已审核，已付款
        //添加详情按钮，退货按钮
        else if (ol[i].status == "5") {
            var editButton = document.createElement("button");
            editButton.type = "button";
            editButton.id = "detail-btn";
            editButton.setAttribute("value", ol[i].id); //将货品id封装在value中
            editButton.className = "btn btn-sm btn-primary";
            editButton.innerHTML = "详情";
            td10.appendChild(editButton);

            var deleButton = document.createElement("button");
            deleButton.type = "button";
            deleButton.id = "return-btn";
            deleButton.setAttribute("value", ol[i].id); //将货品id封装在value中
            deleButton.className = "btn btn-sm btn-danger";
            deleButton.innerHTML = "退货";
            td10.appendChild(deleButton);
        }
        //已退货
        //添加详情按钮
        else if (ol[i].status == "6" || ol[o].status == "7") {
            var editButton = document.createElement("button");
            editButton.type = "button";
            editButton.id = "detail-btn";
            editButton.setAttribute("value", ol[i].id); //将货品id封装在value中
            editButton.className = "btn btn-sm btn-primary";
            editButton.innerHTML = "详情";
            td10.appendChild(editButton);
        }
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
    tempStatus = status;
    $('#order-id').val(order.id);
    $('#client-id').val(order.clientid);
    $('#client-name').val(order.clientname);
    $('#order-type').val(order.type);
    $('#order-position').val(order.warehoursename);
    $('#principal-name').val(order.principalname);

    if (status != "1") {
        $('.temp-add-btn')[0].setAttribute("style", "display:none;");
        $('.modal-footer')[0].setAttribute("style", "display:none;")
        //$('.save-btn')[0].setAttribute("style","display:;")
    } else {
        $('.temp-add-btn')[0].setAttribute("style", "display:block;");
        $('.modal-footer')[0].setAttribute("style", "display:block;")
        //$('.save-btn')[0].setAttribute("style","display:none;")
    }
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
    for (var key in items) {
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
        var td5 = document.createElement("td");
        var deleButton = document.createElement("button");
        deleButton.type = "button";
        deleButton.id = "temp-delete-btn";
        deleButton.setAttribute("value", items[key].itemid); //将货品id封装在value中
        deleButton.className = "btn btn-sm btn-danger";
        deleButton.innerHTML = "删除";
        td5.appendChild(deleButton);
        tr.appendChild(td0);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        if (tempStatus == "1") {
            tr.appendChild(td5);
        }
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
        sstatus.push("1");
        sstatus.push("2")
        sstatus.push("4");
        sstatus.push("7")
    } else if (checks == "任意" && pays == "已付款") {
        sstatus.push("5");
        sstatus.push("6");
    }
    else if (checks == "未审核") {
        sstatus.push("1");
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
    if (sstatus[0] == "") {
        queryList = queryOrder({
            orderid: $('#search-order-id').val(),
            clientid: $('#search-client-id').val(),
            warehourseid: getCookie("warehourseid"),
            warehoursename: getCookie("warehoursename"),
            principalid: $("#search-principal-id").val(),
            type: $('#search-cargo-type').val(),
            status: ''
        });
    } else {
        for (var i = 0; i < sstatus.length; i++) {
            var t = queryOrder({
                orderid: $('#search-order-id').val(),
                clientid: $('#search-client-id').val(),
                warehourseid: getCookie("warehourseid"),
                warehoursename: getCookie("warehoursename"),
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
    console.log("wwe", saleAmount);
    console.log("eklj", profitAmount);
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
    tempStatus = "1";
});


//审核订单
$(document).on('click', '#check-btn', function () {
    var r = confirm("是否审核通过？");
    if (r == true) {
        var id = $(this).val();
        alert(reply = checkOrder({
            orderid: id,
            warehourseid: getCookie("warehourseid"),
            principalid: getCookie("principalid"),
            principalname: getCookie("principalname")
        }).info);
        refreshOrderList();
    }
});

//订单付款
//Map<orderid : List<Map<key:value>>>
$('#ac-pay-btn').click(function () {
    var sgather = $('#pay-actual-charge').val();
    var schange = $('#pay-change').val();
    order = {
        orderid: $('#pay-order-id').val(),
        warehourseid: getCookie("warehourseid"),
        warehoursename: getCookie("warehoursename"),
        principalid: getCookie("principalid"),
        principalname: getCookie("principalname"),
        change: schange,
        gather: sgather
    }
    $('#orderPayModal').modal('hide');
    alert(payOrder(order).info);
    refreshOrderList();
});

//实时更新找回价格
$('#pay-actual-charge').blur(function () {
    var actual = parseFloat($('#pay-actual-charge').val());
    var total = parseFloat($('#pay-total-price').val());
    if (actual < total) {
        $('#pay-change').val("实付不足");
        return;
    }
    $('#pay-change').val(actual - total);
});

//订单删除
$(document).on('click', '#delete-btn', function () {
    var r = confirm("是否删除？");
    if (r == true) {
        var orderid = $(this).val();
        alert(deleteOrder({
            orderid: orderid,
            warehourseid: getCookie("warehourseid")
        }).info);
        refreshOrderList();
    }
});

//货品删除
$(document).on('click', '#temp-delete-btn', function () {
    var itemid = $(this).val();
    tempCargoMap.delete(itemid);
    var cl;
    tempCargoMap.forEach(function(value, key){
    	cl.push(value);
    })
    loadModalTable(cl);
});


//订单退货
$('#ack-return-btn').click(function () {
    console.log("qqq", $('#return-note').val());
    alert(returnOrder({
        orderid: $('#return-order-id').val(),
        warehourseid: getCookie("warehourseid"),
        principalid: getCookie("principalid"),
        //        note : $('#return-note').val(),
        note: "testnote",
        exception: ""
    }).info);
    $('#saleReturnModal').modal('hide');
    refreshOrderList();
});

//弹出订单详情
$(document).on('click', '#detail-btn', function () {
    $('#orderModifyModal').modal('show');
    var orderid = $(this).val();
    loadMadal(tempOrderMap.get(orderid));
});

//弹出编辑订单
$(document).on('click', '#edit-btn', function () {
    $('#orderModifyModal').modal('show');
    var orderid = $(this).val();
    loadMadal(tempOrderMap.get(orderid));
});

//弹出订单付款
$(document).on('click', '#pay-btn', function () {
    $('#orderPayModal').modal('show');
    var orderid = $(this).val();
    var order = tempOrderMap.get(orderid);
    console.log("zzzzz : ", order);
    $('#pay-order-id').val(orderid);
    $('#pay-client-name').val(order[0].get("clientname"));
    $('#pay-pricinpal-name').val(order[0].get("principalname"));
    $('#pay-total-price').val(order[0].get("sumprice"));
    var editTable = document.getElementById("temp-pay-cargo-tbody");
    editTable.innerHTML = "";
    for (var i = 0; i < order.length; i++) {
        var tr = document.createElement("tr");
        tr.setAttribute("id", order[i].get("id"));
        var td0 = document.createElement("td");
        td0.innerHTML = order[i].get("itemname");
        var td1 = document.createElement("td");
        td1.innerHTML = order[i].get("itemid");
        var td2 = document.createElement("td");
        td2.innerHTML = order[i].get("itemnum");
        var td3 = document.createElement("td");
        td3.innerHTML = order[i].get("perprice");
        var td4 = document.createElement("td");
        td4.innerHTML = order[i].get("sumprice");
        tr.appendChild(td0);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        editTable.appendChild(tr);
    }
});

//弹出订单退货
$(document).on('click', '#return-btn', function () {
    var orderid = $(this).val();
    $('#saleReturnModal').modal('show');
    $('#return-order-id').val(tempOrderMap.get(orderid)[0].get("orderid"));
    $('#return-client-name').val(tempOrderMap.get(orderid)[0].get("clientname"));
    $('#return-pricipal-name').val(tempOrderMap.get(orderid)[0].get("principalname"));
    //判断是否付款
    if (tempOrderMap.get(orderid)[0].get("status") == '3') {
        $('#payed-state').val("0");
    } else if (tempOrderMap.get(orderid)[0].get("status") == '4') {
        $('#payed-state').val("1");
    }
    $('#return-total-price').val(tempOrderMap.get(orderid)[0].get("totalprice"));
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
    tempCargo = item;
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

//保存订单货品修改
$('#temp-add-btn').click(function () {
    var orderid = $('#order-id').val();
    var cargoid = $('#cargo-id').val();
    var ol;
    //暂存列表里有对应货品
    if(tempCargoMap.has(cargoid)) {
        if(cargoid == preCargoId) {
            tempCargoMap.get(cargoid).itemnum = $('#cargo-num').val();
            tempCargoMap.get(cargoid).perprice = $('#cargo-perprice').val();
            tempCargoMap.get(cargoid).sumprice = $('#cargo-total-price').val();
            
        } else {
            tempCargoMap.get(cargoid).itemnum = $('#cargo-num').val();
            tempCargoMap.get(cargoid).perprice = $('#cargo-perprice').val();
            tempCargoMap.get(cargoid).sumprice = $('#cargo-total-price').val();
            tempCargoMap.delete(preCargoId);
        }
    } 
    //不存在对应货品 
    else {
        tempCargoMap.set(cargoid, {
            itemid: cargoid,
            itemname: tempCargo.name,
            itemnum: $('#cargo-num').val(),
            perprice: tempCargo.wholesaleprice,
            sumprice: $('#cargo-total-price').val()
            });
    }
    console.log("Modify cargo : ", tempCargoMap.get(cargoid));
    var l = [];
    tempCargoMap.forEach(function(value, key){
        l.push(value);
    })
    loadModalTable(l);
});

//保存订单
$('#save-btn').click(function () {
    if(tempCargoMap.size == 0) {
        alert("出货单货品列表不能为空");
        return;
    }
    if($('#client-id').val() == "") {
    	alert("客户不能为空");
    	return;
    }
    //计算总价
    var sump = 0;
    for (var k in tempCargoMap) {
        sump += parseFloat(tempCargoMap[k].sumprice);
    }
    var cargoObjectList = [];
    //新建订单
    tempCargoMap.forEach(function(value, key) {
    	var obj = {
                id: $('#order-id').val(),  //仓库单id
                warehourseid: getCookie("warehourseid"),
                warehoursename: getCookie("warehoursename"),                                 //填充名称
                principalid: getCookie("principalid"),
                principalname: getCookie("principalname"),
                clientid : $('#client-id').val(),
                clientname :$('#client-name').val(),
                type: 2,
                createtime: '',
                checktime: '',
                status: 1,
                ordersumprice: sump,

                itemid: value.itemid,
                itemnum: value.itemnum,
                itemname: value.itemname,
                perprice: value.perprice,
                sumprice: value.sumprice
            };
        cargoObjectList.push(obj);
    });
    $('#orderModifyModal').modal('hide');
    if ($('#order-id').val() == "") {
        alert(insertOrder(cargoObjectList).info);
    } else {
        alert(updateOrder(cargoObjectList).info);
    }
    refreshOrderList();
});

//获取模态框内点选单元格的值,更新货品信息栏
$(document).on('click', '#temp-tr', function () {
    var td = event.srcElement; // 通过event.srcElement 获取激活事件的对象 td
    console.log("行号：" + (td.parentElement.rowIndex) + "，列号：" + td.cellIndex);
    //填充订单参数
    var itemid = $(this).attr("cid");
    preCargoId = itemid;
    var order;
    if ($('#order-id').val() == "") {
        order = tempOrderMap.get("temp");
    } else {
        order = tempOrderMap.get($('#order-id').val());
    }
    for (var i = 0; i < order.length; i++) {
        if (order[i].get("itemid").toString() == itemid) {
            showCargo(order[i]);
            break;
        }
    }
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
    if(type == "1") {
        return "零售";
    } else if(type == "2") {
        return "批发";
    }
}