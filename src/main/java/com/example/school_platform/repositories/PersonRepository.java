package com.example.school_platform.repositories;

import com.example.school_platform.enums.PersonType;
import com.example.school_platform.exceptions.NotFoundException;
import com.example.school_platform.models.*;
import com.example.school_platform.utilities.BCryptPasswordHash;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Repository
public class PersonRepository {

	private Connection conn;

	public void initiate() throws SQLException {
		DatabaseConnector databaseConnector = new DatabaseConnector();
		Properties properties = databaseConnector.getProperties();
		properties.put("url", "jdbc:mysql://localhost:3306/school_plattform");

		conn = databaseConnector.connect(
				properties.getProperty("url"),
				properties.getProperty("user"),
				properties.getProperty("password"));
	}

	public Set<Person> getAllPersons(){
		Set<Person> persons = new HashSet<>();

		try {
			Statement statement = conn.createStatement();
			ResultSet set = statement.executeQuery("SELECT * FROM persons");

			while(set.next()){
				long id = Long.parseLong(set.getString("id"));
				String name = set.getString("name");
				String ssn = set.getString("ssn");
				String type = set.getString("type");

				Person person = getPersonByType(id, name, ssn, type);
				persons.add(person);
			}
		} catch (SQLException | NotFoundException e) {
			e.printStackTrace();
		}
		return persons;
	}

	public Person getPersonByType(long id, String name, String ssn, String type) throws NotFoundException {
		ResultSet set = null;

		try {
			Statement statement = conn.createStatement();

			switch (type) {
				case "admin":
					set = statement.executeQuery("SELECT * FROM admins WHERE person_id=" + id);
					if(set.next()){
						return new Admin(id, name, ssn, set.getString("email"), set.getString("password"));
					}
					break;
				case "guardian":
					set = statement.executeQuery("SELECT * FROM guardians WHERE person_id=" + id);
					if(set.next()){
						return new Guardian(id, name, ssn, set.getString("email"), set.getString("phone"), set.getString("password"));
					}
					break;
				case "student":
					set = statement.executeQuery("SELECT * FROM students WHERE person_id=" + id);
					if(set.next()){
						return new Student(id, name, ssn);
					}
					break;
				case "teacher":
					set = statement.executeQuery("SELECT * FROM teachers WHERE person_id=" + id);
					if(set.next()){
						return new Teacher(id, name, ssn, set.getString("email"), set.getString("phone"), set.getString("password"));
					}
					break;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}

		throw new NotFoundException("A person does not belong to a subclass");
	}

	public Person addPerson(Person person){
		try {
			PreparedStatement statement = conn.prepareStatement("INSERT INTO persons(name, ssn, type)" +
					"value(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, person.getName());
			statement.setString(2, person.getSsn());
			statement.setString(3, person.getType().toString());
			statement.executeUpdate();
			ResultSet set = statement.getGeneratedKeys();

			if(set.next()) {
				person.setId((set.getLong(1)));
				return addPersonByType(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Person addAdmin(Person person) throws SQLException {
		Admin admin = (Admin) person;
		PreparedStatement statement = conn.prepareStatement("INSERT INTO admins(email, password, person_id)" +
				"value(?, ?, ?)");

		statement.setString(1, admin.getEmail());
		statement.setString(2, admin.getPassword());
		statement.setString(3, String.valueOf(admin.getId()));
		statement.executeUpdate();

		return admin;
	}

	private Person addGuardian(Person person) throws SQLException {
		Guardian guardian = (Guardian) person;
		PreparedStatement statement = conn.prepareStatement("INSERT INTO guardians(email, phone, password, person_id)" +
				"value(?, ?, ?, ?)");

		statement.setString(1, guardian.getEmail());
		statement.setString(2, guardian.getPhone());
		statement.setString(3, guardian.getPassword());
		statement.setString(4, String.valueOf(guardian.getId()));
		statement.executeUpdate();

		return guardian;
	}

	public Person addStudent(Person person) throws SQLException {
		Student student = (Student) person;
		PreparedStatement statement = conn.prepareStatement("INSERT INTO students(person_id)" +
				"value(?)");

		statement.setString(1, String.valueOf(student.getId()));
		statement.executeUpdate();

		return student;
	}

	public Person addTeacher(Person person) throws SQLException {
		Teacher teacher = (Teacher) person;
		PreparedStatement statement = conn.prepareStatement("INSERT INTO teachers(email, phone, password, person_id)" +
				"value(?, ?, ?, ?)");

		statement.setString(1, teacher.getEmail());
		statement.setString(2, teacher.getPhone());
		statement.setString(3, teacher.getPassword());
		statement.setString(4, String.valueOf(teacher.getId()));
		statement.executeUpdate();

		return teacher;
	}

	public Person addPersonByType(Person person){
		Person personResult = null;
		try {
			switch (person.getType()) {
				case ADMIN:
					personResult = addAdmin(person);
					break;
				case GUARDIAN:
					personResult = addGuardian(person);
					break;
				case STUDENT:
					personResult = addStudent(person);
					break;
				case TEACHER:
					personResult = addTeacher(person);
					break;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return personResult;
	}
}
