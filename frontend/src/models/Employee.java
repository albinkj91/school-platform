package models;

public class Employee {

	private String name;
	private String ssn;
	private String email;
	private String phone;

	public Employee(String name, String ssn, String email, String phone) {
		this.name = name;
		this.ssn = ssn;
		this.email = email;
		this.phone = phone;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"name='" + name + '\'' +
				", ssn='" + ssn + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				'}';
	}
}
