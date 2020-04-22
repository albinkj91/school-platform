package com.example.school_platform.controllers;

import com.example.school_platform.services.PersonService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/person")
public class PersonController {

	private final PersonService personService;

	@Autowired
	public PersonController(PersonService personService){
		this.personService = personService;
	}

	@PostMapping("/login")
	public boolean getAllPersons(@RequestParam String email, @RequestParam String password){
		try {
			return personService.getAllPersons(email, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
