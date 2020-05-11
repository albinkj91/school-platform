package com.example.school_platform.models;

import java.sql.Timestamp;

public class Attendance {

	private long id;
	private Timestamp timestamp;
	private boolean attended;
	private long studentId;

	public Attendance(long id, Timestamp timestamp, boolean attended, long studentId) {
		this.id = id;
		this.timestamp = timestamp;
		this.attended = attended;
		this.studentId = studentId;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isAttended() {
		return attended;
	}

	public void setAttended(boolean attended) {
		this.attended = attended;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
}
