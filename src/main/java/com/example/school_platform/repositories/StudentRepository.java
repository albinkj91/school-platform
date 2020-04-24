package com.example.school_platform.repositories;

import com.example.school_platform.exceptions.NotFoundException;
import com.example.school_platform.models.Student;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Repository
public class StudentRepository {

	private Connection conn;


	public void initiate() throws SQLException {
		DatabaseConnector databaseConnector = new DatabaseConnector();
		Properties properties = databaseConnector.getProperties();
		properties.put("url", "jdbc:mysql://localhost:3306/school_plattform");

		conn = databaseConnector.connect(
				properties.getProperty("url"),
				properties.getProperty("user"),
				properties.getProperty("password"));
	}

	public Set<Student> getStudentsByTeacherId(long teacherId) throws NotFoundException {
		Set<Student> students = new HashSet<>();
		try {
			initiate();
			Statement statement = conn.createStatement();
			ResultSet set = statement.executeQuery("SELECT * FROM students s " +
					"INNER JOIN persons p ON s.person_id = p.id " +
					"WHERE teacher_id=" + teacherId);

			while(set.next()){
				students.add(
						new Student(
								set.getLong("id"),
								set.getString("name"),
								set.getString("ssn")));
			}
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new NotFoundException("No students found for teacher with id" + teacherId);
	}
}
