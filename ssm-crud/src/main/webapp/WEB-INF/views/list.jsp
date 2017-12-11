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
<title>员工信息显示页面</title>
<script type="text/javascript" src="${APP_PATH }/js/jquery.js"></script>
<!-- 引入 bootstrap css 样式 -->
<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet"/>
<!-- 引入 bootstrap js -->
<script type="text/javascript" src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	
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
				<button class="btn btn-primary">添加</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 信息展示表格 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>#</th>
						<th>Name</th>
						<th>Gender</th>
						<th>DeptName</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageInfo.list }" var="emp">
						<tr>
							<td>${emp.id }</td>
							<td>${emp.name }</td>
							<td>${emp.gender == "M" ? "男" : "女" }</td>
							<td>${emp.department.deptName }</td>
							<td>
								<button class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-pencil"></span>
									编辑
								</button>
								<button class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-trash"></span>
									删除
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<!-- 分页信息 -->
		<div class="row">
			<div class="col-md-6">
				<font size="2">当前处于第${pageInfo.pageNum }页,一共${pageInfo.pages }页,共${pageInfo.total }条数据</font>
			</div>
			<div class="col-md-6">
				<nav aria-label="Page navigation">
					  <ul class="pagination">
					  	 <li>
					      <a href="${APP_PATH }/emps?pn=1">
					      	 首页
					      </a>
					    </li>
					    <!-- 判断是否有前一页 -->
					    <c:if test="${pageInfo.hasPreviousPage }">
					    	<li>
						    	<a href="${APP_PATH }/emps?pn=${pageInfo.prePage}" aria-label="Previous">
					        		<span aria-hidden="true">&laquo;</span>
					      		</a>
					      	</li>
						</c:if>
						
					     <!-- 判断显示的条数 -->
					    <c:forEach items="${pageInfo.navigatepageNums }" var="num">
					    	<c:if test="${pageInfo.pageNum == num}">
					    		<li class="active"><a href="${APP_PATH }/emps?pn=${num}">${num }</a></li>
					    	</c:if>
					    	<c:if test="${pageInfo.pageNum != num}">
					    		<li><a href="${APP_PATH }/emps?pn=${num}">${num }</a></li>
					    	</c:if>
					    </c:forEach>
					    
					    <!-- 判断是否有下一页 -->
					    <c:if test="${pageInfo.hasNextPage }">
					    	<li>
					      		<a href="${APP_PATH }/emps?pn=${pageInfo.nextPage}" aria-label="Next">
					       			 <span aria-hidden="true">&raquo;</span>
					      		</a>
					   		 </li>
					    </c:if>
					   
					    <li>
					      <a href="${APP_PATH }/emps?pn=${pageInfo.pages}">
					      	 末页
					      </a>
					    </li>
					  </ul>
				</nav>
			</div>
		</div>
		
	</div>
	
</body>
</html>