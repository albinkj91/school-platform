package utilities;

import com.google.gson.Gson;
import models.Employee;
import models.Person;
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

	public static String personToJson(Person person){
		if(person.getType().equals("STUDENT")){
			return "{\"name\":\"" + person.getName() + "\", \"ssn\":\"" + person.getSsn() +
					"\"}";
		}else {
			Gson gson = new Gson();
			return gson.toJson(person);
			/*Employee employee = (Employee) person;
			return "{\"name\":\"" + employee.getName() + "\", \"ssn\":\"" + employee.getSsn() +
					"\", \"type\":\"" + employee.getType() + "\", \"email\":\"" + employee.getEmail() + "\"," +
					"\"password\":\"" + employee.getPassword() + "\", \"phone\":\"" + employee.getPhone() + "\"}";*/
		}
	}
}
