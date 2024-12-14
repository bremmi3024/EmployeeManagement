package com.example.employeeservice.controller;

import com.example.employeeservice.deptClient.Department;
import com.example.employeeservice.entity.Employee;

public class EmployeeWithDepartment {

	private Employee emp;
	private Department dept;
	
	
	public EmployeeWithDepartment(Employee emp, Department dept) {
		this.emp = emp;
		this.dept = dept;
	}
	
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	
	
}
