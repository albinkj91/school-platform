package com.example.school_platform.repositories;

import com.example.school_platform.exceptions.NotFoundException;
import com.example.school_platform.models.*;

import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

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

	public static void main(String[] args) {
		PersonRepository personRepository = new PersonRepository();
		try {
			personRepository.initiate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Set<Person> persons = personRepository.getAllPersons();
		persons.forEach(System.out::println);
	}
}
