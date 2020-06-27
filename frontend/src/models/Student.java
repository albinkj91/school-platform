package models;

import java.util.Set;

public class Student extends Person{

	private long teacherId;
	private Set<Employee> guardians;
	private Set<Subject> subjects;

	public Student(long id, String name, String ssn, String type, long teacherId, Set<Employee> guardians, Set<Subject> subjects) {
		super(id, name, ssn, type);
		this.teacherId = teacherId;
		this.guardians = guardians;
		this.subjects = subjects;
	}

	public Student(String name, String ssn, String type) {
		super(name, ssn, type);
	}

	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}

	public Set<Employee> getGuardians() {
		return guardians;
	}

	public void setGuardians(Set<Employee> guardians) {
		this.guardians = guardians;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
}
