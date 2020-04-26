package com.example.school_platform.repositories;

import com.example.school_platform.models.dto.GuardianGetDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Repository
public class GuardianRepository {

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

	public Set<GuardianGetDTO> getGuardiansByStudentId(long studentId) throws SQLException {
		Set<GuardianGetDTO> guardians = new HashSet<>();
		initiate();
		Statement statement = connection.createStatement();
		ResultSet set = statement.executeQuery("SELECT p.name, g.id, g.email, g.phone FROM student_guardian sg " +
				"INNER JOIN guardians g ON g.id = sg.guardian_id " +
				"INNER JOIN persons p ON p.id = g.person_id " +
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
}
