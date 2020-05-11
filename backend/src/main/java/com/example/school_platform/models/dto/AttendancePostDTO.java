package com.example.school_platform.models.dto;

public class AttendancePostDTO {

	private long studentId;
	private boolean attended;

	public AttendancePostDTO(long studentId, boolean attended) {
		this.studentId = studentId;
		this.attended = attended;
	}


	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public boolean hasAttended() {
		return attended;
	}

	public void setAttended(boolean attended) {
		this.attended = attended;
	}
}
