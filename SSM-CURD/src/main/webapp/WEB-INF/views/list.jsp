<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	/*这里就是/SSM-CURD  */
	String  contextPath = request.getContextPath();
	request.setAttribute("APP_PATH", contextPath);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--
	web路径 ：
	不以  / 开始的相对路径，找资源，以当前资源的路径为基准，经常容易出现问题
	以  /  开始的相对路径，找资源，以服务器的路径为基准 http://localhost:8080

  -->
<!--引入jq   ${APP_PATH} 前面带/ 后面不带 / 所以就是http://localhost:8080/SSM-CURD/static/js/jquery-2.0.0.min.js-->
<script src="${APP_PATH }/static/js/jquery-2.0.0.min.js"></script>
<!--1.引入样式 bootstrap.css,注意这个引进来的 不加 rel=stylesheet还不行！！！-->
<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<!--2.引入bootstrap.js,需要jq的支持（前面）  -->
<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<!--标题  -->
		<div class="row">
			<div class=".col-md-12">
				<h1>SSM_CRUD</h1>
			</div>
		</div>
		<!--按钮  -->
		<div class="row">
			<div class="col-md-2 col-md-offset-10">
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!--表格  -->
		<div class="row">
			<div class=".col-md-12">
				<table class="table table-hover">
					<tr>
						<th>ID</th>
						<th>Emp_Name</th>
						<th>Gender</th>
						<th>Email</th>
						<th>Dept_Name</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageInfo.list }" var="emp">
						<tr>
							<td>${emp.empId }</td>
							<td>${emp.empName}</td>
							<td>${emp.gender=="M"?"男":"女"}</td>
							<td>${emp.email }</td>
							<td>${emp.department.deptName}</td>
							<td>
								<button class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑
								</button>
								<button class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<!--分页  -->
		<div class="row">
			<!--分页文字信息  -->
			<div class="col-md-6">
				当前第${pageInfo.pageNum }页，总共${pageInfo.pages}页，总记录数为${pageInfo.total}
			</div>
			<!--分页条信息  -->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				  <ul class="pagination">
				  	<li><a href="${APP_PATH}/emps?pageNum=1">首页</a></li>
				  	<c:if test="${pageInfo.hasPreviousPage}">
				  		<li>
					      <a href="${APP_PATH}/emps?pageNum=${pageInfo.pageNum-1}" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
				   		</li>
				  	</c:if>
				    
				    <c:forEach items="${pageInfo.navigatepageNums}" var="page_Num"> 
				    	 <c:if test="${page_Num == pageInfo.pageNum}">
				    	 	<li class="active"><a href="#">${page_Num}</a></li>
				    	 </c:if>
				    	 <c:if test="${page_Num != pageInfo.pageNum}">
				    	 	<li><a href="${APP_PATH}/emps?pageNum=${page_Num}">${page_Num}</a></li>
				    	 </c:if>
				    </c:forEach>
				    <!--如果有下一页我才显示下一页-->
				    <c:if test="${pageInfo.hasNextPage}">
				    	 <li>
						      <a href="${APP_PATH}/emps?pageNum=${pageInfo.pageNum+1}" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
				   		 </li>
				    </c:if>
				    <li><a href="${APP_PATH}/emps?pageNum=${pageInfo.total}">末页</a></li>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>