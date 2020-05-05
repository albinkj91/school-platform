package school_platform_gui;

import http_request.HttpRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.Employee;
import utilities.JsonConverter;

public class AdminScene extends Scene {

	private final StackPane pane = new StackPane();
	private final TableView<Employee> table = new TableView<>();
	private final VBox vBox = new VBox(10);
	private final ComboBox<String> personType = new ComboBox<>();
	private final TextField[] inputs = new TextField[5];
	private final Button add = new Button("Add");

	private String currentPerson = "";

	public AdminScene(Parent root) {
		super(root);
		getStylesheets().add("stylesheets/admin-scene.css");
	}

	public void setTable(){
		pane.getChildren().add(table);
		table.setItems(fetchPersons());
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<Employee, String> name = new TableColumn<>("Name");
		TableColumn<Employee, String> ssn = new TableColumn<>("SSN");
		TableColumn<Employee, String> type = new TableColumn<>("Type");
		TableColumn<Employee, String> email = new TableColumn<>("E-mail");
		TableColumn<Employee, String> phone = new TableColumn<>("Phone");

		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

		table.getColumns().add(name);
		table.getColumns().add(ssn);
		table.getColumns().add(type);
		table.getColumns().add(email);
		table.getColumns().add(phone);
	}

	public ObservableList<Employee> fetchPersons(){
		HttpRequest httpRequest = new HttpRequest("http://localhost:8080/person/all");
		String response = httpRequest.getAllPersons();
		Employee[] employees = JsonConverter.convertEmployees(response);

		return  FXCollections.observableArrayList(employees);
	}

	public void setVBoxByPersonType(){
		String value = personType.getValue();

		if(value.equals("STUDENT")){
			vBox.getChildren().removeAll(inputs[2], inputs[3],inputs[4]);
			currentPerson = "STUDENT";
		}else if(currentPerson.equals("STUDENT")){
			vBox.getChildren().remove(add);
			vBox.getChildren().addAll(inputs[2], inputs[3], inputs[4], add);
			currentPerson = value;
		}
	}

	public void setVBox(){
		personType.setPromptText("Type");
		personType.getItems().addAll("ADMIN", "GUARDIAN", "STUDENT", "TEACHER");
		personType.setOnAction(e -> setVBoxByPersonType());
		String[] promptTitles = {"Name", "SSN", "E-mail", "Password", "Phone"};

		for(int i = 0; i < inputs.length; i++){
			if(i == 3){
				inputs[i] = new PasswordField();
			}else {
				inputs[i] = new TextField();
			}
			inputs[i].setPromptText(promptTitles[i]);
		}

		vBox.getChildren().add(personType);
		vBox.getChildren().addAll(inputs);
		vBox.getChildren().add(add);
		vBox.getStyleClass().add("v-box");
	}

	public void addPerson(){
		Employee employee = new Employee(
				inputs[0].getText(),
				inputs[1].getText(),
				personType.getValue(),
				inputs[2].getText(),
				inputs[4].getText());

		HttpRequest httpRequest = new HttpRequest("http://localhost:8080/" + personType.getValue().toLowerCase() + "/add");
		String response = httpRequest.postPerson(employee, inputs[4].getText());

		if(!response.equalsIgnoreCase("failed")){
			table.getItems().add(employee);
			for(TextField input : inputs){
				input.setText("");
			}
		}
	}

	public void setOnActionAddButton(){
		add.setOnAction(e -> addPerson());
	}

	public void setStackPane(){
		pane.getStyleClass().add("stack-pane");
	}

	public StackPane getStackPane(){
		return pane;
	}

	public VBox getVBox() {
		return vBox;
	}
}
