package com.example.school_platform.services;

import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.Guardian;
import com.example.school_platform.models.dto.EmployeePostDTO;
import com.example.school_platform.models.dto.GuardianGetDTO;
import com.example.school_platform.repositories.EmployeeRepository;
import com.example.school_platform.repositories.GuardianRepository;
import com.example.school_platform.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class GuardianService {

	private final GuardianRepository guardianRepository;
	private final PersonRepository personRepository;
	private final EmployeeService employeeService;

	@Autowired
	public GuardianService(GuardianRepository guardianRepository, PersonRepository personRepository, EmployeeService employeeService) {
		this.guardianRepository = guardianRepository;
		this.personRepository = personRepository;
		this.employeeService = employeeService;
	}


	public Set<Guardian> getAll() throws SQLException {
		return guardianRepository.getAll();
	}

	public Set<GuardianGetDTO> getByStudentId(long studentId) throws SQLException {
		return guardianRepository.getByStudentId(studentId);
	}

	public long save(EmployeePostDTO employeePostDTO) throws SQLException, PersistException {
		long personId = personRepository.persist(employeePostDTO);
		employeePostDTO.setPersonId(personId);
		long employeeId = employeeService.save(employeePostDTO);
		return guardianRepository.persist(employeeId);
	}
}
