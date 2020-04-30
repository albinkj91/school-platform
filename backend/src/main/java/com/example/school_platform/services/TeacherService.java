package com.example.school_platform.services;

import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.Teacher;
import com.example.school_platform.models.dto.EmployeePostDTO;
import com.example.school_platform.repositories.PersonRepository;
import com.example.school_platform.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class TeacherService {

	private final TeacherRepository teacherRepository;
	private final PersonRepository personRepository;
	private final EmployeeService employeeService;

	@Autowired
	public TeacherService(TeacherRepository teacherRepository, PersonRepository personRepository, EmployeeService employeeService) {
		this.teacherRepository = teacherRepository;
		this.personRepository = personRepository;
		this.employeeService = employeeService;
	}


	public Set<Teacher> getAll() throws SQLException {
		return teacherRepository.getAll();
	}

	public long save(EmployeePostDTO employeePostDTO) throws SQLException, PersistException {
		long personId = personRepository.persist(employeePostDTO);
		employeePostDTO.setPersonId(personId);
		long employeeId = employeeService.save(employeePostDTO);
		return teacherRepository.persist(employeeId);
	}
}
