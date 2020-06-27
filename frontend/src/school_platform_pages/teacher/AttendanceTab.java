package school_platform_pages.teacher;

import http_request.HttpRequest;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import models.Attendance;
import models.Student;

import java.util.*;

public class AttendanceTab extends Tab {

	private final BorderPane borderPane = new BorderPane();
	private final GridPane gridPane = new GridPane();
	private final List<StudentCheckBox> checkBoxes = new ArrayList<>();
	private final Button save = new Button("Spara");

	private final List<Student> students;

	public AttendanceTab(List<Student> students){
		this.students = students;
		setText("Närvaro");
	}


	public void initialize(){
		setAttendanceTab();
		setCheckBoxes();
		setGridPane();
	}

	private void setAttendanceTab(){
		setClosable(false);
		borderPane.setCenter(gridPane);
		setContent(borderPane);
	}

	private void setGridPane(){
		gridPane.getStyleClass().add("grid-pane");
		save.setOnAction(e -> saveAttendance());
	}

	private void setCheckBoxes(){
		int checkBoxIndex = 0;

		for (int i = 0; i < students.size(); i++) {
			checkBoxes.add(new StudentCheckBox(students.get(i)));
			if(i % 2 == 0){
				gridPane.add(checkBoxes.get(i), 0, checkBoxIndex);
			}else {
				gridPane.add(checkBoxes.get(i), 1, checkBoxIndex++);
			}
		}
		gridPane.add(save, 1, checkBoxIndex + 1);
	}

	private void saveAttendance(){
		Set<Attendance> attendances = new HashSet<>();

		for (int i = 0; i < checkBoxes.size(); i++) {
			attendances.add(new Attendance(
					checkBoxes.get(i).getStudent().getId(),
					checkBoxes.get(i).isSelected()));
		}

		HttpRequest httpRequest = new HttpRequest("http://localhost:8080/attendance/add");
		String response = httpRequest.postAttendance(attendances);
		clearCheckBoxes();
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "Närvaro Registrerad!", ButtonType.CLOSE);
		alert.getDialogPane().getStylesheets().add("stylesheets/alert.css");
		alert.setTitle("Success");
		alert.show();
	}

	private void clearCheckBoxes(){
		checkBoxes.forEach(cb -> cb.setSelected(false));
	}
}
