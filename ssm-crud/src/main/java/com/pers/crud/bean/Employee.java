package com.pers.crud.bean;

import javax.validation.constraints.Pattern;

public class Employee {
    private Integer id;

    //�Զ�������У�����
    @Pattern(regexp = "(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})",
    		message="--->�û���Ӧ����3-16λӢ�Ļ���2-5λ����")
    private String name;

    private String gender;

    private Integer dId;
    
    private Department department;

    public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

	public Employee(Integer id, String name, String gender, Integer dId) {
		
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dId = dId;
	}

	public Employee() {
		
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", gender=" + gender
				+ ", dId=" + dId + ", department=" + department + "]";
	}
    
    
}