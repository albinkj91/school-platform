package school_platform_gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class AdminScene extends Scene {

	private final StackPane pane = new StackPane();
	private final Button button = new Button("Click me");

	public AdminScene(Parent root) {
		super(root);
		getStylesheets().add("stylesheets/admin-scene.css");
	}

	public void setStackPane(){
		pane.getChildren().add(button);
		pane.getStyleClass().add("stack-pane");
	}

	public StackPane getStackPane(){
		return pane;
	}
}
