package com.example.school_platform.services;

import com.example.school_platform.enums.PersonType;
import com.example.school_platform.models.*;
import com.example.school_platform.models.dto.PersonGetDTO;
import com.example.school_platform.models.dto.PersonPostDTO;
import com.example.school_platform.repositories.PersonRepository;
import com.example.school_platform.utilities.BCryptPasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {

	private final PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Set<PersonGetDTO> getAllPersons() throws SQLException {
		Set<PersonGetDTO> personGetDTOSet = new HashSet<>();
		personRepository.initiate();
		Set<Person> persons = personRepository.getAllPersons();
		persons.forEach(p -> {
			switch (p.getType()) {
				case ADMIN:
					Admin admin = (Admin) p;
					personGetDTOSet.add(
							new PersonGetDTO(
									admin.getId(),
									admin.getName(),
									admin.getSsn(),
									admin.getType(),
									admin.getEmail()));
					break;
				case GUARDIAN:
					Guardian guardian = (Guardian) p;
					personGetDTOSet.add(
							new PersonGetDTO(
									guardian.getId(),
									guardian.getName(),
									guardian.getSsn(),
									guardian.getType(),
									guardian.getEmail(),
									guardian.getPhone()));
					break;
				case TEACHER:
					Teacher teacher = (Teacher) p;
					personGetDTOSet.add(
							new PersonGetDTO(
									teacher.getId(),
									teacher.getName(),
									teacher.getSsn(),
									teacher.getType(),
									teacher.getEmail(),
									teacher.getPhone()));
					break;
				case STUDENT:
					Student student = (Student) p;
					personGetDTOSet.add(
							new PersonGetDTO(
									student.getId(),
									student.getName(),
									student.getSsn(),
									student.getType()));
			}
		});
		return personGetDTOSet;
	}

	public boolean authenticatePerson(String email, String password) throws SQLException {
		BCryptPasswordHash validator = new BCryptPasswordHash();
		personRepository.initiate();

		Set<Person> persons = personRepository.getAllPersons();
		persons = filterPersonsForLogin(persons);
		return persons
				.stream()
				.anyMatch(p -> {
					switch (p.getType()) {
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

	private Set<Person> filterPersonsForLogin(Set<Person> persons) {
		return persons
				.stream()
				.filter(p -> p.getType() != PersonType.STUDENT)
				.collect(Collectors.toSet());
	}

	public Person addPerson(PersonPostDTO personPostDTO) throws SQLException {
		personRepository.initiate();
		long id = personRepository.persistPerson(personPostDTO);

		if (personPostDTO.getPassword() != null) {
			BCryptPasswordHash passwordHasher = new BCryptPasswordHash();
			personPostDTO.setPassword(passwordHasher.hash(personPostDTO.getPassword()));
		}

		switch (personPostDTO.getType()) {
			case ADMIN:
				Admin admin = new Admin(
						id,
						personPostDTO.getName(),
						personPostDTO.getSsn(),
						personPostDTO.getEmail(),
						personPostDTO.getPassword());
				return personRepository.persistAdmin(admin);
			case GUARDIAN:
				Guardian guardian = new Guardian(
						id,
						personPostDTO.getName(),
						personPostDTO.getSsn(),
						personPostDTO.getEmail(),
						personPostDTO.getPhone(),
						personPostDTO.getPassword());
				return personRepository.persistGuardian(guardian);
			case STUDENT:
				Student student = new Student(
						id,
						personPostDTO.getName(),
						personPostDTO.getSsn());
				return personRepository.persistStudent(student);
			case TEACHER:
				Teacher teacher = new Teacher(
						id,
						personPostDTO.getName(),
						personPostDTO.getSsn(),
						personPostDTO.getEmail(),
						personPostDTO.getPhone(),
						personPostDTO.getPassword());
				return personRepository.persistTeacher(teacher);
		}
		return null;
	}
}