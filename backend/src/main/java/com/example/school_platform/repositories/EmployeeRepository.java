package com.example.school_platform.repositories;

import com.example.school_platform.models.dto.EmployeePostDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class EmployeeRepository {

	private Connection connection;
	private Statement statement;

	public EmployeeRepository(){
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

	public void persist(String email, String password, String phone, long person_id){
		// TODO
	}
}
