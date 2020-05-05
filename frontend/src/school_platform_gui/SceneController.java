package school_platform_gui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Employee;

public class SceneController {

	private final Stage stage;

	public SceneController(Stage stage){
		this.stage = stage;
	}


	public void changeScene(Stage stage, Scene newScene){
		stage.setScene(newScene);
	}

	public void authentication(Employee employee){
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(stage.getWidth(), stage.getHeight());

		switch(employee.getType()){
			case "ADMIN":
				stage.setTitle("School Platform - Admin");
				AdminScene adminScene = new AdminScene(borderPane);
				adminScene.setStackPane();
				adminScene.setVBox();
				adminScene.setOnActionAddButton();

				borderPane.setCenter(adminScene.getStackPane());
				borderPane.setRight(adminScene.getVBox());

				changeScene(stage, adminScene);
				adminScene.setTable();
				break;
			case "TEACHER":
				stage.setTitle("School Platform - Teacher");
				TeacherScene teacherScene = new TeacherScene(borderPane, employee);

				teacherScene.setGridPane();
				teacherScene.fetchStudents();
				teacherScene.setCheckBoxes();

				borderPane.setCenter(teacherScene.getGridPane());

				changeScene(stage, teacherScene);
				break;
		}
	}
}
