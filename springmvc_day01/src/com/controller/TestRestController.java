package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.glassfish.external.statistics.annotations.Reset;


@Controller
@RequestMapping("/springmvc")
public class TestRestController {
	
	private static final String SUCCESS = "success";
	
	@RequestMapping(value="/testRest",method=RequestMethod.POST)
	public String testRestPost() {
		System.out.println("testRest Post");
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.DELETE) 
	public String testRestDelete(@PathVariable("id") String id) {
		System.out.println("testRest delete: "+ id);
		return SUCCESS;
	}
	
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.PUT)
	public String testRestPut(@PathVariable String id) {
		System.out.println("testRest Put: "+ id);
		return "forward:/springmvc/test";
	}
	
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.GET)
	public String testRest(@PathVariable("id") Integer id) {
		System.out.println("testRest Get: "+ id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/test")
	public String test() {
		System.out.println("test");
		return SUCCESS;
	}
	
}
