package utilities;

import models.Employee;

import java.util.HashMap;
import java.util.Map;

public class JsonConverter {

	public static Employee convertEmployee(String json){
		String[] array = json.split(",");
		Map<String, String> map = new HashMap<>();

		for(String s : array){
			s = s.replaceAll("[{}\"\\[\\]]", "");
			String[] keyPair = s.split(":");
			map.put(keyPair[0], keyPair[1]);
		}

		return new Employee(map.get("name"), map.get("ssn"), map.get("type"), map.get("email"), map.get("phone"));
	}
}
