package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.EmployeeExample;
import com.atguigu.crud.bean.EmployeeExample.Criteria;
import com.atguigu.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
	}

	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}
	
	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		//条件是员工姓名等于empName的话 ,就会返回一个大于0的数字,没有就是==0
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		//如果用户名不在数据库存在就返回true
		return count == 0;
	}

	public Employee getEmp(Integer id) {
		//不用带部门信息，因为我只是编辑员工信息
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	public void delEmpById(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
	}

	//批量删除
	public void delBatch(List<Integer> del_ids) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		//delete from xxx where emp_id in(1,2,3..);
		criteria.andEmpIdIn(del_ids);
		employeeMapper.deleteByExample(example);
	}
}
