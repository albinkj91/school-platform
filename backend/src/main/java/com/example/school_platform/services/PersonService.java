package com.example.school_platform.services;

import com.example.school_platform.enums.PersonType;
import com.example.school_platform.models.*;
import com.example.school_platform.models.dto.EmployeeGetDTO;
import com.example.school_platform.models.dto.EmployeePostDTO;
import com.example.school_platform.repositories.PersonRepository;
import com.example.school_platform.utilities.BCryptPasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	private final PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	/*public Set<EmployeeGetDTO> getAllPersons() throws SQLException {
		Set<EmployeeGetDTO> employeeGetDTOSet = new HashSet<>();
		personRepository.initiate();

		return employeeGetDTOSet;
		TODO - List All Persons (Admin)
	}*/

	/*
	    public Optional<Person> authenticatePerson(String email, String password) throws SQLException {
		BCryptPasswordHash validator = new BCryptPasswordHash();
		personRepository.initiate();
		Set<Person> persons = personRepository.getAllPersons();
		TODO - Login Authentication
	}*/

	/*public Person addPerson(EmployeePostDTO employeePostDTO) throws SQLException {
		personRepository.initiate();
		long id = personRepository.persistEmployee(employeePostDTO);

		if (employeePostDTO.getPassword() != null) {
			BCryptPasswordHash passwordHasher = new BCryptPasswordHash();
			employeePostDTO.setPassword(passwordHasher.hash(employeePostDTO.getPassword()));
		}
		return null;
		TODO - Add Person (Admin)
	}*/
}