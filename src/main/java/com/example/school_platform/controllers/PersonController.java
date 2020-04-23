package com.example.school_platform.controllers;

import com.example.school_platform.models.Person;
import com.example.school_platform.models.dto.PersonDTO;
import com.example.school_platform.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Set;

@RestController
@RequestMapping("/person")
public class PersonController {

	private final PersonService personService;

	@Autowired
	public PersonController(PersonService personService){
		this.personService = personService;
	}

	@GetMapping("/all")
	public Set<Person> getAllPersons(){
		return personService.getAllPersons();
	}

	@PostMapping("/authenticate")
	public boolean authenticate(@RequestParam String email, @RequestParam String password){
		try {
			return personService.authenticatePerson(email, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@PostMapping("/add")
	public Person addPerson(@RequestBody PersonDTO personDTO){
		return personService.addPerson(personDTO);
	}
}
