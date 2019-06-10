package com.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UpAndDownController {

	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file,
			@RequestParam("desc") String desc) throws IOException {
		System.out.println("Originalname: "+file.getOriginalFilename());
		//信息都在流里面
		System.out.println("inputStream: "+file.getInputStream());
		System.out.println("desc: "+desc);
		return "uploadSuccess";
	}
	
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
}
