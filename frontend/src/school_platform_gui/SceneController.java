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
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(stage.getWidth(), stage.getHeight());

		switch(userType){
			case "ADMIN":
				stage.setTitle("School Platform - Admin");
				AdminScene adminScene = new AdminScene(borderPane);
				adminScene.setStackPane();
				adminScene.setVBox();
				adminScene.setOnActionAddButton();
				borderPane.setCenter(adminScene.getStackPane());
				borderPane.setBottom(adminScene.getVBox());
				changeScene(stage, adminScene);
				adminScene.setTable();
				break;
			case "TEACHER":
				System.out.println("Not done yet...");
				break;
		}
	}
}
