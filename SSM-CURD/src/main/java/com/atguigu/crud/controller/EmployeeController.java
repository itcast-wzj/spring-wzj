package com.atguigu.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	/**
	 * 删除员工：
	 * 单个/批量  二合一
	 * 单个删除： 1
	 * 批量删除：1-2-3
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg delEmp(@PathVariable("ids") String ids) {
		//前端传过来的批量删除定义好了，就是以-分割
		//如果包含就是批量删除
		if(ids.contains("-")) {
			//定义一个集合，转换成int类型放进集合，然后mapper有个批量删除就是需要List<Integer>
			List<Integer> del_ids = new ArrayList<Integer>();
			String[] str_ids = ids.split("-");
			for (String id : str_ids) {
				del_ids.add(Integer.parseInt(id));
			}
			
			employeeService.delBatch(del_ids);
		}else {
			int id = Integer.parseInt(ids);
			employeeService.delEmpById(id);
		}
		return Msg.success();
	}
	
	/**
	 * ajax发送put请求的问题： 有两种方式
	 *       第1种方式
	 * 	$.ajax({
	 * 		url:""
	 *		data:"",带上_method=Put
	 *		type:"POST", 类型还是post,跟表单提交put请求一样 
	 *  });
	 *  
	 *      第2 种方式
	 * $.ajax({
	 * 		url:?,
	 * 		type:"PUT" 直接写PUT,但要在web.xml中配置HttpPutFormContentFilter过滤器
	 * });
	 * @param employee
	 * @return
	 */
	@ResponseBody
	//这个路径里面的名字，必须跟你映射的对象的属性名一致，否则映射不到
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT) 
	public Msg updateEmp(Employee employee) {
		System.out.println(employee);
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	
	/**
	 * 根据id获取员工
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id) {
		Employee employee =  employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	
	@ResponseBody
	@RequestMapping("/checkUser")
	public Msg checkUser(@RequestParam("empName") String empName) {
		/*
		 	前端校验的问题：首先我填了个数据库没有的名字，他说用户名可用，但等我点击按钮的
		 	时候这时校验格式就起效果了，说格式不正确，！！！
		 	这样给用户的感觉 就是刚刚明明说可以用，现在要说格式不正确；什么鬼！！
		 	
		 	基于上面这个场景；即在后端也做出校验
		 	也就是发送ajax校验用户名是否存在之前，也先校验一下格式正不正确 !!!
		 	
		 */
		//如果不匹配规则
		String regName = "^[a-z0-9_-]{3,16}$";
		if(!empName.matches(regName)) {
			return Msg.fail().add("va_msg","用户名必须是由3-16的数字和字母组成-后端");
		}
		
		//如果校验通过，才有必要去数据库用户名重复校验
		boolean b = employeeService.checkUser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "用户名重复-后端");
		}
	}
	
	/**
	 * 添加员工
	 * @param pageNum
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emps",method=RequestMethod.POST)
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
	
		//如果里面有错误
		if(result.hasErrors()) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			//就获取所有的错误字段
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				System.out.println("错误的字段名"+fieldError.getField());
				System.out.println("错误信息"+fieldError.getDefaultMessage());
				
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			
			return Msg.fail().add("errorFiled", map);
		}else {
			//校验成功才保存
			employeeService.saveEmp(employee);
			return Msg.success();
		}
	}
	
	/**
	 * 要导入jackson包 
	 * @param pageNum
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emps",method=RequestMethod.GET)
	public Msg getEmpsWithJson(@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,Model model) {
		//如果不使用pageHelper,这不是一个分页查询(因为只是全部显示到页面而已)
		//引入pageHelper分页插件
		//在查询之前只需要调用,传入页码,以及每页记录数
		PageHelper.startPage(pageNum, 5);
		//startPage后面紧跟的这个查询才是一个分页查询！！！
		List<Employee> emps =  employeeService.getAll();
		//使用pageInfo包装查询后的结果，把他交给页面就行，封装了详细的分页信息
		PageInfo page = new PageInfo(emps,5);//这里可以指定导航页码
		return Msg.success().add("pageInfo",page); //链式编程,调用方法后返回值还是原来对象
	}

	/**
	 * 查询员工数据（分页查询）
	 * 不这样做了, 使用json返回   
	 * @return
	 */
//	@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,Model model) {
		//如果不使用pageHelper,这不是一个分页查询(因为只是全部显示到页面而已)
		//引入pageHelper分页插件
		//在查询之前只需要调用,传入页码,以及每页记录数
		PageHelper.startPage(pageNum, 5);
		//startPage后面紧跟的这个查询才是一个分页查询！！！
		List<Employee> emps =  employeeService.getAll();
		//使用pageInfo包装查询后的结果，把他交给页面就行，封装了详细的分页信息
		PageInfo page = new PageInfo(emps);
		model.addAttribute("pageInfo", page); //存到了request域
		return "list"; //然后转发到list.jsp页面
	}
}
