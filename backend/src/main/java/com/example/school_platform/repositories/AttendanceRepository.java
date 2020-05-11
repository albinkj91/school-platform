package com.example.school_platform.repositories;

import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.Attendance;
import com.example.school_platform.models.dto.AttendancePostDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Repository
public class AttendanceRepository {

	private Connection connection;
	private Statement statement;

	public AttendanceRepository(){
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

	public Set<Attendance> getByStudentId(long studentId) throws SQLException {
		Set<Attendance> attendances = new HashSet<>();

		ResultSet set = statement.executeQuery("SELECT * FROM attendances " +
				"WHERE student_id = " + studentId);

		while(set.next()){
			attendances.add(new Attendance(
					set.getLong("id"),
					Timestamp.valueOf(set.getString("attendance_date")),
					set.getBoolean("attended"),
					set.getLong("student-id")));
		}
		return attendances;
	}

	public long persist(AttendancePostDTO attendance) throws SQLException, PersistException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO attendances(student_id, attended)" +
				"value(?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

		statement.setLong(1, attendance.getStudentId());
		statement.setBoolean(2, attendance.hasAttended());
		statement.executeUpdate();
		ResultSet key = statement.getGeneratedKeys();

		if(key.next()){
			return key.getLong(1);
		}
		throw new PersistException();
	}
}
