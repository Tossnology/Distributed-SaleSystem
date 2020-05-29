var tempProviderMap = new Map();

window.onload = function () {
    this.refreshProviderList();
}

function cleanList() {
	var editTable = document.getElementById("provider-tbody");
	editTable.innerHTML = "";
}

function cleanModal() {
	 $('#modal-title').innerHTML = "供应商编辑";
	 document.getElementById('provider-form').reset();
}

//刷新页面
function refreshProviderList() {
    cleanList();
    cleanModal();
    provider = {
        id: $('#search-provider-id').val(),
        name: $('#search-provider-name').val(),
        principalname : $('#search-principalname').val(),
        account : $('#search-account').val(),
    }
    var providerList = this.queryProvider(provider);
    for (var i = 0; i < providerList.length; i++) {
        this.tempProviderMap.set(providerList[i].id.toString(), providerList[i]);
    }
    loadProviderList(providerList);
}

//加载列表
function loadProviderList(cl) {
    var editTable = document.getElementById("provider-tbody");
    for (var i = 0; i < cl.length; i++) {
        var tr = document.createElement("tr");
        tr.setAttribute("id", cl[i].id);
        var td0 = document.createElement("td");
        td0.innerHTML = cl[i].id;
        var td1 = document.createElement("td");
        td1.innerHTML = cl[i].name;
        var td2 = document.createElement("td");
        td2.innerHTML = cl[i].address;
        var td3 = document.createElement("td");
        td3.innerHTML = cl[i].principalname;
        var td4 = document.createElement("td");
        td4.innerHTML = cl[i].account;
        var td5 = document.createElement("td");
        td5.innerHTML = cl[i].time;
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
    document.getElementById('provider-form').reset();
});

//搜索
$('#search-btn').click(function () {
    refreshProviderList();
});

//添加
$(document).on('click', '#add-btn', function() {
	cleanModal();
    $('#providerModal').modal('show'); //show modal
    console.log(document.getElementsByClassName('modal-title')[0]);
    document.getElementsByClassName('modal-title')[0].innerHTML = "供应商添加";
});

//编辑填充信息
$(document).on('click', '#edit-btn', function() {
    $('#providerModal').modal('show'); //show modal
    $('#modal-title').innerHTML = "供应商编辑";
    var provider = tempProviderMap.get($(this).val());
    $('#provider-id').val(provider.id);
    $('#provider-name').val(provider.name);
    $('#provider-address').val(provider.address);
    $('#provider-principalname').val(provider.principalname);
    $('#provider-account').val(provider.account);
});


//保存模态框内容
$(document).on('click', '#save-btn', function() {
    provider = {
        id : $('#provider-id').val(),
        name : $('#provider-name').val(),
        principalname : $('#provider-principalname').val(),
        address : $('#provider-address').val(),
        account : $('#provider-account').val(),
    }
    $('#providerModal').modal('hide');
    if($('#provider-id').val() == "") {
        alert(insertProvider(provider).info);
    } else {
        alert(updateProvider(provider).info);
    }
    refreshProviderList();
});

//删除
$(document).on('click', '#delete-btn' , function() {
    if (confirm("是否删除？")) {
        //实现
        alert(deleteProvider({id : $(this).val()}).info);
        refreshProviderList();
    }
});



