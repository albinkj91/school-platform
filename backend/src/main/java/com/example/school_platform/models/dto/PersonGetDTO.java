package com.example.school_platform.models.dto;

import com.example.school_platform.enums.PersonType;

public abstract class PersonGetDTO {

	private long id;
	private String name;
	private String ssn;
	private PersonType type;

	public PersonGetDTO(long id, String name, String ssn, PersonType type) {
		this.id = id;
		this.name = name;
		this.ssn = ssn;
		this.type = type;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
