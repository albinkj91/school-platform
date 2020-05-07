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
import javafx.stage.Stage;
import models.Employee;
import models.Person;
import models.Student;
import utilities.JsonConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdminScene extends Scene {

	private final StackPane pane = new StackPane();
	private final DefaultMenuBar defaultMenuBar;
	private final TableView<Person> table = new TableView<>();

	private final VBox vBox = new VBox(10);
	private final ComboBox<String> personType = new ComboBox<>();
	private final TextField[] inputs = new TextField[5];
	private final SearchField teacherSearch;
	private final SearchField guardianSearch;
	private final TextArea guardiansAdded = new TextArea();
	private final Button add = new Button("Add");

	final Stage stage;

	private String currentPerson = "";

	public AdminScene(Parent root, Stage stage) {
		super(root);
		this.stage = stage;
		getStylesheets().add("stylesheets/admin-scene.css");
		defaultMenuBar = new DefaultMenuBar();
		teacherSearch = new SearchField(vBox);
		teacherSearch.setPromptText("Teacher");
		guardianSearch = new SearchField(vBox);
		guardianSearch.setPromptText("Guardians");
		guardiansAdded.setEditable(false);
	}


	public void setDefaultMenuBar(){
		defaultMenuBar.setMenu(stage);
	}

	public void setTable() {
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

	public ObservableList<Person> fetchPersons() {
		HttpRequest httpRequest = new HttpRequest("http://localhost:8080/person/all");
		String response = httpRequest.getAllPersons();
		Employee[] persons = JsonConverter.convertEmployees(response);

		return FXCollections.observableArrayList(persons);
	}

	public void setVBoxByPersonType() {
		String value = personType.getValue();

		if (value.equals("STUDENT")) {
			vBox.getChildren().removeAll(inputs[2], inputs[3], inputs[4]);
			vBox.getChildren().remove(add);
			teacherSearch.setSearch(table.getItems(), "TEACHER");
			setTeacherSearchActionListener();
			guardianSearch.setSearch(table.getItems(), "GUARDIAN");
			setGuardianSearchActionListener();
			vBox.getChildren().addAll(guardiansAdded, add);
			currentPerson = "STUDENT";
		} else if (currentPerson.equals("STUDENT")) {
			vBox.getChildren().removeAll(add, teacherSearch, guardianSearch, guardiansAdded);
			vBox.getChildren().addAll(inputs[2], inputs[3], inputs[4], add);
			currentPerson = value;
		}
	}

	public void setVBox() {
		personType.setPromptText("Type");
		personType.getItems().addAll("ADMIN", "GUARDIAN", "STUDENT", "TEACHER");
		personType.setOnAction(e -> setVBoxByPersonType());
		String[] promptTitles = {"Name", "SSN", "E-mail", "Password", "Phone"};

		for (int i = 0; i < inputs.length; i++) {
			if (i == 3) {
				inputs[i] = new PasswordField();
			} else {
				inputs[i] = new TextField();
			}
			inputs[i].setPromptText(promptTitles[i]);
		}

		vBox.getChildren().add(personType);
		vBox.getChildren().addAll(inputs);
		vBox.getChildren().add(add);
		vBox.getStyleClass().add("v-box");
	}

	public void addPerson() {
		Person person = setPersonToPost();

		HttpRequest httpRequest = new HttpRequest("http://localhost:8080/" + personType.getValue().toLowerCase() + "/add");
		String response = httpRequest.postPerson(person);

		if (!response.equalsIgnoreCase("failed")) {
			table.getItems().add(person);
			clearTextFields();
		}
	}

	private Person setPersonToPost(){
		Person person;

		if (personType.getValue().equals("STUDENT")) {
			long teacherId = getPersonIdByName(teacherSearch.getText());
			List<String> guardians = new ArrayList<>(Arrays.asList(guardiansAdded.getText().split("\n")));
			Set<Long> guardianIds = guardians.stream().map(this::getPersonIdByName).collect(Collectors.toSet());

			person = new Student(
					inputs[0].getText(),
					inputs[1].getText(),
					"STUDENT",
					teacherId,
					guardianIds);
		} else {
			person = new Employee(
					inputs[0].getText(),
					inputs[1].getText(),
					personType.getValue(),
					inputs[2].getText(),
					inputs[4].getText());

			((Employee) person).setPassword(inputs[4].getText());
		}
		return person;
	}

	private void clearTextFields(){
		for (TextField input : inputs) {
			input.clear();
		}
		guardiansAdded.clear();
		teacherSearch.clear();
		guardianSearch.clear();
	}

	public void setTeacherSearchActionListener() {
		teacherSearch.getMenuItems().forEach(item -> item.setOnAction(e -> teacherSearch.setText(item.getText())));
	}

	public void setGuardianSearchActionListener() {
		guardianSearch.getMenuItems()
				.forEach(
						item -> item.setOnAction(
								e -> {
									guardiansAdded.setText(guardiansAdded.getText() + item.getText() + "\n");
									guardianSearch.clear();
								}));
	}

	public long getPersonIdByName(String name) {
		return table.getItems()
				.stream()
				.filter(i -> i.getName().equals(name))
				.collect(Collectors.toList())
				.get(0)
				.getId();
	}

	public void setOnActionAddButton() {
		add.setOnAction(e -> addPerson());
	}

	public DefaultMenuBar getDefaultMenuBar() {
		return defaultMenuBar;
	}

	public void setStackPane() {
		pane.getStyleClass().add("stack-pane");
	}

	public StackPane getStackPane() {
		return pane;
	}

	public VBox getVBox() {
		return vBox;
	}
}
