package com.example.school_platform.models;

import com.example.school_platform.enums.PersonType;

public class Student extends Person{
	public Student(long id, String name, String ssn) {
		super(id, name, ssn, PersonType.STUDENT);
	}

	public Student(){}

	@Override
	public String toString() {
		return "Student{" + super.toString() +
				"}";
	}
}
