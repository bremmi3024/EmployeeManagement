package com.example.employeeservice.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exception.EmployeeNotFoundException;
import com.example.employeeservice.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService service;
	
	
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}


	@GetMapping
	public List<Employee> getEmployees() {
		return service.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException {
		return service.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));
	}
	
	@GetMapping("/{id}/with-department")
	public EmployeeWithDepartment getEmployeeWithDepartment(@PathVariable Long id) throws EmployeeNotFoundException {
	return service.getEmployeeWithDepartment(id);
}
	
	@DeleteMapping("/{id}")
	public void deleteEmployeeById(@PathVariable Long id) {
		 service.deleteEmployee(id);
		
	}
	
	@PostMapping
	public Employee createEmployee(@RequestBody Employee emp) {
		System.out.println(emp);
		return service.saveEmployee(emp);
		
	}
	
	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee emp) throws EmployeeNotFoundException {
		return service.updateEmployee(id, emp);
	}
}
