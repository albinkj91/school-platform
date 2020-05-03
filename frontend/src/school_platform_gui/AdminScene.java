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
	private final TextField[] inputs = new TextField[6];
	private final Button add = new Button("Add");

	public AdminScene(Parent root) {
		super(root);
		getStylesheets().add("stylesheets/admin-scene.css");
	}

	public void setTable(){
		pane.getChildren().add(table);
		table.setItems(getEmployees());
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

	public ObservableList<Employee> getEmployees(){
		HttpRequest httpRequest = new HttpRequest("http://localhost:8080/person/all");
		String response = httpRequest.getAllPersons();
		String[] objects = response.split("},");
		ObservableList<Employee> employees = FXCollections.observableArrayList();

		for(String s : objects){
			employees.add(JsonConverter.convertEmployee(s));
		}

		return employees;
	}

	public void setVBox(){
		vBox.getStyleClass().add("v-box");
		String[] promptTitles = {"Name", "SSN", "Type", "E-mail", "Password", "Phone"};

		for(int i = 0; i < inputs.length; i++){
			if(i == 4){
				inputs[i] = new PasswordField();
			}else {
				inputs[i] = new TextField();
			}
			inputs[i].setPromptText(promptTitles[i]);
		}

		vBox.getChildren().addAll(inputs);
		vBox.getChildren().add(add);
	}

	public void addEmployee(){
		Employee employee = new Employee(
				inputs[0].getText(),
				inputs[1].getText(),
				inputs[2].getText(),
				inputs[3].getText(),
				inputs[5].getText());
		HttpRequest httpRequest = new HttpRequest("http://localhost:8080/" + inputs[2].getText().toLowerCase() + "/add");
		String response = httpRequest.postEmployee(employee, inputs[4].getText());

		if(!response.equalsIgnoreCase("failed")){
			table.getItems().add(employee);
			for(TextField input : inputs){
				input.setText("");
			}
		}
	}

	public void setOnActionAddButton(){
		add.setOnAction(e -> {
			addEmployee();
		});
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
