package com.pers.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pers.crud.bean.Employee;
import com.pers.crud.bean.EmployeeExample;
import com.pers.crud.bean.EmployeeExample.Criteria;
import com.pers.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	//查询所有员工信息，且带部门信息
	public List<Employee> getEmps() {
		//返回查询出的所有员工信息
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	//保存一条数据
	public void saveEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);
	}

	//检查注册姓名数据库是否存在
	// = 0 则返回 true，代表可以使用
	public boolean checkName(String empName) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}
	
	//根据 id 查找出数据
	public Employee getEmp(Integer empId) {
		// TODO Auto-generated method stub
		Employee employee = employeeMapper.selectByPrimaryKey(empId);
		return employee;
	}

	//修改一条数据
	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		//System.out.println("service" + employee);
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	//根据单个 id 删除数据
	public void deleteEmpById(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
	}

	//根据一个 id 数组  批量删除
	public void deleteEmps(List<Integer> idList) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(idList);
		employeeMapper.deleteByExample(example);
	}
	
}
