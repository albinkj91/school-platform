package school_platform_pages;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Employee;
import school_platform_pages.teacher.TeacherScene;

public class SceneController {

	private final Stage stage;
	private final BorderPane borderPane = new BorderPane();

	public SceneController(Stage stage){
		this.stage = stage;
		borderPane.setPrefSize(stage.getWidth(), stage.getHeight());
	}


	public void changeScene(Stage stage, Scene newScene){
		stage.setScene(newScene);
	}

	public void authentication(Employee employee){
		if(employee == null){
			Alert alert = new Alert(Alert.AlertType.ERROR, "Ogiltiga inloggningsuppgifter", ButtonType.CLOSE);
			alert.getDialogPane().getStylesheets().add("stylesheets/alert.css");
			alert.setTitle("Error");
			alert.show();
		}else {
			switch (employee.getType()) {
				case "ADMIN":
					setAdminScene();
					break;
				case "TEACHER":
					setTeacherScene(employee);
					break;
			}
		}
	}

	public void setAdminScene(){
		stage.setTitle("School Platform - Admin");
		AdminScene adminScene = new AdminScene(borderPane, stage);

		adminScene.initialize();

		borderPane.setTop(adminScene.getDefaultMenuBar());
		borderPane.setCenter(adminScene.getStackPane());
		borderPane.setRight(adminScene.getVBox());

		changeScene(stage, adminScene);
		adminScene.setTable();
	}

	public void setTeacherScene(Employee employee){
		stage.setTitle("School Platform - Teacher");
		TeacherScene teacherScene = new TeacherScene(borderPane, stage, employee);

		teacherScene.initialize();

		borderPane.setTop(teacherScene.getDefaultMenuBar());
		borderPane.setCenter(teacherScene.getTabPane());
		changeScene(stage, teacherScene);
	}

	public void setLoginScene(){
		LoginScene loginScene = new LoginScene(borderPane, stage);

		loginScene.initialize();

		borderPane.setCenter(loginScene.getVBox());
		changeScene(stage, loginScene);
	}
}
