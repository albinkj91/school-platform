package utilities;

import com.google.gson.Gson;
import models.Employee;
import models.Student;

public class JsonConverter {

	public static Employee convertEmployee(String json){
		Gson gson = new Gson();
		return gson.fromJson(json, Employee.class);
	}

	public static Employee[] convertEmployees(String json){
		Gson gson = new Gson();
		return gson.fromJson(json, Employee[].class);
	}

	public static Student[] convertStudents(String studentsAsJson) {
		Gson gson = new Gson();
		return gson.fromJson(studentsAsJson, Student[].class);
	}

	public static String personToJson(Employee employee, String password){
		if(employee.getType().equals("STUDENT")){
			return "{\"name\":\"" + employee.getName() + "\", \"ssn\":\"" + employee.getSsn() +
					"\"}";
		}else {
			return "{\"name\":\"" + employee.getName() + "\", \"ssn\":\"" + employee.getSsn() +
					"\", \"type\":\"" + employee.getType() + "\", \"email\":\"" + employee.getEmail() + "\", \"password\":\"" + password +
					"\", \"phone\":\"" + employee.getPhone() + "\"}";
		}
	}
}
