package com.example.school_platform.models;

import com.example.school_platform.enums.PersonType;

public class Teacher extends Employee{

	public Teacher(long id, String name, String ssn, String email, String password, String phone) {
		super(id, name, ssn, PersonType.TEACHER, email, password, phone);
	}

	public Teacher(){}


	@Override
	public String toString() {
		return "Teacher{" +
				"email='" + getEmail() + '\'' +
				", phone='" + getPhone() + '\'' +
				", password='" + getPassword() + '\'' +
				"} " + super.toString();
	}
}
