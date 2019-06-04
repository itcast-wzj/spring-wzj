<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="hello">HelloController</a>
	<hr/>
	
	
	<h1>Test Rest Style</h1>
	<!--
		rest风格对应增删改查
		增: post(新建资源)
		删: delete(删除资源)
		改: put(更新资源)
		查: get(获取资源)
		
		表单只支持get和post,而put,delete,put并不支持
		spring3.0添加了一个HiddenHttpMethodFilter过滤器(在web.xml中配置)
		使得支持put,delete 
	  -->
	<form action="springmvc/testRest" method="post">
		<input type="submit" value="testRest post">
	</form>
	<br/>
	
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="DELETE">
		<input type="submit" value="testRest delete">
	</form>
	<br/>
	
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="PUT">
		<input type="submit" value="testRest put">
	</form>
	<br/>
	
	<a href="springmvc/testRest/1">testRest get</a>
	<hr/>
	
	<a href="springmvc/testCookie">testCookie</a>
</body>
</html>