package com.example.school_platform.controllers;

import com.example.school_platform.exceptions.NotFoundException;
import com.example.school_platform.models.Student;
import com.example.school_platform.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Set<Student> getStudentsByTeacherId(@PathVariable("id") long teacherId){
		try {
			return studentService.getStudentsByTeacherId(teacherId);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}