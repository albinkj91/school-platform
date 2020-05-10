package http_request;

import models.Email;
import models.Person;
import utilities.JsonConverter;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class HttpRequest {

	private final String urlInput;

	public HttpRequest(String urlInput){
		this.urlInput = urlInput;
	}


	public String authenticateLogin(String username, String password) {
		try {
			URL url = new URL(urlInput);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			String params = "email=" + username + "&password=" + password;
			byte[] stream = params.getBytes(StandardCharsets.UTF_8);
			int paramsLength = stream.length;

			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", String.valueOf(paramsLength));

			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(stream);
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String read;
			StringBuilder sb = new StringBuilder();
			while((read = in.readLine()) != null){
				sb.append(read);
			}

			in.close();
			out.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "FAILED";
		}
	}

	public String postPerson(Person person){
		try {
			URL url = new URL(urlInput);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			String personAsJson = JsonConverter.personToJson(person);
			byte[] stream = personAsJson.getBytes(StandardCharsets.UTF_8);

			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json;utf-8");

			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(stream);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String read;
			StringBuilder response = new StringBuilder();
			while((read = in.readLine()) != null){
				response.append(read);
			}

			in.close();
			out.close();
			return response.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "FAILED";
		}
	}

	public String getAllPersons(){
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(urlInput);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String read;
			while((read = in.readLine()) != null){
				sb.append(read);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public String getStudentsByTeacher(){
		try {
			URL url = new URL(urlInput);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String read;
			StringBuilder sb = new StringBuilder();
			while((read = in.readLine()) != null){
				sb.append(read);
			}

			in.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "FAILED";
		}
	}

	public String sendEmail(String password, String[] recipients, String subject, String message){
		Email email = new Email(
				"smtp.gmail.com",
				"587",
				"albinkj91@gmail.com",
				password, recipients,
				subject,
				message);

		try {
			URL url = new URL(urlInput);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			String emailAsJson = JsonConverter.EmailToJson(email);
			byte[] stream = emailAsJson.getBytes(StandardCharsets.UTF_8);

			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json;utf-8");

			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(stream);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String read;
			StringBuilder sb = new StringBuilder();
			while((read = in.readLine()) != null){
				sb.append(read);
			}

			out.close();
			in.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "FAILED";
		}
	}
}
