/**
 * 用于首页顶部处理用户名显示和退出登录功能
 */
window.onload = function(){
    fillTopInfo();
}

function fillTopInfo() {
    //判断职位,显示不同欢迎语
    var position = getCookie("position");
    if (position == "clerk") {
        document.getElementById('greeting').innerHTML = ("欢迎店员" + getCookie('principalname'));
    } else if(position == "shop-manager") {
    	document.getElementById('greeting').innerHTML = ("欢迎店长" + getCookie('principalname'));
    } else if (position == "general-manager") {
    	document.getElementById('greeting').innerHTML = ("欢迎经理" + getCookie('principalname'));
    }
}

function logout() {
    if(confirm("确定退出登录？")) {
//        cleanCookie();
        top.location.href = "http://localhost:8080/" + "login.html";
    }
}

function profile() {
    
}

