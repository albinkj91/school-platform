package com.example.school_platform.models;

import com.example.school_platform.enums.PersonType;

public class Guardian extends Person{

	private String email;
	private String phone;
	private String password;

	public Guardian(long id, String name, String ssn, String email, String phone, String password) {
		super(id, name, ssn, PersonType.GUARDIAN);
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Guardian(){}


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

	@Override
	public String toString() {
		return "Guardian{" +
				"email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", password='" + password + '\'' +
				"} " + super.toString();
	}
}
