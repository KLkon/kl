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

//�涨ʹ������ JUnit
@RunWith(value=SpringJUnit4ClassRunner.class)
//���� spring �����ļ�λ�ã�����ʹ�������Զ�ע�� bean
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	
	//ע�� DepartmentMapper
	@Autowired
	private DepartmentMapper departmentMapper;
	
	//ע�� EmployeeMapper
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
//		System.out.println(departmentMapper);
		//���벿��
//		departmentMapper.insertSelective(new Department(null,"������"));
//		departmentMapper.insertSelective(new Department(null,"���β�"));
//		System.out.println("���벿�ųɹ�!");
		//����һ��Ա��
//		employeeMapper.insertSelective(new Employee(null,"KL","M",1));
		//��������Ա��
		//����Ҫ�� Spring �����ļ��м���һ������������ SqlSession
		
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i = 0; i < 50; i ++) {
			String uuid = UUID.randomUUID().toString().substring(0,3) + i;
			mapper.insertSelective(new Employee(null, uuid, "M", 1));
		}
		System.out.println("�������Ա���ɹ� !");
	}

	@Test
	public void testUpdate() {
		Employee employee = new Employee(2, "CJ", "M", 2);
		employeeMapper.updateByPrimaryKeySelective(employee);
		System.out.println("�ɹ�");
	}
	
}
