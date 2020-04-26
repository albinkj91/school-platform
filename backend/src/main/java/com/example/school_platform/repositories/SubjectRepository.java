package com.example.school_platform.repositories;

import com.example.school_platform.enums.Grade;
import com.example.school_platform.models.Subject;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Repository
public class SubjectRepository {

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

	public Set<Subject> getSubjectsByStudentId(long studentId) throws SQLException {
		Set<Subject> subjects = new HashSet<>();
		initiate();
		Statement statement = connection.createStatement();
		ResultSet set = statement.executeQuery("SELECT ss.id, su.name, ss.grade FROM student_subject ss " +
				"INNER JOIN subjects su ON ss.subject_id = su.id " +
				"WHERE student_id = " + studentId);

		while(set.next()){
			subjects.add(new Subject(
					set.getLong("id"),
					set.getString("name"),
					Grade.valueOf(set.getString("grade").toUpperCase())));
		}
		return subjects;
	}
}
