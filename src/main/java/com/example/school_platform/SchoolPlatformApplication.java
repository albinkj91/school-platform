package com.example.school_platform;

import com.example.school_platform.enums.PersonType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolPlatformApplication {

	public static void main(String[] args) {
		System.out.println(PersonType.GUARDIAN);
		SpringApplication.run(SchoolPlatformApplication.class, args);
	}

}