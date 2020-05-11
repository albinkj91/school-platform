package com.example.school_platform.repositories;

import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.Admin;
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

	public Set<Admin> getAll() throws SQLException {
		Set<Admin> admins = new HashSet<>();

		ResultSet set = statement.executeQuery("SELECT a.id, p.name, p.ssn, p.type, e.email, e.password, e.phone " +
				"FROM persons p " +
				"INNER JOIN employees e ON e.person_id = p.id " +
				"INNER JOIN admins a ON a.employee_id = e.id " +
				"WHERE p.type = 'ADMIN'");

		while(set.next()){
			long id = Long.parseLong(set.getString("id"));
			String name = set.getString("name");
			String ssn = set.getString("ssn");
			String email = set.getString("email");
			String password = set.getString("password");
			String phone = set.getString("phone");

			admins.add(new Admin(id, name, ssn, email, password, phone));
		}
		return admins;
	}

	public long persist(long employeeId) throws SQLException, PersistException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO admins(employee_id)" +
				"value(?)", PreparedStatement.RETURN_GENERATED_KEYS);

		statement.setLong(1, employeeId);
		statement.executeUpdate();
		ResultSet key = statement.getGeneratedKeys();

		if(key.next()){
			return key.getLong(1);
		}
		throw new PersistException();
	}
}
