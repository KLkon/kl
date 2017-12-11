package com.pers.crud.handler;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pers.crud.bean.Employee;
import com.pers.crud.bean.Message;
import com.pers.crud.service.EmployeeService;

@Controller
public class EmployeeHandler {

	//ע�� EmployeeService
	@Autowired
	private EmployeeService employeeService;
	
	//���� id ɾ��һ����¼ ���� ɾ��һ������
	/**
	 * ����ɾ�������� ids ���� 1-2-3���� - ����
	 * ����ɾ�������� ids ���ǵ���id 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/emp/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public Message deleteEmp(@PathVariable("ids") String ids) {
		//����ɾ��
		if(ids.contains("-")) {
			String[] idStr = ids.split("-");
			List<Integer> idList = new ArrayList<>();
			for(String id : idStr) {
				idList.add(Integer.parseInt(id));
			}
			employeeService.deleteEmps(idList);
		}else {
			//����ɾ��
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmpById(id);
		}
		return Message.success();
	}
	
	//�޸�һ����¼
	@RequestMapping(value="/emp/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Message updateEmp(Employee employee) {
		//System.out.println(employee);
		employeeService.updateEmp(employee);
		return Message.success();
	}
	
	//ͨ�� empId ����һ����¼
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Message getEmp(@PathVariable("id") Integer empId) {
		Employee employee = employeeService.getEmp(empId);
		return Message.success().add("emp", employee);
	}
	
	//��������������ݿ��Ƿ����
	@RequestMapping("/checkName")
	@ResponseBody
	public Message checkName(@RequestParam("empName") String empName) {
		//��ǰ��У�����ͳһ����У��ǰ�˹���ͨ�����ٲ�ѯ���ݿ�
		String regName = "(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(! empName.matches(regName)) {
			return Message.fail().add("validate_msg", "�û���Ӧ����3-16λӢ�Ļ���2-5λ����");
		} else {
			boolean b = employeeService.checkName(empName);
			if(b) {
				return Message.success();
			} else {
				return Message.fail().add("validate_msg", "�û���������");
			}
		}
		
	}
	
	//�����һ������
	//��� JSR303 У��,BindingResult ���У����
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	@ResponseBody
	public Message saveEmp(@Valid Employee employee, BindingResult result) {
		//���������Ϣ
		Map<String, Object> map = new HashMap<>();
		if(result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError fieldError : errors) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage());	
			}
			return Message.fail().add("fieldErrors", map);
		} else {
			employeeService.saveEmp(employee);
			return Message.success();
		}
	}
	
	
	//��ѯ����Ա������
	//���� Json ����
	@RequestMapping("/emps")
	//�������ݷ�װ�� Json ��ʽ
	@ResponseBody
	public Message getEmpsWithJson(@RequestParam(value="pn", defaultValue="1") Integer pn) {
		PageHelper.startPage(pn, 5);
		List<Employee> emps = employeeService.getEmps();
		PageInfo pageInfo = new PageInfo(emps, 5);
		return Message.success().add("pageInfo", pageInfo);
	}
	
	//��ѯ�������ݣ�����ȥ�� list.jsp ��ʾҳ��
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn", defaultValue="7") Integer pn, Model model) {
		//�ӵ� pn ҳ��ʼ��ʾ����ʾ 5 ������
		PageHelper.startPage(pn, 5);
		//��ʱ��ѯ��ɷ�ҳ��ѯ
		List<Employee> emps = employeeService.getEmps();
		//�� PageInfo ��װ emps, 5 ��������ʾ��ҳ��
		PageInfo page = new PageInfo(emps, 5);
		
		//��Ա����Ϣ���� Model ����������������
		model.addAttribute("pageInfo", page);
		
		return "list";
	}
	
}
