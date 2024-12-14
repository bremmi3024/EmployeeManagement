package com.example.departmentservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.departmentservice.entity.Department;
import com.example.departmentservice.entity.DepartmentRepository;

@Service
public class DepartmentService {

	private final DepartmentRepository repository;

	public DepartmentService(DepartmentRepository repository) {
		this.repository = repository;
	}
	
	public List<Department> getAllDepartments(){
		return repository.findAll();
	}
	
	public Department getDepartmentById(Long id) {
		return repository.getReferenceById(id);
	}
	
	public Department saveDepartment(Department dept) {
		return repository.save(dept);
	}
	
	public Department updateDepartment(Long id, Department dept) {
		Department department=getDepartmentById(id);
		department.setName(dept.getName());
		department.setDescription(dept.getDescription());
		return repository.save(department);
	}
	
	public void deleteDepartment(Long id) {
        repository.deleteById(id);
    }
}
