package com.example.school_platform.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {

	public Properties getProperties(){
		Properties properties = new Properties();

		try {
			properties.load(Files.newBufferedReader(Paths.get("src/main/resources/database.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public Connection connect(String url, String user, String password) throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public Connection initiate() throws SQLException {
		Properties properties = getProperties();
		properties.put("url", "jdbc:mysql://localhost:3306/school_plattform");

		return connect(
				properties.getProperty("url"),
				properties.getProperty("user"),
				properties.getProperty("password"));
	}
}
