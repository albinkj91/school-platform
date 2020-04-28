package com.example.school_platform.repositories;

import com.example.school_platform.enums.PersonType;
import com.example.school_platform.models.dto.EmployeeGetDTO;
import com.example.school_platform.models.dto.GuardianGetDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Repository
public class GuardianRepository {

	private Connection connection;
	private Statement statement;

	public GuardianRepository(){
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

	public Set<EmployeeGetDTO> getAllGuardians(){
		Set<EmployeeGetDTO> guardians = new HashSet<>();

		try {
			ResultSet set = statement.executeQuery("SELECT g.id, p.name, p.ssn, p.type, e.email, e.phone " +
					"FROM persons p " +
					"INNER JOIN employees e ON e.person_id = p.id " +
					"INNER JOIN guardians g ON g.employee_id = e.id " +
					"WHERE p.type = 'GUARDIAN'");

			while(set.next()){
				long id = Long.parseLong(set.getString("id"));
				String name = set.getString("name");
				String ssn = set.getString("ssn");
				String type = set.getString("type");
				String email = set.getString("email");
				String phone = set.getString("phone");

				guardians.add(new EmployeeGetDTO(id, name, ssn, PersonType.valueOf(type), email, phone));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guardians;
	}

	public Set<GuardianGetDTO> getGuardiansByStudentId(long studentId) throws SQLException {
		Set<GuardianGetDTO> guardians = new HashSet<>();
		Statement statement = connection.createStatement();
		ResultSet set = statement.executeQuery("SELECT p.name, g.id, e.email, e.phone " +
				"FROM student_guardian sg " +
				"INNER JOIN guardians g ON g.id = sg.guardian_id " +
				"INNER JOIN employees e ON e.id = g.employee_id " +
				"INNER JOIN persons p ON p.id = e.person_id " +
				"WHERE student_id = " + studentId);

		while(set.next()){
			guardians.add(new GuardianGetDTO(
					set.getLong("id"),
					set.getString("name"),
					set.getString("email"),
					set.getString("phone")));
		}
		return guardians;
	}

	public void persistGuardian(long employee_id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO guardians(employee_id)" +
				"value(?)");

		statement.setString(1, String.valueOf(employee_id));
		statement.executeUpdate();
	}
}
