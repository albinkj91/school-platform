package com.example.school_platform.services;

import com.example.school_platform.exceptions.PersistException;
import com.example.school_platform.models.dto.AttendancePostDTO;
import com.example.school_platform.repositories.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Set;

@Service
public class AttendanceService {

	private final AttendanceRepository attendanceRepository;

	@Autowired
	public AttendanceService(AttendanceRepository attendanceRepository){
		this.attendanceRepository = attendanceRepository;
	}

	public void save(Set<AttendancePostDTO> attendances) {
		attendances.forEach(a -> {
			try {
				attendanceRepository.persist(a);
			} catch (SQLException | PersistException e) {
				e.printStackTrace();
			}
		});
	}
}
