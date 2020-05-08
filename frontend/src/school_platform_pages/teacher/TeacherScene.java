package school_platform_pages.teacher;

import http_request.HttpRequest;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import models.Employee;
import models.Student;
import school_platform_pages.DefaultMenuBar;
import utilities.JsonConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeacherScene extends Scene {

	private final Stage stage;
	private final TabPane tabPane = new TabPane();
	private final DefaultMenuBar defaultMenuBar = new DefaultMenuBar();
	private List<Student> students = new ArrayList<>(30);

	private final AttendanceTab attendanceTab = new AttendanceTab(students);
	private final StudentTab studentTab = new StudentTab(students); // TODO
	private final EmailTab emailTab = new EmailTab(); // TODO

	private final Employee teacher;

	public TeacherScene(Parent root, Stage stage, Employee teacher) {
		super(root);
		this.stage = stage;
		this.teacher = teacher;
		getStylesheets().add("stylesheets/teacher-scene.css");
	}


	public void initialize(){
		setDefaultMenuBar();
		fetchStudents();
		attendanceTab.initialize();
		studentTab.initialize();
		emailTab.initialize();
		setTabPane();
	}

	private void setTabPane(){
		tabPane.getTabs().addAll(attendanceTab, studentTab, emailTab);
	}

	private void setDefaultMenuBar(){
		defaultMenuBar.setMenu(stage);
	}



	private void fetchStudents(){
		HttpRequest httpRequest = new HttpRequest("http://localhost:8080/student/by-teacher/" + teacher.getId());
		String studentsAsJson = httpRequest.getStudentsByTeacher();

		Student[] studentsAsArray = JsonConverter.convertStudents(studentsAsJson);
		students.addAll(Arrays.asList(studentsAsArray));
	}

	public TabPane getTabPane(){
		return tabPane;
	}

	public DefaultMenuBar getDefaultMenuBar() {
		return defaultMenuBar;
	}
}
