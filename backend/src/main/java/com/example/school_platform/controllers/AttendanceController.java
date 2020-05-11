package com.example.school_platform.controllers;

import com.example.school_platform.models.dto.AttendancePostDTO;
import com.example.school_platform.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

	private final AttendanceService attendanceService;

	@Autowired
	public AttendanceController(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}


	@PostMapping("/add")
	public void addAttendance(@RequestBody Set<AttendancePostDTO> attendances) {
		attendanceService.save(attendances);
	}
}
