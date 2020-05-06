package models;

import java.util.Set;

public class Student extends Person {

	private long teacherId;
	private Set<Long> guardianIds;

	public Student(String name, String ssn, String type, long teacherId, Set<Long> guardianIds) {
		super(name, ssn, type);
		this.teacherId = teacherId;
		this.guardianIds = guardianIds;
	}

	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}

	public Set<Long> getGuardianIds() {
		return guardianIds;
	}

	public void setGuardianIds(Set<Long> guardianIds) {
		this.guardianIds = guardianIds;
	}

	@Override
	public String toString() {
		return "Student{" +
				"teacherId=" + teacherId +
				", guardianIds=" + guardianIds +
				"} " + super.toString();
	}
}
