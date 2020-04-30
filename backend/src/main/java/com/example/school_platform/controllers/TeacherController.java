package com.example.school_platform.controllers;

import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.dto.EmployeePostDTO;
import com.example.school_platform.services.TeacherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	private final TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}


	@PostMapping("/add")
	public void add(@RequestBody EmployeePostDTO employeePostDTO){
		try {
			teacherService.save(employeePostDTO);
		} catch (SQLException | PersistException e) {
			e.printStackTrace();
		}
	}
}
