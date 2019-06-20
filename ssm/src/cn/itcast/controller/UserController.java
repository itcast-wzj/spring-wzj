package cn.itcast.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.po.User;
import cn.itcast.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/login")
	public void login(User user,HttpServletResponse resp) throws IOException {
		System.out.print(user);
		User u = userService.login(user);
		if(null != u) {
			resp.getWriter().print("Login success");
			return;
		}
		resp.getWriter().print("Login fail");
	}
}
