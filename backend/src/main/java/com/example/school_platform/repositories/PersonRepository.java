package com.example.school_platform.repositories;

import com.example.school_platform.enums.PersonType;
import com.example.school_platform.exceptions.PersistException;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class PersonRepository {

	private Connection connection;

	public PersonRepository(){
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

	public long persist(String name, String ssn, PersonType type) throws SQLException, PersistException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO persons(name, ssn, type)" +
				"value(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, name);
		statement.setString(2, ssn);
		statement.setString(3, type.toString());
		statement.executeUpdate();
		ResultSet set = statement.getGeneratedKeys();

		if(set.next()) {
			return set.getLong(1);
		}
		throw new PersistException();
	}
}
