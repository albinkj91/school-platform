package school_platform_pages.teacher;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import models.Employee;
import models.Person;
import models.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class StudentTab extends Tab {

	private final BorderPane borderPane = new BorderPane();
	private final TableView<Student> table = new TableView<>();

	private final VBox studentProfile = new VBox(20);
	private final Label name = new Label();
	private final Label ssn = new Label();

	private final List<Student> students;

	public StudentTab(List<Student> students){
		this.students = students;
		setText("Elever");
	}


	public void initialize(){
		setStudentTab();
		setTable();
		setLabels();
		setStudentProfile();
	}

	private void setStudentTab(){
		setClosable(false);
		borderPane.setLeft(table);
		borderPane.setCenter(studentProfile);
		setContent(borderPane);
	}

	private void setTable(){
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		students.sort(Comparator.comparing(Person::getName));
		table.getItems().addAll(students);
		TableColumn<Student, String> name = new TableColumn<>("Namn");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		table.getColumns().add(name);

		table.getSelectionModel().selectedItemProperty().addListener(l -> setProfileWhenClicked());
	}

	private void setProfileWhenClicked(){
		Student selected = table.getSelectionModel().getSelectedItem();
		studentProfile.getChildren().clear();

		studentProfile.getChildren().addAll(name, ssn);
		name.setText(selected.getName());
		ssn.setText(selected.getSsn());

		List<Button> guardianButtons = new ArrayList<>();
		Set<Employee> guardians = selected.getGuardians();
		studentProfile.getChildren().removeAll(guardianButtons);

		HBox hBox = new HBox(40);
		hBox.getStyleClass().add("h-box");
		hBox.setPadding(new Insets(100, 0, 0, 0));
		studentProfile.getChildren().add(hBox);

		guardians.forEach(g -> {
			VBox vBox = setGuardianInfo(g, guardianButtons);
			hBox.getChildren().add(vBox);
		});
	}

	private VBox setGuardianInfo(Employee guardian, List<Button> buttons){
		VBox vBox = new VBox(10);

		Label name = new Label(guardian.getName());
		Label phone = new Label(guardian.getPhone());
		Button button = new Button(guardian.getEmail());

		button.setOnAction(e -> {
			getTabPane().getSelectionModel().select(2);
			((EmailTab)getTabPane().getSelectionModel().getSelectedItem()).setEmailReceiver(button.getText());
		});

		buttons.add(button);

		vBox.getChildren().addAll(name, phone, button);
		return vBox;
	}

	private void setStudentProfile() {
		studentProfile.getStyleClass().add("student-profile");
	}

	private void setLabels(){
		name.setFont(new Font("Arial", 30));
		ssn.setFont(new Font("Arial", 16));
	}
}
