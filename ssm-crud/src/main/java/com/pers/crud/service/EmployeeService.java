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
	
	//��ѯ����Ա����Ϣ���Ҵ�������Ϣ
	public List<Employee> getEmps() {
		//���ز�ѯ��������Ա����Ϣ
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	//����һ������
	public void saveEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);
	}

	//���ע���������ݿ��Ƿ����
	// = 0 �򷵻� true���������ʹ��
	public boolean checkName(String empName) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}
	
	//���� id ���ҳ�����
	public Employee getEmp(Integer empId) {
		// TODO Auto-generated method stub
		Employee employee = employeeMapper.selectByPrimaryKey(empId);
		return employee;
	}

	//�޸�һ������
	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		//System.out.println("service" + employee);
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	//���ݵ��� id ɾ������
	public void deleteEmpById(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
	}

	//����һ�� id ����  ����ɾ��
	public void deleteEmps(List<Integer> idList) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(idList);
		employeeMapper.deleteByExample(example);
	}
	
}
