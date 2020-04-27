package school_platform_gui;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("School Platform - Login");
		primaryStage.setMaximized(true);
		BorderPane root = new BorderPane();

		LoginScene loginScene = new LoginScene(root);
		loginScene.setUsernameField();
		loginScene.setPasswordField();
		loginScene.setVBox();
		root.setCenter(loginScene.getVBox());

		primaryStage.setScene(loginScene);
		primaryStage.show();
	}
}