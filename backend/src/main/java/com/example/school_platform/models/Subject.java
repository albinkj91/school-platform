package com.example.school_platform.models;

import com.example.school_platform.enums.Grade;

public class Subject {

	private long id;
	private String name;
	private Grade grade;

	public Subject(long id, String name, Grade grade) {
		this.id = id;
		this.name = name;
		this.grade = grade;
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

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Subject{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
