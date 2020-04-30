package com.example.school_platform.services;

import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.Person;
import com.example.school_platform.models.dto.PersonPostDTO;
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
	private final GuardianService guardianService;
	private final StudentService studentService;
	private final TeacherRepository teacherRepository;

	@Autowired
	public PersonService(PersonRepository personRepository, AdminRepository adminRepository,
						 GuardianService guardianService, StudentService studentService, TeacherRepository teacherRepository) {
		this.personRepository = personRepository;
		this.adminRepository = adminRepository;
		this.guardianService = guardianService;
		this.studentService = studentService;
		this.teacherRepository = teacherRepository;
	}


	public Set<Person> getAll() throws SQLException {
		Set<Person> personGetDTOSet = new HashSet<>();
		personGetDTOSet.addAll(adminRepository.getAll());
		personGetDTOSet.addAll(guardianService.getAll());
		personGetDTOSet.addAll(studentService.getAll());
		personGetDTOSet.addAll(teacherRepository.getAll());

		return personGetDTOSet;
	}

	public long save(PersonPostDTO person) throws SQLException, PersistException {
		return personRepository.persist(
				person.getName(),
				person.getSsn(),
				person.getType());
	}
}