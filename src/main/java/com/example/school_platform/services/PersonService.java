package com.example.school_platform.services;

import com.example.school_platform.enums.PersonType;
import com.example.school_platform.models.*;
import com.example.school_platform.models.dto.PersonPostDTO;
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

	public Person addPerson(PersonPostDTO personPostDTO) throws SQLException {
		Person personResult = null;
		personRepository.initiate();
		long id = personRepository.persistPerson(personPostDTO);

		if(personPostDTO.getPassword() != null){
			BCryptPasswordHash passwordHasher = new BCryptPasswordHash();
			personPostDTO.setPassword(passwordHasher.hash(personPostDTO.getPassword()));
		}

		try {
			switch (personPostDTO.getType()) {
				case ADMIN:
					Admin admin = new Admin(
							id,
							personPostDTO.getName(),
							personPostDTO.getSsn(),
							personPostDTO.getEmail(),
							personPostDTO.getPassword());
					personResult = personRepository.persistAdmin(admin);
					break;
				case GUARDIAN:
					Guardian guardian = new Guardian(
							id,
							personPostDTO.getName(),
							personPostDTO.getSsn(),
							personPostDTO.getEmail(),
							personPostDTO.getPhone(),
							personPostDTO.getPassword());
					personResult = personRepository.persistGuardian(guardian);
					break;
				case STUDENT:
					Student student = new Student(
							id,
							personPostDTO.getName(),
							personPostDTO.getSsn());
					personResult = personRepository.persistStudent(student);
					break;
				case TEACHER:
					Teacher teacher = new Teacher(
							id,
							personPostDTO.getName(),
							personPostDTO.getSsn(),
							personPostDTO.getEmail(),
							personPostDTO.getPhone(),
							personPostDTO.getPassword());
					personResult = personRepository.persistTeacher(teacher);
					break;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return personResult;
	}
}