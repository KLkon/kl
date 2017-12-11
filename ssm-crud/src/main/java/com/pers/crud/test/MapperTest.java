package com.pers.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pers.crud.bean.Employee;
import com.pers.crud.dao.DepartmentMapper;
import com.pers.crud.dao.EmployeeMapper;

//规定使用哪种 JUnit
@RunWith(value=SpringJUnit4ClassRunner.class)
//给出 spring 配置文件位置，可以使测试类自动注入 bean
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	
	//注入 DepartmentMapper
	@Autowired
	private DepartmentMapper departmentMapper;
	
	//注入 EmployeeMapper
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
//		System.out.println(departmentMapper);
		//加入部门
//		departmentMapper.insertSelective(new Department(null,"开发部"));
//		departmentMapper.insertSelective(new Department(null,"旅游部"));
//		System.out.println("加入部门成功!");
		//加入一个员工
//		employeeMapper.insertSelective(new Employee(null,"KL","M",1));
		//批量加入员工
		//首先要在 Spring 配置文件中加入一个可以批量的 SqlSession
		
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i = 0; i < 50; i ++) {
			String uuid = UUID.randomUUID().toString().substring(0,3) + i;
			mapper.insertSelective(new Employee(null, uuid, "M", 1));
		}
		System.out.println("批量添加员工成功 !");
	}

	@Test
	public void testUpdate() {
		Employee employee = new Employee(2, "CJ", "M", 2);
		employeeMapper.updateByPrimaryKeySelective(employee);
		System.out.println("成功");
	}
	
}
