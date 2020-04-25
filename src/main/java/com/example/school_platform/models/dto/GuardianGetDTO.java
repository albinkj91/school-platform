package com.example.school_platform.models.dto;

import com.example.school_platform.enums.PersonType;

public class GuardianGetDTO {

	private long id;
	private String name;
	private final PersonType type;
	private String email;
	private String phone;

	public GuardianGetDTO(long id, String name, String email, String phone) {
		this.id = id;
		this.name = name;
		this.type = PersonType.GUARDIAN;
		this.email = email;
		this.phone = phone;
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

	public PersonType getType() {
		return type;
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
