//POS机操作
window.onload = function () {
    if (getCookie("principalid") == "undefined") {
        //登录
        alert("未登录,请登录后操作");
        window.location.href = "../login.html";
        return;
    }
    $("#actual-receive").val("");
}

//订单货品暂存列表
var cargoList = [];
//订单货品数量暂存列表
var cargoNum = [];
var tempcargo;
var tempclient;
var totalDiscountRatio = 10;

$("#cargo-id").blur(function () {
    var cargoId = $(this).val();
    cargo = queryCargoById(cargoId, getCookie("warehourseid"));
    if (cargo == null) {
        //提示处理
        alert("货品不存在");
        return;
    }
    tempcargo = cargo;
    document.getElementById('cargo-name').value = cargo.name;
    document.getElementById('cargo-retail-price').value = cargo.retailprice;
});

//添加按钮,点击将货品添加至订单表格中
$('#add-btn').click(function () {
    var cargo = tempcargo;
    cargoList.push(cargo);
    if (document.getElementById('cargo-num').value == "") {
        //提示处理
        alert("请设置数量");
        return;
    }
    cargoNum.push(parseInt(document.getElementById('cargo-num').value));
    var totalPrice = parseFloat(cargo.retailprice) * parseInt(document.getElementById('cargo-num').value);
    var editTable = document.getElementById("temp-cargo-list");
    var tr = document.createElement("tr");
    var td0 = document.createElement("td");
    td0.innerHTML = cargo.name;
    var td1 = document.createElement("td");
    td1.innerHTML = cargo.id;
    var td2 = document.createElement("td");
    td2.innerHTML = document.getElementById('cargo-num').value;
    var td3 = document.createElement("td");
    td3.innerHTML = cargo.retailprice;
    var td4 = document.createElement("td");
    td4.innerHTML = totalPrice.toString();
    tr.appendChild(td0);
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    editTable.appendChild(tr);
    updateHistoryTotalPrice(); //Update total price
});

//更新总价格
function updateHistoryTotalPrice() {
    var totalPrice = 0;
    for (var i = 0; i < cargoList.length; i++) {
        totalPrice += parseFloat(cargoList[i].retailprice) * cargoNum[i];
    }
    document.getElementById('total-price').value = totalPrice.toString();
}

//更新实收
$("#actual-receive").blur(function () {
	if($(this).val() == "") {
		$('#change').val("");
		return;
	}
    var totalPrice = parseFloat(document.getElementById('total-price').value);
    var actualPrice = parseFloat(document.getElementById('actual-receive').value);
    var change = actualPrice - totalPrice;
    if ($('#client-deposit-pay').val() == "" && $('#client-score-pay').val() == "" && change < 0) {
        alert("实收价格不得低于总价");
        return;
    } else if($('#client-deposit-pay').val() == "" && $('#client-score-pay').val() == "" && change > 0) {
    	document.getElementById('change').value = change;
    }
});

//客户名称
$("#client-id").blur(function () {
    var client = queryClientById($("#client-id").val());
    if (client == null) {
        //提示处理
        alert("客户不存在");
        return;
    }
    tempclient = client;
    document.getElementById('client-name').value = client.name;
    if (client.authority == "-1") {
        $('#client-score').val("非会员");
    } else {
        $('#client-score').val(client.point);
    }
    $('#client-deposit').val(client.remain);
});

/**
 * 结清功能,发送订单数据,清空界面
 */
$('#submit-btn').click(function () {
    if ($('#client-id').val() == "") {
        //提示
        alert("请填写客户");
        return;
    }
    if (cargoList.length == 0) {
        alert("POS货品列表不能为空");
        return;
    }
    if ($('#client-score').val() != "非会员") {
        if ($('#client-score-pay').val() != "" && (parseFloat($('#client-score-pay').val()) > parseFloat(tempclient.point))) {
            alert("客户积分不足");
            return;
        }
    }
    if ($('#client-deposit-pay').val() != "" && (parseFloat($('#client-deposit-pay').val()) > parseFloat(tempclient.remain))) {
        alert("客户预存款不足");
        return;
    }

    if ($('#own').is(':checked')) {
        if (confirm("确定进行赊账？")) {
            //更新用户欠款
            $("#actual-receive").val("");
            $('#change').val("");
            client = {
                id : tempclient.id,
                debt : $("#total-price").val()
            }
            updateClient(client);
            if(tempclient.authority != "-1") {
            	var pricetopoint = parseFloat($('#total-price').val()) * parseFloat(tempclient.pricetopoint);
            client = {
                id : tempclient.id,
                authority : tempclient.authority,
                point : parseFloat(tempclient.point) + pricetopoint
            }
            updateMember(client);
            }
            
        }
    }
    //使用现金+预存+积分付款 
    else {
        //计算余额+积分+现金是否足够
        var pointPay = 0;
        var convertprice = 0;
        var depositPay = 0;
        var actualReceive = 0;
        var usedeposit = false;
        var usescore = false;
        var usecash = false;
        if (tempclient.authority != "-1" && $('#client-score-pay').val() != "") {
            pointPay = parseFloat($('#client-score-pay').val());
            convertprice = pointPay * parseFloat(tempclient.pointtoprice);
            usescore = true;
        }
        if ($('#client-deposit-pay').val() != "") {
            depositPay = parseFloat($('#client-deposit-pay').val());
            usedeposit = true;
        }
        if (document.getElementById('actual-receive').value != "") {
            actualReceive = parseFloat($('#actual-receive').val());
            usecash = true;
        }
        console.log("deposit", depositPay);
        console.log("point", convertprice);
        console.log("actualreceive", actualReceive);
        if (convertprice + depositPay + actualReceive < parseFloat($('#total-price').val())) {
            alert("积分和预存金额和现金不足");
            var usedeposit = false;
            var usescore = false;
            var usecash = false;
            return;
        }
        $('#change').val(convertprice + depositPay + actualReceive - parseFloat($('#total-price').val()));
        //更新预存款
        if (usedeposit) {
        	updateClient({
                id: tempclient.id,
                remain: parseFloat(tempclient.remain) - parseFloat($('#client-deposit-pay').val())
            });
        }
        //更新积分
        if(usescore) {
            var pricetopoint = parseFloat($('#total-price').val()) * parseFloat(tempclient.pricetopoint);
            var pdelta = parseFloat(tempclient.point) - pointPay + pricetopoint;
            console.log("point", parseFloat(tempclient.point));
            console.log("pointpay", pointPay);
            console.log("pricetopoi", pricetopoint);
            console.log("pd", pdelta)
            client = {
                id : tempclient.id,
                authority : tempclient.authority,
                point : pdelta
            }
            updateMember(client);
        }
    }
    //整合订单
    //订单基本信息
    var s_clientid = $('#client-id').val();
    var s_clientname = $('#client-name').val();
    var s_sumprice = $('#total-price').val();
    var s_gather = $("#actual-receive").val();
    var s_change = $('#change').val();
    var s_type = 1; //retail
    var s_status = 5;
    orderTempL = [];
    //货品信息
    for (var i = 0; i < cargoList.length; i++) {
        c = {
            clientid: s_clientid,
            clientname: s_clientname,
            warehourseid: getCookie("warehourseid"),
            warehoursename: getCookie("warehoursename"),
            principalid: getCookie("principalid"),
            principalname: getCookie("principalname"),
            ordersumprice: s_sumprice,
            gather: s_gather,
            change: s_change,
            type: s_type,
            status: s_status,
            //货品
            itemid: cargoList[i].id,
            itemname: cargoList[i].name,
            itemnum: cargoNum[i],
            perprice: cargoList[i].retailprice,
            sumprice: cargoNum[i] * parseFloat(cargoList[i].retailprice)
        }
        orderTempL.push(c);
    }
    //调用insert
    alert(insertOrder(orderTempL).info);
    clearPosTerminal();
});

$('#clear-btn').click(function () {
    clearPosTerminal();
});

$('#client-score-pay').change(function () {
    if (tempclient == null || $(this).val() == "") {
        document.getElementById('client-socre-convert').innerHTML = "";
        return;
    }
    var convertprice = parseFloat($(this).val()) * parseFloat(tempclient.pointtoprice);
    document.getElementById('client-socre-convert').innerHTML = "可兑换" + convertprice + "元";
})

$('#discount-btn').click(function() {
    $('#discountModal').modal('show');
    $('#discount-ratio').val('10');
    $('#discount-price').val(parseFloat($('#total-price').val()) * (parseFloat($('#discount-ratio').val())/10));
})

$('#discount-ratio').blur(function() {
    $('#discount-price').val(parseFloat($('#total-price').val()) * (parseFloat($('#discount-ratio').val())/10));
});

$('#discount-save-btn').click(function() {
    if(parseFloat($('#discount-ratio').val()) > 10 || parseFloat($('#discount-ratio').val()) < 0) {
        alert("折扣系数应在0-10之间");
        return;
    }
    $('#discountModal').modal('hide');
    $('#total-price').val($('#discount-price').val());
})

//赊账
$('#own').change(function () {
    if ($(this).is(':checked')) {
        $('#client-socre-pay').val("");
        $('#client-deposit-pay').val("");
    }
});

function clearPosTerminal() {
    document.getElementById('client-id').value = "";
    document.getElementById('client-name').value = "";
    document.getElementById('cargo-id').value = "";
    document.getElementById('cargo-name').value = "";
    document.getElementById('cargo-retail-price').value = "";
    document.getElementById('cargo-num').value = "";
    document.getElementById('client-deposit').value = "";
    document.getElementById('client-score').value = "";
    document.getElementById('client-score-pay').value = "";
    document.getElementById('client-deposit-pay').value = "";
    document.getElementById('temp-cargo-list').innerHTML = "";
    document.getElementById('client-socre-convert').innerHTML = "";
    
    $('#total-price').val("");
    $("#actual-receive").val("");
    $('#change').val("");

    cargoList = [];
    cargoNum = [];
    tempcargo = null;
}
