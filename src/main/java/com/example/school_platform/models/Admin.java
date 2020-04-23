package com.example.school_platform.models;

import com.example.school_platform.enums.PersonType;

public class Admin extends Person{

	private String email;
	private String password;

	public Admin(long id, String name, String ssn, String email, String password) {
		super(id, name, ssn, PersonType.ADMIN);
		this.email = email;
		this.password = password;
	}

	public Admin(){}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin{" +
				"email='" + email + '\'' +
				", password='" + password + '\'' +
				"} " + super.toString();
	}
}
