package com.example.school_platform.services;

import com.example.school_platform.exceptions.NotFoundException;
import com.example.school_platform.models.Student;
import com.example.school_platform.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository){
		this.studentRepository = studentRepository;
	}

	public Set<Student> getStudentsByTeacherId(long teacherId) throws NotFoundException {
		return studentRepository.getStudentsByTeacherId(teacherId);
	}
}
