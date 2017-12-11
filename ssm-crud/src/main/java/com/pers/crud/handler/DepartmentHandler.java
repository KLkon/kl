package com.pers.crud.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pers.crud.bean.Department;
import com.pers.crud.bean.Message;
import com.pers.crud.service.DepartmentService;

@Controller
public class DepartmentHandler {
	
	@Autowired
	private DepartmentService departmentService;
	
	//������в��Ų����� Json ����ʽ���ظ��ͻ���
	@ResponseBody
	@RequestMapping("/depts")
	public Message getDepts() {
		List<Department> depts = departmentService.getDepts();
		Message message = Message.success().add("depts", depts);
		return message;
	}
	
}
