package com.example.school_platform.controllers;

import com.example.school_platform.exceptions.NotFoundException;
import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.Student;
import com.example.school_platform.models.dto.StudentGetDTO;
import com.example.school_platform.models.dto.StudentPostDTO;
import com.example.school_platform.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService){
		this.studentService = studentService;
	}

	@GetMapping("/by-teacher/{id}")
	public Set<StudentGetDTO> getStudentsByTeacherId(@PathVariable("id") long teacherId){
		try {
			return studentService.getByTeacherId(teacherId);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping("/add")
	public void add(@RequestBody StudentPostDTO studentPostDTO){
		try {
			studentService.save(studentPostDTO);
		} catch (SQLException | PersistException e) {
			e.printStackTrace();
		}
	}
}