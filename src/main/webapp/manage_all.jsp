<%@page contentType="text/html; chartset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务管理系统</title>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<!--导入外部样式-->
<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css" />
<!--导入外部样式-->
<link rel="stylesheet" type="text/css" href="css/bootstrap-combined.css" />
<!--导入外部样式-->
<link rel="stylesheet" type="text/css" href="css/futurico.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<!--导入外部js-->
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!--导入外部js-->
<script type="text/javascript" src="js/showHide.js"></script>
<!--导入外部js-->
<script type="text/javascript" src="js/Calendar3.js"></script>
<!--导入外部js-->
<script type="text/javascript" src="js/icheck.js"></script>
<script type="text/javascript" src="js/updatePass.js"></script>
<script type="text/javascript" src="js/reg.js"></script>
<link rel="stylesheet" type="text/css" href="css/component.css" />
<script type="text/javascript" src="js/addTermVerify.js"></script>
<!-- 用户选择导出Excel表选择 页面-->
<script type="text/javascript" src="js/selectPage.js" defer="defer"></script>
<style>
.textRight {
	text-align: right;
	font-size: 18px;
}
</style>
</head>
<body>
	<!-- 引入模态框 -->
	<input type="hidden" name="isHide" value="" id ="isHide">
	<input type="hidden" name="confirm_who"  id ="confirm_who">
<!--
	用途：用于模态框的弹出 query.jsp页面
-->
<!-- 模态框，此模态框主要是用于弹出提示信息的时候 使用的 -->
<button id="model_button" style="display: none; " class="btn btn-primary"
	data-toggle="modal" data-target="#insert_model"></button>
<div class="modal bs-modal-sm" onfocus="javascript:this.blur()" style="overflow: hidden;width:600px;" id="insert_model" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myLargeModalLabel">消息提示</h4>
		</div>
		<div class="modal-body" id="modal-body"
			style="text-align: center; line-height: 40px;"></div>
		<div class="modal-footer" style="text-align: center;">
			<button class="btn btn-info " data-dismiss="modal" aria-hidden="true" onclick="show()" id="isSure">确定</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal -->
<!-- 模态框结束 -->


<!-- 点击修改收支的时候 弹出的模态框 -->
<button id="update_model_button" style="display: none"
	data-toggle="modal" data-target="#update_model"></button>
<div class="modal bs-example-modal-sm" onfocus="javascript:this.blur()" style="overflow: hidden;width:600px;" id="update_model"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
	aria-hidden="true" >
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myLargeModalLabel">修改收支信息</h4>
		</div>
		<div class="modal-body" id="modal-body" style="text-align: center; line-height: 40px;">
			<div class="row-fluid">
				<div class="row-fluid">
					<div class="span12">
						<form method="post" name="updateForm" id="updateForm">
							<input type="hidden" id="id_update" name="id_update" />
							<input type="hidden" id="business_id" name="business_id" />

							<table class="table table-condensed table-bordered"
								style="text-align: center;" border="1">
								<tbody>
									<tr>
										<td class="textRight">缴费/收款人:</td>
										<td><input type="text" class="form-control"
											style="width: 155px; height: 30px;" name="name_update"
											id="name_update" placeholder="请输入缴费/收款人">
											<span style="font-size: 18px;color: red" id="name_update0"></span></td>
									</tr>
									<tr>
										<td class="textRight">班 级:</td>
										<td><input type="text" class="form-control"
											placeholder="请输入班级" style="width: 155px; height: 30px;"
											name="grade_update" id="grade_update"></td>
									</tr>
									<tr>
										<td class="textRight">项 目:</td>
										<td>
										<select style="width: 155px; height: 30px;" name="project_update" id="project_update" placeholder="请选择项目">
											<option value="">请选择</option>
											<c:forEach items="${subjectList}" varStatus="index" var="item" >
											<option value="${item.id}">${item.name}</option>
											</c:forEach>
										</select>
										<span style="font-size: 18px; color: red" id=project_update0></span>
									</tr>

									<tr>
										<td class="textRight">票 据:</td>
										<td>
										<select style="width: 155px; height: 30px;" name="receipt_update" id="receipt_update" placeholder="请选择票据">
                                            <option value="">请选择</option>
                                            <c:forEach items="${ticketTypeList}" varStatus="index" var="item" >
                                            <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
										</select>
										<span style="font-size: 18px;color: red" id="receipt_update0"></span></td>
									</tr>
									<tr>
										<td class="textRight">票据号:</td>
										<td><input type="text" class="form-control"
											style="width: 155px; height: 30px;" name="receiptNum_update"
											id="receiptNum_update" placeholder="请输入票据号">
											<span style="font-size: 18px;color: red" id="receiptNum_update0"></span></td>
									</tr>
									<tr>
										<td class="textRight">金 额:</td>
										<td><input type="text" class="form-control"
											style="width: 155px; height: 30px;" name="moneys_update"
											id="moneys_update" placeholder="请输入金额">
											<span style="font-size: 18px;color: red" id="moneys_update0"></span></td>
									</tr>
									<tr>
										<td class="textRight">收支方式:</td>
										<td><input type="text" class="form-control"
											style="width: 155px; height: 30px;" name="fashion_update"
											id="fashion_update" placeholder="请输入收支方式"></td>
									</tr>
									<tr>
										<td class="textRight">时 间:</td>
										<td><input type="text" class="form-control"
											style="width: 155px; height: 30px;" name="time_update"
											id="time_update" placeholder="请输入收支时间">
											<span style="font-size: 18px;color: red" id="time_update0"></span></td>
									</tr>
									<tr>
										<td class="textRight">备 注:</td>
										<td style="text-align: left;"><textarea rows="10"
												name="remark_update" id="remark_update" style="resize: none"
												class="form-control" placeholder="请输入备注信息"></textarea>
												<span  style="font-size: 18px;color: red"id="remark_update0"></span></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer" style="text-align: center; margin-top: 20px;">
			<button class="btn btn-info " onclick="updateFormActions()" >确定修改</button>
			<button class="btn btn-default " data-dismiss="modal" aria-hidden="true">取消修改</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal -->
<!-- 修改的模态框 end -->


<!-----------修改项目的模态框------------>
	<div class="modal " onfocus="javascript:this.blur()" style="overflow: hidden;width:600px;" id="updItemModal"
		tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
		aria-hidden="true">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">修改项目</h4>
			</div>
			<form name="update_project_form" method="post">
				<input type="hidden" value="1" name="bs" />
				<!-- 代表更新项目 -->
				<input type="hidden" name="project_update_manager"
					id="project_update_manager" />
				<input type="hidden" name="update_text_businessid"
					id="update_text_businessid" />
				<div class="modal-body">
					<p>
						<b>请填写新的项目名：</b>
					</p>
					<input type="text" class="form-control"
						style="width: 155px; height: 30px" onblur="upVerifyProject(this)"
						name="update_text_project" id="update_text_project"
						placeholder="请填写新的项目名"/> <span style="font-size: 18px;"
						id="projectTip"></span>
				</div>
				<div class="modal-footer">
					<button type="button" id="projectBtn" class="btn btn-info" onclick="updateManagerForm('update_project_form','subject/update','update_text_project',4)">确定修改</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消修改</button>
				</div>
			</form>
		</div>
	</div>


<!-----------修改票据的模态框------------>
	<div class="modal " onfocus="javascript:this.blur()" style="overflow: hidden;width:600px;" id="updateTicketTypeModal"
		tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
		aria-hidden="true">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">修改票据类型</h4>
			</div>
			<form name="update_ticket_type_form" method="post">
				<input type="hidden" name="ticket_type_id" id="ticket_type_id" />
				<div class="modal-body">
					<p>
						<b>请填写新的票据类型名称：</b>
					</p>
					<input type="text" class="form-control" style="width: 155px; height: 30px" onblur="updateTicketTypeCheck(this)"
						name="update_ticket_type_name" id="update_ticket_type_name" placeholder="请填写新的票据类型名称"/>
					<span style="font-size: 18px;" id="ticketTypeTip"></span>
				</div>
				<div class="modal-footer">
					<button type="button" id="ticketTypeUpdateSubmitButton" class="btn btn-info"
					onclick="updateManagerForm('update_ticket_type_form','ticket/type/update','update_ticket_type_name',4)">确定修改</button>

					<button type="button" class="btn btn-default" data-dismiss="modal">取消修改</button>
				</div>
			</form>
		</div>
	</div>

<!-----------修改校区的模态框------------>


<!-----------修改校区的模态框------------>

<!-----------修改票据的模态框------------>

<!--  填写修改票据的时候的模态框-->

<!--  填写修改票据的时候的模态框 end-->

<!--
	用途：用于选择导出Excel
-->
<!--  用户选择导出Excel的页面  -->
<div class="modal " id="selectExcel" style="overflow: hidden;width:600px;" id="updateModal"
		tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		aria-hidden="true">
		<div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">请选择您需要导出的表</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <input type="radio" name="pageSelect" id = "curPageSelect"  checked = "checked">&nbsp;当前页导出&nbsp;&nbsp;&nbsp;
            <input type="radio" name="pageSelect" id = "morePageSelect">&nbsp;多页导出&nbsp;&nbsp;&nbsp;
          </div>
          <div class="form-group" id = "selectPage">
            <input type="text" id = "pageText1" style="width:50px;">&nbsp;到
            <input type="text" id = "pageText2" style="width:50px;">页
            (最多只能导出5页)
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" value="${orderPage.pageIndex}" id = "selectPageSubmit">确定</button>
      </div>
    </div>
	</div>
<!--  用户选择导出Excel的页面 end -->
<!-- 提示正在上传Excel数据的模态框 -->
<div class="modal bs-modal-sm"  style="overflow: hidden;width:600px ;position:absolute;" id="importExcel" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
 <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title" >数据上传中，请耐心等待...</h4>
			<h4 class="modal-title" style="color: red">请勿进行其他操作</h4>

		</div>
		<div class="modal-body"
			style="text-align: center; line-height: 40px;" style="z-index: 2">
			<img  src="../images/addExcel.gif">
			</div>
	</div>
	<!-- /.modal-content -->
</div>

<!--
	用途：用于新增收支信息中快速新增项目、票据
-->
<!-----------新增收支信息中快速新增项目------------>
	<div class="modal " onfocus="javascript:this.blur()" style="overflow: hidden;width:600px;" id="addNewProjModal"
		tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
		aria-hidden="true">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">新增项目</h4>
			</div>
			<form name="addProjectForm2" method="post">
				<div class="modal-body">
					<p>
						<b>项目名称：</b>
					</p>
					<input type="text" class="form-control"
						onblur="verifyProject2(this)" maxlength="20"
						style="width: 155px; height: 30px; float: left; margin-bottom: -10px;"
						name="projectInput" id="projectInput2" placeholder="项目"> <!-- 隐藏域 用来判断增加按钮是否可以点击 -->
						<input type="hidden" id="projectNum2" value="0" /> <!-- 用于界面提示项目是否存在 -->
						&nbsp; <span style="font-size: 18px;" id="projectSpan2"></span>
				</div>
				<div class="modal-footer">
					<button type="button" id="newProjBtn2" class="btn btn-info"
						onclick="actionsAddProject2()">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</form>
		</div>
	</div>
<!-----------新增收支信息中快速新增项目end------------>

<!-----------新增收支信息中快速新增票据------------>
	<div class="modal " onfocus="javascript:this.blur()" style="overflow: hidden;width:600px;" id="addNewBillModal"
		tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
		aria-hidden="true">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">新增票据</h4>
			</div>
			<form name="addBillForm2" method="post">
				<div class="modal-body">
					<p>
						<b>票据名称：</b>
					</p>
					<input type="text" class="form-control"
						onblur="verifyReceipt2(this)" maxlength="20"
						style="width: 155px; height: 30px; float: left; margin-bottom: -10px;"
						name="receiptInput" id="receiptInput2" placeholder="票　据"> <!-- 隐藏域 用来判断增加按钮是否可以点击 -->
						<input type="hidden" id="receiptNums2" value="0" /> <!-- 用于界面提示用户是否存在 -->
						&nbsp; <span style="font-size: 18px;" id="receiptSpan2"></span>
				</div>
				<div class="modal-footer">
					<button type="button" id="newBillBtn2" class="btn btn-info"
						onclick="actionsAddReceipt2()">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</form>
		</div>
	</div>
<!-----------新增收支信息中快速新增票据end------------>

	<!-- 中间边框tab内容的div -->
	<div id="myTabContent" class="tab-content">
		<div class="container-fluid" style="border: 0px red solid; width: 100%;margin-top: 10px; margin-bottom: 10px;">
			<div class="row-fluid">
				<div class="span2">
					<div style="background-color: #fff;">
						<!-- 右边的树形目录 -->

<!--
	用途：用于左边的树形菜单
 -->
<!------------------------------------------------查询各项信息的控制按钮------------------------------------------------------>
<div class="accordion-group">
	<div class="accordion-heading">
		<!--查看的图片-->
		<!-- <p class="accordion-toggle"></p> -->
		<a class="accordion-toggle" style="text-decoration: none;">
			<span style="color: #0088CC">查看信息</span>
		</a>
	</div>
	<div id="query" class="accordion-body collapse in">
		<div class="accordion-inner">
			<div class="tabbable tabs-left" id="queryAll">
				<ul id="myTab" class="nav" role="tablist">
					<li  class="active">
                        <a href="#queryBudget" role="tab" onclick="queryBudgetAll();" data-toggle="pill">所有收支信息</a>
                    </li>
                    <li>
                        <a href="#queryItem" role="tab" onclick="queryItemAll()" data-toggle="pill">所有项目</a>
                    </li>
                    <li>
                        <a href="#queryTicketType" role="tab" onclick="queryTicketTypeAll()" data-toggle="pill">所有票据</a>
                    </li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!------------------------------------------------新增各项信息的控制按钮------------------------------------------------------>
<div class="accordion-group">
	<div class="accordion-heading">
		<a class="accordion-toggle" style="text-decoration: none;">
			<span style="color: #0088CC">新增</span>
		</a>
	</div>
	<div id="addAll" class="accordion-body collapse in">
		<div class="accordion-inner">
			<div class="tabbable tabs-left" id="add">
				<ul id="myTab" class="nav" role="tablist">
                    <li>
                        <a href="#addBudget" role="tab" onclick="addBudgetAll()" data-toggle="pill">收支信息</a>
                    </li>
                </ul>
			</div>
		</div>
	</div>
</div>
<!------------------------------------------------账户管理的控制按钮------------------------------------------------------>
<div class="accordion-group">
	<div class="accordion-heading">
		<a class="accordion-toggle" style="text-decoration: none;"> <span
			style="color: #0088CC">账户管理</span>
		</a>
	</div>
	<div id="account-manage" class="accordion-body collapse in">
		<div class="accordion-inner">
			<div class="tabbable tabs-left" id="account">
				<ul id="myTab" class="nav" role="tablist">
				    <c:if test="${currentUser.issuperadmin==true}">
				    <li>
                	    <a href="#user_manage" role="tab" onclick="userListAll();"  data-toggle="pill">所有用户</a>
                	</li>
                	<li>
                        <a href="#user_add" role="tab" onclick="addUserAction();"  data-toggle="pill">添加用户</a>
                    </li>
                    </c:if>
                    <li>
                        <a href="#update_password" role="tab" onclick="updatePasswordAction();"  data-toggle="pill">修改密码</a>
                    </li>
			 		<li>
			     		<a href="user/logout" onclick="logout();"  data-toggle="tab">注销当前用户</a>
			    	</li>
				 </ul>
			</div>
		</div>
	</div>

					</div>
				</div>
			</div>
			<!-- 右边的部分 -->
			<div class="span10">
				<div class="tab-content" style="overflow-x: hidden;">

					<!-- 判断是否显示********************************************************* -->


					<div class="tab-pane active"  id="queryBudget" >
						<!-- 顶部的搜索条件框 -->


<!--

//-->

<!--
	用途：用于右边的顶部搜条件框
 -->
<!------------------------------------------------查询各项信息------------------------------------------------------>
<!-------------------------查询所有收支信息---------------------------->
<div class="container navbar-left" style="border: 0px solid #c3c3c3; border-bottom: 0px;">
	<form class="navbar-form  navbar-left" name="queryFrom" method="post"
		role="search form">
		<div class="form-group" style="float: left;">
			开始时间:<br/>
			<input type="text" value="${queryParams.createTimeStart}" maxlength="20"
				style="height: 30px; width: 160px;" name="startTime" id="startTime"
				class="form-control" placeholder="开始时间"
				onfocus="new Calendar().show(this);">
		</div>
		<div class="form-group" style="float: left;">
			结束时间:<br/>
			<input type="text" value="${queryParams.createTimeEnd}" maxlength="20"
				style="height: 30px; width: 160px;" name="endTime" id="endTime"
				class="form-control" placeholder="结束时间"
				onfocus="new Calendar().show(this);">
		</div>
		<div class="form-group" style="float: left;">
			缴费/收款人:<br/>
			<input type="text" value="${queryParams.orderAccount}" maxlength="20"
				style="height: 30px; width: 160px;" name="queryName" id="queryName"
				class="form-control" placeholder="缴费/收款人">
		</div>
		<div class="form-group" style="float: left;">
			票据号:<br/>
			<input type="text" value="${queryParams.ticketNo}" maxlength="40"
				style="height: 30px; width: 160px;" name="queryReceipt"
				id="queryReceipt" class="form-control" placeholder="票据号">
		</div>
		<div class="form-group" style="float: left;">
			收支方式:<br/>
			<input type="text" value="${queryParams.payType}" maxlength="20"
				style="height: 30px; width: 160px;" name="queryFashion"
				id="queryFashion" class="form-control" placeholder="收支方式">
		</div>
		<div class="form-group">
			班 级:<br/>
			<input type="text" value="${queryParams.orderClass}" maxlength="20"
				style="height: 30px; width: 160px;" name="queryGrade"
				id="queryGrade" class="form-control" placeholder="班级">
		</div>
		<div class="form-group" style="float: left">
			备 注:<br />
			<input type="text" value="${queryParams.orderDes}" maxlength="1000"
				style="height: 30px; width: 160px;" name="queryRemark"
				id="queryRemark" class="form-control" placeholder="备注">
		</div>

		<div class="form-group" style="float: left;display:none ">
			校 区:<br /> <select style="height: 30px; width: 113px;"
				name="business" id="business" placeholder="请选择校区">
				<!-- 非管理员身份进入只显示当前校区名 -->
				<option value="">请选择</option>
				<!-- 非管理员身份进入只显示当前校区名 -->
			</select>
		</div>

		<div class="form-group" style="float: left;">项 目:<br />
		<select name="queryProject" style="height: 30px; width: 113px;" id="queryProject" placeholder="请选择项目">
            <option value="">请选择</option>
            <c:forEach items="${subjectList}" varStatus="index" var="item" >
            <option value="${item.id}" <c:if test="${item.id == queryParams.subjectId}">selected="selected"</c:if>>${item.name}</option>
            </c:forEach>
		</select>
		</div>

		<!-- 收入还是支出单选按钮 -->
		<div class="form-group" style="margin-top: 30px; float: left;">
			<input type="radio" <c:if test="${queryParams.orderPriceType == 1}">checked="checked"</c:if> name="queryMoneys" value="1" />收入
			<input type="radio" <c:if test="${queryParams.orderPriceType == 2}">checked="checked"</c:if> name="queryMoneys" value="2" />支出
			<input type="radio" <c:if test="${queryParams.orderPriceType == 0}">checked="checked"</c:if> name="queryMoneys" value="0" />不限
		</div>
		<!-- 文件上传的隐藏域 -->
		<input type="file" id="leadExcel" name="leadExcel"
			onchange="addExcelInfoServlet()" style="display: none" />
		<div class="form-group btn-group btn-group-sm"
			style="margin-left: 3%; margin-top: 2%;">
			<button type="button" onclick="queryActions()" title="根据你填的数据来进行筛选"
				class="btn btn-info">条件查询</button>
			<button type="button" title="将所有的数据查询出来" id="queryAll"
				onclick="window.location.href='order_query'" class="btn btn-info">查询全部</button>
			<button type="button" data-toggle="modal" data-target="#selectExcel"
				data-whatever="@fat" title="将下面的数据生成Excel表导出" class="btn btn-info">导出Excel数据</button>
			<!--         <button type="button" onclick="createExcelAction()" title="将下面的数据生成Excel表导出" class="btn btn-info">导出下面数据(Excel)</button> -->

			<label title="请将正确格式的Excel数据导入,数据就会正确的显示出来" for="leadExcel"
				class="btn btn-info" id="Exceladd">导入Excel数据</label>

		</div>
	</form>
</div>
<div class="row"></div>
<script type="text/javascript">

/**
 * 创建<option>元素
 */
function createOption(project) {
	/*
	1. 创建<option>元素
	2. 指定元素的文本内容(显示值)
	3. 指定元素的value属性值(实际值)
	4. 返回
	*/
	var option = document.createElement("option");
	var projects=project.split(":");
	option.value = projects[0];//指定元素的value属性值
	var textNode = document.createTextNode(projects[1]);//使用字符串创建一个文本节点
	option.appendChild(textNode);//把文本节点添加到<option>元素中

	return option;
}

/*
 * 清空ProjectSelect中所有有意义的子元素，只留“请选择”<option>
 */
function removeChildElements(projectSelect) {
	// 获取<select id="city">下所有的<option>元素
	var optionEleList = projectSelect.getElementsByTagName("option");
	// 循环次数为<option>个数-1次！
	while(optionEleList.length >1) {
		// 每次删除下标为1的元素，循环下一次时，原来下标为2就变成了下标为1的！
		projectSelect.removeChild(optionEleList[1]);
	}
	// 循环结束时，只省下下标为0的元素的，其他的都删除了。
}

var proSelect = document.getElementById("business");
	proSelect.onchange = function() {

		var url = "subject/query_biz?businessname="+this.value;
		$.ajax({
			url:url,
			type:"get",
			success:function(data){
				var projectSelect = document.getElementById("queryProject");
				//若data的返回值为1，则表示修改数据表成功
				removeChildElements(projectSelect);
				//projectSelect.length = 0 ;
				if(data.length!=0){
				var Projects=data.split(",");
				for(var i = 0; i < Projects.length; i++) {
					var option = createOption(Projects[i]);//使用每个项目的名称创建<option>元素
					projectSelect.appendChild(option);//把<option>元素添加到select元素中
				}
				}
			}
		});

	};

</script>

<!------------------------------------------------查询各项信息 end------------------------------------------------------>
<!-- 表格上的搜索标题  -->
<!--
	用途：表格上的搜索标题   序号 	时间 	缴费/收款人 	项目 	金额(汇总:4159516.68) 	票据 	票据号 	收支方式 	备注 	班级 	校区 	操作
 -->
<table class="table table-striped" style="margin-top: 10px;width: 1024px;">
	<tr>
		<td style="width: 5%;">序号</td>
		<td style="width: 7.0%;">时间</td>
		<td style="width: 9%;">缴费/收款人</td>
		<td style="width: 5%;">项目</td>
		<!-- <td>版本号</td> <td style="width: 10.0%;">总金额：<br/>(8679348.75)</td> -->
		<td style="width: 10.0%;">总金额：<br/>(${orderPage.totalPrice})</td>
		<td style="width: 8%;">票据</td>
		<td style="width: 8%;">票据号</td>
		<td style="width: 14%;">收支方式</td>
		<td style="width: 12%;">备注</td>
		<td style="width: 6%;">班级</td>
		<td style="width: 5%">校区</td>
		<td style="width: 5%;">操作</td>
	</tr>
</table>

<!-- 搜索框下面的显示内容部分 (订单列表) -->
<div style="overflow-y: scroll;height:500px;width: 1024px;margin-top:-22px;display: block;">
    <div>
        <table class="table table-bordered" style="padding-bottom:60px;">
            <tbody>

                <c:forEach items="${orderPage.orderList}" varStatus="varStatus" var="item" >
                <tr
                <c:if test="${item.orderprice < 0}">style="background-color:#c3c3c3"</c:if>
                >
                    <td style="width: 2%;">${varStatus.index+1}</td>
                    <td style="width: 6%;"><fmt:formatDate value="${item.ordertime}" pattern="yyyy-MM-dd"/></td>
                    <td style="width: 9%;">${item.orderaccount}</td>
                    <td style="width: 10%;">${item.subject.name}</td>
                    <td style="width: 8%;">${item.orderprice}</td>
                    <c:choose>
                        <c:when test="${item.orderprice >= 0}">
                    		<td style="width: 10%;">收据</td>
                    	</c:when>
                    	<c:otherwise>
                        	<td style="width: 10%;">付款申请单</td>
                        </c:otherwise>
                    </c:choose>
                    <td style="width: 8%;">${item.ticketno}</td>
                    <td style="width: 10%;">${item.paytype}</td>
                    <td style="width: 18%;">${item.orderdes}</br><b> 数据录入时间：</b></br><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td style="width: 6%;">${item.orderclass}</td>
                    <td style="width: 6%;">${item.subject.school}</td>
                    <c:choose>
                        <c:when test="${item.canUpdateOrDelete}">
                            <td style="width: 6%;">
                                <div class=" btn-group-xs">
                                    <button type="button" class="btn btn-default" style="font-size: 12px;"
                                        onclick="update('${item.id}','<fmt:formatDate value="${item.ordertime}" pattern="yyyy-MM-dd"/>','${item.orderaccount}',
                                                          '${item.subjectid}','','${item.orderprice}',
                                                          '${item.tickettypeid}','${item.ticketno}','${item.paytype}',
                                                          '${item.orderdes}','${item.orderclass}','${item.subject.school}','${item.subject.name}')">修改</button>
                                    <button type="button" class="btn btn-default"
                                        onclick="deleteIncom('${item.id}','${item.orderaccount}')"
                                        style="font-size: 12px;">删除</button>
                                </div>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td style="width: 9%;">已过有效期无法修改</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="navbar-fixed-bottom" style="background-color: #ffffff; padding-left: 30%; margin-right: 20px; margin-left: 15%;">
<!-- 完成了分页展示，所有分页功能通用页面 -->

<ul id="pageBean" class="pagination">
</ul>
<script type="text/javascript">
	var pagesCount = 5; //1.2.3.4.5按钮显示的数量,默认为10个
	var perPage = parseInt("${orderPage.pageCount}"); //总页码
	var tempPageIndex = parseInt("${orderPage.pageIndex}"); //当前的页数
	var startPage = 1; //开始下标
	var endPage = perPage > pagesCount ? pagesCount : perPage; //结束下表 最多显示5条
	if (perPage > pagesCount) { //如果总页数大于预计的页数的话,那么就执行下面的算法

		startPage = tempPageIndex - (parseInt(pagesCount / 2)) >= 0 ? tempPageIndex
				- parseInt(pagesCount / 2)
				: 1; //计算开始下表,这里除以2表示只去当前下表的一般,方便下拉表在中间
		endPage = tempPageIndex + (parseInt(pagesCount / 2)) >= perPage ? perPage
				: tempPageIndex + (parseInt(pagesCount / 2)); //这里结算结束下表，判断每次要到什么位置
		/* if(endPage-startPage<=pagesCount){
			startPage=endPage-pagesCount;
		} */
		if (startPage <= 0) {
			startPage = 1;
		}
		if (endPage <= pagesCount) {
			endPage = pagesCount;
		}
	}
	//当当前页等于1的时候，显示不能上一页
	if (tempPageIndex == 1) {
		$("#pageBean").append("<li class='disabled'><a href='#'>上一页</a></li>");
	} else { // 否则显示上一页功能
		$("#pageBean").append("<li><a href='javascript:previousPage(" + (tempPageIndex - 1) + ");'>上一页</a></li>");
	}
	//遍历开始下标到结束下标，循环添加分页按钮
	for (var i = startPage; i <= endPage; i++) {
		if (i == tempPageIndex) {
			$("#pageBean").append("<li class='active'><a href='#'>" + i + "</a></li>");
		} else {
			$("#pageBean").append("<li><a href='javascript:previousPage(" + i + ");'>" + i + "</a></li>");
		}
	}
	//当当前页码等于总页数的时候，显示不能再下一页
	if (tempPageIndex == perPage || perPage == 0) {
		$("#pageBean").append("<li class='disabled'><a href='#'>下一页</a></li>");
	} else { //否则显示下一页功能
		$("#pageBean").append("<li><a href='javascript:previousPage(" + (tempPageIndex + 1) + ");'>下一页</a></li>");
	}
	$("#pageBean").append("<li>&nbsp;&nbsp;当前页:</li><li>${orderPage.pageIndex}</li>");
	$("#pageBean").append("<li>&nbsp;&nbsp;总页数:</li><li>${orderPage.pageCount}</li><li>去第</li><li><input id='pageIndex' style='width:50px;'/></li><li>页</li><li><button id='searchPage' onclick='previousPage()'>go</button></li>");
</script>
						</div>
					</div>
					<!-- 判断是否显示********************************************************* -->
					<!-- 查询右边菜单的所有校区和票据 -->


<!--
	用途：查询右边菜单的所有校区和票据
 -->

	<!-------------------------查询所有项目信息---------------------------->
	<div class="tab-pane" id="queryItem">
		<h1 style="text-align: center; font-size: 45px;">所有项目</h1>
		<div style="overflow: auto;">
			<table class="table-bordered" style="text-align: center;">
				 <tr style="font-size: 20px; height: 20px;">
					<td ><b>序号</b></td>
					<td><b>校区</b></td>
					<td><b>项目</b></td>
					<td><b>操作</b></td>
				 </tr>

				 <c:forEach items="${subjectList}" varStatus="varStatus" var="item" >
				 <tr>
                    <td>${varStatus.index+1}</td>
                    <td>${item.school}</td>
                    <td>${item.name}</td>
                    <td>
                        <button class="btn btn-primary btn-default" type="button"  id="update_item"
                            onclick="updateProjects('${item.id}','${item.name}','${item.id}')">修改</button>
                        <button class="btn btn-default" type="button"
                            onclick="deleteProjects('${item.id}','${item.name}')">删除</button>
                    </td>
                 </tr>
				 </c:forEach>
			</table>
		</div>
	</div>

	<!-------------------------查询所有票据信息---------------------------->
	<div class="tab-pane" id="queryTicketType">
		<h1 style="text-align: center; font-size: 45px;">所有票据</h1>
		<div style="overflow: auto;">
			<table class="table-bordered" style="text-align: center;">
				 <tr style="font-size: 20px; height: 20px;">
					<td ><b>序号</b></td>
					<td><b>票据名称</b></td>
					<td><b>操作</b></td>
				 </tr>

				 <c:forEach items="${ticketTypeList}" varStatus="varStatus" var="ticketType" >
				 <tr>
                    <td>${varStatus.index+1}</td>
                    <td>${ticketType.name}</td>
                    <td>
                        <button class="btn btn-primary btn-default" type="button"  id="update_ticket_type"
                            onclick="updateTicketType('${ticketType.id}','${ticketType.name}')">修改</button>
                        <button class="btn btn-default" type="button"
                            onclick="deleteTicketType('${ticketType.id}','${ticketType.name}')">删除</button>
                    </td>
                 </tr>
				 </c:forEach>
			</table>
		</div>
	</div>

<!------------------------------------------------添加各项信息------------------------------------------------------>
<!-----------------------------添加收支-------------------------------->

<div class="tab-pane" id="addBudget">
	<form method="post" name="addForm">
		<h1 style="text-align: center; font-size: 45px;">新增收支信息</h1>
		<table class="table-bordered" style="text-align: center;" border="1">
			<tbody>
				<tr>
					<td class="textRight" style="margin-bottom: -2px;">缴费/收款人:</td>
					<td><input type="text" class="form-control" maxlength="20"
						style="width: 155px; height: 30px; margin-bottom: -2px;"
						name="name" id="name" placeholder="请输入缴费/收款人"></td>
				</tr>
				<tr>
					<td class="textRight">班 级:</td>
					<td><input type="text" class="form-control"
						placeholder="请输入班级" maxlength="20"
						style="width: 155px; height: 30px; margin-bottom: -2px;"
						name="grade" id="grade"></td>
				</tr>
				<tr>
					<td class="textRight">项 目:</td>
					<td><select style="width: 155px; height: 30px; margin-bottom: -2px;" name="project" id="project" placeholder="请选择项目">
							<option value="">请选择</option>
							<c:forEach items="${subjectList}" varStatus="index" var="item" >
							<option value="${item.id}">${item.name}</option>
							</c:forEach>
						</select>
						<!-- 若没有想要的项目，可以快速增加 -->
						<button type="button" data-toggle="modal" data-target="#addNewProjModal"
								data-whatever="@fat" title="快速新增项目" class="btn btn-info">新增项目</button>
					</td>
				</tr>
				<!-- -->
				<tr>
					<td class="textRight">票 据:</td>
					<td>
                        <select style="width: 155px; height: 30px; margin-bottom: -2px;" name="receipt" id="receipt" placeholder="请选择票据">
                            <option value="">请选择</option>
                            <c:forEach items="${ticketTypeList}" varStatus="index" var="item" >
                            <option value="${item.id}">${item.name}</option>
                            </c:forEach>
                        </select>
                        <button type="button" data-toggle="modal" data-target="#addNewBillModal" data-whatever="@fat" title="快速新增票据" class="btn btn-info">新增票据</button>
					</td>
				</tr>
				<tr>
					<td class="textRight">票据号:</td>
					<td><input type="text" class="form-control" maxlength="20"
						style="width: 155px; height: 30px; margin-bottom: -2px;"
						name="receiptNum" id="receiptNum" placeholder="请输入票据号"></td>
				</tr>
				<tr>
					<td class="textRight">金 额:</td>
					<td><input type="text" class="form-control" maxlength="11"
						style="width: 155px; height: 30px; margin-bottom: -2px;"
						name="moneys" id="moneys" placeholder="请输入金额"></td>
				</tr>
				<tr>
					<td class="textRight">收支方式:</td>
					<td><input type="text" class="form-control" maxlength="20"
						style="width: 155px; height: 30px; margin-bottom: -2px;"
						name="fashion" id="fashion" placeholder="请输入收支方式"></td>
				</tr>
				<tr>
					<td class="textRight">时 间:</td>
					<td><input type="text" class="form-control" maxlength="20"
						style="width: 155px; height: 30px; margin-bottom: -2px;"
						onfocus="new Calendar().show(this);" name="time" id="time"
						placeholder="请输入收支时间"></td>
				</tr>
				<tr>
					<td class="textRight">备 注:</td>
					<td style="text-align: left;"><textarea rows="6" name="remark"
							id="remark" style="resize: none" class="form-control"
							placeholder="请输入备注信息" maxlength="1000"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input
						type="button" onclick="actions()" class="btn btn-info" value="保　存" />
						<input type="reset" class="btn btn-default" value="重　置" /></td>
				</tr>
			</tbody>
		</table>
	</form>
	<div style="overflow: auto; margin-top: 5%;"></div>
</div>

<!-----------------------------添加用户-------------------------------->
<div class="tab-pane" id="addUserPane">
	<form method="post" name="addUserForm" action="user/add">
		<h1 style="text-align: center; font-size: 45px;">添加用户</h1>
		<table class="table-bordered" style="text-align: center;" border="1">
			<tbody>
				<tr>
					<td class="textRight" style="margin-bottom: -2px;">用户名/登录名:</td>
					<td><input type="text" class="form-control" maxlength="20"
						style="width: 155px; height: 30px; margin-bottom: -2px;"
						name="username" id="add_user_name" placeholder="请输入用户名/登录名"></td>
				</tr>
				<tr>
					<td class="textRight">备注信息:</td>
					<td style="text-align: left;">
                        <textarea rows="6" name="content" id="add_user_content" style="resize: none"
                                  class="form-control" placeholder="请输入备注信息, 字数不超过400" maxlength="400"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
                        <input type="submit" class="btn btn-info" value="保　存" />
                        <input type="reset" class="btn btn-default" value="重　置" />
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<div style="overflow: auto; margin-top: 5%;"></div>
</div>


<!-----------------------------修改密码 (真正使用的 20201114 wangqingsong)-------------------------------->
<div class="tab-pane" id="updatePasswordPane">
	<form method="post" name="updatePassword" action="user/password/update"  onsubmit="return updatePasswordCheck(this)">
	    <input type="hidden" id="password_check_status" value="true" />
		<h1 style="text-align: center; font-size: 45px;">修改密码</h1>
		<table class="table-bordered" style="text-align: center;" border="1">
			<tbody>
				<tr>
					<td class="textRight" style="margin-bottom: -2px;">当前密码:</td>
					<td><input type="password" class="form-control" maxlength="30"
						style="width: 255px; height: 30px; margin-bottom: -2px;"
						name="oldPassword" id="oldPassword" placeholder="请输入当前密码"></td>
				</tr>
				<tr>
                    <td class="textRight" style="margin-bottom: -2px;">新密码:</td>
                    <td><input type="password" class="form-control" maxlength="30"
                        style="width: 255px; height: 30px; margin-bottom: -2px;"
                        name="newPassword" id="newPassword" placeholder="请输入新密码, 长度8~30"></td>
                </tr>
                <tr>
                    <td class="textRight" style="margin-bottom: -2px;">新密码确认:</td>
                    <td><input type="password" class="form-control" maxlength="30"
                        style="width: 255px; height: 30px; margin-bottom: -2px;"
                        name="newPasswordConfirm" id="newPasswordConfirm" placeholder="请确认新密码, 长度8~30"></td>
                </tr>
				<tr>
					<td colspan="2" style="text-align: center;">
                        <input type="submit" class="btn btn-info" value="更  新" />
                        <input type="reset" class="btn btn-default" value="重　置" />
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<div style="overflow: auto; margin-top: 5%;"></div>
</div>

<!--
	用途：新增项目、校区、票据
 -->
 <!---------------------------新增项目------------------------------>
 <div class="tab-pane" id="addProject">
	<form method="post" name="addProjectForm">
		<h1 style="text-align: center; font-size: 45px;">新增(项目、票据)</h1>
		<table class="table table-condensed table-bordered">
			<tbody>
				<!-- ******************************与校区相关联***************************** -->
					<tr>
					<td class="textRight">校区:</td>
					<td>
                        <select style="width: 155px; height: 30px; margin-bottom: -2px;" name="schoolName" id="schoolName" >
                            <option value="">请选择</option>
                            <option value="f166f756cc83478db9ef966e2319d179">成都青鸟</option>
                        </select>
					</td>
				</tr>
				<!-- ******************************与校区相关联***************************** -->

				<tr>
					<td class="textRight"
						style="font-size: 20px; margin-bottom: -10px; width: 300px;">
						项 目:</td>
					<td><input type="text" class="form-control"
						onblur="verifyProject(this)" maxlength="20"
						style="width: 155px; height: 30px; float: left; margin-bottom: -10px;"
						name="projectInput" id="projectInput" placeholder="项　目"> <!-- 隐藏域 用来判断增加按钮是否可以点击 -->
						<input type="hidden" id="projectNum" value="0" /> <!-- 用于界面提示用户是否存在 -->
						&nbsp; <span style="font-size: 18px;" id="projectSpan"></span></td>
				</tr>
			    <tr>
			    <td colspan="3" style="padding-left:320px"><input
						type="button" onclick="actionsAddProject()" id="addBtnAll"
						class="btn btn-info" value="增　加" /> <input type="reset"
						onclick="res()" class="btn btn-default" value="重　置" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<!---------------------------新增校区------------------------------>
 <div class="tab-pane" id="addSchool">
	<form method="post" name="addSchoolForm">
		<h1 style="text-align: center; font-size: 45px;">新增(项目、票据)</h1>
		<table class="table table-condensed table-bordered">
			<tbody>
				<tr>
					<td class="textRight"
						style="font-size: 20px; margin-bottom: -10px; width: 300px;">
						校区:</td>
					<td><input type="text" class="form-control"
						onblur="verifySchool(this)" maxlength="20"
						style="width: 155px; height: 30px; float: left; margin-bottom: -10px;"
						name="schoolInput" id="schoolInput" placeholder="校　区"> <!-- 隐藏域 用来判断增加按钮是否可以点击 -->
						<input type="hidden" id="schoolNum" value="0" /> <!-- 用于界面提示用户是否存在 -->
						&nbsp; <span style="font-size: 18px;" id="schoolSpan"></span></td>
				</tr>
				<tr>
					<td colspan="3" style="padding-left:320px"><input
						type="button" onclick="actionsAddSchool()" id="adSchool"
						class="btn btn-info" value="增　加" /> <input type="reset"
						onclick="res()" class="btn btn-default" value="重　置" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<!---------------------------新增票据------------------------------>
 <div class="tab-pane" id="addBill">
	<form method="post" name="addBillForm">
		<h1 style="text-align: center; font-size: 45px;">新增(项目、票据)</h1>
		<table class="table table-condensed table-bordered">
			<tbody>
				<tr>
					<td class="textRight"
						style="font-size: 20px; margin-bottom: -10px;">票 据:</td>
					<td><input type="text" class="form-control"
						onblur="verifyReceipt(this)" maxlength="20"
						style="width: 155px; height: 30px; float: left; margin-bottom: -10px;"
						name="receiptInput" id="receiptInput" placeholder="票　据"> <!-- 隐藏域 用来判断增加按钮是否可以点击 -->
						<input type="hidden" id="receiptNums" value="0" /> <!-- 用于界面提示用户是否存在 -->
						&nbsp; <span style="font-size: 18px;" id="receiptSpan"></span></td>
				</tr>
				<tr>
					<td colspan="3" style="padding-left:320px"><input
						type="button" onclick="actionsAddReceipt()" id="addBtnAll"
						class="btn btn-info" value="增　加" /> <input type="reset"
						onclick="res()" class="btn btn-default" value="重　置" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>

<!-------------管理账户信息------------------------------------------------------>
<!-------------账户管理20200727------------------------------------------------->
	<div class="tab-pane" id="userManage">
		<h1 style="text-align: center; font-size: 45px;">所有用户</h1>
		<div style="overflow: auto;">
			<table class="table-bordered" style="text-align: center;">
				 <tr style="font-size: 20px; height: 20px;">
					<td ><b>序号</b></td>
					<td><b>登录名</b></td>
					<td><b>密码</b></td>
					<td><b>操作</b></td>
				 </tr>

				 <c:forEach items="${userList}" varStatus="varStatus" var="item" >
				 <tr>
                    <td>${varStatus.index+1}</td>
                    <td>${item.username}</td>
                    <td>${item.password}</td>
                    <td>
                    <!--
                    <button class="btn btn-primary btn-default" type="button"  id="update_item"
                            onclick="updateProjects('${item.id}','${item.username}','${item.id}')">修改</button>
                    -->

                    <button class="btn btn-default" type="button"
                            onclick="deleteUser('${item.id}','${item.username}')">删除</button>
                    </td>
                 </tr>
				 </c:forEach>
			</table>
		</div>
	</div>

<!---------------------------修改账户信息------------------------------>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	/* 点击按钮选择所有选择框 */
	function checkAll(){
 		var isChecked = $("input:checkbox[name='checkAll']").is(":checked");
		$("input:checkbox[name='incomeCheck']").prop("checked",isChecked);
	}

	//当下enter键后视为点击确定处理
	window.onkeydown=function(event){
      var key = $("#confirm_who").val();
      var isShow = null;
      var downKey = event.keyCode;
      if(downKey == 13){
   			//确认修改支付信息
   			if(key == 1 && !$("#update_model").is(":hidden")){
   			updateFormActions();
   			}
   			else if(!$("#insert_model").is(":hidden")){
   			$("#isSure").click();
   			}
  	}

		};


	var moneys = /^-?((\d+)|(\d+\.\d{1,4}))$/; //验证金钱格式
	$('#myTab a').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});

	$("a").bind("focus", function() { //去除所有A标签的虚线框
		$(this).blur();
	});
	function actions() {
		var inputValues = [ "name", "project", "receipt", "receiptNum",
				"moneys", "time" ]; //存储当前必须要输入的文本框ID //,"versions" 版本号
		var isAction = true;//默认表示可以提交
		var str = /^\d{4}-\d{2}-\d{2}$/;
		for (var i = 0; i < inputValues.length; i++) {
			var value = $("#" + inputValues[i]).val();
			if ($.trim(value) == "") {
				$("#modal-body").html($("#" + inputValues[i]).attr("placeholder") + "!!!");
				$("#model_button").click();
				window.scrollTo(0, 0);
				$("#" + inputValues[i]).focus();
				isAction = false;
				return;
			} else {
				$("#bs-example").hide();
			}

		}
		if (isAction) { //判断如果是true的话,那么就表示都通过了,在验证金钱
			if (!moneys.test($("#moneys").val())) {
				$("#modal-body").html("金额格式不对!!!<br/>请核对格式,防止数据错乱。");
				$("#model_button").click();
				isAction = false;
				return;
			}
			;
		}
		if (!str.test($("#time").val())) { //验证时间格式
			$("#modal-body").html("时间格式不对,比如:2012-01-15!!!");
			$("#model_button").click();
			isAction = false;
			return;
		}
		if ($("#remark").val().length >= 250) {
			$("#modal-body").html("备注文字信息输入太长!!!");
			$("#model_button").click();
			isAction = false;
			return;
		}
		if (isAction) {
			var form = document.forms["addForm"];
			form.action = "order_create?isHide=8";
			form.submit();
		}
		;
	}

	function queryActions(info) {
		var inputValues = [ "startTime", "endTime", "queryName",
				"queryReceipt", "queryFashion", "queryGrade", "queryRemark",
				"queryProject", "business", "queryMoneys" ]; //这些ID为了,判断查询的时候是否一个值都没有,如果没有,那么就不执行查询  (,"queryVersions")版本号
		var index = 0;
		for (var i = 0; i < inputValues.length; i++) {
			var value = $("#" + inputValues[i]).val();
			if ($.trim(value) == "") {
				index++;
			}
		}
		if (typeof (info) == "undefined") {
			info = 1;
		}
		if (index < inputValues.length || typeof (info) != "undefined") {
			if (typeof (info) == "undefined") {
				info = 1;
			}
			var form = document.forms["queryFrom"];
			form.action = "order_query?index=" + info;
			form.submit();
		} else {
			$("#modal-body").html("请输入一个条件在查询!!!<br/>(如果要查询全部请点击查询全部按钮)");
			$("#model_button").click();
		}
	}
	/* 注销登录的方法 */
	function logout() {
		if (confirm("确定退出当前登录?")) {
			window.location.href = "user/logout";
		}
	}
	/* 生成Excel表格 */
	function createExcelAction() {
		$.ajax({
			url : "CreateExcelServlet",
			type : "post",
			success : function(data) {
				if (data != 0) {
					window.location.href = data; //返回一个生成的下载路径
				} else {
					$("#modal-body").html("没有数据,不能导出Excel");
					$("#model_button").click();
				}
			}
		});
	}

	/* 项目的添加  */
	function actionsAddProject() {
		var inputValues = ["projectInput","schoolName"]; //存储当前必须要输入的项目名称和学校名称
		var bool = false;
		var x=1;
		for (var i = 0; i < inputValues.length; i++) {
			if ($.trim($("#" + inputValues[i]).val()) != "") {
				x++;
			}
		}
		if(x == 3){
			bool = true;
			}
		if (!bool) {
			$("#modal-body").html("项目名或学校名不能为空!");
			$("#model_button").click();
		} else {
			var project = $.trim($("#projectInput").val()); //获取输入的项目名称
			var schoolId=$.trim($("#schoolName").val());
			$.ajax({
				url:"subject/check_name_exists",
				type:"post",
				data:"project="+project+"&schoolid="+schoolId,
				dataType:"text",
				success:function(data){
					if(data==1){ //若已存在用户名，则提示用户
						$("#projectSpan").css("color","red").html("对不起！此项目已存在！");
						$("#projectNum").attr("value", 1);
						$("#addBtnAll").attr("disabled", true);
						return;
					}else{ //若用户名不存在，则添加
					/* $.ajax({
						url:"addOptionServlet",
						type:"post",
						data:$("form[name='addProjectForm']").seralize,
						success:function(data){
							if(data == 1){
							$.ajax

							}

						}

					})		 */
						var form = document.forms["addProjectForm"];
						form.action = "addOptionServlet?isHide=5";
						form.submit();
					}
				}
			});
		}
	}

	/* 增加收支信息的项目的添加  */
	 function actionsAddProject2() {
		var inputValues = ["projectInput2"]; //存储当前必须要输入的项目名称
		var bool = false;
		for (var i = 0; i < inputValues.length; i++) {
			if ($.trim($("#" + inputValues[i]).val()) !== "") {
				bool = true;
			}
		}
		if (!bool) {
			//alert("项目名称不能为空！")
			$("#projectSpan2").css("color","red").html("项目名称不能为空！");
			/* $("#modal-body").html("项目名称不能为空!");
			$("#model_button").click(); */
		} else {
			var project = $.trim($("#projectInput2").val()); //获取输入的项目名称
			$.ajax({
				url:"subject/check_name_exists",
				type:"post",
				data:{project:project},
				dataType:"text",
				success:function(data){
					if(data==1){ //若已存在用户名，则提示用户
						$("#projectSpan2").css("color","red").html("对不起！此项目已存在！");
						$("#projectNum2").attr("value", 1);
						$("#newProjBtn2").attr("disabled", true);
						return;
					}else{ //若用户名不存在，则添加
						$.ajax({
							url:"subject/create",
							type:"post",
							data:{"projectInput":project},
							success:function(data){
								if(data != ""){
									$('#addNewProjModal').modal('hide');
									$("#project").append( "<option value=" +data + ">" + project + "</option>" );
									$('#project option[value='+data+']').attr('selected',true);
								}
							}
						});
					}
				}
			});
		}
	}

	/* 校区的增加  */
	function actionsAddSchool() {
		var inputValues = ["schoolInput"]; //存储当前必须要输入的学校名称
		var bool = false;
		for (var i = 0; i < inputValues.length; i++) {
			if ($.trim($("#" + inputValues[i]).val()) !== "") { //判断当前用户输入的学校名称是否为空
				bool = true;
			}
		}
		if (!bool) {
			$("#modal-body").html("学校名称不能为空！");
			$("#model_button").click();
		} else {
			var school = $.trim($("#schoolInput").val()); //获取输入的学校名称
			$.ajax({
				url:"VerifyServlet",
				type:"post",
				data:{school:school},
				dataType:"text",
				success:function(data){
					if(data==1){ //要是已存在此学校名，则提示
						$("#schoolSpan").css("color","red").html("对不起！此校区已存在！");
						$("#schoolNum").attr("value",1);
						$("#addBtnAll").attr("disabled", true);
						return;
					} else {//要是填入的学校数据库没有，则添加
						var form = document.forms["addSchoolForm"];
						form.action = "addOptionServlet?isHide=9";
						form.submit();
					}
				}
			});
		}
	}

	/* 票据的增加  */
	function actionsAddReceipt() {
		var inputValues = ["receiptInput"]; //存储当前必须要输入的票据名称
		var bool = false;
		for (var i = 0; i < inputValues.length; i++) {
			if ($.trim($("#" + inputValues[i]).val()) !== "") {
				bool = true;
			}
		}
		if (!bool) {
			$("#modal-body").html("票据名称不能为空!");
			$("#model_button").click();
		} else {
			//获取票据名输入框的值
			var receipt = $.trim($("#receiptInput").val()); //获取用户输入的票据
				$.ajax({
					url:"ticket/check_name_exists",
					type:"post",
					data:{receipt:receipt},
					dataType:"text",
					success:function(data){
						if(data==1){ //若用户输入的票据已经存在,则提示用户
							$("#receiptSpan").css("color","red").html("对不起！此票据已存在！");
							$("#receiptNums").attr("value", 1);
							$("#addBtnAll").attr("disabled", true);
							return;
						}else{ //若输入的用户不存在，则添加
							var form = document.forms["addBillForm"];
							form.action = "addOptionServlet?isHide=10";
							form.submit();
						}
					}
				});
		}
	}

	/* 新增收支信息里票据的增加  */
	function actionsAddReceipt2() {
		var inputValues = ["receiptInput2"]; //存储当前必须要输入的票据名称
		var bool = false;
		for (var i = 0; i < inputValues.length; i++) {
			if ($.trim($("#" + inputValues[i]).val()) !== "") {
				bool = true;
			}
		}
		if (!bool) {
			$("#receiptSpan2").css("color","red").html("票据名不能为空！");
		} else {
			//获取票据名输入框的值
			var receipt = $.trim($("#receiptInput2").val()); //获取用户输入的票据
				$.ajax({
					url:"ticket/check_name_exists",
					type:"post",
					data:{receipt:receipt},
					dataType:"text",
					success:function(data){
						if(data==1){ //若用户输入的票据已经存在,则提示用户
							$("#receiptSpan2").css("color","red").html("对不起！此票据已存在！");
							$("#receiptNums2").attr("value", 1);
							$("#newBillBtn2").attr("disabled", true);
							return;
						}else{ //若输入的用户不存在，则添加
							$.ajax({
								url:"ticket/type/create",
								type:"post",
								data:{"receiptInput":receipt},
								success:function(data){
									if(data != ""){
										$('#addNewBillModal').modal('hide'); //隐藏添加票据模态框
										$("#receipt").append( "<option value=" +data + ">" + receipt + "</option>" );
										$('#receipt option[value='+data+']').attr('selected',true);
									}
								}
							});
						}
					}
				});
		}
	}
	/* 修改信息函数 */
	function update(id, time, name, project, receiptNumber, moneys, billNumber,receiptNum, fashion, remark, grade,bid,pname) {
		$("#id_update").val(id);
		$("#id_update0").html("");
		$("#name_update0").html("");

		$("#name_update").val(name);

		$("#grade_update").val(grade);
		$("#project_update").val(project);
		$("#project_update0").html("");
		//$("#versions_update").val(receiptNumber);
		$("#receipt_update").val(billNumber);
		$("#receipt_update0").html("");

		$("#receiptNum_update").val(receiptNum);
		$("#receiptNum_update0").html("");
		$("#moneys_update").val(moneys);
		$("#moneys_update0").html("");
		$("#fashion_update").val(fashion);
		$("#time_update").val(time);
		$("#time_update0").html("");
		$("#remark_update").val(remark.replace(/　/g, "\r\n"));
		$("#remark_update0").html("");
		var url = "subject/query_biz?businessname="+bid;
		$.ajax({
			url:url,
			type:"get",
			success:function(data){
				var projectSelect = document.getElementById("project_update");
				//若data的返回值为1，则表示修改数据表成功
				//projectSelect.length = 0 ;

				var optionEleList = projectSelect.getElementsByTagName("option");
				// 循环次数为<option>个数-1次！
				while(optionEleList.length >0) {
				// 每次删除下标为1的元素，循环下一次时，原来下标为2就变成了下标为1的！
				projectSelect.removeChild(optionEleList[0]);
					}
				var option1 = document.createElement("option");
				var Projects=data.split(",");
				option1.value = project;
				var textNode = document.createTextNode(pname);
				option1.appendChild(textNode);
				projectSelect.appendChild(option1);
				for(var i = 0; i < Projects.length; i++) {
					if(Projects[i].split(":")[1] == pname){
					continue;
					}

					var option = createOption(Projects[i]);//使用每个项目的名称创建<option>元素
					projectSelect.appendChild(option);//把<option>元素添加到select元素中
					$("#confirm_who").val(1);
				}
				$("#update_model_button").click();
			}
		});


	}
function createOption22(project,pname) {
	/*
	1. 创建<option>元素
	2. 指定元素的文本内容(显示值)
	3. 指定元素的value属性值(实际值)
	4. 返回
	*/
	var option = document.createElement("option");
	var projects=project.split(":");
	option.value = projects[0];//指定元素的value属性值
	var textNode = document.createTextNode(projects[1]);//使用字符串创建一个文本节点
	option.appendChild(textNode);//把文本节点添加到<option>元素中

	return option;
}





	function show(){

	}

	/* 修改时候提交到servlet */
	function updateFormActions() {
		var inputValues = [ "name_update", "project_update", "receipt_update",
				"receiptNum_update", "moneys_update", "time_update" ]; //存储当前必须要输入的文本框ID  版本号,"versions_update"
		var isAction = true;//默认表示可以提交
		var str = /^\d{4}-\d{2}-\d{2}$/;
		for (var i = 0; i < inputValues.length; i++) {
		 $("#" + inputValues[i]+"0").html("");
		 }
		for (var i = 0; i < inputValues.length; i++) {

			var value = $("#" + inputValues[i]).val();
			if ($.trim(value) == "") {

				$("#" + inputValues[i]+0).html($("#" + inputValues[i]).attr("placeholder") + "!!!");
				//$("#model_button").click();
				//window.scrollTo(0, 0);
				//$("#" + inputValues[i]).focus();
				isAction = false;
				return;
			} else {
				$("#bs-example").hide();
			}

		}
		if (isAction) { //判断如果是true的话,那么就表示都通过了,在验证金钱
			if (!moneys.test($("#moneys_update").val())) {
				$("#moneys_update0").html("金额格式不对!!!<br/>请核对格式,防止数据错乱。");
				//$("#model_button").click();
				isAction = false;
				return;
			}
			;
		}
		if (!str.test($("#time_update").val())) { //验证时间格式

			$("#time_update0").html("时间格式不对,比如:2012-01-15!!!");
			//$("#model_button").click();
			isAction = false;
			return;
		}
		if ($("#remark_update").val().length >= 250) {

			$("#remark_update0").html("备注文字信息输入太长!!!");
			//$("#model_button").click();
			isAction = false;
			return;
		}
		if (isAction) {
		//获取条件查询的条件
		/* var inputValues = [ "startTime", "endTime", "queryName",
				"queryReceipt", "queryFashion", "queryGrade", "queryRemark",
				"queryProject", "business" ]; //这些ID为了,判断查询的时候是否一个值都没有,如果没有,那么就不执行查询  (,"queryVersions")版本号
		var condition="";
		for (var i = 0; i < inputValues.length; i++) {
			var value = $("#" + inputValues[i]).val();
			if ($.trim(value) != "") {
				condition += inputValues[i]+"="+value+"&";
			}
			else{
			condition += inputValues[i]+"="+""+"&";
			}
			}
			var value = $("input[name='queryMoneys']:checked").val();

			condition +="queryMoneys"+"="+value+"&";
			condition +="index"+"="+${orderPage.pageIndex};
		 */
		 $.ajax({
		 	url:"order_update",
		 	type:"post",
		 	data:$("#updateForm").serialize(),
		 	success:function(data){
		 	if(data == 1){
		 		var info = ${orderPage.pageIndex};
					queryActions(info);
		 	}
		 	}

		 })

			/* var form = document.forms["updateForm"];
			form.action = "order_update?"+condition;
			form.submit(); */
		}
		;
	}
	/* 修改项目函数 */
	function updateProjects(id,name,bid) {

		$("#project_update_manager").val(id);
		$("#update_text_project").val(name);
		$("#update_text_businessid").val(bid);
		$("#projectTip").html("");

		$("#updItemModal").modal("show");
	}

	/* 修改票据函数 */
    function updateTicketType(id, name) {
        $("#ticket_type_id").val(id);
        $("#update_ticket_type_name").val(name);
        $("#ticketTypeTip").html("");
        $("#updateTicketTypeModal").modal("show");
    }

	/* 修改校区函数 */
	function updateSchool(id,name) {
		$("#business_update_manager").val(id);
		$("#update_text_business").val(name);
		$("#businessTip").html("");

		$("#updSchModal").modal("show");
	}
	/* 修改票据函数 */
	function updateBill(id,name) {
		$("#receipt_update_manager").val(id);
		$("#update_text_bill").val(name);
		$("#billTip").html("");

		$("#updBillModal").modal("show");


	}
	/*
	 *批量删除收支信息
	 */
	 function deleteIncomes(){
	 var j = 0;
	 var condition ="";
	 $("input:checkbox[name='incomeCheck']:checked").each(function(){
	 condition += this.value+",";
	 });
	 if(condition == ""){
	 confirm("请选择要删除的信息!!!!");
	 }
	 else if(confirm("确定要删除当前信息吗!!!")){
	 	$.ajax({
	 	url:"order_delete",
	 	type:"post",
	 	data:"conditions="+condition,
	 	success : function(data) {
					if (data == 1) {

					var info = ${orderPage.pageIndex};
					queryActions(info);

					//window.location.href = "order_query?index="+${orderPage.pageIndex};
					} else {
						$("#modal-body").html("删除信息失败<br/>请重试,抱歉!!!");
						$("#model_button").click();
					}
				}

	 	});
	 };
	 }

	/* 删除收支信息 */
	function deleteIncom(id, name) {
		if (confirm("确定要删除'" + name + "'的数据吗?\n删除后不可恢复!")) {
			$.ajax({
				type : "post",
				url : "order_delete",
				data : {
					id : id,
					name : name
				},
				success : function(data) {
					if (data == 1) {

					var info = ${orderPage.pageIndex};
					queryActions(info);

					//window.location.href = "order_query?index="+${orderPage.pageIndex};
					} else {
						$("#modal-body").html("删除'" + name + "'失败<br/>请重试,抱歉!!!");
						$("#model_button").click();
					}
				}
			});
		}
	}
	/* 删除项目信息 */
	function deleteProjects(id, name) {
		if (confirm("确定要删除'" + name + "'的数据吗?\n删除后不可恢复!")) {
			$.ajax({
				type : "post",
				url : "subject/delete",
				data : {
					id : id,
					name : name
				},
				success : function(data) {
					if (data == 1) {
						window.location.href = "order_query?isHide=4";
					} else {
						$("#modal-body").html("项目：" + name + "存在收支信息，无法删除<br/>抱歉!!!");
						$("#model_button").click();
					}
				}
			});
		}
	}
	/* 删除票据类型信息 */
    function deleteTicketType(id, name) {
        if (confirm("确定要删除'" + name + "'的数据吗?\n删除后不可恢复!")) {
            $.ajax({
                type : "post",
                url : "ticket/type/delete",
                data : {
                    ticket_type_id : id,
                    ticket_type_name : name
                },
                success : function(data) {
                    if (data == 1) {
                        window.location.href = "order_query?isHide=4";
                    } else {
                        $("#modal-body").html("票据类型：" + name + "存在收支信息，无法删除<br/>抱歉!!!");
                        $("#model_button").click();
                    }
                }
            });
        }
    }
	/* 删除用户信息 */
    function deleteUser(id, name) {
        if (confirm("确定要删除'" + name + "'的数据吗?\n删除后不可恢复!")) {
            $.ajax({
                type : "post",
                url : "user/delete",
                data : {
                    id : id,
                    name : name
                },
                success : function(data) {
                    if (data == 1) {
                        window.location.href = "order_query?isHide=11";
                    } else {
                        $("#modal-body").html(
                                "用户：" + name + "无法删除<br/>抱歉!!!");
                        $("#model_button").click();
                    }
                }
            });
        }
    }
	/* 删除校区信息 */
	function deleteSchool(id, name) {
		if (confirm("确定要删除'" + name + "'的数据吗?\n删除后不可恢复!")) {
			$.ajax({
				type : "post",
				url : "DeleteSchoolServlet",
				data : {
					id : id,
					name : name
				},
				success : function(data) {
					if (data == 1) {

						window.location.href = "order_query?isHide=2";
					} else {
						$("#modal-body").html(
								name + "校区存在收支信息，无法删除<br/>抱歉!!!");
						$("#model_button").click();
					}
				}
			});
		}
	}
	/* 删除票据信息 */
	function deleteBill(id, name) {
		if (confirm("确定要删除'" + name + "'的数据吗?\n删除后不可恢复!")) {
			$.ajax({
				type : "post",
				url : "DeleteBillServlet",
				data : {
					id : id,
					name : name
				},
				success : function(data) {
					if (data == 1) {
						window.location.href = "order_query?isHide=3";
					} else {
						$("#modal-body").html(
								"票据：" + name + "存在收支信息，无法删除<br/>抱歉!!!");
						$("#model_button").click();
					}
				}
			});
		}
	}
	/* 修改选项里的表单提交 */
	function updateManagerForm(fromName, url, objName, isHide) {
		//var forms = document.forms[fromName];
		//forms.action = url;
		 if ($.trim($("#" + objName).val()) == "") {
			$("#updItemModal").modal("hide");
			$("#updSchModal").modal("hide");
			$("#updBillModal").modal("hide");
			$("#updateTicketTypeModal").modal("hide");
			$("#modal-body").html($("#" + objName).attr("placeholder") + "!!!");
			$("#model_button").click();
			return;
		}

		$.ajax({
            url:url,
            data:$("form[name='"+fromName+"']").serialize(),
            type:"post",
            success:function(data){
            if(data == 1){
                window.location.href = "order_query?isHide="+isHide;
            }
		}
		})
	};
	/* Excel提交到后台,进行数据录入 */
	function addExcelInfoServlet() {
		var isBool = false; //表示是否符合后缀
		var hz = [ "xls" ]; //后缀名
		var leadExcel = $("#leadExcel");
		for (var i = 0; i < hz.length; i++) {
			if (leadExcel.val().substring(leadExcel.val().lastIndexOf(".") + 1) == hz[i]||leadExcel.val().substring(leadExcel.val().lastIndexOf(".") + 1) == "xlsx") {
				isBool = true;
				break;
			}
		}
		if (isBool) {
			var addExcelForm = document.forms["queryFrom"];
			addExcelForm.action = "order_upload";
			addExcelForm.setAttribute("enctype", "multipart/form-data");
			addExcelForm.submit();

			$("#importExcel").modal("show");
			$("#importExcel").unbind();

			//$("#Exceladd").css("display","none");

		} else {
			$("#modal-body").html(
					"抱歉! 不是Excel文件!!! <br/>请转换成 (文件名.xls),以免影响数据。");
			$("#model_button").click();
		}
	}
	function previousPage(info) {
		var totalPageCount = parseInt("${orderPage.pageCount}");
		if (typeof (info) == "undefined") {
			info = $("#pageIndex").val();
			var te = /^[1-9]+[0-9]*]*$/;
			if (!te.test(info)) {
				alert("输入的页数不是数字");
				return;
			}
			if (info > totalPageCount) {
				alert("输入的页数超过了总页数，请重新输入");
				return;
			}
			if (info.length > 9) {
				alert("输入的过长，请重新输入");
				return;
			}

		}
		/* $.ajax({
			url:"order_query",
			data:{"index":info},
			type:"post",
			success:function(data){
				if (data==1){
					alert(1);
					window.location.href="query.jsp";
				}
			}
		}) */
		/* window.location.href = "order_query?index="+info;  */
		queryActions(info);
	}
	$(document).ready(function() {
		$("#pageIndex").keydown(function(e) {
			var curKey = e.which;
			if (curKey == 13) {
				$("#searchPage").click();
				return false;
			}
		});
	});

	/* 新增票据时快速新增项目弹出模态框  */
	function addProjectType(){
		alert("建立");
		$("#actionsAddProject2").show();
	}

	/* 新增票据时快速新增项目和票据弹出模态框  */
	function addBillType(){
		$("#addNewBillModal").show();
	}

    /* 获取URL中的参数值  */
	function getQueryVariable(variable) {
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
    }

	//页面加载后判断跳转页面
	window.onload = function(){
	var isHide = getQueryVariable('isHide');
	    if(isHide == 0){
		    queryBudgetAll(); //显示所有收支信息
		}
		else if(isHide == 2){
		    querySchoolAll(); //显示所有校区
		}
		else if(isHide == 3){
		    queryBillAll(); //显示所有票据
		}
		else if(isHide == 4){
		    queryItemAll(); //显示所有项目
		}
		else if(isHide == 5){
		    addNewProject(); //显示添加项目
		}
		else if(isHide == 9){
		    addNewSchool(); //显示添加学校
		}
		else if(isHide == 10){
		    addNewBill(); //显示添加票据
		}
		else if(isHide == 6){
		    accountRegisterAll(); //显示注册用户页面
		}
		else if(isHide == 7){
		    accountAddShcollAll(); //显示修改校区密码页面
		}
		else if(isHide == 8){
		    addBudgetAll(); //显示修改收支信息页
		}
		else if(isHide == 1){
		    accountUpdateAll(); //显示修改收支信息页
		}
        else if(isHide == 11){
		    userListAll(); //显示用户列表页面
		}
		else if(isHide == 12){
            addUserAction(); //显示用户添加
        }
        else if(isHide == 1001){
            updatePasswordAction(); // 更新密码
        }
	}
</script>

</html>