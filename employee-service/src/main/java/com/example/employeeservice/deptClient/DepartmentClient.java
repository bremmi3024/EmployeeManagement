package com.example.employeeservice.deptClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="department-service",url="http://localhost:8082")
@FeignClient(name="department-service")
public interface DepartmentClient {

	@GetMapping("/departments/{id}")
	Department getDepartmentById(@PathVariable Long id);
}
