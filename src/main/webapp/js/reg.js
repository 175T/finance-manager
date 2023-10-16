	function reg(form) {

		if (form.nameInfo.value == "") {
			alert("用户不能为空！");
			document.getElementById("nameInfo").focus();
			return false;
		}
		if (form.passwordInfo.value == "") {
			alert("密码不能为空！");
			document.getElementById("passwordInfo").focus();

			return false;
		}
		if (form.repassword.value == "") {
			alert("确认密码不能为空！");
			document.getElementById("repassword").focus();

			return false;
		}
		if (form.passwordInfo.value != form.repassword.value) {
			alert("两次密码输入不一致！");
			document.getElementById("repassword").focus();
			return false;
		}
		var bool = false;
		for(var i = 0 ; i < form.school.length;i++){
			if(form.school[i].checked){
				bool=true;
			}
		}
		if(!bool){
			alert("请选择一个校区!");
		}
		return bool;
	}
	function nocn(){
		for(var i=0;i<document.getElementsByName("passwordInfo")[0].value.length;i++){
			var c = document.getElementsByName("passwordInfo")[0].value.substr(i,1);
			var ts = escape(c);
			if(ts.substring(0,2) == "%u"){
				document.getElementsByName("passwordInfo")[0].value = "";
				alert("这里不能输入中文/全角字符");
			}
		}
	}
	function textCounter() {
		var a = document.getElementById("password");
		var b = document.getElementById("bb");
		if (a.value != null || a.value != " ") {
			b.value = a.value;
		}
	}
	/* 验证此帐号是否注册 */
	function verifyAjax(obj){
		$.ajax({
			url:"VerifyServlet",
			type:"post",
			data:{userName:obj.value},
			success:function(data){
				if(data==1){
					$("#userNameSpan").css("color","red");
					$("#userNameSpan").html("用户名已注册");
					$("#registerButtons").attr("disabled","disabled");
				}else{
					$("#registerButtons").removeAttr("disabled");
					$("#userNameSpan").html("");
				}
			}
		});
	}