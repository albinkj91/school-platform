package com.example.school_platform.models;

import com.example.school_platform.enums.PersonType;

public class Guardian extends Employee{

	public Guardian(long id, String name, String ssn, PersonType type, String email, String password, String phone) {
		super(id, name, ssn, type, email, password, phone);
	}

	public Guardian(){}


	@Override
	public String toString() {
		return "Guardian{" +
				"email='" + getEmail() + '\'' +
				", phone='" + getPhone() + '\'' +
				", password='" + getPassword() + '\'' +
				"} " + super.toString();
	}
}
