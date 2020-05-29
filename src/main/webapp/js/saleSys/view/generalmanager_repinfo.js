window.onload = function () {
    refreshRepList();
};

var repMap = new Map();

function refreshRepList() {
    var qlist;
    if ((qlist = queryWarehourse({
        name: $('#search-rep-name').val(),
        id: $('#search-rep-id').val()
    })) == null) {
        return;
    }
    for (var i in qlist) {
        repMap.set(qlist[i].id.toString(), qlist[i]);
    }
    loadRepList(qlist);
}

function loadRepList(qlist) {
    var editTable = document.getElementById("rep-tbody");
    editTable.innerHTML = "";
    for (var i in qlist) {
        var tr = document.createElement('tr');
        var td0 = document.createElement('td');
        td0.innerHTML = qlist[i].id;
        var td1 = document.createElement('td');
        td1.innerHTML = qlist[i].name;
        var td2 = document.createElement('td');
        td2.innerHTML = qlist[i].location;
        var td3 = document.createElement('td');
        td3.innerHTML = qlist[i].time;
        var td4 = document.createElement('td');
        var editButton = document.createElement("button");
        editButton.type = "button";
        editButton.id = "edit-btn";
        editButton.setAttribute("value", qlist[i].id); //将货品id封装在value中
        editButton.className = "btn btn-sm btn-primary";
        editButton.innerHTML = "编辑";
        td4.appendChild(editButton);
        var deleButton = document.createElement("button");
        deleButton.type = "button";
        deleButton.id = "delete-btn";
        deleButton.setAttribute("value", qlist[i].id); //将货品id封装在value中
        deleButton.className = "btn btn-sm btn-danger";
        deleButton.innerHTML = "删除";
        td4.appendChild(deleButton);

        tr.appendChild(td0);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);

        editTable.appendChild(tr);
    }
}

function loadModal(rep) {
    $('#rep-id').val(rep.id);
    $('#rep-name').val(rep.name);
    $('#rep-location').val(rep.location);
}

//************************************************************/

$('body').on('hidden.bs.modal', '.modal', function () {
    document.getElementById('rep-form').reset();
});

$('#add-btn').click(function () {
    $('#repModal').modal('show');
})

$('#save-btn').click(function () {
    $('#repModal').modal('hide');
    if ($('#rep-id').val() == "") {
        alert(insertWarehourse({
            name: $('#rep-name').val(),
            location: $('#rep-location').val()
        }).info);
    } else {
        alert(updateWarehourse({
            id : $('#rep-id').val(),
        	name: $('#rep-name').val(),
            location: $('#rep-location').val()
        }).info);
    }
    refreshRepList();
})

$(document).on('click', '#edit-btn', function () {
	$('#repModal').modal('show');
    loadModal(repMap.get($(this).val()));
});

$(document).on('click', '#delete-btn', function () {
    if ((r = confirm("是否删除该仓库")) == true) {
        alert(deleteWarehourse({
            id: $(this).val()
        }).info);
        refreshRepList();
    }
});

