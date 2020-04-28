package com.example.school_platform.models;

import com.example.school_platform.enums.PersonType;

public class Admin extends Employee{

	public Admin(long id, String name, String ssn, PersonType type, String email, String password, String phone) {
		super(id, name, ssn, type, email, password, phone);
	}

	public Admin(){}


	@Override
	public String toString() {
		return "Admin{" +
				"email='" + getEmail() + '\'' +
				", phone='" + getPhone() + '\'' +
				", password='" + getPassword() + '\'' +
				"} " + super.toString();
	}
}
