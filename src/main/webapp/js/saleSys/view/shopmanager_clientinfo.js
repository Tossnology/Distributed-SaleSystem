var tempClientMap = new Map();
var tempVip = 
window.onload = function () {
    this.tempVip = this.queryRatioMenu();
    this.refreshClientList();
}

//搜索
$('#search-btn').click(function () {
    //判断
    //搜索
    cleanList();
    stype = $('#search-type').val();
    if(stype == "任意") {
    	stype = '';
    }
    client = {
        id: $('#search-client-id').val(),
        name: $('#search-client-name').val(),
        phone: $('#search-phone').val(),
        email: $('#search-email').val(),
        type: stype
    }
    var cl = queryClient(client);
    console.log("aaa", cl);
    for (var i = 0; i < cl.length; i++) {
        tempClientMap.set(cl[i].id.toString(), cl[i]);
    }
    loadClientList(cl);
});

//添加
$(document).on('click', '#add-btn', function() {
	console.log("client add-btn");
	cleanModal();
    $('#clientModal').modal('show'); //show modal
    document.getElementsByClassName('modal-title')[0].innerHTML = "用户添加";
});

$(document).on('click', '#recharge-btn', function(){
    $('#rechargeModal').modal('show');
    var client = tempClientMap.get($(this).val());
    $('#rclient-id').val(client.id);
    $('#rclient-name').val(client.name);
    $('#rclient-gender').val(client.gender);
    $('#rclient-phone').val(client.phone);
    $('#rclient-email').val(client.email);
    $('#client-deposit').val(client.remain);
    $('#client-recharge').val("");
});

//编辑填充信息
$(document).on('click', '#edit-btn', function() {
    $('#clientModal').modal('show'); //show modal
    $('#modal-title').innerHTML = "用户编辑";
    var client = tempClientMap.get($(this).val());
    $('#client-id').val(client.id);
    $('#client-name').val(client.name);
    $('#client-gender').val(client.gender);
    $('#client-phone').val(client.phone);
    $('#client-email').val(client.email);
    $('#client-type').val(client.type);
    $('#client-label').val(client.note);
});


//保存模态框内容
$(document).on('click', '#save-btn', function() {
    client = {
        id : $('#client-id').val(),
        name : $('#client-name').val(),
        gender : $('#client-gender').val(),
        phone : $('#client-phone').val(),
        email : $('#client-email').val(),
        type : $('#client-type').val(),
        note : $('#client-label').val()
    }
    $('#clientModal').modal('hide');
    if($('#client-id').val() == "") {
        alert(insertClient(client).info);
    } else {
        alert(updateClient(client).info);
    }
    refreshClientList();
});

$('#recharge-save-btn').click(function() {
    var client = tempClientMap.get($('#rclient-id').val());
    var recharge = parseFloat($('#client-recharge').val());
    var remain = parseFloat(client.remain);
    var debt = parseFloat(client.debt);
    if (debt > 0 && remain + recharge - debt < 0) {
        debt = remain + recharge - debt;
        remain = 0;
    } else if(debt > 0 && remain + recharge - debt > 0){
        remain = remain - (debt - recharge);
    	debt = 0;
    } else if(debt == 0) {
        remain = remain + recharge;
    }
    client = {
        id : $('#rclient-id').val(),
        debt : debt,
        remain : remain
    }
    $('#rechargeModal').modal('hide');
    alert(updateClient(client).info);
    refreshClientList();
});

//删除
$(document).on('click', '#delete-btn' , function() {
    var r = confirm("是否删除？");
    if (r == true) {
        //实现
        deleteClient({id : $(this).val()});
        alert("删除成功");
        refreshClientList();
    }
});

function cleanList() {
	var editTable = document.getElementById("client-tbody");
	editTable.innerHTML = "";
}

function cleanModal() {
	 $('#modal-title').innerHTML = "用户编辑";
	 $('#client-id').val('');
	    $('#client-name').val('');
	    $('#client-gender').val('');
	    $('#client-phone').val('');
	    $('#client-email').val('');
	    $('#client-type').val('');
	    $('#client-label').val('');
}

//刷新页面
function refreshClientList() {
    cleanList();
    cleanModal();
    stype = $('#search-type').val();
    if(stype == "任意") {
    	stype = '';
    }
    client = {
        id: $('#search-client-id').val(),
        name: $('#search-client-name').val(),
        phone: $('#search-phone').val(),
        email: $('#search-email').val(),
        type: stype
    }
    var clientList = this.queryClient(client);
    console.log("refresh clientlist : ", clientList);
    console.log("refresh clientType : ", typeof(clientList));
    for (var i = 0; i < clientList.length; i++) {
        this.tempClientMap.set(clientList[i].id.toString(), clientList[i]);
    }
    loadClientList(clientList);
}

//加载列表
function loadClientList(cl) {
    var editTable = document.getElementById("client-tbody");
    for (var i = 0; i < cl.length; i++) {
        var tr = document.createElement("tr");
        tr.setAttribute("id", cl[i].id);
        var td0 = document.createElement("td");
        td0.innerHTML = cl[i].id;
        var td1 = document.createElement("td");
        td1.innerHTML = cl[i].name;
        var td2 = document.createElement("td");
        td2.innerHTML = cl[i].gender;
        var td3 = document.createElement("td");
        td3.innerHTML = cl[i].phone;
        var td4 = document.createElement("td");
        td4.innerHTML = cl[i].email;
        var td5 = document.createElement("td");
        td5.innerHTML = cl[i].type;
        var td6 = document.createElement("td");
        td6.innerHTML = cl[i].remain;
        td6.setAttribute("style","color: green;")
        var td7 = document.createElement("td");
        td7.innerHTML = cl[i].debt;
        td7.setAttribute("style","color:firebrick;")
        var td8 = document.createElement("td");
        var editButton = document.createElement("button");
        editButton.type = "button";
        editButton.id = "edit-btn";
        editButton.setAttribute("value", cl[i].id); //将货品id封装在value中
        editButton.className = "btn btn-sm btn-primary";
        editButton.innerHTML = "编辑";
        td8.appendChild(editButton);
        var rechargeButton = document.createElement("button");
        rechargeButton.type = "button";
        rechargeButton.id = "recharge-btn";
        rechargeButton.setAttribute("value", cl[i].id); //将货品id封装在value中
        rechargeButton.className = "btn btn-sm btn-primary";
        rechargeButton.innerHTML = "充值";
        td8.appendChild(rechargeButton);
        var deleButton = document.createElement("button");
        deleButton.type = "button";
        deleButton.id = "delete-btn";
        deleButton.setAttribute("value", cl[i].id); //将货品id封装在value中
        deleButton.className = "btn btn-sm btn-danger";
        deleButton.innerHTML = "删除";
        td8.appendChild(deleButton);

        tr.appendChild(td0);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        editTable.appendChild(tr);
    }
}

//清除模态框内容
$('body').on('hidden.bs.modal', '.modal', function () {
    document.getElementById('client-form').reset();
    document.getElementById('recharge-form').reset();
});