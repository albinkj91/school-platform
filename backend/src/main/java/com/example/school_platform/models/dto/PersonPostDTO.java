package com.example.school_platform.models.dto;

import com.example.school_platform.enums.PersonType;

public abstract class PersonPostDTO {

	private String name;
	private String ssn;
	private PersonType type;

	public PersonPostDTO(String name, String ssn, PersonType type) {
		this.name = name;
		this.ssn = ssn;
		this.type = type;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public PersonType getType() {
		return type;
	}

	public void setType(PersonType type) {
		this.type = type;
	}
}
