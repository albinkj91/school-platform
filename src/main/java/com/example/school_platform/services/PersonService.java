package com.example.school_platform.services;

import com.example.school_platform.enums.PersonType;
import com.example.school_platform.models.*;
import com.example.school_platform.models.dto.PersonDTO;
import com.example.school_platform.repositories.PersonRepository;
import com.example.school_platform.utilities.BCryptPasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {

	private final PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository){
		this.personRepository = personRepository;
	}

	public Set<Person> getAllPersons() throws SQLException {
		personRepository.initiate();
		return personRepository.getAllPersons();
	}

	public boolean authenticatePerson(String email, String password) throws SQLException {
		BCryptPasswordHash validator = new BCryptPasswordHash();
		personRepository.initiate();

		Set<Person> persons = personRepository.getAllPersons();
		persons = persons.stream().filter(p -> p.getType() == PersonType.GUARDIAN).collect(Collectors.toSet());
		return persons
				.stream()
				.anyMatch(p -> ((Guardian) p).getEmail().equalsIgnoreCase(email) &&
								validator.verifyHash(password, ((Guardian) p).getPassword()));
	}

	public Person addPerson(PersonDTO personDTO) throws SQLException {
		Person personResult = null;
		personRepository.initiate();
		long id = personRepository.addPerson(personDTO);

		try {
			switch (personDTO.getType()) {
				case ADMIN:
					Admin admin = new Admin(
							id,
							personDTO.getName(),
							personDTO.getSsn(),
							personDTO.getEmail(),
							personDTO.getPassword());
					personResult = personRepository.addAdmin(admin);
					break;
				case GUARDIAN:
					Guardian guardian = new Guardian(
							id,
							personDTO.getName(),
							personDTO.getSsn(),
							personDTO.getEmail(),
							personDTO.getPhone(),
							personDTO.getPassword());
					personResult = personRepository.addGuardian(guardian);
					break;
				case STUDENT:
					Student student = new Student(
							id,
							personDTO.getName(),
							personDTO.getSsn());
					personResult = personRepository.addStudent(student);
					break;
				case TEACHER:
					Teacher teacher = new Teacher(
							id,
							personDTO.getName(),
							personDTO.getSsn(),
							personDTO.getEmail(),
							personDTO.getPhone(),
							personDTO.getPassword());
					personResult = personRepository.addTeacher(teacher);
					break;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return personResult;
	}
}