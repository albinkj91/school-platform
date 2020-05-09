package com.example.school_platform.repositories;

import com.example.school_platform.exceptions.NotFoundException;
import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.Student;
import com.example.school_platform.models.dto.StudentGetDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Repository
public class StudentRepository {

	private Connection connection;
	private Statement statement;

	public StudentRepository(){
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

	public Set<Student> getAll() throws SQLException {
		Set<Student> students = new HashSet<>();
		ResultSet set = statement.executeQuery("SELECT s.id, p.name, p.ssn, p.type " +
				"FROM persons p " +
				"INNER JOIN students s ON s.person_id = p.id " +
				"WHERE p.type = 'STUDENT'");

		while(set.next()){
			long id = Long.parseLong(set.getString("id"));
			String name = set.getString("name");
			String ssn = set.getString("ssn");

			students.add(new Student(id, name, ssn));
		}
		return students;
	}

	public Set<StudentGetDTO> getByTeacherId(long teacherId) throws NotFoundException {
		Set<StudentGetDTO> students = new HashSet<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery("SELECT * FROM students s " +
					"INNER JOIN persons p ON s.person_id = p.id " +
					"WHERE teacher_id=" + teacherId);

			while(set.next()){
				students.add(
						new StudentGetDTO(
								set.getLong("id"),
								set.getString("name"),
								set.getString("ssn"),
								set.getLong("teacher_id")));
			}
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new NotFoundException("No students found for teacher with id" + teacherId);
	}

	public long persist(long personId, long teacherId) throws SQLException, PersistException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO students(person_id, teacher_id)" +
				"value(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

		statement.setString(1, Long.toString(personId));
		statement.setString(2, Long.toString(teacherId));
		statement.executeUpdate();
		ResultSet key = statement.getGeneratedKeys();

		if(key.next()){
			return key.getLong(1);
		}
		throw new PersistException();
	}

	public long persistGuardianRelation(long studentId, long guardianId) throws SQLException, PersistException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO student_guardian(student_id, guardian_id)" +
				"value(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

		statement.setString(1, Long.toString(studentId));
		statement.setString(2, Long.toString(guardianId));
		statement.executeUpdate();
		ResultSet key = statement.getGeneratedKeys();

		if(key.next()){
			return key.getLong(1);
		}
		throw new PersistException();
	}
}
