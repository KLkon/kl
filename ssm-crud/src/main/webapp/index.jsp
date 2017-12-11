<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>初始页</title>
<script type="text/javascript" src="${APP_PATH }/js/jquery.js"></script>
<!-- 引入 bootstrap css 样式 -->
<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"/>
<!-- 引入 bootstrap js -->
<script type="text/javascript" src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	
	<!-- 修改数据 Modal -->
	<div class="modal fade" id="updateEmpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel2">员工修改</h4>
	      </div>
	      <div class="modal-body">
	      	<!-- 数据表单 -->
	        <form class="form-horizontal">
			  <!-- 姓名  文本框-->
			  <div class="form-group">
			  	<!-- 描述 -->
			  	<label for="inputUpdateName" class="col-sm-2 control-label">Name</label>
			    <!-- 输入文本框 -->
			    <div class="col-sm-10">
			      <!-- 名字允许修改 -->
			      <!-- <input type="text" name="name" class="form-control" id="inputUpdateName" placeholder="Name"> -->
			      <!-- 名字不允许修改 -->
			      <p class="form-control-static" id="pUpdateName"></p>
			      <span id="helpBlockUpdate" class="help-block"></span>
			    </div>
			  </div>
			  <!-- 性别  选择框-->
			  <div class="form-group">
			  	  <!-- 描述 -->
				  <label class="col-sm-2 control-label">Gender</label>
				  <!-- 单选框 -->
				  <div class="col-sm-10">
					  <label class="radio-inline">
						  <input type="radio" name="gender" id="genderUpdate1" value="M" checked="checked"> 男
					  </label>
					  <label class="radio-inline">
						  <input type="radio" name="gender" id="genderUpdate2" value="F"> 女
					  </label>
				  </div>
			  </div>
			  <!-- 部门选择下拉框 -->
			  <div class="form-group">
			  		<!-- 描述 -->
			  		<label for="deptName" class="col-sm-2 control-label">DeptName</label>
			  		<!-- 下拉框 -->
			  		<div class="col-sm-5">
				  		<select class="form-control" name="dId" id="deptUpdateNameSelect">
						  
						</select>
					</div>
			  </div>
			 </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="updateEmpBtn">修改</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<!-- -------------------------------------------------------------------------- -->
	<!-- 添加数据 Modal -->
	<div class="modal fade" id="addEmpModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">添加员工</h4>
	      </div>
	      <div class="modal-body">
	      	<!-- 数据表单 -->
	        <form class="form-horizontal">
			  <!-- 姓名  文本框-->
			  <div class="form-group">
			  	<!-- 描述 -->
			  	<label for="inputName" class="col-sm-2 control-label">Name</label>
			    <!-- 输入文本框 -->
			    <div class="col-sm-10">
			      <input type="text" name="name" class="form-control" id="inputName" placeholder="Name">
			      <span id="helpBlock" class="help-block"></span>
			    </div>
			  </div>
			  <!-- 性别  选择框-->
			  <div class="form-group">
			  	  <!-- 描述 -->
				  <label class="col-sm-2 control-label">Gender</label>
				  <!-- 单选框 -->
				  <div class="col-sm-10">
					  <label class="radio-inline">
						  <input type="radio" name="gender" id="gender1" value="M" checked="checked"> 男
					  </label>
					  <label class="radio-inline">
						  <input type="radio" name="gender" id="gender2" value="F"> 女
					  </label>
				  </div>
			  </div>
			  <!-- 部门选择下拉框 -->
			  <div class="form-group">
			  		<!-- 描述 -->
			  		<label for="deptName" class="col-sm-2 control-label">DeptName</label>
			  		<!-- 下拉框 -->
			  		<div class="col-sm-5">
				  		<select class="form-control" name="dId" id="deptNameSelect">
						  
						</select>
					</div>
			  </div>
			 </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="saveEmpBtn">保存</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<!-- -------------------------------------------------------------------------- -->
	<!-- 主页信息 -->
	<div class="container">
		
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 两个按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id="addEmp">添加</button>
				<button class="btn btn-danger" id="deleteEmps">删除</button>
			</div>
		</div>
		<!-- 信息展示表格 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="check_all" />
							</th>
							<th>ID</th>
							<th>Name</th>
							<th>Gender</th>
							<th>DeptName</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		<!-- 分页信息 -->
		<div class="row">
			<!-- 分页信息 -->
			<div class="col-md-6" id="page_info"></div>
			<!-- 分页条 -->
			<div class="col-md-6" id="page_nav"></div>
		</div>
		
	</div>
	
	<script type="text/javascript">
		//**********************************************************************
		//显示总页面
		//一个变量，总记录数，用于跳转到最后一页
		var totalRecord;
		//当前页面
		var currentPage;
	
		//页面加载完成直接发送 AJAX 请求
		$(function() {
			to_page(1);
		});
			
			//将 AJAX 查询所有数据方法抽取出来
			function to_page(pn) {
				//发送请求
				$.ajax({
					url:"${APP_PAHT}/ssm-crud/emps",
					data:"pn=" + pn,
					type:"GET",
					success:function(result) {
						//浏览器控制台打印信息
						//console.log(result);
						//解析并显示员工信息
						build_emps_table(result);
						//解析显示分页数据
						build_page_info(result)
						build_page_nav(result);
					}
				});
			}
			
			//-------------------------------------------------------------------------
			//解析表格方法
			function build_emps_table(result) {
				$("#emps_table tbody").empty();
				var emps = result.extend.pageInfo.list;
				$.each(emps,function(index, item) {
					var checkbox =$("<td><input type='checkbox' class='check_one' /></td>");
					var empId = $("<td></td").append(item.id);
					var empName = $("<td></td>").append(item.name);
					var empGender = $("<td></td>").append(item.gender =='M' ? "男" : "女");
					var deptName = $("<td></td>").append(item.department.deptName);
					//按钮
					/**
					    <button class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-pencil"></span>
									编辑
								</button>
								<button class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-trash"></span>
									删除
								</button>
					*/
					var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
										.append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
										.append("编辑");
					//为编辑按钮添加一个 员工 ID 属性
					editBtn.attr("emp-id", item.id);
					var deleteBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
										.append($("<span></span>").addClass("glyphicon glyphicon-trash"))
										.append("删除");
					//为删除按钮添加一个员工 ID 属性
					deleteBtn.attr("delete-id", item.id);
					var btn = $("<td></td>").append(editBtn).append(" ").append(deleteBtn);
					$("<tr></tr>").append(checkbox)
								  .append(empId)
								  .append(empName)
								  .append(empGender)
								  .append(deptName)
								  .append(btn)
								  .appendTo("#emps_table tbody");
				});
			}
			
			//-------------------------------------------------------------------------
			//解析分页信息
			function build_page_info(result) {
				$("#page_info").empty();
				var pageNum = result.extend.pageInfo.pageNum;
				var pages = result.extend.pageInfo.pages;
				var total = result.extend.pageInfo.total;
				$("#page_info").append("当前第" + pageNum + "页,共" + pages + "页,共" + total + "条数据");
				totalRecord = total;
				currentPage = pageNum;
			}
			
			//解析分页条,并且判断页码的合理性
			function build_page_nav(result) {
				$("#page_nav").empty();
				var nav = $("<nav></nav>");
				var ul = $("<ul></ul>").addClass("pagination");
				var firstPage = $("<li></li>").append($("<a></a>").attr("href", "#").append("首页"));
				var prePage = $("<li></li>").append($("<a></a>").attr("href", "#").append($("<span></span>").append("&laquo;")));
				
				//判断是否有前一页
				if(result.extend.pageInfo.hasPreviousPage == false) {
					firstPage.addClass("disabled");
					prePage.addClass("disabled");
				} else {
					firstPage.click(function() {
						to_page(1);
					});
					prePage.click(function() {
						to_page(result.extend.pageInfo.prePage);
					});
				}
				
				var nextPage = $("<li></li>").append($("<a></a>").attr("href", "#").append($("<span></span>").append("&raquo;")));
				var lastPage = $("<li></li>").append($("<a></a>").attr("href", "#").append("末页"));
				
				//判断是否有后一页
				if(result.extend.pageInfo.hasNextPage == false) {
					nextPage.addClass("disabled");
					lastPage.addClass("disabled");
				} else {
					nextPage.click(function() {
						to_page(result.extend.pageInfo.nextPage);
					});
					lastPage.click(function() {
						to_page(result.extend.pageInfo.pages);
					});
				}
				
				//添加首页与前一页
				ul.append(firstPage).append(prePage);
				//遍历页码提示，添加到 ul 中
				$.each(result.extend.pageInfo.navigatepageNums, function(index, item) {
					var num = $("<li></li>").append($("<a></a>").attr("href", "#").append(item));
					if(result.extend.pageInfo.pageNum == item) {
						num.addClass("active");
					}
					num.click(function() {
						to_page(item);
					});
					ul.append(num);
				});
				
				//添加后一页与末页 
				ul.append(nextPage).append(lastPage);
				//ul 添加到 nav 中
				nav.append(ul);
				//最后将 nav 添加到 page_nav 中
				nav.appendTo("#page_nav");
			}
			
			//**********************************************************************
			//验证数据的合法性
			//重置方法
			function reset(ele) {
				 //重置表单
				 $(ele)[0].reset();
				 //清空表单下所有元素的样式
				 $(ele).find("*").removeClass("has-error has-success");
				 $(ele).find("#helpBlock").text("");
			 }
			//添加员工,点击新增按钮弹出模态框
			$("#addEmp").click(function() {
				//弹出框时清空上次的输入的所有数据，即重置表单 form
				//调用重置方法
				/* $("#addEmpModal form")[0].reset();
				$("#inputName").parent().removeClass("has-error has-success");
				$("#helpBlock").empty(); */
				reset("#addEmpModal form");
				//发送 AJAX 请求查出部门信息便于模态框下拉列表显示
				getDepts("#addEmpModal select");
				
				//弹出模态框
				$("#addEmpModal").modal({
					backdrop:false
				});
			});
		    //查出部门信息,传入模态框 id 下的 select 标签，便于解耦
			function getDepts(modalId) {
				//清空之前下拉列表的值
		    	$(modalId).empty();
				$.ajax({
					url:"${APP_PATH}/depts",
					type:"GET",
					success:function(result) {
						//显示在控制台
						//console.log(result);
						//$("#addEmpModal select").append("<option></option>");
						$.each(result.extend.depts, function(index, item) {
							 //若没有传参，即没有 item 参数，则用 this 表示当前 dept 对象
							var deptOption = $("<option></option>").append(item.deptName).attr("value", item.deptId);
							 deptOption.appendTo(modalId);
						});
						
					}
				});
			}
			
		    //--------------------------------------------------------------------------
		    //保存信息前动态检测输入的姓名信息是否在数据库中存在
		    $("#inputName").change(function() {
		    	//alert("----------------------------");
		    	var empName = this.value;
		    	
		    	$.ajax({
		    		url:"${APP_PATH}/checkName",
		    		data:"empName=" + empName,
		    		type:"POST",
		    		success:function(result) {
		    			if(result.code == 1) {
		    				//alert("用户名可以使用");
		    				validateMessage("#inputName", "#helpBlock", "success", "用户名可以使用");
		    				$("#saveEmpBtn").attr("ajax-validate","success");
		    			} else {
		    				//alert("用户名不可以使用");
		    				validateMessage("#inputName", "#helpBlock", "error", result.extend.validate_msg);
		    				$("#saveEmpBtn").attr("ajax-validate","error");
		    			}
		    		}
		    	});
		    });
		    
			//-------------------------------------------------------------------------
			//总添加保存操作
			$("#saveEmpBtn").click(function() {
				 //测试 alert("成功过");
				 //调用 jQuery 验证方法，验证数据的合理性
				if(!validateForm()) {
					 return false;
				 } 
				 //AJAX 判断输入姓名数据库中是否存在
				 //var ajaxValidate = $("#saveEmpBtn").attr("ajax-validate");
				if($(this).attr("ajax-validate") != "success") {
					validateMessage("#inputName", "#helpBlock", "error", "用户名不可用");
					return false;
				 } 
				
				//调用保存方法
				 saveEmp();
			});
			//验证方法
			function validateForm() {
				//获取姓名
				var empName = $("#inputName").val();
				//正则表达式规定姓名规则
				var regName = /(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
				//正则姓名检测
				//若不通过
				
				if(!regName.test(empName)) {
					//alert("用户名应该是3-16位英文或者2-5位中文");
					//在模态框内显示信息
					/* $("#inputName").parent().addClass("has-error");
					$("#helpBlock").append("用户名应该是3-16位英文或者2-5位中文"); 
					添加数据第二种方式
					$("#inputName").next("span").text("用户名应该是3-16位英文或者2-5位中文");
					*/
					
					validateMessage("#inputName", "#helpBlock", "error", "用户名应该是3-16位英文或者2-5位中文");
					return false;
				}else {
					/* $("#inputName").parent().addClass("has-success");
					$("#helpBlock").append(""); */
					
					validateMessage("#inputName", "#helpBlock", "success", "");
				}
				return true;
			}
			//显示的验证信息
			function validateMessage(eleInput,eleSpan, status, msg) {
				//清除之前的状态
				$(eleInput).parent().removeClass("has-error has-success");
				$(eleSpan).empty();
				if("success" == status) {
					$(eleInput).parent().addClass("has-success");
					$(eleSpan).text(msg);
				} else if("error" == status) {
					$(eleInput).parent().addClass("has-error");
					$(eleSpan).text(msg);
				}
			}
			//保存方法
			function saveEmp() {
				$.ajax({
					url:"${APP_PATH}/emp",
					data:$("#addEmpModal form").serialize(),
					type:"POST",
					success:function(result) {
						//测试
						//alert("成功");
						//保存时后端 JSR303 校验
						validateByJSR(result);
					}
				});
			}
			//JSR303 校验
			function validateByJSR(result) {
				if(result.code == 1) {
					//alert(result.msg);
					//关闭模态框
				$("#addEmpModal").modal('hide');
				//跳转到最后一页进行显示
				to_page(totalRecord);
				} else {
					//显示失败信息
					if(result.extend.errorFields != undefined) {
						validateMessage("#inputName", "#helpBlock", "error", result.extend.errorFields);
					}
				}
			}
			
		//**********************************************************************		
		//修改
		//编辑，弹出修改模态框.on(事件名，按钮名， 回调函数)
		$(document).on("click", ".edit_btn", function() {
			//alert("成功");
			//获取员工信息
			getEmp($(this).attr("emp-id"));
			//获取部门信息以便模态框显示
			getDepts("#updateEmpModal select");
			//为修改按钮加上一个属性存放 id
			$("#updateEmpBtn").attr("emp-id", $(this).attr("emp-id"));
			//调用编辑模态框
			$("#updateEmpModal").modal({
				backdrop:false
			});
		});
		//获取员工信息
		function getEmp(empId) {
			//alert(empId);
			$.ajax({
				url:"${APP_PATH}/emp/" + empId,
				type:"GET",
				success:function(result) {
					//取出员工数据
					var empData = result.extend.emp;
					//为姓名栏添值
					$("#pUpdateName").text(empData.name);
					//性别,单选框
					$("#updateEmpModal input[name=gender]").val([empData.gender]);
					//部门，下拉列表
					$("#updateEmpModal select").val([empData.dId]);
				}
			});
		}
		//修改提交按钮
		$("#updateEmpBtn").click(function() {
			//若有数据需要验证则先验证
			
			//发送 ajax 请求进行数据修改
			$.ajax({
				url:"${APP_PATH}/emp/" + $(this).attr("emp-id"),
				data:$("#updateEmpModal form").serialize(),
				type:"PUT",
				success:function(result) {
					//alert(result.msg);
					
					if(result.code == 1) {
						//关闭模态框
						$("#updateEmpModal").modal("hide");
						//页面到当前修改页面
						to_page(currentPage);
					}
				}
			});
		});
		
		//****************************************************************
		//删除
		//单个删除
		$(document).on("click", ".delete_btn", function() {
			//获取需删除的姓名
			var empName = $(this).parents("tr").find("td:eq(2)").text();
			//alert(empName);
			//弹出确认框，是否删除
			var b = confirm("是否删除 " + empName + " 的信息？");
			if(b) {
				//alert("删除");
				//发送删除 ajax 请求
				$.ajax({
					url:"${APP_PATH}/emp/" + $(this).attr("delete-id"),
					type:"DELETE",
					success:function(result) {
						//alert(result.msg);
						if(result.code == 1) {
							//回到本页
							to_page(currentPage);
						} 
					}
				});
			}
		});
			
		//多个删除
		
		//多选框与单选框。实现多选框选中，则单选框全部选中。单选框选满，多选框自动选。
		
		//多选框
		$("#check_all").click(function() {
			//多选框是否选中状态 alert($(this).prop("checked"));
			if($(this).prop("checked")) {
				$(".check_one").prop("checked", true);
			} else {
				$(".check_one").prop("checked", false);
			}
		});
		
		//单选框
		$(document).on("click", ".check_one", function() {
			//alert($(this).prop("checked"));
			//获取当前页面单选框选中的个数
			var count = $(".check_one:checked").length;
			//alert(count);
			//选中的个数与总个数相比较
			var flag = count == $(".check_one").length;
			//若相等则说明单选全选满了，多选框自动选上
			$("#check_all").prop("checked", flag);
		});
		
		//批量删除
		$("#deleteEmps").click(function() {
			//alert("批量删除");
			//获取所有选中的姓名 $("#check_on:checked")
			var empNames = "";
			//获取所有选中的 id
			var empIds = "";
			$.each($(".check_one:checked"), function() {
				//alert($(this).parents("tr").find("td:eq(2)").text());
				empNames += $(this).parents("tr").find("td:eq(2)").text() + ",";
				empIds += $(this).parents("tr").find("td:eq(1)").text() + "-";
			});
			//去除名字字符串多余的 逗号
			empNames = empNames.substring(0, empNames.length-1);
			//去除 empIds 中多余的 -
			empIds = empIds.substring(0, empIds.length-1);
			//alert(empNames);
			if(empNames.length > 0) {
				if(confirm("确认删除【"+ empNames + "】 的信息吗？")) {
					//若确认发送批量删除 ajax 请求
					$.ajax({
						url:"${APP_PATH}/emp/" + empIds,
						type:"DELETE",
						success:function(result) {
							if(result.code == 1) {
								$("#check_all").prop("checked",false);
								to_page(currentPage);
							}
						}
					});
				}
			}else {
				alert("没有选择需删除对象");
			}
			
		});
		
		
	</script>
	
</body>
</html>