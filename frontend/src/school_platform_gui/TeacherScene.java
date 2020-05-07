package school_platform_gui;

import http_request.HttpRequest;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Employee;
import models.Student;
import utilities.JsonConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeacherScene extends Scene {

	private final Stage stage;

	private final DefaultMenuBar defaultMenuBar;
	private final GridPane gridPane = new GridPane();
	private final List<CheckBox> checkBoxes = new ArrayList<>();
	private List<Student> students = new ArrayList<>();

	private Employee teacher;

	public TeacherScene(Parent root, Stage stage, Employee teacher) {
		super(root);
		this.stage = stage;
		this.teacher = teacher;
		defaultMenuBar = new DefaultMenuBar();
		getStylesheets().add("stylesheets/teacher-scene.css");
	}


	public void setGridPane(){
		gridPane.setHgap(20);
		gridPane.setVgap(20);
		gridPane.setPadding(new Insets(40));
		gridPane.getStyleClass().add("grid-pane");
	}

	public void setDefaultMenuBar(){
		defaultMenuBar.setMenu(stage);
	}

	public void setCheckBoxes(){
		int checkBoxIndex = 0;
		for (int i = 0; i < students.size(); i++) {
			checkBoxes.add(new CheckBox(students.get(i).getName()));
			if(i % 2 == 0){
				gridPane.add(checkBoxes.get(i), 0, checkBoxIndex);
			}else {
				gridPane.add(checkBoxes.get(i), 1, checkBoxIndex++);
			}
		}
	}

	public void fetchStudents(){
		HttpRequest httpRequest = new HttpRequest("http://localhost:8080/student/by-teacher/" + teacher.getId());
		String studentsAsJson = httpRequest.getStudentsByTeacher();

		Student[] studentsAsArray = JsonConverter.convertStudents(studentsAsJson);
		students.addAll(Arrays.asList(studentsAsArray));
	}

	public GridPane getGridPane(){
		return gridPane;
	}

	public DefaultMenuBar getDefaultMenuBar() {
		return defaultMenuBar;
	}
}
