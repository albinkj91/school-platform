package com.example.school_platform.repositories;

import com.example.school_platform.enums.PersonType;
import com.example.school_platform.models.dto.EmployeeGetDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Repository
public class AdminRepository {

	private Connection connection;
	private Statement statement;

	public AdminRepository(){
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

	public Set<EmployeeGetDTO> getAllAdmins(){
		Set<EmployeeGetDTO> admins = new HashSet<>();

		try {
			ResultSet set = statement.executeQuery("SELECT a.id, p.name, p.ssn, p.type, e.email, e.phone " +
					"FROM persons p " +
					"INNER JOIN employees e ON e.person_id = p.id " +
					"INNER JOIN admins a ON a.employee_id = e.id " +
					"WHERE p.type = 'ADMIN'");

			while(set.next()){
				long id = Long.parseLong(set.getString("id"));
				String name = set.getString("name");
				String ssn = set.getString("ssn");
				String type = set.getString("type");
				String email = set.getString("email");
				String phone = set.getString("phone");

				admins.add(new EmployeeGetDTO(id, name, ssn, PersonType.valueOf(type), email, phone));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admins;
	}

	public void persistAdmin(long employeeId) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO admins(employee_id)" +
				"value(?)");

		statement.setString(1, String.valueOf(employeeId));
		statement.executeUpdate();
	}
}
