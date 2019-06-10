<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>测试@ExceptionHandler</h3>
	<a href="testExceptionHandler?i=0">Test ExceptionHandler</a>
	<br/>
	
	<a href="testResponseStatus?i=10">Test ResponseStatus</a>
	<br/>
	
	<h3>测试@ResponseStatus</h3>
	<a href="testResponseStatus2?i=1">Test ResponseStatus2</a>
	<br/>
	
	<h3>测试SimpleMappingExceptionResolver</h3>
	<a href="TestSimpleMapping">Test SimpleMapping</a>
	<br/>
	
	<a href="TestSimpleMapping2">Test SimpleMapping2</a>
</body>
</html>