package com.example.school_platform.controllers;

import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.dto.EmployeePostDTO;
import com.example.school_platform.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private final AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@PostMapping("/add")
	public void add(@RequestBody EmployeePostDTO employeePostDTO){
		try {
			adminService.save(employeePostDTO);
		} catch (SQLException | PersistException e) {
			e.printStackTrace();
		}
	}
}
