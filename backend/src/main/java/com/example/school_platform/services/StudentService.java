package com.example.school_platform.services;

import com.example.school_platform.enums.PersonType;
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
	private final TeacherRepository teacherRepository;
	private final PersonService personService;

	@Autowired
	public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository, GuardianRepository guardianRepository,
						  TeacherRepository teacherRepository, PersonService personService){
		this.studentRepository = studentRepository;
		this.subjectRepository = subjectRepository;
		this.guardianRepository = guardianRepository;
		this.teacherRepository = teacherRepository;
		this.personService = personService;
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
		long personId = personService.save(studentPostDTO);
		long studentId = studentRepository.persist(personId);
		studentPostDTO.getGuardianIds().forEach(id -> {
			try {
				studentRepository.persist(studentId, id);
			} catch (SQLException | PersistException e) {
				e.printStackTrace();
			}
		});
		return studentId;
	}
}
