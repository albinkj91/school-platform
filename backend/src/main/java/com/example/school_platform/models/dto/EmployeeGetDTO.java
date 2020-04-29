package com.example.school_platform.models.dto;

import com.example.school_platform.enums.PersonType;

public class EmployeeGetDTO extends PersonGetDTO{

	private String email;
	private String phone;

	public EmployeeGetDTO(long id, String name, String ssn, PersonType type, String email, String phone) {
		super(id, name, ssn, type);
		this.email = email;
		this.phone = phone;
	}

	public EmployeeGetDTO(long id, String name, String ssn, PersonType type, String email) {
		super(id, name, ssn, type);
		this.email = email;
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
