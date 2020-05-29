window.onload = function () {
	this.document.getElementById('search-warehourse').innerHTML = this.buildWMenuOptionHTML();
    this.refreshCargoList();
}

var cargoMap = new Map();


function cleanList() {
	document.getElementById("cargo-tbody").innerHTML = "";
    cargoMap.clear();
}

function refreshCargoList() {
    cleanList();
    var qlist;
    var obj = {
            id: $('#search-cargo-id').val(),
            name: $('#search-cargo-name').val(),
            type: $('#search-cargo-type').val(),
            tablename : $('#search-warehourse').val()
        };
    qlist = queryCargo(obj);
    console.log("aaa", qlist);
    if (qlist == null) {
        return;
    }
    loadCargoList(qlist);
    for(var i in qlist) {
        cargoMap.set(qlist[i].id.toString(), qlist[i]);
    }
}

function loadCargoList(qlist) {
    var editTable = document.getElementById("cargo-tbody");
    editTable.innerHTML = "";
    for (var i in qlist) {
        var tr = document.createElement('tr');
        var td0 = document.createElement('td');
        td0.innerHTML = qlist[i].id;
        var td1 = document.createElement('td');
        td1.innerHTML = qlist[i].name;
        var td2 = document.createElement('td');
        td2.innerHTML = qlist[i].type;
        var td3 = document.createElement('td');
        td3.innerHTML = qlist[i].purchaseprice;
        var td4 = document.createElement('td');
        td4.innerHTML = qlist[i].retailprice;
        var td5 = document.createElement('td');
        td5.innerHTML = qlist[i].wholesaleprice;
        var td6 = document.createElement('td');
        td6.innerHTML = qlist[i].specification;
        var td7 = document.createElement('td');
        td7.innerHTML = qlist[i].time;
        var td8 = document.createElement('td');
        var editButton = document.createElement("button");
        editButton.type = "button";
        editButton.id = "edit-btn";
        editButton.setAttribute("value", qlist[i].id); //将货品id封装在value中
        editButton.className = "btn btn-sm btn-primary";
        editButton.innerHTML = "编辑";
        td8.appendChild(editButton);
        var deleButton = document.createElement("button");
        deleButton.type = "button";
        deleButton.id = "delete-btn";
        deleButton.setAttribute("value", qlist[i].id); //将货品id封装在value中
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

function loadModal(cargo) {
    $('#cargo-id').val(cargo.id);
    $('#cargo-name').val(cargo.name);
    $('#cargo-type').val(cargo.type);
    $('#cargo-format').val(cargo.specification);
    $('#purchase-price').val(cargo.purchaseprice);
    $('#retail-price').val(cargo.retailprice);
    $('#wholesale-price').val(cargo.wholesaleprice);
}


//**************************************/
$('#save-btn').click(function() {
    $('#cargoModal').modal('hide');
    if($('#cargo-id').val() == "") {
    	var obj = {
                name : $('#cargo-name').val(),
                type : $('#cargo-type').val(),
                specification : $('#cargo-format').val(),
                tablename : getCookie("warehourseid")
            };
        alert(insertCargo(obj).info);
    } else {
    	var obj = {
                id : $('#cargo-id').val(),
                name : $('#cargo-name').val(),
                type : $('#cargo-type').val(),
                specification : $('#cargo-format').val(),
                tablename : getCookie("warehourseid")
            };
        alert(updateCargo(obj).info);
    }
    refreshCargoList();
});

$('body').on('hidden.bs.modal', '.modal', function () {
    document.getElementById('cargo-form').reset();
});

$('#add-btn').click(function() {
    $('#cargoModal').modal('show');
});

$('#search-btn').click(function() {
    refreshCargoList();
});

$(document).on('click', '#edit-btn', function() {
    $('#cargoModal').modal('show');
    loadModal(cargoMap.get($(this).val()));
});

$(document).on('click', '#delete-btn', function() {
    if(confirm("是否删除该商品？") 
        && confirm("如果删除，所有商品信息记录会破坏，是否继续？")
        && confirm("库存如果存在相关商品，该商品会消失，继续？")) {
        alert(deleteCargo({
            id : $(this).val(),
            tablename : getCookie("warehourseid")
        }).info);
        refreshCargoList();
    }
});