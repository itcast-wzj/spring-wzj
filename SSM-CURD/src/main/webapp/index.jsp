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
<!--1.引入样式 bootstrap.css-->
<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<!--2.引入bootstrap.js,需要jq的支持（前面）  -->
<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 员工添加的模态框Modal -->
	<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">员工添加</h4>
	      </div>
	      <div class="modal-body">
	      	<!--模态框里面的内容是一个表单  -->
	      	<form class="form-horizontal">
	      	  <!--1.员工姓名  -->
			  <div class="form-group">
			    <label for="emp_name_input" class="col-sm-2 control-label">员工姓名</label>
			    <div class="col-sm-10">
			      <input type="email" class="form-control" id="emp_name_input" name="empName" placeholder="empName">
			      <!--显示校验信息  -->
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <!--2.员工邮箱  -->
			  <div class="form-group">
			    <label for="emp_email_input" class="col-sm-2 control-label">员工邮箱</label>
			    <div class="col-sm-10">
			      <input type="email" class="form-control" id="emp_email_input" name="email" placeholder="itcast_wzj@163.com">
			      <!--显示校验信息  -->
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <!--3.员工性别，单选按钮  -->
			  <div class="form-group">
			    <label for="emp_gender_input" class="col-sm-2 control-label">性别</label>
			    <div class="col-sm-10">
				    <label class="radio-inline">
						<input type="radio" name="inlineRadioOptions" id="gender_add_input1" name="gender" value="M" checked="checked"> 男
					</label>
					<label class="radio-inline">
					  <input type="radio" name="inlineRadioOptions" id="gender_add_input2" name="gender" value="F"> 女
					</label>
			    </div>
			  </div>
			  <!--3.员工所属部门，下拉框 -->
			  <div class="form-group">
			    <label for="deptName_add_select" class="col-sm-2 control-label">部门</label>
			    <div class="col-sm-4">
			    	<!--部门提交部门id即可  -->
					<select class="form-control" name="deptId" id="deptName_add_select">
					  
					</select>
			    </div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
	      </div>
	    </div>
	  </div>
	</div>
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
				<button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!--表格  -->
		<div class="row">
			<div class=".col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th>ID</th>
							<th>Emp_Name</th>
							<th>Gender</th>
							<th>Email</th>
							<th>Dept_Name</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					
					</tbody>
				</table>
			</div>
		</div>
		<!--分页  -->
		<div class="row">
			<!--分页文字信息  -->
			<div class="col-md-6" id="page_info_area">
			
			</div>
			<!--分页条信息  -->
			<div class="col-md-6" id="page_nav_area">
				
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//定义一个全局变量,总记录数 
	var totalRecored;

	$(function(){
		//默认当前页是1
		to_page(1);
	});
	
	function to_page(pageNum){
		$.ajax({
			   url: "${APP_PATH}/emps",
			   data: "pageNum="+pageNum,
			   type: "GET",
			   success: function(result){
				   console.log(result);
			    	//解析并显示员工数据
				   build_emps_table(result);
			    	//解析并显示分页信息
				   build_page_info(result);
			    	//解析并显示分页条
			       build_page_nav(result);
			 }
		});
	}
	
	
	function build_emps_table(result){
		//清空表格中的tbody,而不是整个表格
		$("#emps_table tbody").empty();
		var emps = result.extend.pageInfo.list;
		//遍历员工列表,index是索引，item是每个emp
		$.each(emps,function(index,item){
			//创建一个单元格Td,并填充内容
			var empIdTd = $("<td></td>").append(item.empId);
			var empNameTd = $("<td></td>").append(item.empName);
			var genderTd = $("<td></td>").append(item.gender=='M'?"男":"女");
			var emailTd = $("<td></td>").append(item.email); 
			var deptNameTd = $("<td></td>").append(item.department.deptName);
			
			/*
				<button class="btn btn-primary btn-sm">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑
				</button>
			*/
			//添加两个编辑按钮
			var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm")
							.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
			var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm")
							.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
			//把两个按钮放一个单元格中,中间还空一个字符串
			var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
			//创建一个行,append方法执行完毕后还是返回原来的元素，所以可以链式编程
			$("<tr></tr>").append(empIdTd)
				.append(empNameTd)
				.append(genderTd)
				.append(emailTd)
				.append(deptNameTd)
				.append(btnTd)
				.appendTo("#emps_table tbody"); //最后将行添加到表格里
		});
	}
	
	//解析并显示分页信息
	function build_page_info(result){
		//清空分页信息
		$("#page_info_area").empty();
		$("#page_info_area").append("当前"+result.extend.pageInfo.pageNum+"页, 总共"+result.extend.pageInfo.pages+"页,总"+result.extend.pageInfo.total+"记录数");
		//赋值给全局变量
		totalRecored = result.extend.pageInfo.total;
	}
	
	//解析并显示分页条
	function build_page_nav(result){
		//清空分页条
		$("#page_nav_area").empty();
		//ul
		var ul = $("<ul></ul>").addClass("pagination");
		
		//首页
		var firstPageLi = $("<li></li>").append($("<a></a>").attr("href","#").append("首页"));
		//上一页
		var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));	
		//如果没有上一页,就首页和上一页不能点
		if(result.extend.pageInfo.hasPreviousPage == false){
			firstPageLi.addClass("disabled");
			prePageLi.addClass("disabled");
		}else{
			//为首页和前一页添加点击的事件
			firstPageLi.click(function(){
				to_page(1);
			});
			
			prePageLi.click(function(){
				to_page(result.extend.pageInfo.pageNum - 1);
			});
		}
		
		//添加首页和前一页
		ul.append(firstPageLi).append(prePageLi);
		
		//遍历所有导航页号,给ul添加页码提示
		$.each(result.extend.pageInfo.navigatepageNums,function(index,num){
			//如果当前页码等于遍历出来导航号，就高亮显示
			var numLi = $("<li></li>").append($("<a></a>").append(num));
			if(result.extend.pageInfo.pageNum == num){
				numLi.addClass("active");
			}
			//为每个导航页号,绑定一个click事件
			numLi.click(function(){
				to_page(num);
			});
			ul.append(numLi);
		});
		
		//下一页
		var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
		
		//末页
		var lastPageLi = $("<li></li>").append($("<a></a>").attr("href","#").append("末页"));
		
		//如果没有下一页, 就下一页和末页,不能点
		if(result.extend.pageInfo.hasNextPage == false){
			nextPageLi.addClass("disabled");
			lastPageLi.addClass("disabled");
		}else{
			//为下一页和末页添加点击事件
			nextPageLi.click(function(){
				to_page(result.extend.pageInfo.pageNum + 1);
			});
			
			lastPageLi.click(function(){
				to_page(result.extend.pageInfo.pages);
			});
		}
		
		//添加下一页和末页 
		ul.append(nextPageLi).append(lastPageLi);
		
		//构建nav元素, 将ul添加进去
		var navEle = $("<nav></nav>").append(ul);
		
		//再找到显示分页条的div,将nav添加进去
		navEle.appendTo("#page_nav_area");
		//或者这样写: $("#page_nav_area").append(navEle);
	}
	
	//重置表单数据和样式
	function reset_form(ele){
		//清除表单数据
		$(ele)[0].reset();
		//清除表单样式: 框的颜色和提示信息
		$(ele).find("*").removeClass("has-error has-success");
		$(ele).find(".help-block").text("");
	}
	
	//点击按钮,弹出一个模态框
	$("#emp_add_modal_btn").click(function(){
		//清除表单数据(表单重置（重置表单数据和样式）)
		//Jq中没有reset方法，所以转换成js的Dom对象
		/* $("#empAddModal form")[0].reset(); */
		reset_form("#empAddModal form");
		
		//发出ajax请求,查出部门信息,显示在下拉列表
		getDepts();
		
		$("#empAddModal").modal({
			//这个设置就是点击背景,模态框不会消失
			backdrop:"static"
		});
	});
	
	//查询所有部门信息,并显示到下拉列表
	function getDepts(){
		$.ajax({
			url:"${APP_PATH}/depts",
			type:"GET",
			success:function(result){
				//{"code":100,"msg":"处理成功","extend":{"depts":[{"deptId":1,"deptName":"开发部"},{"deptId":2,"deptName":"测试部"}]}}
				//遍历当前部门
				$.each(result.extend.depts,function(){//不写参数,this就相当于里面的元素
					var optionEle = $("<option></option>").append(this.deptName).attr("value",this.deptId);
					//获取select标签, 将构建的option元素添加进去
					optionEle.appendTo("#deptName_add_select");
				});
			}
		});
	}
	
	//点击按钮,可以添加
	$("#emp_save_btn").click(function(){
		//1.先对提交给服务器的数据进行校验
		if(!validate_add_form()){
			return false;
		}
		
		//2.校验用户名,如果不满足就返回false
		var flag = $("#emp_save_btn").attr("ajax-validate");
		if(flag == "error"){
			return false ;
		}
		
		//2.发送ajax请求保存员工
		//alert($("#empAddModal form").serialize()); 会将表单中的数据拼接好
		//empName=wangzhijian&email=itcast_wzj%40163.com&inlineRadioOptions=M&deptId=1
		$.ajax({
			url:"${APP_PATH}/emps",
			type:"POST", //因为restful风格,添加使用post
			data:$("#empAddModal form").serialize(),
			success:function(result){
				//alert(result.msg);
				//员工保存成功
				//1.关闭模态框
				$("#empAddModal").modal('hide');
				//2.发送ajax请求显示最后一页数据即可
				to_page(totalRecored)
			}
		});
	});
	
	//对表单数据进行校验
	function validate_add_form(){
		//校验用户名
		var empName = $("#emp_name_input").val();
		//就是可以a-zA-Z0-9_-这些东西组成的3-16  或者 中文两 个
		var regName = /^([a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
		if(!regName.test(empName)){
			/* alert("用户名可以是6-16位英文和数字的组合或者是2~5个中文"); */
			/* $("#emp_name_input").parent().addClass("has-error");
			$("#emp_name_input").next("span").text("用户名可以是6-16位英文和数字的组合或者是2~5个中文"); */
			show_validate_msg("#emp_name_input","error","用户名可以是6-16位英文和数字的组合或者是2~5个中文");
			return false; //不让往下执行发送ajax请求
		}else{
			/* $("#emp_name_input").parent().addClass("has-success");
			$("#emp_name_input").next("span").text(""); */
			show_validate_msg("#emp_name_input","success","");
		}
		
		//校验邮箱
		var empEmail = $("#emp_email_input").val();
		var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
		if(!regEmail.test(empEmail)){
			/* alert("邮箱格式不正确"); */
			/* $("#emp_email_input").parent().addClass("has-error");
			$("#emp_email_input").next("span").text("邮箱格式不正确") */
			show_validate_msg("#emp_email_input","error","邮箱格式不正确");
			return false;
		}else{
			/* $("#emp_email_input").parent().addClass("has-success");
			$("#emp_email_input").next("span").text("") */
			show_validate_msg("#emp_email_input","success","");
		}
		return true;
	}
	
	//抽取成一个方法,显示校验结果的提示信息
	function show_validate_msg(ele,status,msg){
		//清除当前元素的校验状态
		$(ele).parent().removeClass("has-success has-error");
		$(ele).next("span").text("");
		if(status == "success"){
			$(ele).parent().addClass("has-success");
			$(ele).next("span").text("");
		}else{
			$(ele).parent().addClass("has-error");
			$(ele).next("span").text(msg);
		}
	}
	
	
	//通过ajax校验用户名是否可用
	$("#emp_name_input").change(function(){
		var empName = this.value;
		$.ajax({
			url:"${APP_PATH}/checkUser",
			data:"empName="+empName,
			type:"POST",
			success:function(result){
				if(result.code == 100){
					show_validate_msg("#emp_name_input","success","用户名可以使用");
					$("#emp_save_btn").attr("ajax-validate","success");
				}else{
					show_validate_msg("#emp_name_input","error","用户名已经存在");
					$("#emp_save_btn").attr("ajax-validate","error");
				}
			}
		});
	});
</script>
</html>