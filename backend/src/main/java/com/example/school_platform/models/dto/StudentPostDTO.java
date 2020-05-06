package com.example.school_platform.models.dto;

import com.example.school_platform.enums.PersonType;

import java.util.Set;

public class StudentPostDTO extends PersonPostDTO {

	private long teacherId;
	private Set<Long> guardianIds;

	public StudentPostDTO(String name, String ssn, long teacherId, Set<Long> guardianIds) {
		super(name, ssn, PersonType.STUDENT);
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
		return "StudentPostDTO{" +
				"teacherId=" + teacherId +
				", guardianIds=" + guardianIds +
				"} " + super.toString();
	}
}
