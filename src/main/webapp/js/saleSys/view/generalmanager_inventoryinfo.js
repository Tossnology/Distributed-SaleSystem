//保存盘点列表，{cargoId : cargo object}
var checkStockMap = new Map();
var tempStockMap = new Map();
var preRep;

window.onload = function () {
    this.document.getElementById('search-warehourse-id').innerHTML = this.buildWMenuOptionHTML(); 
    this.document.getElementById('search-warehourse-id').value = "-1";
    this.preRep = this.queryWarehourseMenu();
    this.refreshStockList();
}

//加载库存列表
function loadStockList(sl) {
    var editTable = document.getElementById("inventory-tbody");
    for (var i = 0; i < sl.length; i++) {
    	tempStockMap.set(sl[i].itemid.toString(), sl[i]);
        var tr = document.createElement("tr");
        tr.setAttribute("id", sl[i].itemid);
        var td0 = document.createElement("td");
        td0.innerHTML = sl[i].itemid;
        var td1 = document.createElement("td");
        td1.innerHTML = sl[i].itemname;  //请求后台货物
        var td2 = document.createElement("td");
        td2.innerHTML = sl[i].type;
        var td3 = document.createElement("td");
        td3.innerHTML = sl[i].specification;
        var td4 = document.createElement("td");
        td4.innerHTML = preRep.get(sl[i].hourseid);
        var td5 = document.createElement("td");
        td5.innerHTML = sl[i].itemnum;
        var td6 = document.createElement("td");
        td6.innerHTML = sl[i].overstock;
        var td7 = document.createElement("td");
        checkinput = document.createElement('input');
        checkinput.className = "form-control";
        checkinput.setAttribute("placeholder", "最新盘点");
        checkinput.setAttribute("id", "check-input");
        checkinput.setAttribute("itemid", sl[i].itemid);
        checkinput.setAttribute("hourseid", sl[i].hourseid);
        td7.appendChild(checkinput);
        var td8 = document.createElement("td");
        td8.innerHTML = sl[i].time;

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

function cleanStockList() {
    document.getElementById("inventory-tbody").innerHTML = "";
}

function refreshStockList() {
    this.document.getElementById('overstock-amount').innerHTML = this.getOverStockAmount($('#search-warehourse-id').val());
    cleanStockList();
    loadStockList(queryStock({
        itemid: $('#search-cargo-id').val(),
        itemname: $('#search-cargo-name').val(),
        type: $('#search-cargo-type').val(),
        hourseid: $('#search-warehourse-id').val()  //获取仓库id
    }));
}

//搜索
$('#search-btn').click(function () {
    refreshStockList();
});

//更新盘点,（为0时删除/）
$('#update-btn').click(function () {
    if (confirm("是否更新盘点？")) {
        templ = [];
        checkStockMap.forEach(function(value, key){
            var stock = {
                hourseid: value.hourseid,
                itemid: value.itemid,
                itemname: value.itemname,
                itemnum: value.itemnum
            }
            templ.push(stock);
        });
        alert(updateStock(templ).info);
        refreshStockList();
    }
});

//触发盘点文本框
$(document).on('blur', '#check-input', function () {
    var checknum = $(this).val();
    var id = this.getAttribute("itemid");
    if (checknum == "") {
        if (checkStockMap.get(id) != null) {
            delete checkStockMap[id];
            return;
        } else {
            return;
        }
    }
    var obj = {
        hourseid: tempStockMap.get(id).hourseid, //仓库id
        itemid: id,
        itemname: tempStockMap.get(id).itemname, //货品名称
        itemnum: checknum
    };
    checkStockMap.set(id.toString(), obj);
    console.log("Add new check : " , checkStockMap.get(id));
});


