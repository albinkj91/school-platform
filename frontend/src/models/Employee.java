package models;

public class Employee extends Person{

	private String email;
	private String password;
	private String phone;

	public Employee(long id, String name, String ssn, String type, String email, String password, String phone) {
		super(id, name, ssn, type);
		this.email = email;
		this.password = password;
		this.phone = phone;
	}

	public Employee(String name, String ssn, String type, String email, String phone) {
		super(name, ssn, type);
		this.email = email;
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
				"email='" + email + '\'' +
				", password='" + password + '\'' +
				", phone='" + phone + '\'' +
				"} " + super.toString();
	}
}
