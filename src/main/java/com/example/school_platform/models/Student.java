package com.example.school_platform.models;

import com.example.school_platform.enums.PersonType;

import java.util.List;

public class Student extends Person{

	private List<Subject> subjects;
	private List<Guardian> guardians;
	private Teacher teacher;

	public Student(long id, String name, String ssn, List<Subject> subjects, List<Guardian> guardians, Teacher teacher) {
		super(id, name, ssn, PersonType.STUDENT);
		this.subjects = subjects;
		this.guardians = guardians;
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

	public List<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(List<Guardian> guardians) {
		this.guardians = guardians;
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
