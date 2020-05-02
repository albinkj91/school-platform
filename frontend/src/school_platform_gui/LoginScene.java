package school_platform_gui;

import http_request.LoginRequest;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScene extends Scene {

	private final TextField usernameField = new TextField();
	private final PasswordField passwordField = new PasswordField();
	private final Label usernameLabel = new Label("Username");
	private final Label passwordLabel = new Label("Password");
	private final Button loginButton = new Button("Login");
	private final VBox vBox = new VBox(10);

	private final String endpoint = "http://localhost:8080/employee/authenticate";

	private Stage stage;

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
		LoginRequest loginRequest = new LoginRequest(endpoint);
		String response = loginRequest.authenticateLogin(usernameField.getText(), passwordField.getText());
		SceneController sceneController = new SceneController(stage);
		sceneController.authentication("ADMIN");
	}
}
