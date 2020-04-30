package com.example.school_platform.models.dto;

import com.example.school_platform.enums.PersonType;

public class EmployeePostDTO extends PersonPostDTO{

	private String email;
	private String phone;
	private String password;
	private long personId;

	public EmployeePostDTO(String name, String ssn, PersonType type, String email, String phone, String password) {
		super(name, ssn, type);
		this.email = email;
		this.phone = phone;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}
}
