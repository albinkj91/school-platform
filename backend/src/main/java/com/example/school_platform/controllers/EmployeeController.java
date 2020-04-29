package com.example.school_platform.controllers;

import com.example.school_platform.exceptions.NotFoundException;
import com.example.school_platform.models.Person;
import com.example.school_platform.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping("/authenticate")
	public Person authenticate(@RequestParam String email, @RequestParam String password){
		try {
			return employeeService.authenticatePerson(email, password);
		} catch (SQLException | NotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
