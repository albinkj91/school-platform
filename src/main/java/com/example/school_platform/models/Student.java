package com.example.school_platform.models;

import com.example.school_platform.enums.PersonType;

import java.util.List;

public class Student extends Person{

	private List<Subject> subjects;
	private Teacher teacher;

	public Student(long id, String name, String ssn, List<Subject> subjects, Teacher teacher) {
		super(id, name, ssn, PersonType.STUDENT);
		this.subjects = subjects;
		this.teacher = teacher;
	}

	public Student(long id, String name, String ssn){
		super(id, name, ssn, PersonType.STUDENT);
	}

	public Student(){}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Student{} " + super.toString();
	}
}
