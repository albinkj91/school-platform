package models;

public class Person {

	private long id;
	private String name;
	private String ssn;
	private String type;

	public Person(long id, String name, String ssn, String type) {
		this.id = id;
		this.name = name;
		this.ssn = ssn;
		this.type = type;
	}

	public Person(String name, String ssn, String type) {
		this.name = name;
		this.ssn = ssn;
		this.type = type;
	}

	public Person(){}


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

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", name='" + name + '\'' +
				", ssn='" + ssn + '\'' +
				", type='" + type + '\'' +
				'}';
	}
}
