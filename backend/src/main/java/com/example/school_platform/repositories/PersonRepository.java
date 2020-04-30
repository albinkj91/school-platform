package com.example.school_platform.repositories;

import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.dto.PersonPostDTO;
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

	public long persist(PersonPostDTO personPostDTO) throws SQLException, PersistException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO persons(name, ssn, type)" +
				"value(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, personPostDTO.getName());
		statement.setString(2, personPostDTO.getSsn());
		statement.setString(3, personPostDTO.getType().toString());
		statement.executeUpdate();
		ResultSet set = statement.getGeneratedKeys();

		if(set.next()) {
			return set.getLong(1);
		}
		throw new PersistException();
	}
}
