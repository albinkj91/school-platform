package com.example.school_platform.repositories;

import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.dto.EmployeePostDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class EmployeeRepository {

	private Connection connection;

	public EmployeeRepository(){
		setupConnection();
	}

	public void setupConnection(){
		DatabaseConnector connector = new DatabaseConnector();
		try {
			connection = connector.initiate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public long persist(EmployeePostDTO employeePostDTO) throws SQLException, PersistException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO employees(email, password, phone, person_id )" +
				"value(?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

		statement.setString(1, employeePostDTO.getEmail());
		statement.setString(2, employeePostDTO.getPassword());
		statement.setString(3, employeePostDTO.getPhone());
		statement.setString(4, Long.toString(employeePostDTO.getPersonId()));
		statement.executeUpdate();
		ResultSet key = statement.getGeneratedKeys();

		if(key.next()){
			return key.getLong(1);
		}
		throw new PersistException();
	}
}
