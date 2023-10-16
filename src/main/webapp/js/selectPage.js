//用户选择导出Excel表选择页面
$("#selectPage").hide();
$(":radio[name='pageSelect']").click(function(){
    var index = $(":radio[name='pageSelect']").index($(this));
    if(index == 1) //选中第2个时，div显示
        $("#selectPage").show();
    else //当被选中的不是第2个时，div隐藏
        $("#selectPage").hide();
});

//根据用户的选择导出Excel表
$("#selectPageSubmit").click(function(){
	 //判断按钮 当前模态框中的单选按钮的值  0表示选中导出当前页  1表示选中多页导出
	 var pageSelect = document.getElementsByName("pageSelect");

	 for(var i = 0; i < pageSelect.length; i++){
		  var page1 = 0;
		  var page2 = 1;
		  if(pageSelect[i].checked == true ){
			  if(i == 0){
				  page1 = parseInt($(this).val()); //获得当前页
				  page2 = 1;
				  $('#selectExcel').modal('hide');
			  }else if(i == 1){
				//判断用户输入的页数
				page1 = $("#pageText1").val();
				page2 = $("#pageText2").val();
			  }else{
					return;
			  }

			  if( !isNaN( page1 ) && !isNaN( page2 )){ //判断用户输入的是否是数字，是的话则执行操作
					if(page2 - page1 >= 5 && page1 > 0){
						alert("最多只能导出5页！");
						return;
					}else if(page1 <= 0 || page2 <= 0){
						alert("请输入大于0的数！");
						return;
					}else if(page2 - page1<=0){
						page2 = page1;
					}
					//执行导出多页操作
					var page = {
							"page1":page1,
							"page2":page2,
							"startTime":$("#startTime").val(),
							"endTime":$("#endTime").val(),
							"queryName":$("#queryName").val(),
							"queryReceipt":$("#queryReceipt").val(),
							"queryFashion":$("#queryFashion").val(),
							"queryGrade":$("#queryGrade").val(),
							"queryProject":$("#queryProject").val(),
							"queryRemark":$("#queryRemark").val(),
							"queryMoneys":$("input[name='queryMoneys']:checked").val(),
							"business":$("#business").val(),
					};

					var url = "order_get_download_url";
					$.ajax({
						url:url,
						type:"get",
						data:page,
						success:function(data){
							//若data的返回值为1，则表示导出表成功
							$("#selectExce03").hide();
							if(data != 0){
//								createExcelAction();
								document.getElementById("curPageSelect").checked = true;
								$("#pageText1").val("");
								$("#pageText2").val("");
								$("#selectPage").hide();
								window.open(data);
							}else{
								//若data返回值不是1，提示操作失败！
								alert("您选择的页数没有数据！");
							}
						}
					});
			  }else{
				  alert("请输入数字！");
			  }

		  }
	 }
})