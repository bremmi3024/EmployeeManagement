package com.example.departmentservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.departmentservice.entity.Department;
import com.example.departmentservice.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	private DepartmentService service;

	public DepartmentController(DepartmentService service) {
		this.service = service;
	}

	@GetMapping
	public List<Department> getAllDepartments() {
		return service.getAllDepartments();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
		Optional<Department> dept=Optional.of(service.getDepartmentById(id));
		if(dept.isPresent()) {
			return ResponseEntity.ok(dept.get());
		} else {
			
			throw new DeptNotFoundException(id.toString());
		}
	}

	@PostMapping("/depts")
	public ResponseEntity<Department> createDepartment(@RequestBody Department deptartment) {
		Department dept = service.saveDepartment(deptartment);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dept.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public Department updateDepartmentById(@PathVariable Long id, @RequestBody Department dept) {
		return service.updateDepartment(id, dept);
	}

	@DeleteMapping("/{id}")
	public void deleteDepartmentById(@PathVariable Long id) {
		service.deleteDepartment(id);
	}
}
