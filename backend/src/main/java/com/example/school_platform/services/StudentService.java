package com.example.school_platform.services;

import com.example.school_platform.exceptions.NotFoundException;
import com.example.school_platform.models.Student;
import com.example.school_platform.repositories.GuardianRepository;
import com.example.school_platform.repositories.StudentRepository;
import com.example.school_platform.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class StudentService {

	private final StudentRepository studentRepository;
	private final SubjectRepository subjectRepository;
	private final GuardianRepository guardianRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository, GuardianRepository guardianRepository){
		this.studentRepository = studentRepository;
		this.subjectRepository = subjectRepository;
		this.guardianRepository = guardianRepository;
	}

	public Set<Student> getStudentsByTeacherId(long teacherId) throws NotFoundException {
		Set<Student> students = studentRepository.getStudentsByTeacherId(teacherId);

		students.forEach(s -> {
			try {
				s.setSubjects(subjectRepository.getSubjectsByStudentId(s.getId()));
				s.setGuardians(guardianRepository.getGuardiansByStudentId(s.getId()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		return students;
	}
}
