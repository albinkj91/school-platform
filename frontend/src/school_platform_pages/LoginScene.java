package school_platform_pages;

import http_request.HttpRequest;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Employee;
import utilities.JsonConverter;

public class LoginScene extends Scene {

	private final TextField usernameField = new TextField();
	private final PasswordField passwordField = new PasswordField();
	private final Label usernameLabel = new Label("E-post");
	private final Label passwordLabel = new Label("Lösenord");
	private final Button loginButton = new Button("Logga In");
	private final VBox vBox = new VBox(10);

	private final Stage stage;

	public LoginScene(Parent root, Stage stage) {
		super(root);
		this.stage = stage;
		getStylesheets().add("stylesheets/login-scene.css");
	}


	public void initialize(){
		setVBox();
		setUsernameField();
		setPasswordField();
		setLoginButton();
		setOnActionLoginButton();
	}

	private void setVBox(){
		vBox.getChildren().addAll(
				usernameLabel,
				usernameField,
				passwordLabel,
				passwordField,
				loginButton);
		vBox.getStyleClass().add("v-box");
	}

	private void setUsernameField(){
		usernameLabel.getStyleClass().add("username-label");
		usernameField.getStyleClass().add("username-field");
	}

	private void setPasswordField(){
		passwordLabel.getStyleClass().add("password-label");
		passwordField.getStyleClass().add("password-field");
	}

	private void setLoginButton(){
		loginButton.getStyleClass().add("login-button");
	}

	private void setOnActionLoginButton(){
		loginButton.setOnAction(e -> authenticateAndRedirect());
	}

	private void authenticateAndRedirect(){
		HttpRequest httpRequest = new HttpRequest("http://localhost:8080/employee/authenticate");
		String response = httpRequest.authenticateLogin(usernameField.getText(), passwordField.getText());
		Employee employee = JsonConverter.convertEmployee(response);
		SceneController sceneController = new SceneController(stage);
		sceneController.authentication(employee);
	}

	public VBox getVBox(){
		return vBox;
	}
}
