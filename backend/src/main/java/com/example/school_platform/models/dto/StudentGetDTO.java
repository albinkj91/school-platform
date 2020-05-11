package com.example.school_platform.models.dto;

import com.example.school_platform.enums.PersonType;
import com.example.school_platform.models.Attendance;
import com.example.school_platform.models.Subject;

import java.util.Set;

public class StudentGetDTO extends PersonGetDTO{

	private long teacherId;
	private Set<Subject> subjects;
	private Set<GuardianGetDTO> guardians;
	private Set<Attendance> attendances;

	public StudentGetDTO(long id, String name, String ssn, PersonType type, long teacherId,
						 Set<Subject> subjects, Set<GuardianGetDTO> guardians, Set<Attendance> attendances) {
		super(id, name, ssn, type);
		this.teacherId = teacherId;
		this.subjects = subjects;
		this.guardians = guardians;
		this.attendances = attendances;
	}

	public StudentGetDTO(long id, String name, String ssn, long teacherId) {
		super(id, name, ssn, PersonType.STUDENT);
		this.teacherId = teacherId;
	}


	public long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}

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

	public Set<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(Set<Attendance> attendances) {
		this.attendances = attendances;
	}
}
