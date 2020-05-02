package http_request;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class LoginRequest {

	private final String urlInput;

	public LoginRequest(String urlInput){
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
}
