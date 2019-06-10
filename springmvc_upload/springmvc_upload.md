## 1.1、上传

① springmvc做文件上传是依赖于MultipartResolver接口，因为他一个接口，注册到ioc容器中
必须是一个他的实现类CommonsMultipartResolver,其实就是 web阶段的CommonsFileUpload
1.所以用他之前得导入jar包
commons-io.jar
commons-logging.jar

② .在spring中注册CommonsMultipartResolver , **id必须为multipartResolver**

```xml
<!--这个id必须为multipartResolver,否则会发生400！！！！  -->
	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="defaultEncoding" value="UTF-8"></property>
		<!--以字节为单位, 1kb = 1024byte  -->
		<property name="maxUploadSize" value="1024000"></property>
	</bean>
```



③ .写一个文件上传的表单

```html
<form action="upload" method="POST" enctype="multipart/form-data">
		file: <input type="file" name="file"><br/> 
		desc: <input type="text" name="desc"><br/> 
			  <input type="submit" value="Submit">
</form>
```

④ 测试

```java
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file,
			@RequestParam("desc") String desc) throws IOException {
		System.out.println("Originalname: "+file.getOriginalFilename());
		//信息都在流里面
		System.out.println("inputStream: "+file.getInputStream());
		System.out.println("desc: "+desc);
		return "uploadSuccess";
	}
```

## 1.2、下载

① index.jsp

```html
<a href="download">下载</a>
```

②测试

```java
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(HttpSession httpSession) throws IOException{
		ServletContext context = httpSession.getServletContext();
		// 第一个斜杠是表示在webContent下   相对路径
		InputStream in = context.getResourceAsStream("/download/abc.txt");
		//创建一个刚好装inputstream里面内容的字节大小
		byte[] bytes = new byte[in.available()];
		//把流装到数组里
		in.read(bytes);
		
		//第二个参数要你传一个MultiValueMap接口的实现类，而HttpHeaders就是
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=abc.txt");
		
		HttpStatus statusCode = HttpStatus.OK;
		
		ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(bytes, headers, statusCode);
		return entity;
	}
```

详细：参考案例和**官方文档**