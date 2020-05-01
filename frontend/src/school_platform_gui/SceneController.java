package school_platform_gui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneController {

	private final Stage stage;

	public SceneController(Stage stage){
		this.stage = stage;
	}


	public void changeScene(Stage stage, Scene newScene){
		stage.setScene(newScene);
	}

	public void authentication(String userType){
		switch(userType){
			case "ADMIN":
				BorderPane borderPane = new BorderPane();
				AdminScene adminScene = new AdminScene(borderPane);
				adminScene.setStackPane();
				borderPane.setCenter(adminScene.getStackPane());
				changeScene(stage, adminScene);
				break;
			case "TEACHER":
				System.out.println("Not done yet...");
				break;
		}
	}
}
