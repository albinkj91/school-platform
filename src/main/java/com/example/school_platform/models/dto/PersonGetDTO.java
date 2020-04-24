package com.example.school_platform.models.dto;

import com.example.school_platform.enums.PersonType;

public class PersonGetDTO {

	private String name;
	private String ssn;
	private PersonType type;
	private String email;
	private String phone;

	public PersonGetDTO(String name, String ssn, PersonType type, String email, String phone) {
		this.name = name;
		this.ssn = ssn;
		this.type = type;
		this.email = email;
		this.phone = phone;
	}

	public PersonGetDTO(String name, String ssn, PersonType type, String email) {
		this.name = name;
		this.ssn = ssn;
		this.type = type;
		this.email = email;
	}

	public PersonGetDTO(String name, String ssn, PersonType type) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
