package com.example.school_platform.repositories;

import com.example.school_platform.enums.PersonType;
import com.example.school_platform.models.Person;
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

	public Set<EmployeeGetDTO> getAll(){
		Set<EmployeeGetDTO> teachers = new HashSet<>();

		try {
			ResultSet set = statement.executeQuery("SELECT t.id, p.name, p.ssn, p.type, e.email, e.phone " +
					"FROM persons p " +
					"INNER JOIN employees e ON e.person_id = p.id " +
					"INNER JOIN teachers t ON t.employee_id = e.id " +
					"WHERE p.type = 'TEACHER'");

			while(set.next()){
				long id = Long.parseLong(set.getString("id"));
				String name = set.getString("name");
				String ssn = set.getString("ssn");
				String type = set.getString("type");
				String email = set.getString("email");
				String phone = set.getString("phone");

				teachers.add(new EmployeeGetDTO(id, name, ssn, PersonType.valueOf(type), email, phone));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teachers;
	}

	public void persist(long employee_id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO teachers(employee_id)" +
				"value(?)");

		statement.setString(1, String.valueOf(employee_id));
		statement.executeUpdate();
	}
}
