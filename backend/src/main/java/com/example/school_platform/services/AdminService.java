package com.example.school_platform.services;

import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.Admin;
import com.example.school_platform.models.dto.EmployeePostDTO;
import com.example.school_platform.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class AdminService {

	private final AdminRepository adminRepository;
	private final PersonService personService;
	private final EmployeeService employeeService;

	@Autowired
	public AdminService(AdminRepository adminRepository, PersonService personService, EmployeeService employeeService) {
		this.adminRepository = adminRepository;
		this.personService = personService;
		this.employeeService = employeeService;
	}

	public Set<Admin> getAll() throws SQLException {
		return adminRepository.getAll();
	}

	public long save(EmployeePostDTO employeePostDTO) throws SQLException, PersistException {
		long personId = personService.save(employeePostDTO);
		employeePostDTO.setPersonId(personId);
		long employeeId = employeeService.save(employeePostDTO);
		return adminRepository.persist(employeeId);
	}
}
