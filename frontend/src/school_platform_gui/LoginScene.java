package school_platform_gui;

import http_request.HttpRequest;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Person;
import utilities.JsonConverter;

public class LoginScene extends Scene {

	private final TextField usernameField = new TextField();
	private final PasswordField passwordField = new PasswordField();
	private final Label usernameLabel = new Label("Username");
	private final Label passwordLabel = new Label("Password");
	private final Button loginButton = new Button("Login");
	private final VBox vBox = new VBox(10);

	private final Stage stage;

	public LoginScene(Parent root, Stage stage) {
		super(root);
		this.stage = stage;
		getStylesheets().add("stylesheets/login-scene.css");
	}


	public void setVBox(){
		vBox.getChildren().addAll(
				usernameLabel,
				usernameField,
				passwordLabel,
				passwordField,
				loginButton);
		vBox.getStyleClass().add("v-box");
	}

	public VBox getVBox(){
		return vBox;
	}

	public void setUsernameField(){
		usernameLabel.getStyleClass().add("username-label");
		usernameField.getStyleClass().add("username-field");
	}

	public void setPasswordField(){
		passwordLabel.getStyleClass().add("password-label");
		passwordField.getStyleClass().add("password-field");
	}

	public void setLoginButton(){
		loginButton.getStyleClass().add("login-button");
	}

	public void setOnActionLoginButton(){
		loginButton.setOnAction(e -> authenticateAndRedirect());
	}

	public void authenticateAndRedirect(){
		String endpoint = "http://localhost:8080/employee/authenticate";
		HttpRequest httpRequest = new HttpRequest(endpoint);
		String response = httpRequest.authenticateLogin(usernameField.getText(), passwordField.getText());
		Person person = JsonConverter.convertEmployee(response);
		SceneController sceneController = new SceneController(stage);
		sceneController.authentication(person.getType());
	}
}
