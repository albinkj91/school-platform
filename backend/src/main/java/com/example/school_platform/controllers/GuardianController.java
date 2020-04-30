package com.example.school_platform.controllers;

import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.dto.EmployeePostDTO;
import com.example.school_platform.services.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/guardian")
public class GuardianController {

	private final GuardianService guardianService;

	@Autowired
	public GuardianController(GuardianService guardianService) {
		this.guardianService = guardianService;
	}

	@PostMapping("/add")
	public void add(@RequestBody EmployeePostDTO employeePostDTO){
		try {
			guardianService.save(employeePostDTO);
		} catch (SQLException | PersistException e) {
			e.printStackTrace();
		}
	}
}
