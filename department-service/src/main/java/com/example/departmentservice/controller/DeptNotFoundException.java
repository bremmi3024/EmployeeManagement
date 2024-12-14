package com.example.departmentservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class DeptNotFoundException extends RuntimeException {
	public DeptNotFoundException(String message) {
		super(message);
	}

}
