package school_platform_gui;

import http_request.HttpRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.Employee;
import models.Person;
import utilities.JsonConverter;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AdminScene extends Scene {

	private final StackPane pane = new StackPane();
	private final TableView<Person> table = new TableView<>();
	private final VBox vBox = new VBox(10);
	private final ComboBox<String> personType = new ComboBox<>();
	private final TextField[] inputs = new TextField[5];
	private final TextField teacherSearch = new TextField();
	private final ContextMenu teacherSearchResult = new ContextMenu();
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

		TableColumn<Person, String> name = new TableColumn<>("Name");
		TableColumn<Person, String> ssn = new TableColumn<>("SSN");
		TableColumn<Person, String> type = new TableColumn<>("Type");
		TableColumn<Person, String> email = new TableColumn<>("E-mail");
		TableColumn<Person, String> phone = new TableColumn<>("Phone");

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

	public ObservableList<Person> fetchPersons(){
		HttpRequest httpRequest = new HttpRequest("http://localhost:8080/person/all");
		String response = httpRequest.getAllPersons();
		Employee[] employees = JsonConverter.convertEmployees(response);

		return  FXCollections.observableArrayList(employees);
	}

	public void setVBoxByPersonType(){
		String value = personType.getValue();

		if(value.equals("STUDENT")){
			vBox.getChildren().removeAll(inputs[2], inputs[3],inputs[4]);
			vBox.getChildren().remove(add);
			setTeacherSearch();
			vBox.getChildren().add(add);
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

	public void setTeacherSearch(){
		teacherSearchResult.getStyleClass().add("context-menu");
		vBox.getChildren().add(teacherSearch);
		List<MenuItem> items = new LinkedList<>();
		List<Person> teachers = table.getItems().stream().filter(p -> p.getType().equals("TEACHER")).collect(Collectors.toList());

		teachers.forEach(t -> {
			MenuItem newItem = new MenuItem(t.getName());
			newItem.setOnAction(e -> {
				teacherSearch.setText(newItem.getText());
			});
			items.add(newItem);
		});

		teacherSearch.setContextMenu(teacherSearchResult);

		teacherSearch.textProperty().addListener(observable -> {
			teacherSearchResult.getItems().clear();
			teacherSearchResult.getItems().addAll(items);
			teacherSearchResult.getItems().removeAll(
					teacherSearchResult
							.getItems()
							.stream()
							.filter(i -> !i.getText().toLowerCase().contains(teacherSearch.getText().toLowerCase()))
							.collect(Collectors.toList()));

			if (!teacherSearchResult.isShowing() && !teacherSearch.getText().equals("")) {
				teacherSearchResult.show(teacherSearch, Side.BOTTOM, 0, 1);
			}else if(teacherSearch.getText().equals("")){
				teacherSearchResult.hide();
			}
		});
	}

	public void addPerson(){
		Person person;
		if(personType.getValue().equals("STUDENT")){
			person = new Person(
					inputs[0].getText(),
					inputs[1].getText(),
					"STUDENT");
		}else {
			person = new Employee(
					inputs[0].getText(),
					inputs[1].getText(),
					personType.getValue(),
					inputs[2].getText(),
					inputs[4].getText());

			((Employee)person).setPassword(inputs[4].getText());
		}

		HttpRequest httpRequest = new HttpRequest("http://localhost:8080/" + personType.getValue().toLowerCase() + "/add");
		String response = httpRequest.postPerson(person);

		if(!response.equalsIgnoreCase("failed")){
			table.getItems().add(person);
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
