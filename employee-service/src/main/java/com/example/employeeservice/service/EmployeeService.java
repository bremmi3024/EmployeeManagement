package com.example.employeeservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeservice.controller.EmployeeWithDepartment;
import com.example.employeeservice.deptClient.Department;
import com.example.employeeservice.deptClient.DepartmentClient;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exception.EmployeeNotFoundException;
import com.example.employeeservice.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository repository;

	@Autowired
	private DepartmentClient departmentFeignClient;
	
	public EmployeeWithDepartment getEmployeeWithDepartment(Long empId) throws EmployeeNotFoundException {
		
		Employee employee = repository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + empId + " not found"));
		Department dept=departmentFeignClient.getDepartmentById(employee.getId());
		return new EmployeeWithDepartment(employee,dept);
	}
	
	public EmployeeService(EmployeeRepository empRepository) {
		this.repository = empRepository;
	}
	
	public List<Employee> getAllEmployees(){
		return repository.findAll();
	}

	public Optional<Employee> getEmployee(Long id) {
		return repository.findById(id);
	}
	
	public Employee saveEmployee(Employee emp) {
		return repository.save(emp);
	}
	
	public void deleteEmployee(Long id) {
		repository.deleteById(id);
	}
	
	public Employee updateEmployee(Long id, Employee employeeDetails) throws EmployeeNotFoundException {
		Employee employee = getEmployee(id)
	            .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
		employee.setName(employeeDetails.getName());
		employee.setEmail(employeeDetails.getEmail());
        employee.setPosition(employeeDetails.getPosition());
        employee.setSalary(employeeDetails.getSalary());
        employee.setDepartmentId(employeeDetails.getDepartmentId());
		return repository.save(employee);
	}
}
