//店长管理货品信息

tempCargoList = [];

window.onload = function() {
    this.console.log("warehourseid" , getCookie("warehourseid"));
    this.console.log("principalname", getCookie("principalname"));
    this.refreshCargoList();
}

//搜索处理
$('#search-btn').click(function () {
	cleanList();
    var s_cargoname = $('#search-cargo-name').val().toString();
    var s_cargoid = $('#search-cargo-id').val().toString();
    var s_cargotype = $('#search-cargo-type').val();
    if(s_cargotype == "任意") {
        s_cargotype = '';
    }
    cargo = {
        id : s_cargoid,
        name : s_cargoname,
        type : s_cargotype,
        tablename : getCookie("warehourseid")
    }
    var queryList = queryCargo(cargo);
    tempCargoList = queryList;
    loadCargoList(queryList);
});

//添加货品
$('#add-btn').click(function() {
//    $('#cargoModal').modal('show');
	 alert("无权限");
	 return;
});

//保存货品
$('#save-btn').click(function() {
    //添加新货品
    if ($('#cargo-id').val() == "") {
        newCargo = {
            name : $('#cargo-name').val(),
            type : $('#cargo-type').val(),
            specification : $('#cargo-format').val(),
            retailprice : $('#retail-price').val(),
            wholesaleprice :  $('#wholesale-price').val(),
            tablename : getCookie("warehourseid")
        }
        $('#cargoModal').modal('hide');
        alert(insertCargo(newCargo).info);
    } 
    //编辑货品
    else {
        newCargo = {
            id : $('#cargo-id').val(),
            name : $('#cargo-name').val(),
            type : $('#cargo-type').val(),
            specification : $('#cargo-format').val(),
            purchaseprice : $('#purchase-price').val(),
            retailprice : $('#retail-price').val(),
            wholesaleprice :  $('#wholesale-price').val(),
            tablename : getCookie("warehourseid")
        }
        $('#cargoModal').modal('hide');
        alert(updateCargo(newCargo).info);
    }
    refreshCargoList();
});


//编辑货品填充信息
$(document).on('click', '#edit-btn', function() {
	$('#cargoModal').modal('show'); //show modal
    var cargoid = $(this).val();
    var cargo;
    for (var i = 0; i < tempCargoList.length; i++) {
        if (tempCargoList[i].id == cargoid) {
            cargo = tempCargoList[i];
            break;
        }
    }
    //填充信息
    $('#cargo-id').val(cargo.id);
    $('#cargo-name').val(cargo.name);
    $('#cargo-type').val(cargo.type);
    $('#cargo-format').val(cargo.specification);
    $('#retail-price').val(cargo.retailprice);
    $('#purchase-price').val(cargo.purchaseprice);
    $('#wholesale-price').val(cargo.wholesaleprice);
});

//删除货品
$(document).on('click', '#delete-btn', function () {
    var r = confirm("是否删除？");
    if (r == true) {
        //实现
        alert(deleteCargo({
            id : $(this).val(),
            tablename : getCookie("warehourseid")}).info);
        refreshCargoList();
    }
});

function cleanList() {
	var editTable = document.getElementById("cargo-tbody");
	editTable.innerHTML = "";
}

//刷新页面
function refreshCargoList() {
    cleanList();
    var s_cargoname = $('#search-cargo-name').val().toString();
    var s_cargoid = $('#search-cargo-id').val().toString();
    var s_cargotype = $('#search-cargo-type').val();
    if(s_cargotype == "任意") {
        s_cargotype = '';
    }
    cargo = {
        id : s_cargoid,
        name : s_cargoname,
        type : s_cargotype,
        tablename : getCookie("warehourseid")
    }
    var cargoList = this.queryCargo(cargo);
    console.log("refresh cargolist : ", cargoList);
    console.log("refresh cargoType : ", typeof(cargoList));
    tempCargoList = cargoList;
    loadCargoList(cargoList);
}

//加载货品信息
function loadCargoList(cargoList) {
    var editTable = document.getElementById("cargo-tbody");
    for (var i = 0; i < cargoList.length; i++) {
        //增加表格
        var tr = document.createElement("tr");
        tr.setAttribute("id", cargoList[i].id);
        var td0 = document.createElement("td");
        td0.innerHTML = cargoList[i].id;
        var td1 = document.createElement("td");
        td1.innerHTML = cargoList[i].name;
        var td2 = document.createElement("td");
        td2.innerHTML = cargoList[i].type;
        var td3 = document.createElement("td");
        td3.innerHTML = cargoList[i].purchaseprice;
        var td4 = document.createElement("td");
        td4.innerHTML = cargoList[i].retailprice;
        var td5 = document.createElement("td");
        td5.innerHTML = cargoList[i].wholesaleprice;
        var td6 = document.createElement("td");
        td6.innerHTML = cargoList[i].specification;
        var td7 = document.createElement("td");  //update time
        td7.innerHTML = cargoList[i].time;
        var td8 = document.createElement("td");
        var editButton = document.createElement("button");
        editButton.type = "button";
        //editButton.id = "edit-btn";
        editButton.setAttribute("id", "edit-btn");
        editButton.setAttribute("value", cargoList[i].id); //将货品id封装在value中
        editButton.className = "btn btn-sm btn-primary";
        editButton.innerHTML = "编辑";
        td8.appendChild(editButton);

        var deleButton = document.createElement("button");
        deleButton.type = "button";
        deleButton.id = "delete-btn";
        deleButton.setAttribute("value", cargoList[i].id); //将货品id封装在value中
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
