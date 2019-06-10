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
		//��Ϣ����������
		System.out.println("inputStream: "+file.getInputStream());
		System.out.println("desc: "+desc);
		return "uploadSuccess";
	}
	
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(HttpSession httpSession) throws IOException{
		ServletContext context = httpSession.getServletContext();
		// ��һ��б���Ǳ�ʾ��webContent��   ���·��
		InputStream in = context.getResourceAsStream("/download/abc.txt");
		//����һ���պ�װinputstream�������ݵ��ֽڴ�С
		byte[] bytes = new byte[in.available()];
		//����װ��������
		in.read(bytes);
		
		//�ڶ�������Ҫ�㴫һ��MultiValueMap�ӿڵ�ʵ���࣬��HttpHeaders����
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=abc.txt");
		
		HttpStatus statusCode = HttpStatus.OK;
		
		ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(bytes, headers, statusCode);
		return entity;
	}
}
