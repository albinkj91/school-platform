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

		LoginScene loginScene = new LoginScene(root, primaryStage);
		loginScene.setUsernameField();
		loginScene.setPasswordField();
		loginScene.setLoginButton();
		loginScene.setVBox();
		loginScene.setOnActionLoginButton();
		root.setCenter(loginScene.getVBox());
		primaryStage.setScene(loginScene);

		// Admin-page

		/*AdminScene adminScene = new AdminScene(root);
		adminScene.setStackPane();
		adminScene.setVBox();
		adminScene.setOnActionAddButton();
		root.setCenter(adminScene.getStackPane());
		root.setRight(adminScene.getVBox());
		adminScene.setTable();
		primaryStage.setScene(adminScene);*/

		// Teacher-page

		/*TeacherScene teacherScene = new TeacherScene(root, new Person(1, "", "", "", "", ""));
		teacherScene.setGridPane();
		teacherScene.setCheckBoxes();
		root.setCenter(teacherScene.getGridPane());
		primaryStage.setScene(teacherScene);*/

		primaryStage.show();
	}
}