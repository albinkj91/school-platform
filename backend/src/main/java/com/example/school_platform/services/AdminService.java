package com.example.school_platform.services;

import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.Admin;
import com.example.school_platform.models.dto.EmployeePostDTO;
import com.example.school_platform.repositories.AdminRepository;
import com.example.school_platform.repositories.EmployeeRepository;
import com.example.school_platform.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class AdminService {

	private final AdminRepository adminRepository;
	private final PersonRepository personRepository;
	private final EmployeeService employeeService;

	@Autowired
	public AdminService(AdminRepository adminRepository, PersonRepository personRepository, EmployeeService employeeService) {
		this.adminRepository = adminRepository;
		this.personRepository = personRepository;
		this.employeeService = employeeService;
	}


	public Set<Admin> getAll() throws SQLException {
		return adminRepository.getAll();
	}

	public long save(EmployeePostDTO employeePostDTO) throws SQLException, PersistException {
		long personId = personRepository.persist(employeePostDTO);
		employeePostDTO.setPersonId(personId);
		long employeeId = employeeService.save(employeePostDTO);
		return adminRepository.persist(employeeId);
	}
}
