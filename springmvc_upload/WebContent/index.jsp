<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="upload" method="POST" enctype="multipart/form-data">
		file: <input type="file" name="file"><br/> 
		desc: <input type="text" name="desc"><br/> 
			  <input type="submit" value="Submit">
	</form>
	
	<hr/>
	<a href="download">下载</a>
</body> 
</html>