package com.example.school_platform.services;

import com.example.school_platform.enums.PersonType;
import com.example.school_platform.models.Admin;
import com.example.school_platform.models.Person;
import com.example.school_platform.repositories.PersonRepository;
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

	public boolean getAllPersons(String email, String password) throws SQLException {
		personRepository.initiate();
		Set<Person> persons = personRepository.getAllPersons();
		persons = persons.stream().filter(p -> p.getType() == PersonType.ADMIN).collect(Collectors.toSet());
		return persons
				.stream()
				.anyMatch(p -> ((Admin) p).getEmail().equalsIgnoreCase(email) &&
								((Admin) p).getPassword().equals(password));
	}
}
