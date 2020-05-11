package school_platform_pages;

import javafx.application.Application;
import javafx.stage.Stage;
import models.Employee;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("School Platform - Login");
		primaryStage.setMaximized(true);
		SceneController sceneController = new SceneController(primaryStage);
		sceneController.setLoginScene();
		primaryStage.show();
	}
}