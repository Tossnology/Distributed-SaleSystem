var tempClientMap = new Map();

window.onload = function () {
    this.refreshClientList();
}

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
        td5.innerHTML = cl[i].type == "1"? "零售":"批发";
        var td6 = document.createElement("td");
        var editButton = document.createElement("button");
        editButton.type = "button";
        editButton.id = "edit-btn";
        editButton.setAttribute("value", cl[i].id); //将货品id封装在value中
        editButton.className = "btn btn-sm btn-primary";
        editButton.innerHTML = "编辑";
        td6.appendChild(editButton);
        var deleButton = document.createElement("button");
        deleButton.type = "button";
        deleButton.id = "delete-btn";
        deleButton.setAttribute("value", cl[i].id); //将货品id封装在value中
        deleButton.className = "btn btn-sm btn-danger";
        deleButton.innerHTML = "删除";
        td6.appendChild(deleButton);

        tr.appendChild(td0);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        editTable.appendChild(tr);
    }
}

//清除模态框内容
$('body').on('hidden.bs.modal', '.modal', function () {
    $('#client-form').reset();
});

//搜索
$('#search-btn').click(function () {
    //判断
    //搜索
    cleanList();
    client = {
        id: $('#search-client-id').val(),
        name: $('#search-client-name').val(),
        phone: $('#search-phone').val(),
        email: $('#search-email').val(),
        type: $('#search-type').val()
    }
    var cl = queryClient(client);
    for (var i = 0; i < cl.length; i++) {
        tempClientMap.set(cl[i].id.toString(), cl[i]);
    }
    loadClientList(cl);
});

//添加
$(document).on('click', '#add-btn', function() {
	cleanModal();
    $('#clientModal').modal('show'); //show modal
    $('.modal-title')[0].text("用户添加");
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
    $('#clientModal').mmodal('hide');
    if($('#client-id').val() == "") {
        alert(insertClient(client).info);
    } else {
        alert(updateClient(client).info);
    }
    refreshClientList();
});

//删除
$(document).on('click', '#delete-btn' , function() {
    if (confirm("是否删除？")) {
        //实现
        alert(deleteClient({id : $(this).val()}).info);
        refreshClientList();
    }
});



