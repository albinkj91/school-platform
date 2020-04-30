package com.example.school_platform.models.dto;

import com.example.school_platform.enums.PersonType;

import java.util.Set;

public class StudentPostDTO extends PersonPostDTO {

	private String teacherId;
	private Set<Long> guardianIds;

	public StudentPostDTO(String name, String ssn, String teacherId, Set<Long> guardianIds) {
		super(name, ssn, PersonType.STUDENT);
		this.teacherId = teacherId;
		this.guardianIds = guardianIds;
	}


	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public Set<Long> getGuardianIds() {
		return guardianIds;
	}

	public void setGuardianIds(Set<Long> guardianIds) {
		this.guardianIds = guardianIds;
	}
}
