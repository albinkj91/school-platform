package utilities;

import com.google.gson.Gson;
import models.*;

import java.util.Set;

public class JsonConverter {

	public static Employee convertEmployee(String json){
		Gson gson = new Gson();
		return gson.fromJson(json, Employee.class);
	}

	public static Employee[] convertPerson(String json){
		Gson gson = new Gson();
		return gson.fromJson(json, Employee[].class);
	}

	public static Student[] convertStudents(String studentsAsJson) {
		Gson gson = new Gson();
		return gson.fromJson(studentsAsJson, Student[].class);
	}

	public static String personToJson(Person person){
		Gson gson = new Gson();
		return gson.toJson(person);
	}

	public static String emailToJson(Email email) {
		Gson gson = new Gson();
		return gson.toJson(email, Email.class);
	}

	public static String attendanceSetToJson(Set<Attendance> attendance){
		Gson gson = new Gson();
		return gson.toJson(attendance, Set.class);
	}
}
