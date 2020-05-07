package school_platform_gui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Employee;

public class SceneController {

	private final Stage stage;
	private final BorderPane borderPane;

	public SceneController(Stage stage){
		this.stage = stage;
		borderPane = new BorderPane();
		borderPane.setPrefSize(stage.getWidth(), stage.getHeight());
	}


	public void changeScene(Stage stage, Scene newScene){
		stage.setScene(newScene);
	}

	public void authentication(Employee employee){
		switch(employee.getType()){
			case "ADMIN":
				setAdminScene();
				break;
			case "TEACHER":
				setTeacherScene(employee);
				break;
		}
	}

	public void setAdminScene(){
		stage.setTitle("School Platform - Admin");
		AdminScene adminScene = new AdminScene(borderPane, stage);
		adminScene.setDefaultMenuBar();
		adminScene.setStackPane();
		adminScene.setVBox();
		adminScene.setOnActionAddButton();

		borderPane.setTop(adminScene.getDefaultMenuBar());
		borderPane.setCenter(adminScene.getStackPane());
		borderPane.setRight(adminScene.getVBox());

		changeScene(stage, adminScene);
		adminScene.setTable();
	}

	public void setTeacherScene(Employee employee){
		stage.setTitle("School Platform - Teacher");
		TeacherScene teacherScene = new TeacherScene(borderPane, stage, employee);

		teacherScene.setDefaultMenuBar();
		teacherScene.setGridPane();
		teacherScene.fetchStudents();
		teacherScene.setCheckBoxes();

		borderPane.setTop(teacherScene.getDefaultMenuBar());
		borderPane.setCenter(teacherScene.getGridPane());
		changeScene(stage, teacherScene);
	}

	public void setLoginScene(){
		LoginScene loginScene = new LoginScene(borderPane, stage);
		loginScene.setUsernameField();
		loginScene.setPasswordField();
		loginScene.setLoginButton();
		loginScene.setVBox();
		loginScene.setOnActionLoginButton();
		borderPane.setCenter(loginScene.getVBox());
		changeScene(stage, loginScene);
	}
}
