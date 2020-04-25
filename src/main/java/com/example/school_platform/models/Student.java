package com.example.school_platform.models;

import com.example.school_platform.enums.PersonType;
import com.example.school_platform.models.dto.GuardianGetDTO;

import java.util.Set;

public class Student extends Person{

	private Set<Subject> subjects;
	private Set<GuardianGetDTO> guardians;

	public Student(long id, String name, String ssn, Set<Subject> subjects, Set<GuardianGetDTO> guardians) {
		super(id, name, ssn, PersonType.STUDENT);
		this.subjects = subjects;
		this.guardians = guardians;
	}

	public Student(long id, String name, String ssn){
		super(id, name, ssn, PersonType.STUDENT);
	}

	public Student(){}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}

	public Set<GuardianGetDTO> getGuardians() {
		return guardians;
	}

	public void setGuardians(Set<GuardianGetDTO> guardians) {
		this.guardians = guardians;
	}

	@Override
	public String toString() {
		return "Student{} " + super.toString();
	}
}
