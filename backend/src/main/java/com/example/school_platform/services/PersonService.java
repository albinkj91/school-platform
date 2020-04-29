package com.example.school_platform.services;

import com.example.school_platform.models.dto.PersonGetDTO;
import com.example.school_platform.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Service
public class PersonService {

	private final PersonRepository personRepository;
	private final AdminRepository adminRepository;
	private final GuardianRepository guardianRepository;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;

	@Autowired
	public PersonService(PersonRepository personRepository, AdminRepository adminRepository, GuardianRepository guardianRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
		this.personRepository = personRepository;
		this.adminRepository = adminRepository;
		this.guardianRepository = guardianRepository;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
	}


	public Set<PersonGetDTO> getAllPersons() {
		Set<PersonGetDTO> personGetDTOSet = new HashSet<>();
		personGetDTOSet.addAll(adminRepository.getAll());
		personGetDTOSet.addAll(guardianRepository.getAll());
		personGetDTOSet.addAll(studentRepository.getAll());
		personGetDTOSet.addAll(teacherRepository.getAll());

		return personGetDTOSet;
	}

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