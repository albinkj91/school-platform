package school_platform_pages.teacher;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class PasswordDialog extends Dialog<String> {

	private final PasswordField passwordField = new PasswordField();

	public PasswordDialog(){
		setDialog();
	}


	public void setDialog(){
		getDialogPane().getStylesheets().add("stylesheets/dialog.css");
		setTitle("Verifiering");

		getDialogPane().getButtonTypes().add(new ButtonType("Bekräfta", ButtonBar.ButtonData.APPLY));
		getDialogPane().getButtonTypes().add(new ButtonType("Avbryt", ButtonBar.ButtonData.CANCEL_CLOSE));

		VBox vBox = new VBox(20);
		vBox.getStyleClass().add("v-box");

		vBox.getChildren().addAll(passwordField);
		getDialogPane().setContent(vBox);
		passwordField.requestFocus();
	}

	public PasswordField getPasswordField(){
		return passwordField;
	}
}
