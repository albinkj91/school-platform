package com.example.school_platform.repositories;

import com.example.school_platform.exceptions.NotFoundException;
import com.example.school_platform.models.*;
import com.example.school_platform.models.dto.PersonPostDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Repository
public class PersonRepository {

	private Connection connection;


	public void initiate() throws SQLException {
		DatabaseConnector databaseConnector = new DatabaseConnector();
		Properties properties = databaseConnector.getProperties();
		properties.put("url", "jdbc:mysql://localhost:3306/school_plattform");

		connection = databaseConnector.connect(
				properties.getProperty("url"),
				properties.getProperty("user"),
				properties.getProperty("password"));
	}

	public Set<Person> getAllPersons(){
		Set<Person> persons = new HashSet<>();

		try {
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery("SELECT * FROM persons");

			while(set.next()){
				long id = Long.parseLong(set.getString("id"));
				String name = set.getString("name");
				String ssn = set.getString("ssn");
				String type = set.getString("type");

				Person person = getByType(id, name, ssn, type);
				persons.add(person);
			}
		} catch (SQLException | NotFoundException e) {
			e.printStackTrace();
		}
		return persons;
	}

	public Person getByType(long personId, String name, String ssn, String type) throws NotFoundException {
		ResultSet set;

		try {
			Statement statement = connection.createStatement();

			switch (type) {
				case "admin":
					set = statement.executeQuery("SELECT * FROM admins WHERE person_id=" + personId);
					if(set.next()){
						return new Admin(
								set.getLong("id"),
								name,
								ssn,
								set.getString("email"),
								set.getString("password"));
					}
					break;
				case "guardian":
					set = statement.executeQuery("SELECT * FROM guardians WHERE person_id=" + personId);
					if(set.next()){
						return new Guardian(
								set.getLong("id"),
								name,
								ssn,
								set.getString("email"),
								set.getString("phone"),
								set.getString("password"));
					}
					break;
				case "student":
					set = statement.executeQuery("SELECT * FROM students WHERE person_id=" + personId);
					if(set.next()){
						return new Student(
								set.getLong("id"),
								name,
								ssn);
					}
					break;
				case "teacher":
					set = statement.executeQuery("SELECT * FROM teachers WHERE person_id=" + personId);
					if(set.next()){
						return new Teacher(
								set.getLong("id"),
								name,
								ssn,
								set.getString("email"),
								set.getString("phone"),
								set.getString("password"));
					}
					break;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}

		throw new NotFoundException("A person does not belong to a subclass");
	}

	public long persistPerson(PersonPostDTO personPostDTO) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO persons(name, ssn, type)" +
				"value(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, personPostDTO.getName());
		statement.setString(2, personPostDTO.getSsn());
		statement.setString(3, personPostDTO.getType().toString());
		statement.executeUpdate();
		ResultSet set = statement.getGeneratedKeys();

		if(set.next()) {
			return set.getLong(1);
		}
		return -1;
	}

	public Person persistAdmin(Admin admin) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO admins(email, password, person_id)" +
				"value(?, ?, ?)");

		statement.setString(1, admin.getEmail());
		statement.setString(2, admin.getPassword());
		statement.setString(3, String.valueOf(admin.getId()));
		statement.executeUpdate();

		return admin;
	}

	public Person persistGuardian(Guardian guardian) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO guardians(email, phone, password, person_id)" +
				"value(?, ?, ?, ?)");

		statement.setString(1, guardian.getEmail());
		statement.setString(2, guardian.getPhone());
		statement.setString(3, guardian.getPassword());
		statement.setString(4, String.valueOf(guardian.getId()));
		statement.executeUpdate();

		return guardian;
	}

	public Person persistStudent(Student student) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO students(person_id)" +
				"value(?)");

		statement.setString(1, String.valueOf(student.getId()));
		statement.executeUpdate();

		return student;
	}

	public Person persistTeacher(Teacher teacher) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO teachers(email, phone, password, person_id)" +
				"value(?, ?, ?, ?)");

		statement.setString(1, teacher.getEmail());
		statement.setString(2, teacher.getPhone());
		statement.setString(3, teacher.getPassword());
		statement.setString(4, String.valueOf(teacher.getId()));
		statement.executeUpdate();

		return teacher;
	}
}