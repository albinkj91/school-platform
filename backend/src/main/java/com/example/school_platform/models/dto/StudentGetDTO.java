package com.example.school_platform.models.dto;

import com.example.school_platform.enums.PersonType;

public class StudentGetDTO extends PersonGetDTO{

	public StudentGetDTO(long id, String name, String ssn) {
		super(id, name, ssn, PersonType.STUDENT);
	}
}
