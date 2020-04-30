package com.example.school_platform.services;

import com.example.school_platform.enums.PersonType;
import com.example.school_platform.exceptions.NotFoundException;
import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.Employee;
import com.example.school_platform.models.Person;
import com.example.school_platform.models.dto.EmployeePostDTO;
import com.example.school_platform.repositories.EmployeeRepository;
import com.example.school_platform.utilities.BCryptPasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final PersonService personService;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, PersonService personService) {
		this.employeeRepository = employeeRepository;
		this.personService = personService;
	}

	public long save(EmployeePostDTO employeePostDTO) throws SQLException, PersistException {
		BCryptPasswordHash passwordHasher = new BCryptPasswordHash();
		employeePostDTO.setPassword(passwordHasher.hash(employeePostDTO.getPassword()));
		return employeeRepository.persist(employeePostDTO);
	}

	public Person authenticatePerson(String email, String password) throws SQLException, NotFoundException {
		BCryptPasswordHash validator = new BCryptPasswordHash();
		Optional<Person> person = personService
				.getAll()
				.stream()
				.filter(e -> e.getType() != PersonType.STUDENT &&
						((Employee)e).getEmail().equals(email) &&
						validator.verifyHash(password, ((Employee)e).getPassword()))
				.findFirst();

		if(person.isPresent()){
			return person.get();
		}
		throw new NotFoundException();
	}
}
