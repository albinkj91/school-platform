package com.example.school_platform.services;

import com.example.school_platform.exceptions.NotFoundException;
import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.Student;
import com.example.school_platform.models.dto.StudentPostDTO;
import com.example.school_platform.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class StudentService {

	private final StudentRepository studentRepository;
	private final SubjectRepository subjectRepository;
	private final GuardianRepository guardianRepository;
	private final PersonRepository personRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository,
						  GuardianRepository guardianRepository, PersonRepository personRepository){
		this.studentRepository = studentRepository;
		this.subjectRepository = subjectRepository;
		this.guardianRepository = guardianRepository;
		this.personRepository = personRepository;
	}


	public Set<Student> getAll() throws SQLException {
		return studentRepository.getAll();
	}

	public Set<Student> getByTeacherId(long teacherId) throws NotFoundException {
		Set<Student> students = studentRepository.getByTeacherId(teacherId);

		students.forEach(s -> {
			try {
				s.setSubjects(subjectRepository.getByStudentId(s.getId()));
				s.setGuardians(guardianRepository.getByStudentId(s.getId()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		return students;
	}

	public long save(StudentPostDTO studentPostDTO) throws SQLException, PersistException {
		long personId = personRepository.persist(studentPostDTO);
		long studentId = studentRepository.persist(personId, studentPostDTO.getTeacherId());

		studentPostDTO.getGuardianIds().forEach(id -> {
			try {
				studentRepository.persistGuardianRelation(studentId, id);
			} catch (SQLException | PersistException e) {
				e.printStackTrace();
			}
		});
		return studentId;
	}
}
