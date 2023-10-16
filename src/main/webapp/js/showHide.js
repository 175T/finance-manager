/************点击所有收支信息时触发********************/
function queryBudgetAll(){
    $("#queryBudget").show(); 			    //显示所有收支信息
    $("#queryBill").hide();				    //隐藏所有票据信息
    $("#queryItem").hide();					//隐藏所有项目信息
    $("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
    $("#querySchool").hide();				//隐藏所有校区信息
    $("#addBudget").hide();					//隐藏添加收支信息
    $("#addOption").hide();					//隐藏添加校区、项目、票据选项
    $("#accountUpdate").hide();			    //隐藏修改账户信息
    $("#accountRegister").hide();			//隐藏注册账户信息
    $("#accountAddShcollAll").hide();       //隐藏所有修改校区
    $("#addBill").hide();					//隐藏添加账单
    $("#addProject").hide();				//隐藏添加项目
    $("#addSchool").hide();					//隐藏添加学校
    $("#userManage").hide();				//隐藏用户列表
    $("#addUserPane").hide();				//隐藏添加用户
    $("#updatePasswordPane").hide();		//隐藏更新密码
}

/************点击所有校区时触发********************/
function querySchoolAll(){
    $("#querySchool").show(); 			//显示所有校区信息
    $("#queryBill").hide();						//隐藏所有票据信息
    $("#queryItem").hide();					//隐藏所有项目信息
    $("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
    $("#queryBudget").hide();				//隐藏所有收支信息
    $("#addBudget").hide();					//隐藏添加收支信息
    $("#addOption").hide();					//隐藏添加校区、项目、票据选项
    $("#accountUpdate").hide();			//隐藏修改账户信息
    $("#accountRegister").hide();			//隐藏注册账户信息
    $("#accountAddShcollAll").hide();// 隐藏所有修改校区
    $("#addBill").hide();					//隐藏添加账单
    $("#addProject").hide();				//隐藏添加项目
    $("#addSchool").hide();					//隐藏添加学校
    $("#userManage").hide();				//隐藏用户列表
    $("#addUserPane").hide();				//隐藏添加用户
    $("#updatePasswordPane").hide();		//隐藏更新密码
}

/************点击所有票据时触发********************/
function queryBillAll(){
    $("#queryBill").show(); 					//显示所有票据信息
    $("#querySchool").hide();				//隐藏所有校区信息
    $("#queryItem").hide();					//隐藏所有项目信息
    $("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
    $("#queryBudget").hide();				//隐藏所有收支信息
    $("#addBudget").hide();					//隐藏添加收支信息
    $("#addOption").hide();					//隐藏添加校区、项目、票据选项
    $("#accountUpdate").hide();			//隐藏修改账户信息
    $("#accountRegister").hide();			//隐藏注册账户信息
    $("#accountAddShcollAll").hide();// 隐藏所有修改校区
    $("#addBill").hide();					//隐藏添加账单
    $("#addProject").hide();				//隐藏添加项目
    $("#addSchool").hide();					//隐藏添加学校
    $("#userManage").hide();				//隐藏用户列表
    $("#addUserPane").hide();				//隐藏添加用户
    $("#updatePasswordPane").hide();		//隐藏更新密码
}

/************点击所有项目时触发********************/
function queryItemAll(){
    $("#queryItem").show(); 				//显示所有项目信息
    $("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
    $("#querySchool").hide();				//隐藏所有校区信息
    $("#queryBill").hide();						//隐藏所有票据信息
    $("#queryBudget").hide();				//隐藏所有收支信息
    $("#addBudget").hide();					//隐藏添加收支信息
    $("#addOption").hide();					//隐藏添加校区、项目、票据选项
    $("#accountUpdate").hide();			//隐藏修改账户信息
    $("#accountRegister").hide();			//隐藏注册账户信息
    $("#accountAddShcollAll").hide();// 隐藏所有修改校区
    $("#addBill").hide();					//隐藏添加账单
    $("#addProject").hide();				//隐藏添加项目
    $("#addSchool").hide();					//隐藏添加学校
    $("#userManage").hide();				//隐藏用户列表
    $("#addUserPane").hide();				//隐藏添加用户
    $("#updatePasswordPane").hide();		//隐藏更新密码
}

/************点击所有票据时触发********************/
function queryTicketTypeAll(){
    $("#queryTicketType").show(); 				//显示所有票据类型信息
    $("#queryItem").hide(); 		    //隐藏所有项目信息
    $("#querySchool").hide();				//隐藏所有校区信息
    $("#queryBill").hide();						//隐藏所有票据信息
    $("#queryBudget").hide();				//隐藏所有收支信息
    $("#addBudget").hide();					//隐藏添加收支信息
    $("#addOption").hide();					//隐藏添加校区、项目、票据选项
    $("#accountUpdate").hide();			//隐藏修改账户信息
    $("#accountRegister").hide();			//隐藏注册账户信息
    $("#accountAddShcollAll").hide();// 隐藏所有修改校区
    $("#addBill").hide();					//隐藏添加账单
    $("#addProject").hide();				//隐藏添加项目
    $("#addSchool").hide();					//隐藏添加学校
    $("#userManage").hide();				//隐藏用户列表
    $("#addUserPane").hide();				//隐藏添加用户
    $("#updatePasswordPane").hide();		//隐藏更新密码
}

/************点击添加收支信息时触发********************/
function addBudgetAll(){
    $("#addBudget").show(); 				//显示添加收支信息
    $("#queryBill").hide();						//隐藏所有票据信息
    $("#queryItem").hide();					//隐藏所有项目信息
    $("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
    $("#querySchool").hide();				//隐藏所有校区信息
    $("#queryBudget").hide();				//隐藏所有收支信息
    $("#addOption").hide();					//隐藏添加校区、项目、票据选项
    $("#accountUpdate").hide();			//隐藏修改账户信息
    $("#accountRegister").hide();			//隐藏注册账户信息
    $("#accountAddShcollAll").hide();// 隐藏所有修改校区
    $("#addBill").hide();					//隐藏添加账单
    $("#addProject").hide();				//隐藏添加项目
    $("#addSchool").hide();					//隐藏添加学校
    $("#userManage").hide();				//隐藏用户列表
    $("#addUserPane").hide();				//隐藏添加用户
    $("#updatePasswordPane").hide();		//隐藏更新密码
}

/************点击新增校区、项目、票据选项时触发********************/
function addOptionAll(){
    $("#addOption").show(); 				//显示添加校区、项目、票据选项
    $("#querySchool").hide();				//隐藏所有校区信息
    $("#queryBill").hide();						//隐藏所有票据信息
    $("#queryItem").hide();					//隐藏所有项目信息
    $("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
    $("#queryBudget").hide();				//隐藏所有收支信息
    $("#addBudget").hide();					//隐藏添加收支信息
    $("#accountUpdate").hide();			//隐藏修改账户信息
    $("#accountRegister").hide();			//隐藏注册账户信息
    $("#accountAddShcollAll").hide();// 隐藏所有修改校区
    $("#addBill").hide();					//隐藏添加账单
    $("#addProject").hide();				//隐藏添加项目
    $("#addSchool").hide();					//隐藏添加学校
    $("#userManage").hide();				//隐藏用户列表
    $("#addUserPane").hide();				//隐藏添加用户
    $("#updatePasswordPane").hide();		//隐藏更新密码
}

/************点击修改账户信息时触发********************/
function accountUpdateAll(){
    $("#accountUpdate").show(); 		//显示修改账户信息
    $("#querySchool").hide();				//隐藏所有校区信息
    $("#queryItem").hide();					//隐藏所有项目信息
    $("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
    $("#queryBudget").hide();				//隐藏所有收支信息
    $("#addBudget").hide();					//隐藏添加收支信息
    $("#addOption").hide();					//隐藏添加校区、项目、票据选项
    $("#queryBill").hide();						//隐藏所有票据信息
    $("#accountRegister").hide();			//隐藏注册账户信息
    $("#accountAddShcollAll").hide();// 隐藏所有修改校区
    $("#addBill").hide();					//隐藏添加账单
    $("#addProject").hide();				//隐藏添加项目
    $("#addSchool").hide();					//隐藏添加学校
    $("#userManage").hide();				//隐藏用户列表
    $("#addUserPane").hide();				//隐藏添加用户
    $("#updatePasswordPane").hide();		//隐藏更新密码
}

/************点击所有项目时触发********************/
function accountRegisterAll(){
    $("#accountRegister").show(); 		//显示注册账户信息
    $("#querySchool").hide();				//隐藏所有校区信息
    $("#queryBill").hide();						//隐藏所有票据信息
    $("#queryBudget").hide();				//隐藏所有收支信息
    $("#addBudget").hide();					//隐藏添加收支信息
    $("#addOption").hide();					//隐藏添加校区、项目、票据选项
    $("#accountUpdate").hide();			//隐藏修改账户信息
    $("#queryItem").hide();					//隐藏所有项目信息
    $("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
    $("#accountAddShcollAll").hide();       // 隐藏所有修改校区
    $("#addBill").hide();					//隐藏添加账单
    $("#addProject").hide();				//隐藏添加项目
    $("#addSchool").hide();					//隐藏添加学校
    $("#userManage").hide();				//隐藏用户列表
    $("#addUserPane").hide();				//隐藏添加用户
    $("#updatePasswordPane").hide();		//隐藏更新密码
}
/***修改所有校区密码***/
function accountAddShcollAll(){
	$("#accountAddShcollAll").show();// 显示所有修改校区
	$("#querySchool").hide();				//隐藏所有校区信息
	$("#queryBill").hide();						//隐藏所有票据信息
	$("#queryBudget").hide();				//隐藏所有收支信息
	$("#addBudget").hide();					//隐藏添加收支信息
	$("#addOption").hide();					//隐藏添加校区、项目、票据选项
	$("#accountUpdate").hide();			//隐藏修改账户信息
	$("#queryItem").hide();					//隐藏所有项目信息
	$("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
	$("#accountRegister").hide(); 		//隐藏注册账户信息
	$("#addBill").hide();					//隐藏添加账单
	$("#addProject").hide();				//隐藏添加项目
	$("#addSchool").hide();					//隐藏添加学校
	$("#userManage").hide();				//隐藏用户列表
	$("#addUserPane").hide();				//隐藏添加用户
	$("#updatePasswordPane").hide();		//隐藏更新密码
}

/**
 * 时间：2015-9-16 15:28:11
 * 用途：用于增加新的项目、学校、票据
 */
/************添加项目********************/
function addNewProject(){
	$("#addProject").show();				//显示添加项目
	$("#addSchool").hide();					//隐藏添加学校
	$("#addBill").hide();					//隐藏添加账单
	$("#accountAddShcollAll").hide();		//隐藏所有修改校区
	$("#querySchool").hide();				//隐藏所有校区信息
	$("#queryBill").hide();				    //隐藏所有票据信息
	$("#queryBudget").hide();				//隐藏所有收支信息
	$("#addBudget").hide();					//隐藏添加收支信息
	$("#accountUpdate").hide();				//隐藏修改账户信息
	$("#queryItem").hide();					//隐藏所有项目信息
	$("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
	$("#accountRegister").hide(); 			//隐藏注册账户信息
	$("#userManage").hide();				//隐藏用户列表
	$("#addUserPane").hide();				//隐藏添加用户
	$("#updatePasswordPane").hide();		//隐藏更新密码
}

/************添加学校********************/
function addNewSchool(){
	$("#addSchool").show();					//显示添加学校
	$("#addProject").hide();				//隐藏添加项目
	$("#addBill").hide();					//隐藏添加账单
	$("#accountAddShcollAll").hide();		//隐藏所有修改校区
	$("#querySchool").hide();				//隐藏所有校区信息
	$("#queryBill").hide();				    //隐藏所有票据信息
	$("#queryBudget").hide();				//隐藏所有收支信息
	$("#addBudget").hide();					//隐藏添加收支信息
	$("#accountUpdate").hide();				//隐藏修改账户信息
	$("#queryItem").hide();					//隐藏所有项目信息
	$("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
	$("#accountRegister").hide(); 			//隐藏注册账户信息
	$("#userManage").hide();				//隐藏用户列表
	$("#addUserPane").hide();				//隐藏添加用户
	$("#updatePasswordPane").hide();		//隐藏更新密码
}

/************添加账单********************/
function addNewBill(){
	$("#addBill").show();					//显示添加账单
	$("#addProject").hide();				//隐藏添加项目
	$("#addSchool").hide();					//隐藏添加学校
	$("#accountAddShcollAll").hide();		//隐藏所有修改校区
	$("#querySchool").hide();				//隐藏所有校区信息
	$("#queryBill").hide();				    //隐藏所有票据信息
	$("#queryBudget").hide();				//隐藏所有收支信息
	$("#addBudget").hide();					//隐藏添加收支信息
	$("#accountUpdate").hide();				//隐藏修改账户信息
	$("#queryItem").hide();					//隐藏所有项目信息
	$("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
	$("#accountRegister").hide(); 			//隐藏注册账户信息
	$("#userManage").hide();				//隐藏用户列表
	$("#addUserPane").hide();				//隐藏添加用户
	$("#updatePasswordPane").hide();		//隐藏更新密码
}

/************用户列表********************/
function userListAll(){
	$("#userManage").show();				//显示用户列表
	$("#addBill").hide();					//显示添加账单
	$("#addProject").hide();				//隐藏添加项目
	$("#addSchool").hide();					//隐藏添加学校
	$("#accountAddShcollAll").hide();		//隐藏所有修改校区
	$("#querySchool").hide();				//隐藏所有校区信息
	$("#queryBill").hide();				    //隐藏所有票据信息
	$("#queryBudget").hide();				//隐藏所有收支信息
	$("#addBudget").hide();					//隐藏添加收支信息
	$("#accountUpdate").hide();				//隐藏修改账户信息
	$("#queryItem").hide();					//隐藏所有项目信息
	$("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
	$("#accountRegister").hide(); 			//隐藏注册账户信息
	$("#addUserPane").hide();				//隐藏添加用户
	$("#updatePasswordPane").hide();		//隐藏更新密码
}

/************添加用户********************/
function addUserAction(){
    $("#addUserPane").show();				//显示添加用户
	$("#userManage").hide();				//显示用户列表
	$("#addBill").hide();					//显示添加账单
	$("#addProject").hide();				//隐藏添加项目
	$("#addSchool").hide();					//隐藏添加学校
	$("#accountAddShcollAll").hide();		//隐藏所有修改校区
	$("#querySchool").hide();				//隐藏所有校区信息
	$("#queryBill").hide();				    //隐藏所有票据信息
	$("#queryBudget").hide();				//隐藏所有收支信息
	$("#addBudget").hide();					//隐藏添加收支信息
	$("#accountUpdate").hide();				//隐藏修改账户信息
	$("#queryItem").hide();					//隐藏所有项目信息
	$("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
	$("#accountRegister").hide(); 			//隐藏注册账户信息
	$("#updatePasswordPane").hide();		//隐藏更新密码
}

/************更新密码********************/
function updatePasswordAction(){
    $("#updatePasswordPane").show();		//显示更新密码
    $("#addUserPane").hide();				//显示添加用户
	$("#userManage").hide();				//显示用户列表
	$("#addBill").hide();					//显示添加账单
	$("#addProject").hide();				//隐藏添加项目
	$("#addSchool").hide();					//隐藏添加学校
	$("#accountAddShcollAll").hide();		//隐藏所有修改校区
	$("#querySchool").hide();				//隐藏所有校区信息
	$("#queryBill").hide();				    //隐藏所有票据信息
	$("#queryBudget").hide();				//隐藏所有收支信息
	$("#addBudget").hide();					//隐藏添加收支信息
	$("#accountUpdate").hide();				//隐藏修改账户信息
	$("#queryItem").hide();					//隐藏所有项目信息
	$("#queryTicketType").hide(); 		    //隐藏所有票据类型信息
	$("#accountRegister").hide(); 			//隐藏注册账户信息
}

