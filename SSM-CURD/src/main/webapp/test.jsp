<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("APP_PATH", path);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${APP_PATH }/static/js/jquery-2.0.0.min.js"></script>
<!--1.引入样式 bootstrap.css-->
<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<!--2.引入bootstrap.js,需要jq的支持（前面）  -->
<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 员工更新的模态框：尽量作为 body 标签的直接子元素 -->
	<div class="modal fade" id="emp_update_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <!--头部  -->
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">编辑员工</h4>
	      </div>
	      <!--中间：内容写到这里,表单  -->
	      <div class="modal-body">
	        <form class="form-horizontal">
			  <div class="form-group">
			  <label for="emp_name_static" class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-10">
				  	<!--静态控件  -->
				  	<p class="form-control-static" id="emp_name_static"></p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="emp_emailId" class="col-sm-2 control-label">邮箱</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="email" id="emp_emailId" placeholder="email@163.com">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			  <div class="form-group">
			  	<label for="inputPassword3" class="col-sm-2 control-label">性别</label>
			  	<!--要多加一个列把它包住；控制他的长度  -->
			  	<div class="col-sm-10">
				    <label class="radio-inline">
					  <input type="radio" name="gender" id="inlineRadio1" value="M" checked="checked"> 男
					</label>
					<label class="radio-inline">
					  <input type="radio" name="gender" id="inlineRadio2" value="F"> 女
					</label>
			  	</div>
			  </div>
			  
			  <div class="form-group">
			    <label for="deptName_select" class="col-sm-2 control-label">部门</label>
			    <!--要多加一个列把它包住；控制他的长度  -->
			    <div class="col-sm-5">
			    	<!--传递deptId过去  -->
			    	<select class="form-control" name="deptId" id="emp_edit_select">
			    	 
					 </select>
			    </div>
			  </div>
			</form>
	      </div>
	      <!--底部  -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
	      </div>
	    </div>
	  </div>
	</div>



	<!-- 员工添加的模态框：尽量作为 body 标签的直接子元素 -->
	<div class="modal fade" id="emp_add_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <!--头部  -->
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
	      </div>
	      <!--中间：内容写到这里,表单  -->
	      <div class="modal-body">
	        <form class="form-horizontal">
			  <div class="form-group">
			  	<!--for 属性规定 label 与哪个表单元素绑定。常常用户点击文字就需要将光标聚焦到对应的表单上面,但是这个好像写与不写，或者不对应都是没关系的  -->
			    <label for="emp_nameId" class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-10">
			      <input type="email" class="form-control" name="empName" id="emp_nameId" placeholder="username">
			       <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="emp_emailId" class="col-sm-2 control-label">邮箱</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="email" id="emp_emailId" placeholder="email@163.com">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  
			  <div class="form-group">
			  	<label for="inputPassword3" class="col-sm-2 control-label">性别</label>
			  	<!--要多加一个列把它包住；控制他的长度  -->
			  	<div class="col-sm-10">
				    <label class="radio-inline">
					  <input type="radio" name="gender" id="inlineRadio1" value="M" checked="checked"> 男
					</label>
					<label class="radio-inline">
					  <input type="radio" name="gender" id="inlineRadio2" value="F"> 女
					</label>
			  	</div>
			  </div>
			  
			  <div class="form-group">
			    <label for="deptName_select" class="col-sm-2 control-label">部门</label>
			    <!--要多加一个列把它包住；控制他的长度  -->
			    <div class="col-sm-5">
			    	<!--传递deptId过去  -->
			    	<select class="form-control" name="deptId" id="deptName_select">
			    	  <!--这里不能写死,应该从后台获取,得写js/jq构建  -->
					 <!--  <option>1</option> -->
					 </select>
			    </div>
			  </div>
			</form>
	      </div>
	      <!--底部  -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="emp_save_btn">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!--
		说明:
		bootstrap中说了行必须包含在.container中, 
		而且只有列才能作为行的直接子元素, 一共12列
	-->
	<div class="container">
		<!--1标题  -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM</h1>
			</div>
		</div>
		<!--2.按钮  -->
		<div class="row">
			<!--列偏移  -->
			<div class="col-md-5 col-md-offset-8">
				<button class="btn btn-primary" id="emp_add_btn">增加</button>
				<button class="btn btn-danger" id="del_emp_all_btn">批量删除</button>
			</div>
		</div>
		<!--3.表格  -->
		<div class="row">
			<div class="col-md-10">
				<!--为表格添加样式,必须有table  -->
				<table class="table table-hover" id="emp_table">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="check_all"/>
							</th>
							<th>ID</th>
							<th>姓名</th>
							<th>性别</th>
							<th>邮箱</th>
							<th>部门 </th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- <tr>
							<td>1001</td>
							<td>wzj</td>
							<td>男</td>
							<td>itcast_wzj@163.com</td>
							<td>开发部</td>
							<td>
								<button class="btn btn-primary btn-sm">
									添加的字体图标 
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 编辑
								</button>
								<button class="btn btn-danger btn-sm">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 删除
								</button>
							</td>
						</tr> -->
					</tbody>
				</table>
			</div>
		</div>
		<!--4分页:   -->
		<div class="row">
			<!--4.1分页信息  -->
			<div class="col-md-4" id="page_info">
				
			</div>
			<!--4.2分页条：bootstrap的组件中有  -->
			<div class="col-md-7" id="page_bar">
				<nav aria-label="Page navigation" id="nav_list">
				  <!-- <ul class="pagination">
				    <li>
				      <a href="#" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
				    <li><a href="#">1</a></li>
				    <li><a href="#">2</a></li>
				    <li><a href="#">3</a></li>
				    <li><a href="#">4</a></li>
				    <li><a href="#">5</a></li>
				    <li>
				      <a href="#" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				  </ul> -->
				</nav>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var totalRecord,currentPage;
	$(function(){
		//默认页面加载完后去发送一个ajax请求
		to_page(1);
	});
	
	//上面这个ajax请求的方法得抽取出来,不然后面的首页，末页，每个导航页码都得写 
	function to_page(pageNum){
		$.ajax({
			url:"${APP_PATH}/emps",
			data:"pageNum="+pageNum,
			type:"GET",
			success:function(result){
				build_emp_table(result);
				build_page_info(result); 
				build_page_bar(result);
			}
		});
	}
	////////////////////////////构建页面,填充数据部分//////////////////////////////////////////////
	//1.拿到后台数据,构建员工表格
	function build_emp_table(result){
		 //清除之前的样式, 防止叠加
		 $("#emp_table tbody").empty();
		 $.each(result.extend.pageInfo.list,function(index,emp){
			var checkBoxId = $("<td></td>").append("<input type='checkbox' class='check_item' />");
			var empIdTd = $("<td>"+emp.empId+"</td>");
			var empNameTd = $("<td>"+emp.empName+"</td>");
			
			var genderTd = $("<td></td>").append(emp.gender=="M"?"男":"女");
			var emailTd = $("<td>"+emp.email+"</td>");
			var deptNameTd = $("<td>"+emp.department.deptName+"</td>");
			
			//这个后面的edit-btn，del-btn 手动添加上去 ；为了后面的点击编辑的时候!!!!!
			var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit-btn").append("<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>").append("编辑");
			//为编辑按钮添加一个自定义属性，来表示当前员工的id,方便编辑的时候，根据这个id修改
			editBtn.attr("edit-id",emp.empId);
			
			var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm del-btn").append("<span class='glyphicon glyphicon-trash' aria-hidden='true'></span>").append("删除");
			//为删除按钮添加一个自定义属性，来表示当前员工的id,方便点击删除的时候，根据这个id删除
			delBtn.attr("del-id",emp.empId);
			
			var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
			
			$("<tr></tr>").append(checkBoxId)
						  .append(empIdTd)
						  .append(empNameTd)
						  .append(genderTd)
						  .append(emailTd)
						  .append(deptNameTd)
						  .append(btnTd)
						  .appendTo("#emp_table tbody");
		}); 
	}
	
	//2.构建分页信息
	function build_page_info(result){
		//清除之前的样式, 防止叠加
		$("#page_info").empty();
		var pageInfo = result.extend.pageInfo;
		$("#page_info").append("当前页"+pageInfo.pageNum+"，总页数"+pageInfo.pages+"，总记录数"+pageInfo.total+"");
		totalRecord = pageInfo.pages;
		currentPage = pageInfo.pageNum;
	}
	
	//3构建分页条信息
	function build_page_bar(result){
		//清除之前的样式, 防止叠加
		$("#nav_list").empty();
		var pageInfo = result.extend.pageInfo;
		
		//由内向外创建,先创建li,再 创建ul等等
		var ul = $("<ul></ul>").addClass("pagination");
		//首页
		var firstPage = $("<li></li>").append("<a>首页</a>");
		//为首页添加点击事件,然后发送一个ajax请求,pageNum=1,这个时候就要把之前的代码给抽取下
		firstPage.click(function(){
			//去首页
			to_page(1);
		});
		
		//上一页
		var prePage = $("<li></li>").append($("<a></a>").append("<span>&laquo;</span>"));
		//点击上一页,就是当前页+1,知道为什么要抽取成to_page方法了吧，不然这里要得写ajax请求，代码完全一致，只是pageNum不同
		prePage.click(function(){
			to_page(pageInfo.pageNum-1);
		});
		
		ul.append(firstPage).append(prePage);
		
		//中间有个导航页号,需要从pageInfo中拿出来遍历
		$.each(result.extend.pageInfo.navigatepageNums,function(index,num){
			//console.log(num);
			var navLi = $("<li></li>").append($("<a></a>").append(num));
			//如果当前页码，与遍历出来的导航页号一致，就高亮显示
			if(result.extend.pageInfo.pageNum == num){
				navLi.addClass("active");
			}
			//点击页码发送ajax请求, 如果不抽取一个请求page的ajax方法, 这里还得自己写，会造成代码冗余
			navLi.click(function(){
				to_page(num);
			});
			ul.append(navLi);
		}); 
		
		//下一页
		 var nextPage = $("<li></li>").append($("<a></a>").append("<span>&raquo;</span>"));
		//点击下一页,就是当前页+1,知道为什么要抽取成to_page方法了吧，不然这里要得写ajax请求，代码完全一致，只是pageNum不同
		nextPage.click(function(){
			to_page(pageInfo.pageNum+1);			
		});
		
		//尾页,用链接的话,把鼠标方上去的话,会看到请求的地址,而且返回的话,整个页面都是json数据,而用ajax这种方式就看不到
		//var lastPage = $("<li></li>").append($("<a>尾页</a>").attr("href","${APP_PATH}/emps?pageNum="+pageInfo.pages));
		//为尾页添加点击事件,然后发送一个ajax请求,pageNum=总页数,这个时候就要把之前的代码给抽取下
		var lastPage = $("<li></li>").append("<a>尾页</a>");
		lastPage.click(function(){
			to_page(pageInfo.pages);
		});
		ul.append(nextPage).append(lastPage); 
		
		ul.appendTo("#nav_list");
		
	}
	
	/////////////////////模态框部分内容(添加员工，校验)//////////////////////////////////////////
	$("#emp_nameId").change(function(){
			var nameVal = $("#emp_nameId").val();
			$.ajax({
				url:"${APP_PATH}/checkUser",
				data:"empName="+nameVal,
				type:"GET",
				success:function(result){
					console.log(result);
					//alert(result);
					//状态码是200，我自定义是失败
					if(result.code == 200){
						//alert(result.extend.va_msg);
						$("#emp_nameId").parent().addClass("has-error");
						$("#emp_nameId").next("span").text(result.extend.va_msg);
						//给点击添加的按钮,加个属性
						$("#emp_save_btn").attr("validate_info","error");
						
					}else{
						//alert("用户名可用");
						//要把error的样式清空
						$("#emp_nameId").parent().removeClass("has-error");
						$("#emp_nameId").parent().addClass("has-success");
						$("#emp_nameId").next("span").text("用户名可用");
						$("#emp_save_btn").attr("validate_info","success");
					}
				}
			});
		});
	
	
	//点击添加按钮，弹出一个模态框
	$("#emp_add_btn").click(function(){
		//清空样式
		$("#emp_add_modal form").find("*").removeClass("has-error has-success");
		//清空表单数据: 转成js对象, jq对象没有reset()方法
		$("#emp_add_modal form")[0].reset();
		//获取部门信息
		getDepts("#deptName_select");
		//获取modal对象调用 方法弹出
		$("#emp_add_modal").modal();
		
		
		//添加保存按钮之前1.去校验格式 2.ajax判断是否用户名重复 3.然后才能保存成功，否则不能保存
		$("#emp_save_btn").click(function(){
			//这里把它注释，是为了测试，假如用户跳过了前端校验
			//前端校验
			/* if(!validate_form()){
				return false;
			} */
			
			// 二: ajax判断是否用户名重复
			if($("#emp_save_btn").attr("validate_info") == "error"){
				return false;
			}
			
			// 三: 添加员工
			//获取表单序列化数据
			var formDate = $("#emp_add_modal form").serialize();
			$.ajax({
				url:"${APP_PATH}/emps",
				data:formDate,
				type:"POST", //restful 新增
				success:function(result){
					if(result.code == 200){
						alert("处理失败");
						//有哪个字段的错误，就显示哪个字段
						if(result.extend.errorFiled.email != undefined){
							//alert(result.extend.errorFiled.email);
							//获取邮箱字段的DOM对象；为他添加后端返回jsr303的错误提示
							$("#emp_emailId").parent().addClass("has-error");
							$("#emp_emailId").next("span").text(result.extend.errorFiled.email);
						}
						if(result.extend.errorFiled.empName != undefined){
							//alert(result.extend.errorFiled.empName);
							//获取name字段的DOM对象；为他添加后端返回jsr303的错误提示
							$("#emp_empNameId").parent().addClass("has-error");
							$("#emp_empNameId").next("span").text(result.extend.errorFiled.empName);
						}
					}else{
						alert("添加成功");
						//添加成功后,关闭模态框和跳到最后一页显示新增加的内容
						$("#emp_add_modal").modal("hide");
						to_page(totalRecord);						
					}
				}
			});
		});
	});
	
	
	function getDepts(selectEle){
		$("#deptName_select").empty();
		$.ajax({
			url:"${APP_PATH}/depts",
			type:"GET",
			success:function(result){
				//这里注意depts是一个集合,得先遍历!!! 不遍历直接取里面的属性是取不到的！！！
				var depts = result.extend.depts;
				$.each(depts,function(index,dept){
					$("<option value="+dept.deptId+">"+dept.deptName+"</option>").appendTo(selectEle); //selectEle
				});
			}
		});
	}
	
	//校验用户名和邮箱的格式
	function validate_form(){
		//一： 校验格式
		//校验姓名的格式
		var nameVal = $("#emp_nameId").val();
		var regName = /^[a-z0-9_-]{3,16}$/;
		if(!regName.test(nameVal)){
			//alert("用户名格式不正确");
			//为父元素添加样式，为子元素添加提示
			$("#emp_nameId").parent().addClass("has-error");
			$("#emp_nameId").next("span").text("用户名格式不正确!- 前端");
			return false;
		}else{
			$("#emp_nameId").parent().addClass("has-success");
			$("#emp_nameId").next("span").text("");
		}
		
		//校验邮箱的格式
		var emailVal = $("#emp_emailId").val();
		var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
		if(!regEmail.test(emailVal)){
			//alert("邮箱格式不正确");
			$("#emp_emailId").parent().addClass("has-error");
			$("#emp_emailId").next("span").text("邮箱格式不正确-前端");
			return false
		}else{
			$("#emp_emailId").parent().addClass("has-success");
			$("#emp_emailId").next("span").text("");
			return true;
		}
	}
	
	//=================员工编辑部门====================================
	/*
		我们在按钮创建之前就绑定了click,所以绑不上 
		可以在创建按钮的时候，绑定 点击live
		jq新版本把live() 去除了，使用on进行替代(事件处理)
		
		$(".edit-btn").click(function(){
		alert("1");
	});
	*/
	//把click事件，绑定到edit-btn类上，记住加点
	$(document).on("click",".edit-btn",function(){
		//更新模态框中的部门名称样式叠加问题
		$("#emp_update_modal #emp_edit_select").empty();
		
		//1.弹出编辑模态框之前，也得获取部门名称信息
		// 之前写的那个getDepts()他是最终，放到添加模态框的select中,而我现在的编辑模态框要显示在编辑模态框下的select,就是添加的位置不一样，所以把位置变成参数传入就行 ，抽取一个方法
		getDepts("#emp_edit_select");
		//2.获取当前员工的id，
		getEmp($(this).attr("edit-id"));
		
		//把员工id传递给更新模态框的更新按钮 
		$("#emp_update_modal #emp_update_btn").attr("update-id",$(this).attr("edit-id"));
		//弹出编辑模态框
		$("#emp_update_modal").modal();
	});
	
	//根据员工id,发送一个ajax请求
	function getEmp(id){
		$.ajax({
			url:"${APP_PATH}/emp/"+id,
			type:"GET",
			success:function(result){
				console.log(result);
				var emp = result.extend.emp;
				$("#emp_name_static").text(emp.empName);
				$("#emp_emailId").val(emp.email);
				//是编辑模态框下单选按钮
				$("#emp_update_modal input[name=gender]").val([emp.gender]);
				$("#emp_update_modal select").val([emp.deptId]);
			}
		});
	}
	
	//点击模态框的更新按钮
	$("#emp_update_modal #emp_update_btn").click(function(){
		//1.校验邮箱; 这里等下写
		
		//2.发送ajax更新请求
		var empId = $(this).attr("update-id");
		$.ajax({
			url:"${APP_PATH}/emp/"+empId,
			data:$("#emp_update_modal form").serialize(),//+"&_method=PUT"
			type:"PUT",
			success:function(result){
				//然后关闭模态框，跳到末页
				$("#emp_update_modal").modal('hide'); 
				//在构建分页信息的时候，用一个全局变量保存当前页
				to_page(currentPage);
			}
		});
	});
	
	
	//为表格中的删除按钮绑定点击事件,直接绑没效果，跟编辑按钮一样的
	/* $(".del-btn").click(function(){
		alert("删除");
	}); */
	$(document).on("click",".del-btn",function(){
		//弹出是否删除对话框,弹出名字
		var empName = $(this).parents("tr").find("td:eq(2)").text()
		
		if(confirm("确认要删除"+empName+"员工吗")){
			//如果确定就是返回true,再发送删除员工的ajax请求
			//获取当前按钮上属性为del-id的值
			var delId = $(this).attr("del-id");
			$.ajax({
				url:"${APP_PATH}/emp/"+delId,
				data:"_method=DELETE",
				type:"POST",
				success:function(result){
					console.log(result);
					//删除之后,得刷新一下页面,调用回到本页就行了
					to_page(currentPage);
				}
			});
		}
	});
	
	//当选中第一个checkbox后，其他的都勾选上 
	$("#check_all").click(function(){
		//这里用attr()的话是undefined, 建议自定义属性用attr() 而本身的属性用prop()
		//这里的全选，就是根据第一个checkbox的 是否选中的变化而变化
		//alert($(this).prop("checked"));
		
		//获取所有下面所有的checkbox,设置他的checked属性与主checkbox的属性一致就行了
		$(".check_item").prop("checked",$(this).prop("checked"));
	});
	
	//check_item 判断当前选中的状态是否是5个
	/* $(".check_item").click(function(){
		alert(1);
	}); */
	
	$(document).on("click",".check_item",function(){
		//如果check_item选中的个数，刚好等于check_item的个数，就将最上面的checkbox 勾选上
		var flag = $(".check_item:checked").length == $(".check_item").length;
		$("#check_all").prop("checked",flag);
	});
	
	//批量删除
	$("#del_emp_all_btn").click(function(){
		
		var emp_names = "";
		var emp_ids = "";
		$.each($(".check_item:checked"),function(index,check_item){
			//获取单元格中的内容；所以要text() 取出内容
			//alert($(check_item).parents("tr").find("td:eq(2)").text());
			//组装员工名字
			emp_names += $(this).parents("tr").find("td:eq(2)").text()+",";
			//组成员工id
			emp_ids += $(this).parents("tr").find("td:eq(1)").text()+"-";
		});
		
		//多了个,把他给截取掉
		emp_names = emp_names.substring(0,emp_names.length-1);
		emp_ids = emp_ids.substring(0,emp_ids.length-1);
		if(confirm("确认删除"+emp_names+"")){
			//如果确定，就发送ajax请求，批量删除 
			$.ajax({
				url:"${APP_PATH}/emp/"+emp_ids,
				type:"DELETE",
				success:function(result){
					alert("批量删除成功!");
					to_page(currentPage);
				}
			});
		}
	});
	
</script>
</html>