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
		persons = filterPersonsForLogin(persons);
		return persons
				.stream()
				.anyMatch(p -> {
					switch(p.getType()){
						case ADMIN:
							return ((Admin) p)
									.getEmail()
									.equalsIgnoreCase(email) &&
									validator.verifyHash(password, ((Admin) p).getPassword());
						case TEACHER:
							return ((Teacher) p)
									.getEmail()
									.equalsIgnoreCase(email) &&
									validator.verifyHash(password, ((Teacher) p).getPassword());
						case GUARDIAN:
							return ((Guardian) p)
									.getEmail()
									.equalsIgnoreCase(email) &&
									validator.verifyHash(password, ((Guardian) p).getPassword());
						default:
							return false;
					}
				});
	}

	private Set<Person> filterPersonsForLogin(Set<Person> persons){
		return persons
				.stream()
				.filter(p -> p.getType() != PersonType.STUDENT)
				.collect(Collectors.toSet());
	}

	public Person addPerson(PersonDTO personDTO) throws SQLException {
		Person personResult = null;
		personRepository.initiate();
		long id = personRepository.persistPerson(personDTO);

		if(personDTO.getPassword() != null){
			BCryptPasswordHash passwordHasher = new BCryptPasswordHash();
			personDTO.setPassword(passwordHasher.hash(personDTO.getPassword()));
		}

		try {
			switch (personDTO.getType()) {
				case ADMIN:
					Admin admin = new Admin(
							id,
							personDTO.getName(),
							personDTO.getSsn(),
							personDTO.getEmail(),
							personDTO.getPassword());
					personResult = personRepository.persistAdmin(admin);
					break;
				case GUARDIAN:
					Guardian guardian = new Guardian(
							id,
							personDTO.getName(),
							personDTO.getSsn(),
							personDTO.getEmail(),
							personDTO.getPhone(),
							personDTO.getPassword());
					personResult = personRepository.persistGuardian(guardian);
					break;
				case STUDENT:
					Student student = new Student(
							id,
							personDTO.getName(),
							personDTO.getSsn());
					personResult = personRepository.persistStudent(student);
					break;
				case TEACHER:
					Teacher teacher = new Teacher(
							id,
							personDTO.getName(),
							personDTO.getSsn(),
							personDTO.getEmail(),
							personDTO.getPhone(),
							personDTO.getPassword());
					personResult = personRepository.persistTeacher(teacher);
					break;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return personResult;
	}
}