package com.example.school_platform.models;

import com.example.school_platform.enums.PersonType;

public abstract class Employee extends Person{
	
	private String email;
	private String password;
	private String phone;

	public Employee(long id, String name, String ssn, PersonType type, String email, String password, String phone) {
		super(id, name, ssn, type);
		this.email = email;
		this.password = password;
		this.phone = phone;
	}

	public Employee() {}


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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
