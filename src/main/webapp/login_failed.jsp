<%@page contentType="text/html; chartset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务管理系统登录</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body style="text-align: center; margin: 0px auto; width: 450px;">
<div class="container-fluid" id="login">
		<div class="row-fluid">
			<div class="span12">
				<div class="page-header">
					<p><b style="font-size: 33px;">财务管理系统登录</b></p>
				</div>
			</div>
		</div>
		<form class="form-horizontal" role="form" style="text-align: left" name="loginAction" method="post">
			<div class="form-group" style="width:500px;">
				<label for="inputEmail3" class="col-sm-2 control-label" style="text-align:left" >用户名:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="userName" name="username"
						placeholder="请输入用户名">
				</div>
			</div>
			<div class="form-group" style="width:500px;">
				<label for="inputPassword3" class="col-sm-2 control-label" style="text-align:left">密　码:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="inputPassword3" name="password"
						placeholder="请输入密码">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10" style="text-align: center;">
					<button type="button" onclick="actions()" class="btn btn-info" style="width:130px;">登录</button>

				</div>
			</div>
		</form>
	</div>
	<button id="model_button" style="display:none;" class="btn btn-primary" data-toggle="modal" data-target=".bs-modal-lg"></button>
	<div  class="modal fade bs-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	      <div class="modal-dialog modal-lg">
	        <div class="modal-content">

	          <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	            <h4 class="modal-title" id="myLargeModalLabel">消息提示</h4>
	          </div>
	          <div class="modal-body" id = "modal-body" style="text-align:center;line-height:40px;">
	          </div>
	          <div style="text-align:center;">
	          		<button class="btn btn-info " data-dismiss="modal" aria-hidden="true" >确定</button>
	          </div>
	          <br/>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	  </div><!-- /.modal --> <!-- 模态框结束 -->
</body>
<script type="text/javascript">
	function getBodyWidth() {
		return document.documentElement.clientWidth
				|| document.body.clientWidth;
	}
	function windowSize() {
		var body = $("body").eq(0);
		var width = parseInt(body.css("width"));
		if (getBodyWidth() <= width) {
			body.css("width", getBodyWidth() + "px");
		} else {
			body.css("width", "450px");
		};
	}
	onload = function() {
		windowSize();
	};
	onresize = function() {
		windowSize();
	};
	function actions(){
		var actions = document.forms["loginAction"];
		var ids = ["userName","inputPassword3"];
		for(var i = 0 ; i < ids.length ;i++){
			if($.trim($("#"+ids[i]).val())==""){
				$("#modal-body").html($("#"+ids[i]).attr("placeholder")+"!!!");
				$("#model_button").click();
				return;
			};
			if($("#"+ids[i]).val().length>50){
				$("#modal-body").html("长度太长,请核对!!!");
				$("#model_button").click();
				return;
			};
		}

		actions.action="user/login";
		actions.submit();
	};
	window.onkeydown=function(event){
		if(event.keyCode==13){
			actions();
		}
	};
</script>

	<script type="text/javascript">
		$("#modal-body").html("登录失败!<br/>用户名或密码错误!!!");
		$("#model_button").click();
		/* 清空当前的session消息对话 */
	</script>

</html>