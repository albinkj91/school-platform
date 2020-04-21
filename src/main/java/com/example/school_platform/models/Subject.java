package com.example.school_platform.models;

public class Subject {

	private long id;
	private String name;

	public Subject(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Subject{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
