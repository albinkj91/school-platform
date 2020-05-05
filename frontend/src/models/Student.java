package models;

import java.util.Arrays;

public class Student extends Person {

	private Employee teacher;
	private Employee[] guardians;
	private Subject[] subjects;

	public Student(long id, String name, String ssn, String type, Employee[] guardians, Subject[] subjects) {
		super(id, name, ssn, type);
		this.guardians = guardians;
		this.subjects = subjects;
	}


	public Employee getTeacher() {
		return teacher;
	}

	public void setTeacher(Employee teacher) {
		this.teacher = teacher;
	}

	public Employee[] getGuardians() {
		return guardians;
	}

	public void setGuardians(Employee[] guardians) {
		this.guardians = guardians;
	}

	public Subject[] getSubjects() {
		return subjects;
	}

	public void setSubjects(Subject[] subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Student{" +
				"teacher=" + teacher +
				", guardians=" + Arrays.toString(guardians) +
				", subjects=" + Arrays.toString(subjects) +
				"} " + super.toString();
	}
}
