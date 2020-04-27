package school_platform_gui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LoginScene extends Scene {

	private TextField usernameField = new TextField();
	private PasswordField passwordField = new PasswordField();
	private Label usernameLabel = new Label("Username");
	private Label passwordLabel = new Label("Password");
	private VBox vBox = new VBox(10);

	public LoginScene(Parent root) {
		super(root);
		getStylesheets().add("stylesheets/login-scene.css");
	}


	public void setVBox(){
		vBox.getChildren().addAll(
				usernameLabel,
				usernameField,
				passwordLabel,
				passwordField);
		vBox.setMaxWidth(500);
		vBox.setMaxHeight(500);
		vBox.setAlignment(Pos.CENTER);
		vBox.getStyleClass().add("vbox");
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
}
