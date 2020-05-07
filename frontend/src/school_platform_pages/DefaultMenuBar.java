package school_platform_pages;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class DefaultMenuBar extends MenuBar {

	private List<MenuItem> items  = new LinkedList<>();;
	private Menu menu;

	public DefaultMenuBar(){
		getStylesheets().add("stylesheets/menu-bar.css");
	}

	public void setMenu(Stage stage){
		menu = new Menu("Arkiv");
		getMenus().add(menu);
		MenuItem logOut = new MenuItem("Logga ut");
		MenuItem exit = new MenuItem("Avsluta");
		logOut.setOnAction(e -> logOut(stage));
		exit.setOnAction(e -> exit());

		items.add(logOut);
		items.add(exit);
		menu.getItems().addAll(items);
	}

	public void exit(){
		System.exit(0);
	}

	public void logOut(Stage stage){
		SceneController sceneController = new SceneController(stage);
		sceneController.setLoginScene();
	}
}
