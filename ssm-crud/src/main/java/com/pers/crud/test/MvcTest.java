package com.pers.crud.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.pers.crud.bean.Employee;

/*
 * 模拟测试前端请求
 */
//可以注入 SpringMVC IOC 容器本身
@WebAppConfiguration
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class MvcTest {
	
	//注入 SpringMVC IOC 容器
	@Autowired
	private WebApplicationContext context;
	
	//获取虚拟请求
	MockMvc mockMvc;
	//在测试前创建
	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void test() throws Exception {
		//虚拟请求执行请求,并且返回 返回结果
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps")).andReturn();
		//获取请求
		MockHttpServletRequest request = result.getRequest();
		//获取 PageInfo
		@SuppressWarnings("unchecked")
		PageInfo<Employee> pageInfo = (PageInfo<Employee>) request.getAttribute("pageInfo");
		
		System.out.println("当前页码  ：" + pageInfo.getPageNum());
		System.out.println("总页码 ：" + pageInfo.getPages());
		System.out.println("总记录数 ：" + pageInfo.getTotal());
		System.out.println("连续显示的导航页码 :");
		int[] nums = pageInfo.getNavigatepageNums();
		for(int i : nums) {
			System.out.print(" " + i);
		}
		System.out.println();
		//获取当前页面员工信息
		List<Employee> emps = pageInfo.getList();
		for(Employee emp : emps) {
			System.out.println("员工ID ：" + emp.getId() + " 员工姓名  ：" + emp.getName());
			System.out.println("部门姓名 ：" + emp.getDepartment().getDeptName());
		}
	}

}
