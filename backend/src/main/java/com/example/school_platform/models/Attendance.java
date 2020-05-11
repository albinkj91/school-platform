package com.example.school_platform.models;

import java.sql.Timestamp;

public class Attendance {

	private long id;
	private Timestamp timestamp;
	private long studentId;

	public Attendance(long id, Timestamp timestamp, long studentId) {
		this.id = id;
		this.timestamp = timestamp;
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

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
}
