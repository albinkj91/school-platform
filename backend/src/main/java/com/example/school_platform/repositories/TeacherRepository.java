package com.example.school_platform.repositories;

import com.example.school_platform.enums.PersonType;
import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.Teacher;
import com.example.school_platform.models.dto.EmployeeGetDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Repository
public class TeacherRepository {

	private Connection connection;
	private Statement statement;

	public TeacherRepository(){
		setupConnection();
	}

	public void setupConnection(){
		DatabaseConnector connector = new DatabaseConnector();
		try {
			connection = connector.initiate();
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Set<Teacher> getAll() throws SQLException {
		Set<Teacher> teachers = new HashSet<>();

		ResultSet set = statement.executeQuery("SELECT t.id, p.name, p.ssn, p.type, e.email, e.password, e.phone " +
				"FROM persons p " +
				"INNER JOIN employees e ON e.person_id = p.id " +
				"INNER JOIN teachers t ON t.employee_id = e.id " +
				"WHERE p.type = 'TEACHER'");

		while(set.next()){
			long id = Long.parseLong(set.getString("id"));
			String name = set.getString("name");
			String ssn = set.getString("ssn");
			String email = set.getString("email");
			String password = set.getString("password");
			String phone = set.getString("phone");

			teachers.add(new Teacher(id, name, ssn, email, password, phone));
		}
		return teachers;
	}

	public long persist(long employee_id) throws SQLException, PersistException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO teachers(employee_id)" +
				"value(?)", PreparedStatement.RETURN_GENERATED_KEYS);

		statement.setLong(1, employee_id);
		statement.executeUpdate();
		ResultSet key = statement.getGeneratedKeys();

		if(key.next()){
			return key.getLong(1);
		}
		throw new PersistException();
	}
}
