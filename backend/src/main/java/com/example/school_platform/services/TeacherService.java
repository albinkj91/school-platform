package com.example.school_platform.services;

import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.Teacher;
import com.example.school_platform.models.dto.EmployeePostDTO;
import com.example.school_platform.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class TeacherService {

	private final TeacherRepository teacherRepository;
	private final PersonService personService;
	private final EmployeeService employeeService;

	public TeacherService(TeacherRepository teacherRepository, PersonService personService, EmployeeService employeeService) {
		this.teacherRepository = teacherRepository;
		this.personService = personService;
		this.employeeService = employeeService;
	}

	public Set<Teacher> getAll() throws SQLException {
		return teacherRepository.getAll();
	}

	public long save(EmployeePostDTO employeePostDTO) throws SQLException, PersistException {
		long personId = personService.save(employeePostDTO);
		employeePostDTO.setPersonId(personId);
		long employeeId = employeeService.save(employeePostDTO);
		return teacherRepository.persist(employeeId);
	}
}
