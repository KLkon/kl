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

	//注入 EmployeeService
	@Autowired
	private EmployeeService employeeService;
	
	//根据 id 删除一条记录 或者 删除一组数据
	/**
	 * 批量删除，参数 ids 类似 1-2-3，以 - 连接
	 * 单个删除，参数 ids 就是单个id 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/emp/{ids}", method=RequestMethod.DELETE)
	@ResponseBody
	public Message deleteEmp(@PathVariable("ids") String ids) {
		//批量删除
		if(ids.contains("-")) {
			String[] idStr = ids.split("-");
			List<Integer> idList = new ArrayList<>();
			for(String id : idStr) {
				idList.add(Integer.parseInt(id));
			}
			employeeService.deleteEmps(idList);
		}else {
			//单个删除
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmpById(id);
		}
		return Message.success();
	}
	
	//修改一条记录
	@RequestMapping(value="/emp/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Message updateEmp(Employee employee) {
		//System.out.println(employee);
		employeeService.updateEmp(employee);
		return Message.success();
	}
	
	//通过 empId 查找一条记录
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Message getEmp(@PathVariable("id") Integer empId) {
		Employee employee = employeeService.getEmp(empId);
		return Message.success().add("emp", employee);
	}
	
	//检查新添姓名数据库是否存在
	@RequestMapping("/checkName")
	@ResponseBody
	public Message checkName(@RequestParam("empName") String empName) {
		//与前端校验规则统一，先校验前端规则，通过后再查询数据库
		String regName = "(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(! empName.matches(regName)) {
			return Message.fail().add("validate_msg", "用户名应该是3-16位英文或者2-5位中文");
		} else {
			boolean b = employeeService.checkName(empName);
			if(b) {
				return Message.success();
			} else {
				return Message.fail().add("validate_msg", "用户名不可用");
			}
		}
		
	}
	
	//新添加一条数据
	//添加 JSR303 校验,BindingResult 存放校验结果
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	@ResponseBody
	public Message saveEmp(@Valid Employee employee, BindingResult result) {
		//用来存放信息
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
	
	
	//查询所有员工数据
	//返回 Json 数据
	@RequestMapping("/emps")
	//返回数据封装成 Json 格式
	@ResponseBody
	public Message getEmpsWithJson(@RequestParam(value="pn", defaultValue="1") Integer pn) {
		PageHelper.startPage(pn, 5);
		List<Employee> emps = employeeService.getEmps();
		PageInfo pageInfo = new PageInfo(emps, 5);
		return Message.success().add("pageInfo", pageInfo);
	}
	
	//查询所有数据，并且去到 list.jsp 显示页面
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn", defaultValue="7") Integer pn, Model model) {
		//从第 pn 页开始显示，显示 5 条数据
		PageHelper.startPage(pn, 5);
		//此时查询变成分页查询
		List<Employee> emps = employeeService.getEmps();
		//用 PageInfo 包装 emps, 5 是连续显示的页码
		PageInfo page = new PageInfo(emps, 5);
		
		//将员工信息放入 Model ，即放入请求域中
		model.addAttribute("pageInfo", page);
		
		return "list";
	}
	
}
