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
 * ģ�����ǰ������
 */
//����ע�� SpringMVC IOC ��������
@WebAppConfiguration
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class MvcTest {
	
	//ע�� SpringMVC IOC ����
	@Autowired
	private WebApplicationContext context;
	
	//��ȡ��������
	MockMvc mockMvc;
	//�ڲ���ǰ����
	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void test() throws Exception {
		//��������ִ������,���ҷ��� ���ؽ��
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps")).andReturn();
		//��ȡ����
		MockHttpServletRequest request = result.getRequest();
		//��ȡ PageInfo
		@SuppressWarnings("unchecked")
		PageInfo<Employee> pageInfo = (PageInfo<Employee>) request.getAttribute("pageInfo");
		
		System.out.println("��ǰҳ��  ��" + pageInfo.getPageNum());
		System.out.println("��ҳ�� ��" + pageInfo.getPages());
		System.out.println("�ܼ�¼�� ��" + pageInfo.getTotal());
		System.out.println("������ʾ�ĵ���ҳ�� :");
		int[] nums = pageInfo.getNavigatepageNums();
		for(int i : nums) {
			System.out.print(" " + i);
		}
		System.out.println();
		//��ȡ��ǰҳ��Ա����Ϣ
		List<Employee> emps = pageInfo.getList();
		for(Employee emp : emps) {
			System.out.println("Ա��ID ��" + emp.getId() + " Ա������  ��" + emp.getName());
			System.out.println("�������� ��" + emp.getDepartment().getDeptName());
		}
	}

}
