package school_platform_gui;

import http_request.LoginRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import models.Employee;
import utilities.JsonConverter;

public class AdminScene extends Scene {

	private final StackPane pane = new StackPane();
	private final TableView<Employee> table = new TableView<>();

	public AdminScene(Parent root) {
		super(root);
		getStylesheets().add("stylesheets/admin-scene.css");
	}

	public void setTable(){
		pane.getChildren().add(table);

		table.setItems(getEmployees());

		TableColumn<Employee, String> name = new TableColumn<>("Name");
		TableColumn<Employee, String> ssn = new TableColumn<>("SSN");
		TableColumn<Employee, String> email = new TableColumn<>("E-mail");
		TableColumn<Employee, String> phone = new TableColumn<>("Phone");

		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

		table.getColumns().add(name);
		table.getColumns().add(ssn);
		table.getColumns().add(email);
		table.getColumns().add(phone);
	}

	public ObservableList<Employee> getEmployees(){
		LoginRequest loginRequest = new LoginRequest("http://localhost:8080/person/all");
		String response = loginRequest.getAllPersons();
		String[] objects = response.split("},");
		ObservableList<Employee> employees = FXCollections.observableArrayList();

		for(String s : objects){
			employees.add(JsonConverter.convertEmployee(s));
		}

		return employees;
	}

	public void setStackPane(){
		pane.getStyleClass().add("stack-pane");
	}

	public StackPane getStackPane(){
		return pane;
	}
}
