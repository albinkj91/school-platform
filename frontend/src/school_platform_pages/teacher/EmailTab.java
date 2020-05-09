package school_platform_pages.teacher;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class EmailTab extends Tab {

	private final BorderPane borderPane = new BorderPane();
	private final VBox emailLayout = new VBox(20);
	private final TextField to = new TextField();
	private final TextField subject = new TextField();
	private final TextArea message = new TextArea();
	private final Button send = new Button("Skicka");

	public EmailTab(){
		setText("E-mail");
	}


	public void initialize(){
		setEmailTab();
		setEmailLayout();
	}

	private void setEmailTab(){
		setClosable(false);
		borderPane.setCenter(emailLayout);
		setContent(borderPane);
	}

	private void setEmailLayout(){
		to.setPromptText("To:");
		subject.setPromptText("Subject:");
		message.setWrapText(true);
		emailLayout.getStyleClass().add("email-layout");
		emailLayout.getChildren().addAll(to, subject, message, send);
	}

	public void setEmailReceiver(String address){
		to.setText(address);
	}
}
