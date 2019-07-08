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
	<!-- Modal -->
	<div class="modal fade" id="add_emp_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">员工添加页面</h4>
	      </div>
	      <!--模态框中放的内容  -->
	      <div class="modal-body">
      		<form class="form-horizontal">
			  <div class="form-group">
			  	<!--for 属性规定 label 与哪个表单元素绑定。常常用户点击文字就需要将光标聚焦到对应的表单上面,但是这个好像写与不写，或者不对应都是没关系的  -->
			    <label for="emp_Name" class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="empName" id="emp_Name" placeholder="姓名">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="emp_email" class="col-sm-2 control-label">邮箱</label>
			    <div class="col-sm-10">
			      <input type="email" class="form-control" name="email" id="emp_email" placeholder="邮箱">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			  	<label for="inputPassword3" class="col-sm-2 control-label">性别</label>
			  	<div class="col-sm-10">
			  		<label class="radio-inline">
					  <input type="radio" name="gender" id="emp_gender_M" value="M" checked="checked"> 男
					</label>
					<label class="radio-inline">
					  <input type="radio" name="gender" id="emp_gender_F" value="F"> 女 
				 	</label>
			  	</div>
			  </div>
			  <div class="form-group">
			  	  <label for="deptName_select" class="col-sm-2 control-label">部门</label>
				  <div class="col-sm-5">
				  	<!--提交部门id即可  -->
				  	<select class="form-control" name="deptId" id="deptName_select">
						 
				 	 </select>
				  </div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="emp_save_btn">添加</button>
	      </div>
	    </div>
	  </div>
	</div>


	<div class="container">
		<!--标题  -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM</h1>
			</div>
		</div>
		<!--按钮  -->
		<div class="row">
			<div class="col-md-6 col-md-offset-8">
				<button class="btn btn-primary" id="emp_add_btn">增加</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!--表格  -->
		<div class="row">
			<div class="col-md-10">
				<table class="table table-hover" id="emp_table">
					<thead>
						<tr>
							<th>员工ID</th>
							<th>员工姓名</th>
							<th>员工性别</th>
							<th>员工邮箱</th>
							<th>部门名称</th>
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
			<!--分页信息  -->
			<div class="col-md-4" id="page_Info">
				
			</div>
			<!--分页条  -->
			<div class="col-md-6" id="page_bar">
				
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//定义一个全局变量,总记录数,关闭模态框，跳到末页 
	var totalRecored;

	$(function(){
		to_page(1);
	});
	
	function to_page(pageNum){
		$.ajax({
			url:"${APP_PATH}/emps",
			data:"pageNum="+pageNum,
			type:"GET",
			success:function(result){
				console.log(result);
				//构建表格数据
				build_emp_table(result);
				//构建分页信息
				build_paging(result);
				//构建分页条
				build_pageBar(result);
			}
		});
	}
	
	function build_emp_table(result){
		//清空表格中的tbody,而不是整个表格
		$("#emp_table tbody").empty();
		//先创建单元格,放在行中 ，然后放入到tbody中
		$.each(result.extend.pageInfo.list,function(index,emp){
			var empIdTd = $("<td></td>").append(emp.empId);
			var empNameTd = $("<td></td>").append(emp.empName);
			var genderTd = $("<td></td>").append(emp.gender=="M"?"男":"女");
			var emailTd = $("<td></td>").append(emp.email);
			var deptNameTd = $("<td></td>").append(emp.department.deptName);
			//添加两个按钮
			var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm").append("添加");
			var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm").append("删除");
			var btnId = $("<td></td>").append(editBtn).append(" ").append(delBtn);
			$("<tr></tr>").append(empIdTd).
				append(empNameTd).
				append(genderTd).
				append(emailTd).
				append(deptNameTd).append(btnId).appendTo("#emp_table tbody");
		});
	}
	
	
	function build_paging(result){
		//清空分页信息 
		$("#page_Info").empty();
		$("#page_Info").append("当前页:"+result.extend.pageInfo.pageNum+"，总页数:"+result.extend.pageInfo.pages+" ，总记录数:"+result.extend.pageInfo.total+"");
		//为全局变量totalRecored赋值
		totalRecored = result.extend.pageInfo.total;
	}
	
	function build_pageBar(result){
		//清空分页条区域
		$("#page_bar").empty();
		//构建ul,将li放在ul中的
		var ul =  $("<ul></ul>").addClass("pagination");
		//首页
		var firstPage = $("<li></li>").append($("<a></a>").append("首页"));
		firstPage.click(function(){
			to_page(1);
		});
		//上一页 
		var prePage =  $("<li></li>").append($("<a></a>").append("&laquo;"));
		prePage.click(function(){
			to_page(result.extend.pageInfo.pageNum - 1);
		});
		
		ul.append(firstPage).append(prePage);
		
		//中间的导航页码
		$.each(result.extend.pageInfo.navigatepageNums,function(index,num){
			//如果当前页码等于遍历出来导航号，就高亮显示
			var numLi = $("<li></li>").append($("<a></a>").append(num));
			if(result.extend.pageInfo.pageNum == num){
				numLi.addClass("active");
			}
			numLi.click(function(){
				to_page(num);
			});
			ul.append(numLi);
		});
		
		//下一页
		var nextPage = $("<li></li>").append($("<a></a>").append("&raquo;"));
		nextPage.click(function(){
			to_page(result.extend.pageInfo.pageNum + 1);
		});
		//末页
		var lastPage = $("<li></li>").append($("<a></a>").append("末页"));
		lastPage.click(function(){
			to_page(result.extend.pageInfo.pages);
		});
		ul.append(nextPage).append(lastPage);
		
		var navEle = $("<nav></nav>").append(ul);
		
		//再将nav,添加到page_bar的div中
		navEle.appendTo("#page_bar");
	}
	//////////////////////////////////////////////////////////////////////////////////
	//添加员工模块,点击按钮弹出一个模态框
	$("#emp_add_btn").click(function(){
		//1在弹出框之前,应该发送ajax请求部门信息
		getDepts();
		//2弹出模态框
		$("#add_emp_modal").modal({})
		//3前端输入校验,正则
		/* $("#emp_Name").change(function(){
			var nameVal = this.value;
			//有必要去了解一下正则
			//3~6个a-z,0-9,_,-组成的 或者 2-5中文
			var regName = /^([a-z0-9_-]{3,16}$)/;
			if(!regName.test(nameVal)){
				alert("校验失败")
				//添加新样式之前,应该都把旧样式清空
				//$("#emp_Name").parent().removeClass("has-success has-error");
				//$("#emp_Name").parent().removeClass("has-success");
				$("#emp_Name").parent().addClass("has-error");
				$("#emp_Name").next("span").text("3~6个a-z,0-9,_,-组成的 或者 2-5中文");
				//$("#name_div").addClass("has-error");
				return false;
			}else{
				//把this 用$()起来，就可以调用jq中的方法了，否则会报错
				alert("校验成功");
				$(this).parent().removeClass("has-error");
				$(this).parent().addClass("has-success");
				$(this).next("span").text("");
			}
		}); */
		
		//校验邮箱
		/* $("#emp_email").change(function(){
			//this.val(); 这样会报错,  应该把js转成jq对象, 再去调用jq中的方法 
			var emailVal = $(this).val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(emailVal)){
				//如果不满足,为当前对象父元素添加一个错误的样式,并为当前元素下面的span元素添加错误信息
				$(this).parent().addClass("has-error");
				$(this).next("span").text("邮箱格式不合法!");
				return false;
			}else{
				//把失败清空下样式就行
				$(this).parent().removeClass("has-error");
				$(this).parent().addClass("has-success");
				$(this).next("span").text("");
			}
		}); */
		
		//4ajax校验用户名是否重复
		
		
		//5.点击按钮，触发 添加员工的ajax操作
		$("#emp_save_btn").click(function(){
			//校验其实是要放在点保存按钮之前的
			if(!validate_add_form()){
				return false;
			}
			
			//这个serialize()是将 name1=value1&name2=value2等等..
			//例子：empName=tom&email=596220505%40qq.com&gender=F&Id=2
			var formDate = $("#add_emp_modal form").serialize();
			$.ajax({
				url:"${APP_PATH}/emps",
				data:formDate,
				type:"POST", //restful 新增
				success:function(result){
					alert("添加成功 ");
					//添加成功有两个问题要处理1.自己关闭模态框，和2.跳到末页显示新增的员工
					$("#add_emp_modal").modal('hide');
					//定义一个全局变量为总记录数
					to_page(totalRecored);
				}
			});
		});
	});
	
	//校验用户名和邮箱,如果不成功就不让提交
	function validate_add_form(){
			//有很多相同的代码可以抽取成一个方法 TODO show_msg!!
			//校验名字
			var nameVal = $("#emp_Name").val();
			alert(nameVal);
			//有必要去了解一下正则
			//3~6个a-z,0-9,_,-组成的 或者 2-5中文
			var regName = /^([a-z0-9_-]{3,16}$)/;
			if(!regName.test(nameVal)){
				alert("校验失败")
				//添加新样式之前,应该都把旧样式清空
				//$("#emp_Name").parent().removeClass("has-success has-error");
				//$("#emp_Name").parent().removeClass("has-success");
				$("#emp_Name").parent().addClass("has-error");
				$("#emp_Name").next("span").text("3~6个a-z,0-9,_,-组成的 或者 2-5中文");
				//$("#name_div").addClass("has-error");
				return false;
			}else{
				//把this 用$()起来，就可以调用jq中的方法了，否则会报错
				alert("校验成功");
				$("#emp_Name").parent().removeClass("has-error");
				$("#emp_Name").parent().addClass("has-success");
				$("#emp_Name").next("span").text("");
			}
			
			//校验邮箱			
			//this.val(); 这样会报错,  应该把js转成jq对象, 再去调用jq中的方法 
			var emailVal = $("#emp_email").val();
			alert("邮箱:"+emailVal);
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!regEmail.test(emailVal)){
				//如果不满足,为当前对象父元素添加一个错误的样式,并为当前元素下面的span元素添加错误信息
				$("#emp_email").parent().addClass("has-error");
				$("#emp_email").next("span").text("邮箱格式不合法!");
				return false;
			}else{
				//把失败清空下样式就行
				$("#emp_email").parent().removeClass("has-error");
				$("#emp_email").parent().addClass("has-success");
				$("#emp_email").next("span").text("");
				return true;
			}
	}
	
	
	function getDepts(){
		$.ajax({
			url:"${APP_PATH}/depts",
			type:"GET",
			success:function(result){
				//console.log(result);
				$("#deptName_select").empty();
				build_deptName_select(result);
			}
		});
	}
	
	//构建部门下拉列表
	function build_deptName_select(result){
		$.each(result.extend.depts,function(index,dept){
			//1.var optionEle = $("<option></option>").attr("value",dept.deptId).append(dept.deptName);
			//上面那种也可以,创建元素,添加属性,再添加内容
			//2.var optionEle = $("<option value="+dept.deptId+"></option>").append(dept.deptName);
			//3.如果熟练了,我推荐我自己的这种方式
			var optionEle = $("<option value="+dept.deptId+">"+dept.deptName+"</option>")
			optionEle.appendTo("#deptName_select"); 
		});
	}
	
</script>
</html>