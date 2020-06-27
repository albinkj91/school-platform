package school_platform_pages.admin;

import http_request.HttpRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Employee;
import models.Person;
import models.Student;
import models.StudentPost;
import school_platform_pages.DefaultMenuBar;
import school_platform_pages.SearchField;
import utilities.JsonConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdminScene extends Scene {

	private final DefaultMenuBar defaultMenuBar = new DefaultMenuBar();;
	private final VBox vBox = new VBox(10);
	private final ComboBox<String> personType = new ComboBox<>();
	private final TextField[] inputs = new TextField[5];
	private final SearchField teacherSearch = new SearchField(vBox);
	private final SearchField guardianSearch = new SearchField(vBox);
	private final TextArea guardiansAdded = new TextArea();
	private final Button add = new Button("Add");

	private ObservableList<Person> persons;
	private final TabPane tabPane = new TabPane();
	private final EmployeeListingTab employeeListingTab = new EmployeeListingTab();
	private final StudentListingTab studentListingTab = new StudentListingTab();
	private final Stage stage;

	private String currentPerson = "";

	public AdminScene(Parent root, Stage stage) {
		super(root);
		this.stage = stage;
		getStylesheets().add("stylesheets/admin-scene.css");
		teacherSearch.setPromptText("Teacher");
		guardianSearch.setPromptText("Guardians");
		guardiansAdded.setEditable(false);
	}


	public void initialize() {
		setDefaultMenuBar();
		persons = fetchPersons();
		employeeListingTab.setEmployees(persons);
		studentListingTab.setEmployees(persons);
		setTabPane();
		setVBox();
		setOnActionAddButton();
	}

	public void setDefaultMenuBar(){
		defaultMenuBar.setMenu(stage);
	}

	public ObservableList<Person> fetchPersons() {
		HttpRequest httpRequest = new HttpRequest("http://localhost:8080/person/all");
		String response = httpRequest.getAllPersons();
		Employee[] persons = JsonConverter.convertPerson(response);

		return FXCollections.observableArrayList(persons);
	}

	public void setVBoxByPersonType() {
		String value = personType.getValue();

		if (value.equals("STUDENT")) {
			vBox.getChildren().removeAll(inputs[2], inputs[3], inputs[4]);
			vBox.getChildren().remove(add);
			teacherSearch.setSearch(persons, "TEACHER");
			setTeacherSearchActionListener();
			guardianSearch.setSearch(persons, "GUARDIAN");
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
			if(person.getType().equalsIgnoreCase("STUDENT")) {
				Student student = new Student(person.getName(), person.getSsn(), person.getType());
				studentListingTab.getStudents().add(student);
			}else{
				Employee employee = new Employee(
						person.getName(),
						person.getSsn(),
						person.getType(),
						((Employee)person).getEmail(),
						((Employee)person).getPhone());
				employeeListingTab.getEmployees().add(employee);
			}
			clearTextFields();
		}
	}

	private Person setPersonToPost(){
		Person person;

		if (personType.getValue().equals("STUDENT")) {
			long teacherId = getPersonIdByName(teacherSearch.getText());
			List<String> guardians = new ArrayList<>(Arrays.asList(guardiansAdded.getText().split("\n")));
			Set<Long> guardianIds = guardians.stream().map(this::getPersonIdByName).collect(Collectors.toSet());

			person = new StudentPost(
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
		return persons.stream()
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

	public VBox getVBox() {
		return vBox;
	}

	private void setTabPane(){
		employeeListingTab.setEmployeeListingTab();
		studentListingTab.setStudentListingTab();
		tabPane.getTabs().addAll(studentListingTab, employeeListingTab);
	}

	public TabPane getTabPane() {
		return tabPane;
	}
}
