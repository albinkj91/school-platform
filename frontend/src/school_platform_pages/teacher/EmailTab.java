package school_platform_pages.teacher;

import http_request.HttpRequest;
import javafx.scene.control.*;
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
		to.setPromptText("Till:");
		subject.setPromptText("Rubrik:");
		message.setWrapText(true);
		emailLayout.getStyleClass().add("email-layout");
		send.setOnAction(e -> sendEmail());
		emailLayout.getChildren().addAll(to, subject, message, send);
	}

	private void sendEmail(){
		String[] recipients = to.getText().split(" ");
		HttpRequest httpRequest = new HttpRequest("http://localhost:8080/email/send");

		PasswordDialog passwordDialog = new PasswordDialog();
		passwordDialog.showAndWait();

		if(!passwordDialog.getPasswordField().getText().equals("")) {
			String response = httpRequest.sendEmail(passwordDialog.getPasswordField().getText(), recipients, subject.getText(), message.getText());
			System.out.println(response);
			to.clear();
			subject.clear();
			message.clear();
		}else{
			System.out.println("Password can't have length 0");
		}
	}

	public void setEmailReceiver(String address){
		to.setText(address);
	}
}
