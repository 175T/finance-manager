/**
 * 添加和修改项目、校区、票据时的验证
 * 李作焕
 * @param obj
 */
//添加时验证
function verifyProject(obj){
//获取项目名输入框的值
		var project = $.trim($("#projectInput").val()); //获取输入的项目名称
		var schoolId=$.trim($("#schoolName").val());

		$.ajax({
			url:"subject/check_name_exists",
			type:"post",
			data:"project="+project+"&schoolid="+schoolId,
			dataType:"text",
			success:function(data){
				if(data==1){
					$("#projectSpan").css("color","red").html("对不起！此项目已存在！");
					$("#projectNum").attr("value", 1);
					$("#addBtnAll").attr("disabled", true);
				}else{
					if (project!="") {
						$("#projectSpan").css("color","green").html("此项目名可以使用！");
						$("#projectNum").attr("value", 0);
						if ($("#projectNum").val()!=1&&$("#schoolNum").val()!=1&&$("#receiptNums").val()!=1) {
							$('#addBtnAll').removeAttr("disabled");
						}
					}else{
						$("#projectSpan").css("color","green").html("");
						$("#projectNum").attr("value", 0);
						if ($("#projectNum").val()!=1&&$("#schoolNum").val()!=1&&$("#receiptNums").val()!=1) {
							$('#addBtnAll').removeAttr("disabled");
						}
						}
				}

			},
			error:function(){
				alert("验证失败！");
			}
		});

}
//添加收支信息时验证项目名称是否存在
function verifyProject2(obj){
//获取项目名输入框的值
	var project = $.trim($("#projectInput2").val());
	$.ajax({
		url:"subject/check_name_exists",
		type:"post",
		data:{project:project},
		dataType:"text",
		success:function(data){
			if(data==1){
				$("#projectSpan2").css("color","red").html("对不起！此项目已存在！");
				$("#projectNum2").attr("value", 1);
				$("#newProjBtn2").attr("disabled", true);
			}else{
				if (project!="") {
					$("#projectSpan2").css("color","green").html("此项目名可以使用！");
					$("#projectNum2").attr("value", 0);
					if ($("#projectNum2").val()!=1) {
						$('#newProjBtn2').removeAttr("disabled");
					}
				}else{
					$("#projectSpan2").css("color","green").html("");
					$("#projectNum2").attr("value", 0);
					if ($("#projectNum2").val()!=1) {
						$('#newProjBtn2').removeAttr("disabled");
					}
				}
			}

		},
		error:function(){
			alert("验证失败！");
		}
	});
}
function verifySchool(obj){
	//获取校区名输入框的值
		var school = $.trim($("#schoolInput").val());
			$.ajax({
				url:"VerifyServlet",
				type:"post",
				data:{school:school},
				dataType:"text",
				success:function(data){
					if(data==1){
						$("#schoolSpan").css("color","red").html("对不起！此校区已存在！");
						$("#schoolNum").attr("value",1);
						$("#addBtnAll").attr("disabled", true);
						return false;
					}else{
						if (school!="") {
							$("#schoolSpan").css("color","green").html("此校区名可以使用！");
							$("#schoolNum").attr("value",0);
							if ($("#projectNum").val()!=1&&$("#schoolNum").val()!=1&&$("#receiptNums").val()!=1) {
								$('#addBtnAll').removeAttr("disabled");
							}
						}else{
							$("#schoolSpan").css("color","green").html("");
							$("#schoolNum").attr("value",0);
							/*var x=document.getElementById("receiptNums").value;
							var y=document.getElementById("projectNum").value;
							var z=document.getElementById("schoolNum").value;
							alert(x+","+y+","+z);*/
							if ($("#projectNum").val()!=1&&$("#schoolNum").val()!=1&&$("#receiptNums").val()!=1) {
								$('#addBtnAll').removeAttr("disabled");
							}
							}
					}
				},
				error:function(){
					alert("验证失败！");
				}
			});
	}
function verifyReceipt(obj){
		//获取票据名输入框的值
		var receipt = $.trim($("#receiptInput").val());
			$.ajax({
				url:"ticket/check_name_exists",
				type:"post",
				data:{receipt:receipt},
				dataType:"text",
				success:function(data){
					if(data==1){
						$("#receiptSpan").css("color","red").html("对不起！此票据已存在！");
						$("#receiptNums").attr("value", 1);
						$("#addBtnAll").attr("disabled", true);
						return false;
					}else{
						if (receipt!="") {
							$("#receiptSpan").css("color","green").html("此票据名可以使用！");
							$("#receiptNums").attr("value", 0);

							if ($("#projectNum").val()!=1&&$("#schoolNum").val()!=1&&$("#receiptNums").val()!=1) {
								$('#addBtnAll').removeAttr("disabled");
							}
						}else{
							$("#receiptSpan").css("color","green").html("");
							$("#receiptNums").attr("value", 0);
							if ($("#projectNum").val()!=1&&$("#schoolNum").val()!=1&&$("#receiptNums").val()!=1) {
								$('#addBtnAll').removeAttr("disabled");
							}
							}
					}

				},
				error:function(){
					alert("验证失败！");
				}
			});
	}

//新增收支信息里票据的增加
function verifyReceipt2(obj){
	//获取票据名输入框的值
	var receipt = $.trim($("#receiptInput2").val());
	$.ajax({
		url:"ticket/check_name_exists",
		type:"post",
		data:{receipt:receipt},
		dataType:"text",
		success:function(data){
			if(data==1){
				$("#receiptSpan2").css("color","red").html("对不起！此票据已存在！");
				$("#receiptNums2").attr("value", 1);
				$("#newBillBtn2").attr("disabled", true);
				return false;
			}else{
				if (receipt!="") {
					$("#receiptSpan2").css("color","green").html("此票据名可以使用！");
					$("#receiptNums2").attr("value", 0);

					if ($("#receiptNums2").val()!=1) {
						$('#newBillBtn2').removeAttr("disabled");
					}
				}else{
					$("#receiptSpan2").css("color","green").html("");
					$("#receiptNums2").attr("value", 0);
					if ($("#receiptNums2").val()!=1) {
						$('#newBillBtn2').removeAttr("disabled");
					}
				}
			}

		},
		error:function(){
			alert("验证失败！");
		}
	});
}



function res(){
	$("#projectSpan").css("color","green").html("");
	$("#schoolSpan").css("color","green").html("");
	$("#receiptSpan").css("color","green").html("");
	$('#addBtnAll').removeAttr("disabled");
}

//修改时验证
function upVerifyProject(obj){
	//获取项目名输入框的值
		var project = $.trim($("#update_text_project").val());
		var id=$.trim($("#update_text_businessid").val());
			$.ajax({
				url:"subject/check_name_exists",
				type:"post",
				data:"project="+project+"&schoolid="+id,
				dataType:"text",
				success:function(data){
					if(data==1){
						$("#projectTip").css("color","red").html("对不起！此项目已存在！");
						$("#projectBtn").attr("disabled", true);
						return false;
					}else{
						if (project!="") {
							$("#projectTip").css("color","green").html("此项目名可以使用！");
							$('#projectBtn').removeAttr("disabled");
						}else{
							$("#projectTip").css("color","green").html("");
							$('#projectBtn').removeAttr("disabled");
							}
					}

				},
				error:function(){
					alert("验证失败！");
				}
			});
	}

//修改票据类型时验证
function updateTicketTypeCheck(obj){
    //获取修改票据页面输入框的值
    var name = $.trim($("#update_ticket_type_name").val());
    var id = $.trim($("#ticket_type_id").val());
    $.ajax({
        url:"ticket/check_name_exists",
        type:"post",
        data:"receipt="+name+"&id="+id,
        dataType:"text",
        success:function(data){
            if(data==1){
                $("#ticketTypeTip").css("color","red").html("对不起！此票据类型名称已存在！");
                $("#ticketTypeUpdateSubmitButton").attr("disabled", true);
                return false;
            }else{
                if (name!="") {
                    $("#ticketTypeTip").css("color","green").html("此票据类型名称可以使用！");
                    $('#ticketTypeUpdateSubmitButton').removeAttr("disabled");
                }else{
                    $("#ticketTypeTip").css("color","green").html("");
                    $('#ticketTypeUpdateSubmitButton').removeAttr("disabled");
                }
            }
        },
        error:function(){
            alert("验证失败！");
        }
    });
}

	function upVerifySchool(obj){
		//获取校区名输入框的值
			var school = $.trim($("#update_text_business").val());
				$.ajax({
					url:"VerifyServlet",
					type:"post",
					data:{school:school},
					dataType:"text",
					success:function(data){
						if(data==1){
							$("#businessTip").css("color","red").html("对不起！此校区已存在！");
							$("#businessBtn").attr("disabled", true);
							return false;
						}else{
							if (school!="") {
								$("#businessTip").css("color","green").html("此校区名可以使用！");
								$('#businessBtn').removeAttr("disabled");
							}else{
								$("#businessTip").css("color","green").html("");
								$('#businessBtn').removeAttr("disabled");
								}
						}

					},
					error:function(){
						alert("验证失败！");
					}
				});
		}
	function upVerifyReceipt(obj){
			//获取票据名输入框的值
			var receipt = $.trim($("#update_text_bill").val());
				$.ajax({
					url:"ticket/check_name_exists",
					type:"post",
					data:{receipt:receipt},
					dataType:"text",
					success:function(data){
						if(data==1){
							$("#billTip").css("color","red").html("对不起！此票据已存在！");
							$("#billBtn").attr("disabled", true);
							return false;
						}else{
							if (receipt!="") {
								$("#billTip").css("color","green").html("此票据名可以使用！");
								$('#billBtn').removeAttr("disabled");
							}else{
								$("#billTip").css("color","green").html("");
								$('#billBtn').removeAttr("disabled");
								}
						}

					},
					error:function(){
						alert("验证失败！");
					}
				});
	}