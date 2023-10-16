function updatePasswordCheck(form) {
    if (form.oldPassword.value == "") {
        alert("当前密码不能为空！");
        document.getElementById("oldPassword").focus();
        return false;
    }

    if (form.newPassword.value == "") {
        alert("新密码不能为空！");
        document.getElementById("newPassword").focus();
        return false;
    }

    if (form.newPasswordConfirm.value == "") {
        alert("确认密码不能为空！");
        document.getElementById("newPasswordConfirm").focus();
        return false;
    }

    if (form.newPassword.value != form.newPasswordConfirm.value) {
        alert("两次新密码输入不一致！");
        document.getElementById("newPassword").focus();
        return false;
    }

    if (form.newPassword.value.length < 8 || form.newPassword.value.length > 30) {
        alert("密码长度范围: [8, 30]");
        document.getElementById("newPassword").focus();
        return false;
    }


    $.ajax({
        url:"user/password/check",
        type:"post",
        data:{password:form.oldPassword.value},
        dataType:"text",
        success:function(data){
            if(data != 1){ //不能更新密码
                alert("当前密码错误！");
                $("#password_check_status").attr("value","false");
                return false;
            }
        }
    });
}

function nocn(){
    for(var i=0;i<document.getElementsByName("password")[0].value.length;i++){
        var c = document.getElementsByName("password")[0].value.substr(i,1);
        var ts = escape(c);
        if(ts.substring(0,2) == "%u"){
            document.getElementsByName("password")[0].value = "";
            alert("这里不能输入中文/全角字符");
        }
    }
}